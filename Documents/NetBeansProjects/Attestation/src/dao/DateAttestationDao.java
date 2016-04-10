/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.entities.DateAttestation;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author nastja
 */
public interface DateAttestationDao {
    
    DateAttestation findCurrent(Date date);
    
    Date lastAttestation(DateAttestation da);

    public List<DateAttestation> findAll();

    public void create(DateAttestation dateAttestation);

    public void update(DateAttestation oldDate, DateAttestation newDate);

    public void delete(DateAttestation get);
}
