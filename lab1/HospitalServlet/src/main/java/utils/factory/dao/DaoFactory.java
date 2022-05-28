package utils.factory.dao;

import model.dao.classes.*;

public interface DaoFactory {

    /**
     * The method creates assignmentDAOImpl object
     *
     * @return - created assignmentDAOImpl
     */
    AssignmentDAOImpl createAssignmentDAO();

    /**
     * The method creates assignmentTypeDAOImpl object
     *
     * @return - created assignmentTypeDAOImpl
     */
    AssignmentsTypeDAOImpl createAssignmentTypeDAO();

    /**
     * The method creates diagnosisDAOImpl object
     *
     * @return - created diagnosisDAOImpl
     */
    DiagnosisDAOImpl createDiagnosisDAO();

    /**
     * The method creates doctorDAOImpl object
     *
     * @return - created doctorDAOImpl
     */
    DoctorDAOImpl createDoctorDAO();

    /**
     * The method creates doctorsTypeDAOImpl object
     *
     * @return - created doctorsTypeDAOImpl
     */
    DoctorsTypeDAOImpl createDoctorsTypeDAO();

    /**
     * The method creates patientDAOImpl object
     *
     * @return - created patientDAOImpl
     */
    PatientDAOImpl createPatientDAO();

    /**
     * The method creates treatmentDAOImpl object
     *
     * @return - created treatmentDAOImpl
     */
    TreatmentDAOImpl createTreatmentDAO();

    /**
     * The method creates roleDAOImpl object
     *
     * @return - created roleDAOImpl
     */
    RoleDAOImpl createRoleDAO();

    /**
     * The method creates userDAOImpl object
     *
     * @return - created userDAOImpl
     */
    UserDAOImpl createUserDAO();
}
