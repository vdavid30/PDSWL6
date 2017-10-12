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
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2114928
 */
public class PacienteDAOMyBATIS implements PacienteDAO{
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
    
    public void actualizarPaciente(@Param("paci") Paciente p){
    
    }
}
