package edu.eci.pdsw.persistence.impl.mappers;



import edu.eci.pdsw.samples.entities.Paciente;
import java.util.Date;

import java.util.List;

/**
 *
 * @author 2106913
 */
public interface PacienteMapper {
        
    public Paciente loadPacienteById(int id,String tipoid);
    
    public List<Paciente> loadPacientes();

}
