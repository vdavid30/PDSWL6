/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.persistence.EPSDAO;
import edu.eci.pdsw.persistence.mybatis.mappers.EpsMapper;
import edu.eci.pdsw.samples.entities.Eps;
import java.util.List;

/**
 *
 * @author 2114928
 */
public class EPSDAOMyBATIS implements EPSDAO {
    @Inject 
    public EpsMapper epsMapper;
    public List<Eps> loadAllEPS(){
        return epsMapper.loadAllEPS();
    }
}
