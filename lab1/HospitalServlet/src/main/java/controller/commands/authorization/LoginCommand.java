package controller.commands.authorization;

import constants.HospitalJspPaths;
import constants.HospitalUriPaths;
import controller.commands.Command;
import model.entity.Doctor;
import model.entity.Patient;
import model.service.DoctorService;
import model.service.PatientService;
import utils.validation.AuthenticationValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginCommand implements Command {

    AuthenticationValidation validation = new AuthenticationValidation();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        List<Doctor> doctorList = doctorService.findAllDoctors();
        List<Doctor> nurseList = doctorService.findAllNurses();
        List<Patient> patientList = patientService.findAll();

        session.setAttribute("pageNumDoctors", 1);
        session.setAttribute("pageNumPatients", 1);
        session.setAttribute("allDoctors", doctorList);
        session.setAttribute("allPatients", patientList);
        session.setAttribute("allDoctorsNurses", Stream.concat(doctorList.stream(), nurseList.stream())
                .collect(Collectors.toList()));
        session.setAttribute("patientService", patientService);
        request.setAttribute("activeDoctors", doctorList);
        request.setAttribute("activePatients", patientList);

        if (validation.login(email, password, session, request)) {
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", defineRole(session));
            return;
        }

        request.getRequestDispatcher(HospitalJspPaths.SIGN_IN).forward(request, response);
    }

    private String defineRole(HttpSession session) {
        String currentRole = (String) session.getAttribute("role");

        switch (currentRole) {
            case "ADMIN":
                return HospitalUriPaths.ADMIN_HOME;
            case "DOCTOR":
                return HospitalUriPaths.DOCTOR_HOME;
            case "PATIENT":
            case "CURED":
                return HospitalUriPaths.PATIENT_HOME;
            case "NURSE":
                return HospitalUriPaths.NURSE_HOME;
            default: return "/";
        }
    }
}
