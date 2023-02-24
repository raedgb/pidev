/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Fayechi
* @param <U>
 */
  public interface InterfaceService <U> {
    public void ajouter(U u) throws SQLException ;
    public List<U> getAll() throws SQLException ;
    public List<U> findById(int id);
    
}
