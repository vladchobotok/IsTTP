package model.dao.interfaces;

import model.entity.Treatment;

public interface TreatmentDAO {

    /**
     * The method finds treatment by id and returns it
     *
     * @param id - id of treatment
     * @return - expected treatment
     */
    Treatment findById(int id);

    /**
     * The method inserts new treatment into database
     *
     * @param assignmentId - id of assignment
     * @param diagnosisId - id of diagnosis
     * @return - id of treatment
     */
    int insertTreatment(int assignmentId, int diagnosisId);

    /**
     * The method writes diagnosis into treatment
     *
     * @param diagnosisId - id of diagnosis
     * @param treatmentId - id of treatment
     */
    void setDiagnosis(int diagnosisId, int treatmentId);

    /**
     * The method deletes from database
     *
     * @param treatmentId - id of treatment
     */
    void deleteTreatmentById(int treatmentId);
}
