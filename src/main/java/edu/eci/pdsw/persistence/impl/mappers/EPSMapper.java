/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.impl.mappers;



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
public interface EPSMapper {
    public List<Eps> loadAllEPS();
}
