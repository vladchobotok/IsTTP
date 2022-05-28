package model.service;

import model.dao.classes.AssignmentsTypeDAOImpl;
import model.entity.AssignmentsType;
import utils.factory.dao.DaoFactory;
import utils.factory.dao.DaoFactoryImpl;

import java.util.List;

public class AssignmentTypeService {
    private static final DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    private static final AssignmentsTypeDAOImpl assignmentsTypeDAO = daoFactory.createAssignmentTypeDAO();

    public AssignmentTypeService() { }

    public AssignmentsType findAssignmentTypeById(int id) {
        return assignmentsTypeDAO.findById(id);
    }

    public List<AssignmentsType> findAllType() {
        return assignmentsTypeDAO.findAllType();
    }
}
