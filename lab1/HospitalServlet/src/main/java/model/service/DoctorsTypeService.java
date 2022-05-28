package model.service;

import model.dao.classes.DoctorsTypeDAOImpl;
import model.entity.DoctorsType;
import utils.factory.dao.DaoFactory;
import utils.factory.dao.DaoFactoryImpl;

import java.util.List;

public class DoctorsTypeService {
    private static final DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    private static final DoctorsTypeDAOImpl doctorsTypeDAO = daoFactory.createDoctorsTypeDAO();

    public DoctorsTypeService() { }

    public DoctorsType findDoctorsTypeById(int id) {
        return doctorsTypeDAO.findById(id);
    }

    public List<DoctorsType> findAllType() {
        return doctorsTypeDAO.findAllType();
    }
}
