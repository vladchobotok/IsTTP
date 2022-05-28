package controller.commands.homepages;

import constants.HospitalJspPaths;
import controller.commands.Command;
import model.entity.*;
import model.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorHomeCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(String.valueOf(session.getAttribute("user_id")));

        DoctorService doctorService = new DoctorService();
        Doctor doctor = doctorService.findDoctorByUserId(id);

        AssignmentTypeService assignmentTypeService = new AssignmentTypeService();
        List<AssignmentsType> assignmentsTypes = assignmentTypeService.findAllType();

        List<Patient> patients = new ArrayList<>();

        DiagnosisService diagnosisService = new DiagnosisService();
        List<Diagnosis> diagnoses = diagnosisService.findAllType();

        List<Patient> allPatients = (List<Patient>)session.getAttribute("allPatients");
        for (Patient patient: allPatients) {
            int executorId = patient.getTreatment().getAssignment().getExecutor().getId();
            if(executorId == doctor.getId() && doctor.getId() != patient.getDoctor().getId() && patient.getUser().getRole().getId() != 5){
                patients.add(patient);
            }
            if(doctor.getId() == patient.getDoctor().getId() && patient.getUser().getRole().getId() != 5){
                patients.add(patient);
            }
        }
        session.setAttribute("currentDoctor", doctor);
        session.setAttribute("patients", patients);
        session.setAttribute("assignmentTypes", assignmentsTypes);
        session.setAttribute("diagnoses", diagnoses);
        request.getRequestDispatcher(HospitalJspPaths.DOCTOR_HOME).forward(request, response);
    }
}
