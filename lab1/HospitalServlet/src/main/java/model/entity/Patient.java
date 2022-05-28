package model.entity;

import model.dao.classes.DoctorDAOImpl;
import model.dao.classes.TreatmentDAOImpl;
import model.dao.classes.UserDAOImpl;

import java.io.Serializable;
import java.util.Objects;

public class Patient implements Serializable {
    private int id;
    private User user;

    private Doctor doctor;

    private Treatment treatment;

    public Patient(int id) {
        this.id = id;
    }

    public Patient(int id, Doctor doctor, Treatment treatment) {
        this.id = id;
        this.doctor = doctor;
        this.treatment = treatment;
    }

    public Patient(int id, int user, int doctor, int treatment) {
        this.id = id;
        this.user = detectUser(user);
        this.doctor = detectDoctor(doctor);
        this.treatment = detectTreatment(treatment);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id &&
                Objects.equals(doctor, patient.doctor) &&
                Objects.equals(treatment, patient.treatment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, treatment);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", treatment=" + treatment +
                '}';
    }
    public Doctor detectDoctor(int id) {
        return new DoctorDAOImpl().findDoctorById(id);
    }
    public Treatment detectTreatment(int id) {
        return new TreatmentDAOImpl().findById(id);
    }
    public User detectUser(int id) { return new UserDAOImpl().findById(id).get(); }

}
