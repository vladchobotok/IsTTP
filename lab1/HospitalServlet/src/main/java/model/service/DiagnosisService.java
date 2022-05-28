package model.service;

import model.dao.classes.DiagnosisDAOImpl;
import model.entity.Diagnosis;
import utils.factory.dao.DaoFactory;
import utils.factory.dao.DaoFactoryImpl;

import java.util.List;

public class DiagnosisService {
    private static final DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    private static final DiagnosisDAOImpl diagnosisDAO = daoFactory.createDiagnosisDAO();

    public DiagnosisService() { }

    public Diagnosis findDiagnosisById(int id) {
        return diagnosisDAO.findById(id);
    }

    public List<Diagnosis> findAllType() {
        return diagnosisDAO.findAllType();
    }
}
