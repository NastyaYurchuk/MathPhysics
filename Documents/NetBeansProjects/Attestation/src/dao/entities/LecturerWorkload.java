
package dao.entities;

/**
 *
 * @author nastja
 */
public class LecturerWorkload {
   private int id;
   private String nameSubject;
   private String groupCode;
   private int idUser;

    public LecturerWorkload(int id, String nameSubject, String groupCode, int idUser) {
        this.id = id;
        this.nameSubject = nameSubject;
        this.groupCode = groupCode;
        this.idUser = idUser;
    }

    public LecturerWorkload() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

   
   
   
}
