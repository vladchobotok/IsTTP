package controller.commands.doctor;

import constants.HospitalJspPaths;
import constants.HospitalUriPaths;
import controller.commands.Command;
import model.entity.Doctor;
import model.entity.Patient;
import model.service.AssignmentService;
import model.service.DoctorService;
import model.service.PatientService;
import model.service.TreatmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CreateAssignment implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int executorId;
        if(request.getParameter("doctorId2") != null){
            executorId = Integer.parseInt(request.getParameter("doctorId2"));
        }
        else {
            request.setAttribute("bad_getaway", "emptyExecutor");
            request.getRequestDispatcher(HospitalJspPaths.DOCTOR_HOME).forward(request, response);
            return;
        }
        int prescriberId = ((Doctor) session.getAttribute("currentDoctor")).getId();
        int assignmentType;
        String description = request.getParameter("description");
        if(request.getParameter("assignmentType") != null){
            assignmentType = Integer.parseInt(request.getParameter("assignmentType"));
        }
        else {
            request.setAttribute("bad_getaway", "emptyAssignmentType");
            request.getRequestDispatcher(HospitalJspPaths.DOCTOR_HOME).forward(request, response);
            return;
        }

        DoctorService doctorService = new DoctorService();

        String isNurse = doctorService.findDoctorById(executorId).getDoctorType().getType();

        if(assignmentType == 2 && isNurse.equals("Nurse")) {
            request.setAttribute("bad_getaway", "nurseCannotDoOperations");
            request.getRequestDispatcher(HospitalJspPaths.DOCTOR_HOME).forward(request, response);
            return;
        }

        AssignmentService assignmentService = new AssignmentService();
        int assignmentId = assignmentService.insertAssignment(executorId, prescriberId, description, assignmentType);

        TreatmentService treatmentService = new TreatmentService();
        int treatmentId = treatmentService.insertTreatment(assignmentId, 1);

        PatientService patientService = new PatientService();
        patientService.assignTreatment(treatmentId, patientId);
        session.setAttribute("allPatients", patientService.findAll());

        response.sendRedirect(HospitalUriPaths.DOCTOR_HOME);
    }
}
