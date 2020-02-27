package ua.epam.petProjectSpring.servlet;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.epam.petProjectSpring.model.Developer;
import ua.epam.petProjectSpring.repository.jdbc.JdbcDeveloperRepositoryImpl;
import ua.epam.petProjectSpring.service.DeveloperServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet(name = "DeveloperServlet", urlPatterns = "/api/v1/developers")
public class DeveloperServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DeveloperServlet.class);
    private DeveloperServiceImpl developerService = new DeveloperServiceImpl(new JdbcDeveloperRepositoryImpl());
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        logger.debug("DeveloperServlet->Get");
        String id = req.getParameter("id");
        if (id == null) {
            List<Developer> developerList = developerService.read();
            if (developerList == null) {
                resp.sendError(404);
            } else {
                writer.println(gson.toJson(developerList));
            }
        } else {
            long developerId = Long.parseLong(id);
            Developer developer = developerService.readById(developerId);
            writer.println(gson.toJson(developer));
        }
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("DeveloperServlet->Post");
        Developer developer = gson.fromJson(req.getReader(), Developer.class);
        developerService.create(developer);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("DeveloperServlet->Put");
        Developer developer = gson.fromJson(req.getReader(), Developer.class);
        developerService.update(developer.getId(), developer);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("DeveloperServlet->Delete");
        if (req.getParameter("id") == null){
            resp.sendError(400, "Invalid parameter id");
        } else {
            developerService.delete(Long.parseLong(req.getParameter("id")));
        }
    }
}