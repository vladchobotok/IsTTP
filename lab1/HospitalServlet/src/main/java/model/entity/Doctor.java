package model.entity;

import model.dao.classes.DoctorsTypeDAOImpl;
import model.dao.classes.UserDAOImpl;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public class Doctor implements Serializable {
    private int id;
    private User userId;
    private DoctorsType doctorType;

    public Doctor(int id, int userId, int doctorType) {
        this.id = id;
        this.userId = detectUser(userId).get();
        this.doctorType = detectDoctorsType(doctorType);
    }

    public Doctor(int id, User user, DoctorsType doctorType) {
        this.id = id;
        this.userId = user;
        this.doctorType = doctorType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public DoctorsType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorsType doctorType) {
        this.doctorType = doctorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id &&
                Objects.equals(userId, doctor.userId) &&
                Objects.equals(doctorType, doctor.doctorType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, doctorType);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", doctorType='" + doctorType + '\'' +
                '}';
    }
    public Optional<User> detectUser(int id) {
        return new UserDAOImpl().findById(id);
    }

    public DoctorsType detectDoctorsType(int id) {
        return new DoctorsTypeDAOImpl().findById(id);
    }
}
