### Escuela Colombiana de Ingeniería
### Procesos de desarrollo de Software - PDSW
#### Tecnologías de persistencia - Frameworks de Persistencia - Introducción a MyBatis


En este laboratorio, se trabajará en el desarrollo de una prueba de concepto 

## Parte I

1. Agregue como dependencias el driver de MySQL, el driver de H2, y la librería de MyBatis:

	```xml
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.36</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.5</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>log4j-over-slf4j</artifactId>
            <version>1.7.2</version>
        </dependency>
        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib</artifactId>
            <version>2.2.2</version>
        </dependency>                
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.184</version>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
            <type>zip</type>
        </dependency>
    ```

2. Ubique el archivo de configuración de MyBATIS (mybatis-config.xml). Éste está en la ruta donde normalmente se ubican los archivos de configuración de aplicaciones montadas en Maven (src/main/resources). Edítelo y agregue en éste, después de la sección &lt;settings&gt; los siguientes 'typeAliases':

	```xml
    <typeAliases>
        <typeAlias type='edu.eci.pdsw.samples.entities.Paciente' alias='Paciente'/>
        <typeAlias type='edu.eci.pdsw.samples.entities.Consulta' alias='Consulta'/>
        <typeAlias type='edu.eci.pdsw.samples.entities.Eps' alias='Eps'/>
    </typeAliases>
	```

3. Ahora, ubique y abra la interfaz del 'mapper' que se configurará para manipular la persistecia de los objetos de tipo Paciente (PacienteMapper). A cada uno de los parámetros de los tres métodos, agruéguele una anotación de tipo @Param para asociarle -al respectivo parámetro- el nombre con el cual se referenciará desde la definción del 'mapper'. Por ejemplo, para el primer método:

	```java
public Paciente loadPacienteById(@Param("idpaciente")int id,@Param("tipoidpaciente") String tipoid);
	```

4. Abra el archivo XML en el cual se definirán los parámetros para que MyBatis genere el 'mapper' de Paciente (PacienteMapper.xml). Lo primero que debe hacer, es agregar un elemento de tipo &lt;resultMap&gt;, en el cual se defina, para una entidad(clase) en particular, a qué columnas estarán asociadas cada una de sus propiedades (recuerde que propiedad != atributo). La siguiente es un ejemplo del uso de la sintaxis de &lt;resultMap&gt; para la clase Maestro, la cual tiene una relación 'uno a muchos' con la clase Detalle: 

	```xml
    <resultMap type='Maestro' id='MaestroResult'>
        <id property='propiedad1' column='COLUMNA1'/>
        <result property='propiedad2' column='COLUMNA2'/>
        <result property='propiedad3' column='COLUMNA3'/>        
        <collection property='propiedad4' ofType='Detalle'></collection>
    </resultMap>
    <resultMap type='Detalle' id='DetalleResult'>
        <id property='propiedadx' column='COLUMNAX'/>
        <result property='propiedady' column='COLUMNAY'/>
        <result property='propiedadz' column='COLUMNAZ'/>        
    </resultMap>
	```

	Como observa, Para cada propiedad de la clase se agregará un elemento de tipo &lt;result&gt;, el cual, en la propiedad 'property' indicará el nombre de la propiedad, y en la columna 'column' indicará el nombre de la columna de su tabla correspondiente (en la que se hará persistente). En caso de que la columna sea una llave primaria, en lugar de 'result' se usará un elemento de tipo 'id'. Finalmente, observe que si la clase tiene un atributo de tipo colección (List, Set, etc), se agregará un elemento de tipo &lt;collection&gt;, indicando (en la propiedad 'ofType') de qué tipo son los elementos de la colección. En cuanto al indentificador del 'resultMap', como convención se suele utilizar el nombre del tipo de dato concatenado con 'Result' como sufijo.
	
	Teniendo en cuenta lo anterior, haga dos 'resultMap': uno para la clase Paciente y otro para la clase Consulta. 

5. Una vez haya hecho lo anterior, es necesario que en el elemento &lt;collection&gt; del maestro se agregue una propiedad que indique cual es el 'resultMap' a través del cual se podrá 'mapear' los elementos contenidos en dicha colección. Para el ejemplo anterior, como la colección contiene elementos de tipo 'Detalle', se agregará el elemento __resultMap__ con el identificador del 'resultMap' de Detalle:

	```xml
	<collection property='propiedad3' ofType='Detalle' resultMap='DetalleResult'></collection>
	```

	Teniendo en cuenta lo anterior, haga los ajustes correspondientes en la configuración para el caso del modelo de Pacientes y Consultas.


