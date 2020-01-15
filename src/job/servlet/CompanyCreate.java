package job.servlet;

import job.dal.*;
import job.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/companycreate")
public class CompanyCreate extends HttpServlet {
	
	protected CompanyDao companyDao;
	
	@Override
	public void init() throws ServletException {
		companyDao = CompanyDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CompanyCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate CompanyName.
        String companyName = req.getParameter("companyname");
        if (companyName == null || companyName.trim().isEmpty()) {
            messages.put("success", "Invalid CompanyName");
        } else {
        	// Create the Company.
        	String URL = req.getParameter("url");
        	String yearFounded = req.getParameter("yearfounded");
        	String city = req.getParameter("city");
        	String state = req.getParameter("state");
        	String country = req.getParameter("country");
        	String zipCode = req.getParameter("zipcode");
        	String companyType = req.getParameter("companytype");
        	String description = req.getParameter("description");
        	String companyCategoryId = req.getParameter("companycategoryid");
        	
	        try {
	        	CompanyCategoryDao companyCategoryDao = CompanyCategoryDao.getInstance();
	        	CompanyCategory companyCategory = 
	        			companyCategoryDao.getCompanyCategoryById(Integer.parseInt(companyCategoryId));
	        	Company company = new Company(companyName, URL, yearFounded, city, state, 
	        			country, zipCode, companyType, description, companyCategory);
	        	company = companyDao.create(company);
	        	messages.put("success", "Successfully created Company " + companyName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CompanyCreate.jsp").forward(req, resp);
    }
}
