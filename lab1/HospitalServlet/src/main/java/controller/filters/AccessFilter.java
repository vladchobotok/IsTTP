package controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.HospitalJspPaths.FORBIDDEN_PAGE;
import static constants.HospitalUriPaths.*;

@WebFilter("/*")
public class AccessFilter implements Filter {
    private static final Map<String, String> PROTECTED_URIS = new HashMap<>();
    private static final List<String> GUEST = new ArrayList<>();

    static {
        GUEST.add(LOGIN);
        GUEST.add(REGISTER);

        PROTECTED_URIS.put(NURSE_HOME, "NURSE");
        PROTECTED_URIS.put(DOCTOR_HOME, "DOCTOR");
        PROTECTED_URIS.put(PATIENT_HOME, "PATIENT");
        PROTECTED_URIS.put(ADMIN_HOME, "ADMIN");
        PROTECTED_URIS.put(ADMIN_ASSIGN_DOCTOR, "ADMIN");
        PROTECTED_URIS.put(ADMIN_REGISTER_DOCTOR, "ADMIN");
        PROTECTED_URIS.put(ADMIN_REGISTER_PATIENT, "ADMIN");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String uri = httpServletRequest.getRequestURI();

        String currentRole = PROTECTED_URIS.get(uri);
        String sessionRole = (String) session.getAttribute("role");

        if (GUEST.contains(uri) && sessionRole != null) {
            request.getRequestDispatcher(FORBIDDEN_PAGE).forward(request, response);
            return;
        }

        if (PROTECTED_URIS.containsKey(uri)) {
            if (sessionRole == null) {
                request.getRequestDispatcher(HOME_HOSPITAL).forward(request, response);
                return;
            }
            if (!currentRole.equals(sessionRole) && !sessionRole.equals("CURED") && !currentRole.equals("PATIENT")) {
                request.getRequestDispatcher(FORBIDDEN_PAGE).forward(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
