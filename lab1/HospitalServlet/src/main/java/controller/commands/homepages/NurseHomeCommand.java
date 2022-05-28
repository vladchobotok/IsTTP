package controller.commands.homepages;

import constants.HospitalJspPaths;
import controller.commands.Command;
import model.entity.Assignment;
import model.entity.Doctor;
import model.entity.Patient;
import model.service.DoctorService;
import model.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NurseHomeCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(String.valueOf(session.getAttribute("user_id")));

        DoctorService doctorService = new DoctorService();
        Doctor nurse = doctorService.findDoctorByUserId(id);

        List<Patient> allPatients = (List<Patient>)session.getAttribute("allPatients");
        List<Patient> patients = new ArrayList<>();
        for (Patient patient: allPatients) {
            String doctorType = patient.getTreatment().getAssignment().getExecutor().getDoctorType().getType();
            if(doctorType.equals("Nurse") && patient.getUser().getRole().getId() != 5){
                patients.add(patient);
            }
        }

        session.setAttribute("currentNurse", nurse);
        session.setAttribute("patients", patients);
        request.getRequestDispatcher(HospitalJspPaths.NURSE_HOME).forward(request, response);
    }
}
