package app.classes;

import app.controllers.UsersController;
import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public class Main extends HttpServlet {

    private String ctrl = null;
    private String controller = null;
    private String md = null;
    private String model = null;
    private String action = null;
    private ArrayList<String> args = new ArrayList<String>();

    public String getCtrl() {
        return ctrl;
    }

    public void setCtrl(String ctrl) {
        this.ctrl = ctrl;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getArgs() {
        return args;
    }

    public void setArgs(ArrayList<String> args) {
        this.args = args;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = this.preparaURL(request, response);

        String ctrl = this.preparaController(url);
        String controller = ctrl + "Controller";
        controller = controller.substring(0, 1).toUpperCase() + controller.substring(1);

        String md = ctrl;
        String model = md + "Model";
        model = model.substring(0, 1).toUpperCase() + model.substring(1);

        String action = this.preparaAction(url);
        
        UsersController users = new UsersController();

        this.setCtrl(ctrl);
        this.setController(controller);
        this.setMd(md);
        this.setModel(model);
        this.setAction(action);
        this.setArgs(this.preparaArgs(url));

//        try (PrintWriter out = response.getWriter()) {

//            out.println("url: " + url);
//            out.println("ctrl: " + ctrl);
//            out.println("controller: " + controller);
//            out.println("md: " + md);
//            out.println("model: " + model);
//            out.println("action: " + action);
//            out.println("");
//            out.println("");
//
//            for (int i = 0; i < this.args.size(); i++) {
//                out.println("Parametro " + i + ") " + this.args.get(i));
//            }

//            out.println(linkurl.contains("/"));

            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet TinyListeners</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>" + linkurl + "</h1>");
//            out.println("<h1>Servlet TinyListeners at " + request.getContextPath() + "</h1>");
//            out.println("<p>request.getScheme() " + request.getScheme() + "</p>");
//            out.println("<p>request.getScheme() " + request.getScheme() + "</p>");
//            out.println("<p>request.getServerName() " + request.getServerName() + "</p>");
//            out.println("<p>request.getServerPort() " + request.getServerPort() + "</p>");
//            out.println("<p>request.getRequestURI() " + request.getRequestURI() + "</p>");
//            out.println("<p>request.getRequestURL() " + request.getRequestURL() + "</p>");
//            out.println("<p>URL " + request.getScheme() + "</p>");
//            out.println("<p>DOMAIN " + domain + "</p>");
//            out.println("</body>");
//            out.println("</html>");
//        }

        this.args.clear();
        
//        getServletContext().getRequestDispatcher("/view/users/add.jsp").forward(request, response);
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/users/add.jsp");
        dispatcher.forward(request, response);
        
//        getServletContext().getRequestDispatcher("/view/users/add.jsp").forward(request, response);
    }

    private String preparaURL(HttpServletRequest request, HttpServletResponse response) {
        ServletContext ctx = request.getServletContext();
        String domain = ctx.getInitParameter("DOMAIN").toString().trim();
        String url = request.getRequestURL().toString().trim();
        String linkurl = url.replace(domain, "");
        linkurl = linkurl.replaceFirst("/", "");
        if (!linkurl.equals("") && linkurl.substring(linkurl.length() - 1).equals("/")) {
            linkurl = linkurl.replaceFirst("(?s)(.*)/", "$1" + "");
        }
        return linkurl;
    }

    private String preparaController(String url) {

        String[] elem = null;

        if (url.equals("")) {
            return "pages";
        }

        if (!url.contains("/")) {
            return url;
        } else {
            elem = url.split("/");
            return elem[0];
        }
    }

    private String preparaAction(String url) {
        String[] elem = null;

        if (url.equals("")) {
            return "index";
        }

        if (!url.contains("/")) {
            return "index";
        } else {
            elem = url.split("/");
            if (elem[1] != null) {
                return elem[1];
            } else {
                return "index";
            }
        }
    }

    private ArrayList<String> preparaArgs(String url) {
        ArrayList<String> list = new ArrayList<String>();
                
        if (!url.equals("") && url.contains("/")) {
            String[] elem = url.split("/");
            for (int i = 0; i < elem.length; i++) {
                if (i > 1 && elem[i] != null) {
                    list.add(elem[i]);
                }
            }
        }
        
        return list;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
