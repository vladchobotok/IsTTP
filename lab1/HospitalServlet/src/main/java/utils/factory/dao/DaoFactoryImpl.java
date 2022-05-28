package utils.factory.dao;

import model.dao.classes.*;

public class DaoFactoryImpl implements DaoFactory{

    private static DaoFactoryImpl instance;

    public static DaoFactoryImpl getInstance() {
        if (instance == null) {
            synchronized (DaoFactoryImpl.class) {
                if (instance == null)
                    instance = new DaoFactoryImpl();
            }
        }
        return instance;
    }

    private DaoFactoryImpl() {
    }

    @Override
    public AssignmentDAOImpl createAssignmentDAO() {
        return new AssignmentDAOImpl();
    }

    @Override
    public AssignmentsTypeDAOImpl createAssignmentTypeDAO() {
        return new AssignmentsTypeDAOImpl();
    }

    @Override
    public DiagnosisDAOImpl createDiagnosisDAO() {
        return new DiagnosisDAOImpl();
    }

    @Override
    public DoctorDAOImpl createDoctorDAO() {
        return new DoctorDAOImpl();
    }

    @Override
    public DoctorsTypeDAOImpl createDoctorsTypeDAO() {
        return new DoctorsTypeDAOImpl();
    }

    @Override
    public PatientDAOImpl createPatientDAO() {
        return new PatientDAOImpl();
    }

    @Override
    public TreatmentDAOImpl createTreatmentDAO() {
        return new TreatmentDAOImpl();
    }

    @Override
    public RoleDAOImpl createRoleDAO() {
        return new RoleDAOImpl();
    }

    @Override
    public UserDAOImpl createUserDAO() {
        return new UserDAOImpl();
    }
}
