package org.example.todolist.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.todolist.model.TodoItem;
import org.example.todolist.service.TodoService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/TodoList")
public class TodoServlet extends HttpServlet {
    private final TodoService todoService = new TodoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TodoItem> todoList = todoService.getAllTasks();
        req.setAttribute("todoList", todoList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "add":
                String description = req.getParameter("description");
                String title = req.getParameter("title");
                String targetDate = req.getParameter("targetDate");
                String status = req.getParameter("status");
                todoService.addTask(title, description, status.equals("true"), LocalDate.parse(targetDate));
                break;

            case "complete":
                int completeId = Integer.parseInt(req.getParameter("id"));
                todoService.changeCompleteTask(completeId);
                break;

            case "delete":
                int deleteId = Integer.parseInt(req.getParameter("id"));
                todoService.deleteTask(deleteId);
                break;

            default:
                req.setAttribute("error", "Invalid action: " + action);
                break;
        }
        resp.sendRedirect(req.getContextPath() + "/TodoList");
    }
}
