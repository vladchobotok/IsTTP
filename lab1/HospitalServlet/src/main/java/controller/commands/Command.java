package controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    /**
     * The method executes command's functional inside servlet implementation
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
