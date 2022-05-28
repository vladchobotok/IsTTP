package model.dao.interfaces;

import model.entity.AssignmentsType;

import java.util.List;

public interface AssignmentsTypeDAO {

    /**
     * The method finds assignment type by id and returns it
     *
     * @param id - id of assignment type
     * @return - expected assignment type
     */
    AssignmentsType findById(int id);

    /**
     * The method returns all assignment types
     *
     * @return - list of assignment types
     */
    List<AssignmentsType> findAllType();
}
