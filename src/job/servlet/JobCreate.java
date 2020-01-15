package job.servlet;

import job.dal.*;
import job.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jobcreate")
public class JobCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/JobCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate MainJobTitle.
        String companyId = req.getParameter("companyid");
        if (companyId == null || companyId.trim().isEmpty()) {
            messages.put("success", "Invalid CompanyId");
        } else {
        	// Create the Job.
        	String mainJobTitle = req.getParameter("mainjobtitle");
        	String stringDateAdvertised = req.getParameter("dateadvertised");
        	String jobLocationId = req.getParameter("joblocationid");
        	String workTypeId = req.getParameter("worktype");
        	String classification = req.getParameter("classification");
        	String jobDescription = req.getParameter("jobdescription");
        	String pageURL = req.getParameter("pageURL");
        	String userAccountId = req.getParameter("useraccountid");
        	// dateadvertised must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	Date dateAdvertised = new Date();
	        try {
	        	dateAdvertised = dateFormat.parse(stringDateAdvertised);
	        } catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
	        try {
	        	CompanyDao companyDao = CompanyDao.getInstance();
	        	Company company = 
	        			companyDao.getCompanyById(Integer.parseInt(companyId));
	        	WorkTypeDao workTypeDao = WorkTypeDao.getInstance();
	        	WorkType workType = 
	        			workTypeDao.getWorkTypeById(Integer.parseInt(workTypeId));
	        	JobLocationDao jobLocationDao = JobLocationDao.getInstance();
	        	JobLocation jobLocation = 
	        			jobLocationDao.getJobLocationById(Integer.parseInt(jobLocationId));
	        	UserAccountDao userAccountDao = UserAccountDao.getInstance();
	        	UserAccount userAccount = 
	        			userAccountDao.getJobUserFromUserAccountId(userAccountId);
	        	Job job = new Job(company, mainJobTitle, dateAdvertised, jobLocation, workType, classification, 
	        			jobDescription, pageURL, userAccount);
	        	job = jobDao.create(job);
	        	messages.put("success", "Successfully created " + mainJobTitle);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/JobCreate.jsp").forward(req, resp);
    }
}