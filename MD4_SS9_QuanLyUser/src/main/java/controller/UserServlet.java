package controller;

import model.User;
import service.IUserService;
import service.UserServiceIMPL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userServlet", value = "/user-servlet")
public class UserServlet extends HttpServlet {
    IUserService userService = new UserServiceIMPL();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "show":
                showListUser(request, response);
                break;
            case "create":
                showCreateUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createUser(request, response);
                break;
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idDel = Integer.parseInt(request.getParameter("id"));
        userService.remove(idDel);
        request.setAttribute("listUser", userService.findAll());
        request.getRequestDispatcher("view/User.jsp").forward(request, response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = userService.findAll();
        int id;
        if (listUser.isEmpty()) {
            id = 1;
        } else {
            id = listUser.get(listUser.size() - 1).getId() + 1;
        }
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(id, name, email, country);
        userService.save(newUser);
        request.setAttribute("message", "Create New User Success");
        request.getRequestDispatcher("view/create.jsp").forward(request, response);
    }

    private void showListUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listUser", userService.findAll());
        request.getRequestDispatcher("view/User.jsp").forward(request, response);
    }

    private void showCreateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("view/create.jsp").forward(request, response);
    }
}
