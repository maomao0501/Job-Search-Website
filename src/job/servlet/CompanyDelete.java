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


@WebServlet("/companydelete")
public class CompanyDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Company");        
        req.getRequestDispatcher("/CompanyDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate CompanyId.
        String companyId = req.getParameter("companyid");
        if (companyId == null || companyId.trim().isEmpty()) {
            messages.put("title", "Invalid CompanyId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Company.
	        Company company = new Company(Integer.parseInt(companyId));
	        try {
	        	company = companyDao.delete(company);
	        	// Update the message.
		        if (company == null) {
		            messages.put("title", "Successfully deleted Company" + companyId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + companyId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CompanyDelete.jsp").forward(req, resp);
    }
}
