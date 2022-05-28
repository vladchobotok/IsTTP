package utils.validation;

import model.entity.User;
import model.service.DoctorService;
import model.service.PatientService;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationValidation {

    private static final String EMAIL_REGEX = "^[a-zA-Z\\d_.+-]+@[a-zA-Z\\d-]+\\.[a-zA-Z\\d-.]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    UserService userService = new UserService();
    PatientService patientService = new PatientService();
    DoctorService doctorService = new DoctorService();

    public boolean login(String username, String password, HttpSession httpSession, HttpServletRequest request) {
        Optional<User> user = userService.signIn(username, password);

        if(username.equals("")){
            request.setAttribute("bad_getaway", "emptyEmail");
            return false;
        }
        if(password.equals("")){
            request.setAttribute("bad_getaway", "emptyPassword");
            return false;
        }
        if(!user.isPresent()){
            request.setAttribute("bad_getaway", "incorrectInput");
            return false;
        }

        httpSession.setAttribute("role", user.get().getRole().getName());
        httpSession.setAttribute("user_id", user.get().getId());
        return true;
    }

    public boolean registerPatient(User user, String confirmedPassword, HttpServletRequest request){
        if (!isRegisterValid(user, confirmedPassword, request)){
            return false;
        }

        Matcher emailMatcher = Pattern.compile(EMAIL_REGEX).matcher(user.getEmail());
        Matcher passwordMatcher = Pattern.compile(PASSWORD_REGEX).matcher(confirmedPassword);
        if (emailMatcher.matches() && passwordMatcher.matches()) {
            userService.registerUser(user);
            return patientService.registerPatient(userService.getUserIdByEmail(user.getEmail()));
        }
        else {
            if(!emailMatcher.matches()){
                request.setAttribute("bad_getaway", "incorrectEmailFormat");
            }
            else{
                request.setAttribute("bad_getaway", "incorrectPasswordFormat");
            }
            return false;
        }
    }

    public boolean registerDoctor(User user, String confirmedPassword, int doctorType, HttpServletRequest request){
        if (!isRegisterValid(user, confirmedPassword, request)){
            return false;
        }
        Matcher emailMatcher = Pattern.compile(EMAIL_REGEX).matcher(user.getEmail());
        Matcher passwordMatcher = Pattern.compile(PASSWORD_REGEX).matcher(confirmedPassword);
        if (emailMatcher.matches() && passwordMatcher.matches()) {
            userService.registerUser(user);
            return doctorService.addDoctor(userService.getUserIdByEmail(user.getEmail()), doctorType);

        } else {
            if(!emailMatcher.matches()){
                request.setAttribute("bad_getaway", "incorrectEmailFormat");
            }
            else{
                request.setAttribute("bad_getaway", "incorrectPasswordFormat");
            }
            return false;
        }
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

    public boolean isRegisterValid(User user, String confirmedPassword, HttpServletRequest request){
        if (user.getName().equals("")){
            request.setAttribute("bad_getaway", "emptyName");
            return false;
        }
        if(user.getSurname().equals("")){
            request.setAttribute("bad_getaway", "emptySurname");
            return false;
        }
        if(user.getEmail().equals("")){
            request.setAttribute("bad_getaway", "emptyEmail");
            return false;
        }
        if(user.getBirthday() == null || user.getBirthday().isAfter(LocalDate.now())){
            request.setAttribute("bad_getaway", "incorrectBirthday");
            return false;
        }
        if(user.getPassword().equals("")){
            request.setAttribute("bad_getaway", "emptyPassword");
            return false;
        }
        if(userService.getUserIdByEmail(user.getEmail()) != 0) {
            request.setAttribute("bad_getaway", "accountAlreadyExists");
            return false;
        }

        if (!user.getPassword().equals(confirmedPassword)) {
            request.setAttribute("bad_getaway", "wrongConfirmedPassword");
            return false;
        }
        return true;
    }
}
