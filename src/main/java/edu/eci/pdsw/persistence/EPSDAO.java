/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence;

import edu.eci.pdsw.samples.entities.Eps;
import java.util.List;

/**
 *
 * @author 2114928
 */
public interface EPSDAO {
    public List<Eps> loadAllEPS();
}
