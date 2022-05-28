package controller.commands.admin;

import constants.HospitalJspPaths;
import controller.commands.Command;
import model.entity.Patient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PatientsList implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Patient> patients;

        int currentPatientPage;

        if(request.getParameter("currentPatientsPage") != null){
            currentPatientPage = Integer.parseInt(request.getParameter("currentPatientsPage"));
        }
        else {
            currentPatientPage = 1;
        }

        patients = (List<Patient>) session.getAttribute("allPatients");

        int numberOfPatientPages;
        int numberOfPatients;

        numberOfPatients = patients.size();

        int startPatients = (currentPatientPage - 1) * 5;
        int endPatients = Math.min(startPatients + 5, numberOfPatients);
        patients = patients.subList(startPatients, endPatients);

        if (numberOfPatients % 5 == 0) {
            numberOfPatientPages = numberOfPatients / 5;
        } else {
            numberOfPatientPages = (numberOfPatients / 5) + 1;
        }
        request.setAttribute("activePatients", patients);
        request.setAttribute("numberOfPatientPages", numberOfPatientPages);
        request.setAttribute("currentPatientPage", currentPatientPage);
        request.getRequestDispatcher(HospitalJspPaths.ADMIN_PATIENTS_PAGE).forward(request, response);
    }
}
