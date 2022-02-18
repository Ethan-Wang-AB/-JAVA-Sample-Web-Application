/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Company;
import models.User;
import services.AccountService;
import services.GmailService;

/**
 *
 * @author 845593
 */
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            AccountService accountService = new AccountService();
            HttpSession session = request.getSession();
            if (request.getParameterMap().containsKey("uuid") && request.getParameterMap().containsKey("email")) {
                String emailDirect = request.getParameter("email");
                String uuid = request.getParameter("uuid");
                if (!emailDirect.equals("")) {
                    User user = accountService.get(emailDirect);
                    if (user != null) {
                        String uuidTest = user.getDirectLoginUuid();
                        if (uuidTest.equals(uuid)) {
                            session.setAttribute("email", emailDirect);
                            if (user.getRole().getRoleId() == 1) {

                                response.sendRedirect("admin");
                            } else if (user.getRole().getRoleId() == 3) {
                                response.sendRedirect("companyadmin");

                            } else {
                                response.sendRedirect("inventory");
                            }

                        }
                    }

                }

            }
            List<Company> companies = accountService.getAllCompany();
            request.setAttribute("companies", companies);

            getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService accountService = new AccountService();
        User user;

        try {
            String email = "";
            String firstname = "";
            String lastname = "";
            String companyName = request.getParameter("company");

            if (session.getAttribute("emailRegister") == null) {
                email = request.getParameter("email");
                firstname = request.getParameter("firstname");
                lastname = request.getParameter("lastname");
                String password = request.getParameter("password");
                Company company = accountService.getCompany(companyName);
                accountService.insert(email, firstname, lastname, password, company);
                user = accountService.get(email);
                user.setDisplay(false);
                session.setAttribute("emailRegister", email);
                request.setAttribute("firstname", firstname);
                request.setAttribute("lastname", lastname);

            } else {
                email = (String) session.getAttribute("emailRegister");
                user = accountService.get(email);
                firstname = user.getFirstName();
                lastname = user.getLastName();
            }

            int confirmationCode = (int) (Math.random() * 10000 / 1);

            if (session.getAttribute("confirmation") == null) {
                session.setAttribute("confirmation", confirmationCode);
                HashMap<String, String> tags = new HashMap<>();

                tags.put("firstname", firstname);
                tags.put("lastname", lastname);
                tags.put("action", "registration");
                tags.put("code", confirmationCode + "");

                String path = getServletContext().getRealPath("/WEB-INF");
                String template = path + "/emailtemplates/confirmation.html";

                GmailService.sendMail(email, "Confirm your registration", template, tags);
                getServletContext().getRequestDispatcher("/WEB-INF/confirmationregister.jsp").forward(request, response);
                return;

            } else {
                int confirmation = (int) session.getAttribute("confirmation");
                if (request.getParameter("code") != null && !request.getParameter("code").equals("")) {
                    int code = Integer.parseInt(request.getParameter("code"));
                    if (confirmation == code) {

                        user = accountService.get(email);
                        user.setDisplay(true);

                        String uuid = UUID.randomUUID().toString();
                        String url = request.getRequestURL().toString();
                        String link = url + "?uuid=" + uuid + "&email=" + email;
                        HashMap<String, String> tags = new HashMap<>();

                        tags.put("firstname", firstname);
                        tags.put("lastname", lastname);
                        tags.put("link", link);

                        String path = getServletContext().getRealPath("/WEB-INF");
                        String template = path + "/emailtemplates/directlink.html";
                        user.setDirectLoginUuid(uuid);
                        accountService.update(user);

                        GmailService.sendMail(email, "Registration succesful!", template, tags);

                        response.sendRedirect("login?registration");
                        return;

                    }
                } else {
                    getServletContext().getRequestDispatcher("/WEB-INF/confirmationregister.jsp").forward(request, response);

                }
            }

        } catch (Exception ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
