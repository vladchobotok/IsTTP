package model.entity;

import model.dao.classes.AssignmentDAOImpl;
import model.dao.classes.DiagnosisDAOImpl;

import java.io.Serializable;
import java.util.Objects;

public class Treatment implements Serializable {
    private int id;
    private Assignment assignment;
    private Diagnosis diagnosis;

    public Treatment(int id, int assignment, int diagnosis) {
        this.id = id;
        this.assignment = detectAssignment(assignment);
        this.diagnosis = detectDiagnosis(diagnosis);
    }
    public Treatment(int id, Assignment assignment, Diagnosis diagnosis) {
        this.id = id;
        this.assignment = assignment;
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treatment treatment = (Treatment) o;
        return id == treatment.id &&
                Objects.equals(assignment, treatment.assignment) &&
                Objects.equals(diagnosis, treatment.diagnosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assignment, diagnosis);
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "id=" + id +
                ", assignment=" + assignment +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }

    public Assignment detectAssignment(int id) {
        return new AssignmentDAOImpl().findById(id);
    }

    public Diagnosis detectDiagnosis(int id) {
        return new DiagnosisDAOImpl().findById(id);
    }
}