6. Ahora, va a asociar sentencias SQL para cada uno de los métodos de la interfaz del 'mapper' (en este caso, la interfaz PacienteMapper). Si el método a implementar es una consulta (es decir, un método que retorna un resultado), se usa un elemento de tipo &lt;select&gt;. Como identificador se usará el nombre (sólo el nombre!) del método. Por ejemplo, si se tuviera la siguiente interfaz:


	```java
	public List<Maestro> consultarMaestrosEspeciales(@Param("salario") int id, @Param("categoria")int cat);
	```

	Su elemento &lt;select&gt; correspondiente sería:

	```xml
    <select id='consultarMaestrosEspeciales' parameterType='map' resultMap='MaestroResult'>
        select ma.propiedad1, ma.propiedad2, ma.propiedad3, det.propiedadx, det.propiedady, det.propiedadz from MAESTROS as ma left join DETALLES as det on ...
        where ma.propiedad2> #{salario} and ma.propiedad3< #{categoria} 
    </select>
	```
	De lo anterior, note que:
	* En la propiedad 'parameterType' se usa 'map' dado que el método del mapper recibe más de un parámetro.
	* En el query se hace uso de la conveción #{} para hacer referencia a los parámetros que se enviarán a través del método definido en la interfaz del mapper.
	* La propiedad 'resultMap' tiene asociado el identificador del 'resultMap' que se haya definido para el tipo de dato que retorne la consulta.

	Teniendo esto en cuenta, haga la implementación del elemento 'select' para el método 'loadPacienteById' de PacienteMapper, con la consulta correspondiente (la consulta que hace 'join' entre pacientes y consultas, usada en el ejercicio anterior).
	
7. Si intenta utilizar el 'mapper' tal como está hasta ahora, se puede presentar un problema: qué pasa si las tablas a las que se les hace JOIN tienen nombres de columnas iguales?... Con esto MyBatis no tendría manera de saber a qué atributos corresponde cada una de las columnas. Para resolver esto, si usted hace un query que haga JOIN entre dos o más tablas, siempre ponga un 'alias' con un prefijo el query. Por ejemplo, si se tiene

	```sql	
	select ma.propiedad1, det.propiedad1 ....
	```	

	Se debería cambiar a:

	```sql		
	select ma.propiedad1, det.propiedad1 as detalle_propiedad1
	```

	Y posteriormente, en la 'colección' o en la 'asociación' correspondiente en el 'resultMap', indicar que las propiedades asociadas a ésta serán aquellas que tengan un determinado prefijo:


	```xml
    <resultMap type='Maestro' id='MaestroResult'>
        <id property='propiedad1' column='COLUMNA1'/>
        <result property='propiedad2' column='COLUMNA2'/>
        <result property='propiedad3' column='COLUMNA3'/>        
        <collection property='propiedad4' ofType='Detalle' columnPrefix='detalle_'></collection>
    </resultMap>
	```


8. Use el programa de prueba suministrado (MyBatisExample) para probar cómo a través del 'mapper' generado por MyBatis, se puede consultar un paciente. 

	```java	
    ...
    SqlSessionFactory sessionfact = getSqlSessionFactory();
    SqlSession sqlss = sessionfact.openSession();
    PacienteMapper pedmp=sqlss.getMapper(PacienteMapper.class);
    System.out.println(pedmp.loadPacienteById(1026585448, "cc"));
    ...
	```

## Parte II

Ahora, va a asociar consultas SQL a las dos operaciones restantes de la interfaz de PacienteMapper: insertarPaciente e insertarConsulta. El esquema es simiar al anterior, salvo que en lugar de usar un elemento de tipo &lt;select&gt; se usará uno de tipo &lt;insert&gt;. 

1. Implemente el &lt;insert&gt; para 'insertPaciente', haciendo referencia a los parámetros recibidos por el método de la interfaz (usando #{}):

	```xml
    <insert id="insertPaciente">
		COMPLETAR
    </insert>
	```

2. Implemente el &lt;insert&gt; para 'insertConsulta', haciendo referencia a los parámetros recibidos por el método de la interfaz (usando #{}). En este caso, dado CONSULTAS tiene una llave autogenerada, se debe agregar la propiedad 'useGeneratedKeys' en "true", y la propiedad "keyProperty" asociándole el atributo del objeto en el cual se almacenará la llave primaria generada. Por ejemplo, si el parámetro Consulta se declaró como "con":   

	```java
public void insertConsulta(@Param("con") Consulta con,@Param("pacid")int id,@Param("pactipoid")String tipoid);
	```
	La propiedad 'keyProperty' tendrá como valor "con.id":
	
	```xml
    <insert id="insertConsulta" useGeneratedKeys="true" keyProperty="con.id">
    	COMPLETAR
    </insert>
	```
3. Usando las dos operaciones del mapper (que ya quedaron configuradas), implemente el método 'registrarNuevoPaciente', el cual, como lo indica su especificación, debe registrar un nuevo paciente y sus consultas relacionadas.


##Parte III

1. Teniendo en cuenta el ejercicio anterior, revise cómo se harían las pruebas con un esquema de persistencia basado en MyBATIS. Para esto, revise cómo se configura MyBatis para usar la base de datos 'temporal' H2 (mybatis-config-h2.xml).
2. Revise cómo a través de MyBatis se realizan las siguientes operaciones:

* Inicio de sesión.
* Commit y rollback.
* Cierre de sesión.

	Con lo anterior, integre esta solución (y complemente lo que haga falta) en el esquema de patrón DAO realizado anteriormente (es decir, implemente la fábrica concreta y sus respectivos DAOs concretos).
