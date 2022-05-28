package controller.commands.admin;

import constants.HospitalJspPaths;
import constants.HospitalUriPaths;
import controller.commands.Command;
import model.entity.Doctor;
import model.entity.Patient;
import model.service.DoctorService;
import model.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingCommand implements Command {

    private static final Comparator<Doctor> SORT_DOCTOR_BY_ALPHABET = Comparator.comparing(o -> o.getUser().getName());
    private static final Comparator<Doctor> SORT_DOCTOR_BY_TYPE = Comparator.comparing(o -> o.getDoctorType().getType());
    private static final Comparator<Doctor> SORT_DOCTOR_BY_PATIENT_COUNT = Comparator.comparing(o -> new PatientService().findAllDoctorPatients(o.getId()).size());

    private static final Comparator<Patient> SORT_PATIENTS_BY_ALPHABET = Comparator.comparing(o -> o.getUser().getName());
    private static final Comparator<Patient> SORT_PATIENTS_BY_DATE = Comparator.comparing(o -> o.getUser().getBirthday());

    private static final String allDoctors = "allDoctors";
    private static final String allPatients = "allPatients";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int type = 0;
        if(request.getParameter("type") != null){
            type = Integer.parseInt(request.getParameter("type"));
        }

        HttpSession session = request.getSession();

        PatientService patientService = new PatientService();
        List<Patient> patients = patientService.findAll();

        DoctorService service = new DoctorService();
        List<Doctor> doctors = service.findAllDoctors();
        switch (type) {
            case 1:
                doctors.sort(SORT_DOCTOR_BY_ALPHABET);
                session.setAttribute(allDoctors, doctors);
                session.setAttribute(allPatients, patients);
                response.sendRedirect(HospitalUriPaths.ADMIN_DOCTORS_PAGE);
                break;
            case 2:
                doctors.sort(SORT_DOCTOR_BY_TYPE);
                session.setAttribute(allDoctors, doctors);
                session.setAttribute(allPatients, patients);
                response.sendRedirect(HospitalUriPaths.ADMIN_DOCTORS_PAGE);
                break;
            case 3:
                doctors.sort(SORT_DOCTOR_BY_PATIENT_COUNT);
                Collections.reverse(doctors);
                session.setAttribute(allDoctors, doctors);
                session.setAttribute(allPatients, patients);
                response.sendRedirect(HospitalUriPaths.ADMIN_DOCTORS_PAGE);
                break;
            case 4:
                patients.sort(SORT_PATIENTS_BY_ALPHABET);
                session.setAttribute(allDoctors, doctors);
                session.setAttribute(allPatients, patients);
                response.sendRedirect(HospitalUriPaths.ADMIN_PATIENTS_PAGE);
                break;
            case 5:
                patients.sort(SORT_PATIENTS_BY_DATE);
                session.setAttribute(allDoctors, doctors);
                session.setAttribute(allPatients, patients);
                response.sendRedirect(HospitalUriPaths.ADMIN_PATIENTS_PAGE);
                break;
            case 6:
                response.sendRedirect(HospitalUriPaths.ADMIN_DOCTORS_PAGE);
                break;
            case 7:
                response.sendRedirect(HospitalUriPaths.ADMIN_PATIENTS_PAGE);
                break;
            default:
                response.sendRedirect(HospitalUriPaths.ADMIN_HOME);
                break;
        }
    }
}
