package model.service;

import model.dao.classes.TreatmentDAOImpl;
import model.entity.Treatment;
import utils.factory.dao.DaoFactory;
import utils.factory.dao.DaoFactoryImpl;

public class TreatmentService {
    private static final DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    private static final TreatmentDAOImpl treatmentDAO = daoFactory.createTreatmentDAO();

    public TreatmentService() { }

    public Treatment findTreatmentById(int id) {
        return treatmentDAO.findById(id);
    }

    public int insertTreatment(int assignmentId, int diagnosisId){
        return treatmentDAO.insertTreatment(assignmentId, diagnosisId);
    }

    public void setDiagnosis(int diagnosisId, int treatmentId){
        treatmentDAO.setDiagnosis(diagnosisId, treatmentId);
    }

    public void deleteTreatmentById(int treatmentId){
        treatmentDAO.deleteTreatmentById(treatmentId);
    }
}
