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


@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {
	
	UserAccountDao userAccountDao = new UserAccountDao();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        
        // Retrieve user and validate.
        String UserAccountId = req.getParameter("UserAccountId");
        String action = req.getParameter("action");
        if (UserAccountId == null || UserAccountId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserAccountId.");
        } else {
        	try {
        		if (action.equals("get")) {
        			UserAccount userAccount = userAccountDao.getJobUserFromUserAccountId(UserAccountId);
            		if(userAccount == null) {
            			messages.put("success", "UserAccountId does not exist.");
            		}
            		req.setAttribute("jobUser", userAccount);
				}
        		else
        		{
        			String UserTypeId = req.getParameter("UserTypeId");
                	String firstName = req.getParameter("firstname");
                	String lastName = req.getParameter("lastname");
                	String email = req.getParameter("email");
                	String password = req.getParameter("password");
                	String DateOfBirth = req.getParameter("DateOfBirth");
                	String Gender = req.getParameter("Gender");
                	String ContactNumber = req.getParameter("ContactNumber");
                	// dob must be in the format yyyy-mm-dd.
                	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                	Date DateOfB = new Date();
                	try {
                		DateOfB = dateFormat.parse(DateOfBirth);
                	} catch (ParseException e) {
                		e.printStackTrace();
        				throw new IOException(e);
                	}
        			UserAccount userAccount = new UserAccount();
        			
        			userAccount.setContactNumber(ContactNumber);
    	        	userAccount.setDateOfBirth(DateOfB);
    	        	userAccount.setEmail(email);
    	        	userAccount.setFirstName(firstName);
    	        	userAccount.setGender(Gender);
    	        	userAccount.setLastName(lastName);
    	        	userAccount.setPassword(password);
    	        	userAccount.setRegistrationDate(new Date());
    	        	userAccount.setUserAccountId(UserAccountId);
    	        	userAccount.setUserType(new UserType(UserTypeId,"1"));
        			
        			UserAccount userAccount1 = userAccountDao.updateJobUserFromUserAccountId(userAccount);
            		if(userAccount != null) {
            			messages.put("success", "Updated User " + UserAccountId);
            			req.setAttribute("jobUser", userAccount1);
            		}
        		}
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		this.doGet(req, resp);
        /*// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		BlogUsers blogUser = blogUsersDao.getBlogUserFromUserName(userName);
        		if(blogUser == null) {
        			messages.put("success", "UserName does not exist. No update to perform.");
        		} else {
        			String newLastName = req.getParameter("lastname");
        			if (newLastName == null || newLastName.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid LastName.");
        	        } else {
        	        	blogUser = blogUsersDao.updateLastName(blogUser, newLastName);
        	        	messages.put("success", "Successfully updated " + userName);
        	        }
        		}
        		req.setAttribute("blogUser", blogUser);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);*/
    }
}
