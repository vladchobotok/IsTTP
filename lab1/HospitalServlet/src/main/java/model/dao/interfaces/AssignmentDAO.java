package model.dao.interfaces;

import model.entity.Assignment;

public interface AssignmentDAO {

    /**
     * The method finds assignment by id and returns it
     *
     * @param id - id of assignment
     * @return - expected assignment
     */
    Assignment findById(int id);

    /**
     * The method updates executor of specific assignment
     *
     * @param executorId - id of executor that should be replaced by
     * @param id - id of assignment
     */
    void updateAssignmentExecutor(int executorId, int id);

    /**
     * The method updates prescriber of specific assignment
     *
     * @param prescriberId - id of prescriber that should be replaced by
     * @param id - id of assignment
     */
    void updateAssignmentPrescriber(int prescriberId, int id);

    /**
     * The method inserts new assignment into database
     *
     * @param executorId - executor id of assignment
     * @param prescriberId - prescriber id of assignment
     * @param description - description of assignment
     * @param assignmentTypeId - assignment type id of assignment
     * @return
     */
    int insertAssignment(int executorId, int prescriberId, String description, int assignmentTypeId);

    /**
     * The method deletes assignment from database
     *
     * @param assignmentId - id of assignment
     */
    void deleteAssignmentById(int assignmentId);
}
