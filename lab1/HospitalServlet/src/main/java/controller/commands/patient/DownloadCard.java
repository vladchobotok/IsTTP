package controller.commands.patient;

import constants.HospitalUriPaths;
import controller.commands.Command;
import model.entity.Patient;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class DownloadCard implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            Patient patient = (Patient) session.getAttribute("currentPatient");

            String fileName = "hospitalCard.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Card of " + patient.getUser().getName() + " " + patient.getUser().getSurname());

            XSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Name");
            rowhead.createCell(1).setCellValue("Surname");
            rowhead.createCell(2).setCellValue("Diagnosis");
            rowhead.createCell(3).setCellValue("Curing doctor");
            rowhead.createCell(4).setCellValue("Status");

            XSSFRow row = sheet.createRow((short) 1);
            row.createCell(0).setCellValue(patient.getUser().getName());
            row.createCell(1).setCellValue(patient.getUser().getSurname());
            row.createCell(2).setCellValue(patient.getTreatment().getDiagnosis().getType());
            row.createCell(3).setCellValue(patient.getDoctor().getUser().getName() + " " + patient.getDoctor().getUser().getSurname() + " (" + patient.getDoctor().getDoctorType().getType() + ")");
            row.createCell(4).setCellValue(patient.getUser().getRole().getName());

            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();

            File fileToDownload = new File(fileName);
            InputStream in = new FileInputStream(fileToDownload);

            String mimeType = new MimetypesFileTypeMap().getContentType(fileName);

            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setContentLength((int) fileToDownload.length());

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", fileToDownload.getName());
            response.setHeader(headerKey, headerValue);

            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = in.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            in.close();
            outStream.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        finally {
            response.sendRedirect(HospitalUriPaths.PATIENT_HOME);
        }
    }
}
