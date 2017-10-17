/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.persistence.PacienteDAO;
import edu.eci.pdsw.persistence.mybatis.mappers.PacienteMapper;
import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Eps;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.guice.transactional.Transactional;

/**
 *
 * @author 2114928
 */
public class PacienteDAOMyBATIS implements PacienteDAO {
    public Eps eps;
    public Date fecha;
    
    @Inject
    private PacienteMapper pacienteMapper;
    
    
    public Paciente loadPacienteByID(int id,String tipoid){
        return pacienteMapper.loadPacienteByID(id, tipoid);
    }
    
    public List<Paciente> loadPacientes(){
        return pacienteMapper.loadPacientes();
    }
    
    public void insertarPaciente(@Param("paci") Paciente p){
    
    }
    
    public void insertConsulta(@Param("con") Consulta con,@Param("idp") int idPaciente,@Param("tipoidp") String tipoid,@Param("costoc") int costoconsulta){
    
    }
    @Transactional
    @Override
    public void agregarConsultaPaciente(String idPaciente, String tipoid, Consulta consulta) throws ExcepcionServiciosPacientes {
         try {
            PacienteDAO.agregarConsultaPaciente(idPaciente,tipoid,consulta,consulta.getCosto());
        } catch (PersistenceException ex) {
            Logger.getLogger(PacienteDAOMyBATIS.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public void actualizarPaciente(@Param("paci") Paciente p){
    	this.actualizar(p);
    }
    @Override
    public void actualizar(Paciente p) throws PersistenceException {
        try{
            pacienteMapper.actualizarPaciente(p);
        }   
        catch(Exception e){
            throw new PersistenceException("Error al actualizar el paciente "+p.getId(),e);
        }
        
    }
}
