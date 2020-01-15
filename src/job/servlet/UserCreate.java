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
import javax.xml.crypto.Data;


@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {
	
    UserAccountDao userAccountDao =new UserAccountDao();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String UserAccountId = req.getParameter("UserAccountId");
        if (UserAccountId == null || UserAccountId.trim().isEmpty()) {
            messages.put("success", "Invalid UserAccountId");
        } else {
        	// Create the User.
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
	        try {
	        	// Exercise: parse the input for StatusLevel.
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
	        	
	        	if (userAccount!=null) {
	        		boolean b=userAccountDao.create(userAccount);
	        		if(b==true)
		        	 {
		        		 messages.put("success", "Successfully created " + UserAccountId); 
		        	 }
				}
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }
}
