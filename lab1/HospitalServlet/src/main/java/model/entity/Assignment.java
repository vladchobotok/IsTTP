package model.entity;

import model.dao.classes.AssignmentsTypeDAOImpl;
import model.dao.classes.DoctorDAOImpl;

import java.io.Serializable;
import java.util.Objects;

public class Assignment implements Serializable {
    private int id;
    private Doctor executor;
    private Doctor prescriber;
    private String description;
    private AssignmentsType type;

    public Assignment(int id) {
        this.id = id;
    }

    public Assignment(int id, int executor, int prescriber, String description, int type) {
        this.id = id;
        this.executor = detectDoctor(executor);
        this.prescriber = detectDoctor(prescriber);
        this.description = description;
        this.type = detectAssignmentType(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getExecutor() {
        return executor;
    }

    public void setExecutor(Doctor executor) {
        this.executor = executor;
    }

    public Doctor getPrescriber() {
        return prescriber;
    }

    public void setPrescriber(Doctor prescriber) {
        this.prescriber = prescriber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AssignmentsType getType() {
        return type;
    }

    public void setType(AssignmentsType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return id == that.id &&
                Objects.equals(executor, that.executor) &&
                Objects.equals(prescriber, that.prescriber) &&
                Objects.equals(description, that.description) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, executor, prescriber, description, type);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }

    public Doctor detectDoctor(int id) {
        return new DoctorDAOImpl().findDoctorById(id);
    }

    public AssignmentsType detectAssignmentType(int id) {
        return new AssignmentsTypeDAOImpl().findById(id);
    }
}
