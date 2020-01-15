package job.servlet;

import job.dal.*;
import job.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jobupdate")
public class JobUpdate extends HttpServlet {
	
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

        // Retrieve job and validate.
        String jobId = req.getParameter("jobid");
        if (jobId == null || jobId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid JobId.");
        } else {
        	try {
        		Job job = jobDao.getJobById(Integer.parseInt(jobId));
        		if(job == null) {
        			messages.put("success", "JobId does not exist.");
        		}
        		req.setAttribute("job", job);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/JobUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve job and validate.
        String jobId = req.getParameter("jobid");
        if (jobId == null || jobId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CompanyId.");
        } else {
        	try {
        		Job job = jobDao.getJobById(Integer.parseInt(jobId));
        		if(job == null) {
        			messages.put("success", "JobId does not exist. No update to perform.");
        		} else {
        			String newMainJobTitle = req.getParameter("mainjobtitle");
        			if (newMainJobTitle == null || newMainJobTitle.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid MainJobTitle.");
        	        } else {
        	        	job = jobDao.updateMainJobTitle(job, newMainJobTitle);
        	        	messages.put("success", "Successfully updated Job" + jobId);
        	        }
        		}
        		req.setAttribute("job", job);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/JobUpdate.jsp").forward(req, resp);
    }
}