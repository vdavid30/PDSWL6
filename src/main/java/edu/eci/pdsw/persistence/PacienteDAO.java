/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence;

import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author 2114928
 */
public interface PacienteDAO {
    public Paciente loadPacienteByID(@Param("idp") int id,@Param("tipoidp") String tipoid);
    
    public List<Paciente> loadPacientes();
    
    public void insertarPaciente(@Param("paci") Paciente p);
    
    public void insertConsulta(@Param("con") Consulta con,@Param("idp") int idPaciente,@Param("tipoidp") String tipoid,@Param("costoc") int costoconsulta);
    
    public void actualizarPaciente(@Param("paci") Paciente p);
    
    public void actualizar(Paciente p) throws PersistenceException;

	public static void agregarConsultaPaciente(String idPaciente, String tipoid, Consulta consulta,long costo) throws ExcepcionServiciosPacientes {		
	}
	
	public void agregarConsultaPaciente(String idPaciente, String tipoid, Consulta consulta) throws ExcepcionServiciosPacientes;

    
}
