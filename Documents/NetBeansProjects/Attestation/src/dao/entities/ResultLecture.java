
package dao.entities;

import java.sql.Date;



/**
 *
 * @author nastja
 */
public class ResultLecture {
    private int id;
    private int idWorkload;
    private int idStudent;
    private int maxRate;
    private int studentRate;
    private Date dateLecture;

    public ResultLecture(int studentRate, int idStudent, int idWorkload, int id, Date dateLecture , int maxRate  ) {
        this.id = id;
        this.idWorkload = idWorkload;
        this.idStudent = idStudent;
        this.maxRate = maxRate;
        this.studentRate = studentRate;
        this.dateLecture = dateLecture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdWorkload() {
        return idWorkload;
    }

    public void setIdWorkload(int idWorkload) {
        this.idWorkload = idWorkload;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(int maxRate) {
        this.maxRate = maxRate;
    }

    public int getStudentRate() {
        return studentRate;
    }

    public void setStudentRate(int studentRate) {
        this.studentRate = studentRate;
    }

    public Date getDateLecture() {
        return dateLecture;
    }

    public void setDateLecture(Date dateLecture) {
        this.dateLecture = dateLecture;
    }

 
    
}
