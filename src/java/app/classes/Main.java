package app.classes;

import app.controllers.UsersController;
import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;
import java.lang.reflect.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.ClassNotFoundException;

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
//        response.setContentType("text/html;charset=UTF-8");

        String url = this.preparaURL(request, response);

        String ctrl = this.preparaController(url);
        String controller = ctrl + "Controller";
        controller = controller.substring(0, 1).toUpperCase() + controller.substring(1);
        String pacote = "app.controllers." + controller;

        String md = ctrl;
        String model = md + "Model";
        model = model.substring(0, 1).toUpperCase() + model.substring(1);

        String action = this.preparaAction(url);

//        UsersController users = new UsersController();
        this.setCtrl(ctrl);
        this.setController(controller);
        this.setMd(md);
        this.setModel(model);
        this.setAction(action);
        this.setArgs(this.preparaArgs(url));

        try (PrintWriter out = response.getWriter()) {

            out.println("url: " + url);
            out.println("ctrl: " + ctrl);
            out.println("controller: " + controller);
            out.println("md: " + md);
            out.println("model: " + model);
            out.println("action: " + action);
            out.println("pacote: " + pacote);

            out.println("-------------------------------------------------------");
            for (int i = 0; i < this.args.size(); i++) {
                out.println("Parametro " + (i + 1) + ") " + this.args.get(i));
            }

            try {
                Class<?> cls = Class.forName(pacote);
                UsersController users = new UsersController();
                
                try {
                    Class[] parameterType = new Class[3];
                    parameterType[0] = ArrayList.class;
                    parameterType[1] = HttpServletRequest.class;
                    parameterType[2] = HttpServletResponse.class;

                    Method mtd = cls.getMethod(action, parameterType);
//                    Method mtd = cls.getMethod(action);

                    Object[] params = new Object[3];
                    params[0] = this.args;
                    params[1] = request;
                    params[2] = response;
                    
                    try {
                        mtd.invoke(users, params);
                        out.println("users ok");
                    } catch (IllegalAccessException e) {
                        out.println("IllegalAccessException e");
                        out.println(e.toString());
                        out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        out.println("IllegalArgumentException e");
                        out.println(e.toString());
                        out.println(e.getMessage());
                    } catch (InvocationTargetException e) {
                        out.println("InvocationTargetException e");
                        out.println(e.toString());
                        out.println(e.getMessage());
                    }

                    out.println(Arrays.toString(params));

                } catch (NoSuchMethodException methodException) {
                    out.println("NoSuchMethodException: methodException");
                    out.println("Line: 118");
                    out.println(methodException.toString());
                    out.println(methodException.getMessage());
                }

//                try {
//                    Class[] parameterType = new Class[2];
//                    parameterType[0] = ArrayList.class;
//                    parameterType[1] = HttpServletRequest.class;
//                    parameterType[2] = HttpServletResponse.class;
//                    out.println("Show 2");
//                    Method mtd = cls.getDeclaredMethod(action, parameterType);
//                    out.println("Show");
//                    try {
//                        Object[] params = new Object[3];
//                        params[0] = this.args;
//                        params[1] = request;
//                        params[2] = response;
//                        Object resultado = cls.class.invoke(controller, params);
//                    } catch (IllegalAccessException ilegalException) {
//                        out.println("IllegalAccessException: ilegalException");
//                        out.println("Line: 118");
//                        out.println(ilegalException.toString());
//                    }
//                } catch (NoSuchMethodException methodException) {
//                    out.println("NoSuchMethodException: methodException");
//                    out.println("Line: 118");
//                    out.println(methodException.toString());
//                }
//                Constructor<?>[] construtores = cls.getDeclaredConstructors();
                //todos os construtores
//                out.println("-------------------------------------------------------");
//                for (int i = 0; i < construtores.length; i++) {
//                    out.println(construtores[i]);
//                }
                //todos os atributos da classe
//                Field[] atributos = cls.getDeclaredFields();
//
//                out.println("-------------------------------------------------------");
//                for (int i = 0; i < atributos.length; i++) {
//                    out.println(atributos[i]);
//                }
                //todos os atributos públicos
//                Field[] attrs = cls.getFields();
//
//                out.println("-------------------------------------------------------");
//                for (int i = 0; i < attrs.length; i++) {
//                    out.println(attrs[i]);
//                }
                //todos os métodos da classe
//                Method[] metodos = cls.getDeclaredMethods();
//
//                out.println("-------------------------------------------------------");
//                for (int i = 0; i < metodos.length; i++) {
//                    out.println(metodos[i]);
//                    Class<?>[] parametros = metodos[i].getParameterTypes();
//                    for (int x = 0; x < parametros.length; x++) {
//                        out.println("Parametro " + x + ": " + parametros[x]);
//                    }
//                    out.println("");
//                }
                //todos os métodos públicos
//                Method[] mtds = cls.getMethods();
//
//                out.println("-------------------------------------------------------");
//                for (int i = 0; i < mtds.length; i++) {
//                    out.println(mtds[i]);
//                    Class<?>[] params = mtds[i].getParameterTypes();
//                    for (int x = 0; x < params.length; x++) {
//                        out.println("Parametro " + x + ": " + params[x]);
//                    }
//                    out.println("");
//                }
            } catch (ClassNotFoundException exception) {
                out.println("ClassNotFoundException: exception");
                out.println("Package: app.classes");
                out.println("Class Name: Main");
                out.println("Method: processRequest");
                out.println("Line: 118");
                out.println(exception.toString());
                out.println(exception.getMessage());
            }

            this.args.clear();
        }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
