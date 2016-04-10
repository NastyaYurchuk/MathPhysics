
package dao.entities;

import java.sql.Date;

/**
 *
 * @author nastja
 */
public class DateAttestation {
    
    Date beginDate;
    Date endDate;
    int id;

    public DateAttestation() {
        
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateAttestation(int id, Date beginDate, Date endDate ) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.id = id;
    }

    public boolean isEmpty() {
        return this.beginDate == null && this.endDate == null;
    }
    
    
}
