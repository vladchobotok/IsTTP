package controller.commands.admin;

import constants.HospitalJspPaths;
import controller.commands.Command;
import model.entity.Doctor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DoctorsList implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Doctor> doctors;

        int currentDoctorPage;

        if(request.getParameter("currentDoctorsPage") != null){
            currentDoctorPage = Integer.parseInt(request.getParameter("currentDoctorsPage"));
        }
        else {
            currentDoctorPage = 1;
        }

        doctors = (List<Doctor>) session.getAttribute("allDoctors");

        int numberOfDoctorPages;
        int numberOfDoctors;

        numberOfDoctors = doctors.size();

        int startDoctors = (currentDoctorPage - 1) * 5;
        int endDoctors = Math.min(startDoctors + 5, numberOfDoctors);
        doctors = doctors.subList(startDoctors, endDoctors);

        if (numberOfDoctors % 5 == 0) {
            numberOfDoctorPages = numberOfDoctors / 5;
        } else {
            numberOfDoctorPages = (numberOfDoctors / 5) + 1;
        }
        request.setAttribute("activeDoctors", doctors);
        request.setAttribute("numberOfDoctorPages", numberOfDoctorPages);
        request.setAttribute("currentDoctorsPage", currentDoctorPage);
        request.getRequestDispatcher(HospitalJspPaths.ADMIN_DOCTORS_PAGE).forward(request, response);
    }
}
