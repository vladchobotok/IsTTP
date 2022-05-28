package model.service;

import model.dao.classes.AssignmentDAOImpl;
import model.entity.Assignment;
import utils.factory.dao.DaoFactory;
import utils.factory.dao.DaoFactoryImpl;

public class AssignmentService {
    private static final DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    private static final AssignmentDAOImpl assignmentDAO = daoFactory.createAssignmentDAO();

    public AssignmentService(){}

    public Assignment findAssignmentById(int id){
        return assignmentDAO.findById(id);
    }

    public void updateAssignmentExecutor(int executorId, int id) {
        assignmentDAO.updateAssignmentExecutor(executorId, id);
    }

    public void updateAssignmentPrescriber(int prescriberId, int id) {
        assignmentDAO.updateAssignmentPrescriber(prescriberId, id);
    }

    public int insertAssignment(int executorId, int prescriberId, String description, int assignmentTypeId) {
        return assignmentDAO.insertAssignment(executorId, prescriberId, description, assignmentTypeId);
    }

    public void deleteAssignmentById(int assignmentId){
        assignmentDAO.deleteAssignmentById(assignmentId);
    }
}
