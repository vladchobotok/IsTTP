package controller.commands.homepages;

import constants.HospitalJspPaths;
import controller.commands.Command;
import model.entity.Patient;
import model.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PatientHomeCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(String.valueOf(session.getAttribute("user_id")));

        PatientService patientService = new PatientService();
        Patient patient = patientService.findPatientByUserId(id);

        session.setAttribute("currentPatient", patient);
        request.getRequestDispatcher(HospitalJspPaths.PATIENT_HOME).forward(request, response);
    }
}
