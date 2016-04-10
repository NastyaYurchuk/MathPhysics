/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entities;

/**
 *
 * @author nastja
 */
public class StudentAttestation {
    int idAttest;
    int idStud;
    String nameSubject;
    String attestation;

    public int getIdAttest() {
        return idAttest;
    }

    public void setIdAttest(int idAttest) {
        this.idAttest = idAttest;
    }

    public int getIdStud() {
        return idStud;
    }

    public void setIdStud(int idStud) {
        this.idStud = idStud;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getAttestation() {
        return attestation;
    }

    public void setAttestation(String attestation) {
        this.attestation = attestation;
    }

    public StudentAttestation(int idAttest, int idStud, String nameSubject, String attestation) {
        this.idAttest = idAttest;
        this.idStud = idStud;
        this.nameSubject = nameSubject;
        this.attestation = attestation;
    }

    @Override
    public String toString() {
        return attestation;
    }

    public StudentAttestation() {
    }
    
    
}
