package model.dao.interfaces;

import model.entity.Diagnosis;

import java.util.List;

public interface DiagnosisDAO {

    /**
     * The method finds diagnosis by id and returns it
     *
     * @param id - id of diagnosis
     * @return - expected diagnosis
     */
    Diagnosis findById(int id);

    /**
     * The method returns all diagnoses
     *
     * @return - list of diagnoses
     */
    List<Diagnosis> findAllType();
}
