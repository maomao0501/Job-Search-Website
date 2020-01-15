package job.servlet;

import job.dal.*;
import job.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jobjoblocation")
public class JobJobLocation extends HttpServlet {
	
	protected JobLocationDao jobLocationDao;
	
	@Override
	public void init() throws ServletException {
		jobLocationDao = JobLocationDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate CompanyId.
        String jobLocationId = req.getParameter("joblocationid");
        if (jobLocationId == null || jobLocationId.trim().isEmpty()) {
            messages.put("title", "Invalid joblocationid.");
        } else {
        	messages.put("title", "JobLocation" + jobLocationId);
            // Retrieve JobLocation, and store in the request.
            JobLocation joblocation;
            try {
            	joblocation = jobLocationDao.getJobLocationById(Integer.parseInt(jobLocationId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
            req.setAttribute("joblocation", joblocation);
        }

        req.getRequestDispatcher("/JobJobLocation.jsp").forward(req, resp);
	}
}