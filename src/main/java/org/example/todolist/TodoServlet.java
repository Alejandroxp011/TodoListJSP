package org.example.todolist;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TodoList")
public class TodoServlet extends HttpServlet {
    private final List<TodoItem> todoList = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void init(ServletConfig config) throws ServletException {
        todoList.add(new TodoItem(nextId++, "Learn Java EE", "Learn Java EE with the help of the guide", null));
        todoList.add(new TodoItem(nextId++, "Learn Java Servlet", "Learn Java Servlet with the help of the guide", null));
        todoList.add(new TodoItem(nextId++, "Learn JavaServer Pages", "Learn JavaServer Pages with the help of the guide", null));
        todoList.add(new TodoItem(nextId++, "Learn Expression Language", "Learn Expression Language with the help of the guide", null));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("todoList", todoList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            String description = req.getParameter("description");

        } else if ("complete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            todoList.stream()
                    .filter(item -> item.getId() == id)
                    .findFirst()
                    .ifPresent(item -> item.setCompleted(!item.isCompleted()));
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            todoList.removeIf(item -> item.getId() == id);
        }
        resp.sendRedirect(req.getContextPath() + "/TodoList");
    }
}
