package model.dao.interfaces;

import model.entity.DoctorsType;

import java.util.List;

public interface DoctorsTypeDAO {

    /**
     * The method finds doctor type by id and returns it
     *
     * @param id - id of doctor type
     * @return - expected user type
     */
    DoctorsType findById(int id);

    /**
     * The method returns all types of doctors
     *
     * @return - list of doctor types
     */
    List<DoctorsType> findAllType();
}
