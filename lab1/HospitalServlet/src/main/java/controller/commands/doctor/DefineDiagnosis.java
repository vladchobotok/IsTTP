package controller.commands.doctor;

import constants.HospitalJspPaths;
import controller.commands.Command;
import model.service.PatientService;
import model.service.TreatmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static constants.HospitalUriPaths.DOCTOR_HOME;

public class DefineDiagnosis implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int patientId = Integer.parseInt(request.getParameter("patientId1"));
        int diagnosis;
        if(request.getParameter("diagnosis") != null){
            diagnosis = Integer.parseInt(request.getParameter("diagnosis"));
        }
        else {
            request.setAttribute("bad_getaway", "emptyDiagnosis");
            request.getRequestDispatcher(HospitalJspPaths.DOCTOR_HOME).forward(request, response);
            return;
        }

        PatientService patientService = new PatientService();
        int treatmentId = patientService.findPatientById(patientId).getTreatment().getId();

        TreatmentService treatmentService = new TreatmentService();
        treatmentService.setDiagnosis(diagnosis, treatmentId);
        session.setAttribute("allPatients", patientService.findAll());

        response.sendRedirect(DOCTOR_HOME);
    }
}
