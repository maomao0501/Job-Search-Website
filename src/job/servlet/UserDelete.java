package job.servlet;

import job.dal.*;
import job.model.*;

import java.io.IOException;
import java.sql.SQLException;
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


@WebServlet("/userdelete")
public class UserDelete extends HttpServlet {
	
	UserAccountDao userAccountDao = new UserAccountDao();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete BlogUser");        
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
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
            messages.put("title", "Invalid UserAccountId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        try {
	        	boolean b = userAccountDao.delete(UserAccountId);
	        	// Update the message.
		        if (b == true) {
		            messages.put("title", "Successfully deleted " + UserAccountId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + UserAccountId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
    }
}
