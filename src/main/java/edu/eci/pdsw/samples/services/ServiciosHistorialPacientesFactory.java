/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;

import com.google.inject.AbstractModule;
import static com.google.inject.Guice.createInjector;
import com.google.inject.Injector;
import edu.eci.pdsw.persistence.EPSDAO;
import edu.eci.pdsw.persistence.PacienteDAO;
import edu.eci.pdsw.persistence.mybatis.EPSDAOMyBATIS;
import edu.eci.pdsw.persistence.mybatis.PacienteDAOMyBATIS;
import edu.eci.pdsw.samples.simpleview.ServiciosPacientesImpl;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;


/**
 *
 * @author hcadavid
 */
public class ServiciosHistorialPacientesFactory {

    private static ServiciosHistorialPacientesFactory instance = new ServiciosHistorialPacientesFactory();

    private static Injector injector;
    private static Injector testInjector;

    public ServiciosHistorialPacientesFactory() {
        
        injector = createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.MySQL);              
                setClassPathResource("mybatis-config.xml");
                bind(ServiciosPacientes.class).to(ServiciosPacientesImpl.class);
                bind(PacienteDAO.class).to(PacienteDAOMyBATIS.class);
                bind(EPSDAO.class).to(EPSDAOMyBATIS.class);
            }

        }
        );
        testInjector = createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setClassPathResource("mybatis-config-h2.xml");
                bind(ServiciosPacientes.class).to(ServiciosPacientesImpl.class);
                bind(EPSDAO.class).to(EPSDAOMyBATIS.class);                
                bind(PacienteDAO.class).to(PacienteDAOMyBATIS.class);
            }
        }
        );
    }

    public ServiciosPacientes getServiciosPaciente() {
        return injector.getInstance(ServiciosPacientes.class);
    }

    public static ServiciosHistorialPacientesFactory getInstance() {
        return instance;
    }

}
