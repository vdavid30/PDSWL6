/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.simpleview;

import edu.eci.pdsw.persistence.impl.mappers.PacienteMapper;
import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Eps;
import edu.eci.pdsw.samples.entities.Paciente;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBATISExample {
    public static Paciente pacie;
    public static Consulta consul;

/**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getLocalizedMessage(),e);
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession();
        PacienteMapper pmapper=sqlss.getMapper(PacienteMapper.class);
        
        
        
        
        //Paciente paci=pmapper.loadPacienteByID(1026585441, "CC");
        //System.out.println(paci.getNombre());
        Eps eps= new Eps("Compensar", "8456981");
        pacie=new Paciente(21114928, "CC", "David Vaca ", java.sql.Date.valueOf("2000-01-01"), eps);
        consul=new Consulta(java.sql.Date.valueOf("2017-01-01"), "C mamo X2", 2500);
        //registrarNuevoPaciente(pmapper,pacie);
        
        List<Paciente> pacientes=pmapper.loadPacientes();
        
        for(Paciente pa:pacientes){
             System.out.println(pa.getNombre());
         }
        
        //registrarConsulta(pmapper,consul);
        
        sqlss.commit();
    }

    /**
     * Registra un nuevo paciente y sus respectivas consultas (si existiesen).
     * @param pmap mapper a traves del cual se hará la operacion
     * @param p paciente a ser registrado
     */
    public static void registrarNuevoPaciente(PacienteMapper pmap, Paciente p){
        pmap.insertarPaciente(p);
        pmap.insertConsulta(consul, p.getId(), p.getTipoId(), 2500);
    }
    
    public static void registrarConsulta(PacienteMapper pmap, Consulta c){
        pmap.insertConsulta(c, pacie.getId(), pacie.getTipoId(), 2500);
    }
       
    /**
     * @obj Actualizar los datos básicos del paciente, con sus * respectivas consultas.
     * @pre El paciente p ya existe
     * @param pmap mapper a traves del cual se hará la operacion
     * @param p paciente a ser registrado
     */
    public void actualizarPaciente(PacienteMapper pmap, Paciente p){
        pmap.
    }
    

    
}
