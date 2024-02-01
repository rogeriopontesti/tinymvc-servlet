/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.classes;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 *
 * @author 28934
 */

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
    	ServletContext ctx = event.getServletContext();
    	
//    	String url = ctx.getInitParameter("DBURL");
//    	String u = ctx.getInitParameter("DBUSER");
//    	String p = ctx.getInitParameter("DBPWD");
//    	
//    	//create database connection from init parameters and set it to context
//    	DBConnectionManager dbManager = new DBConnectionManager(url, u, p);
//    	ctx.setAttribute("DBManager", dbManager);
    	System.out.println("Database connection initialized for Application.");
    }

    public void contextDestroyed(ServletContextEvent event) {
    	ServletContext ctx = event.getServletContext();
//    	DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
//    	dbManager.closeConnection();
    	System.out.println("Database connection closed for Application.");
    	
    }

}
