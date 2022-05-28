package model.dao.interfaces;

import model.entity.Doctor;
import model.entity.User;

import java.util.List;

public interface DoctorDAO {

    /**
     * The method returns all doctors
     *
     * @return - list of doctors
     */
    List<Doctor> findAllDoctors();

    /**
     * The method returns all nurses
     *
     * @return - list of nurses
     */
    List<Doctor> findAllNurses();

    /**
     * The method inserts new doctor into database
     *
     * @param userId - user id of doctor
     * @param doctorType - type of doctor
     * @return - is doctor successfully inserted
     */
    boolean insertDoctor(int userId, int doctorType);

    /**
     * The method finds doctor by user id and returns it
     *
     * @param id - user id of doctor
     * @return - expected doctor
     */
    Doctor findDoctorByUserId(int id);

    /**
     * The method finds doctor by id and returns it
     *
     * @param id - id of doctor
     * @return - expected doctor
     */
    Doctor findDoctorById(int id);
}
