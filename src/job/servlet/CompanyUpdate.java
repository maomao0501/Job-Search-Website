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


@WebServlet("/companyupdate")
public class CompanyUpdate extends HttpServlet {
	
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

        // Retrieve company and validate.
        String companyId = req.getParameter("companyid");
        if (companyId == null || companyId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CompanyId.");
        } else {
        	try {
        		Company company = companyDao.getCompanyById(Integer.parseInt(companyId));
        		if(company == null) {
        			messages.put("success", "CompanyId does not exist.");
        		}
        		req.setAttribute("company", company);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CompanyUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve company and validate.
        String companyId = req.getParameter("companyid");
        if (companyId == null || companyId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CompanyId.");
        } else {
        	try {
        		Company company = companyDao.getCompanyById(Integer.parseInt(companyId));
        		if(company == null) {
        			messages.put("success", "CompanyId does not exist. No update to perform.");
        		} else {
        			String newCompanyName = req.getParameter("companyname");
        			if (newCompanyName == null || newCompanyName.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid CompanyName.");
        	        } else {
        	        	company = companyDao.updateCompanyName(company, newCompanyName);
        	        	messages.put("success", "Successfully updated company" + companyId);
        	        }
        		}
        		req.setAttribute("company", company);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CompanyUpdate.jsp").forward(req, resp);
    }
}