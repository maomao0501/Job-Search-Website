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


@WebServlet("/companyjobs")
public class CompanyJobs extends HttpServlet {
	
	protected JobDao jobDao;
	
	@Override
	public void init() throws ServletException {
		jobDao = JobDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate CompanyId.
        String companyId = req.getParameter("companyid");
        if (companyId == null || companyId.trim().isEmpty()) {
            messages.put("title", "Invalid companyid.");
        } else {
        	messages.put("title", "Jobs for company" + companyId);
            // Retrieve Jobs, and store in the request.
            List<Job> jobs = new ArrayList<Job>();
            try {
            	Company company = new Company(Integer.parseInt(companyId));
            	jobs = jobDao.getJobForCompany(company);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
            req.setAttribute("jobs", jobs);
        }

        req.getRequestDispatcher("/CompanyJobs.jsp").forward(req, resp);
	}
}
