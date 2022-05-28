package model.service;

import model.dao.classes.DoctorDAOImpl;
import model.entity.Doctor;
import model.entity.User;
import utils.factory.dao.DaoFactory;
import utils.factory.dao.DaoFactoryImpl;

import java.util.List;

public class DoctorService {
    private static final DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    private static final DoctorDAOImpl doctorDAO = daoFactory.createDoctorDAO();

    public DoctorService(){}

    public boolean addDoctor(int userId, int doctorType) {
        return doctorDAO.insertDoctor(userId, doctorType);
    }

    public Doctor findDoctorByUserId(int id) {
        return doctorDAO.findDoctorByUserId(id);
    }

    public Doctor findDoctorById(int id) {
        return doctorDAO.findDoctorById(id);
    }

    public List<Doctor> findAllDoctors() {
        return doctorDAO.findAllDoctors();
    }

    public List<Doctor> findAllNurses() {
        return doctorDAO.findAllNurses();
    }
}
