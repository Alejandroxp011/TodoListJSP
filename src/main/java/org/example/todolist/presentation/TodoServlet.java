package org.example.todolist.presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.todolist.domain.entity.TodoItem;
import org.example.todolist.application.TodoService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/TodoList")
public class TodoServlet extends HttpServlet {
    private final TodoService todoService = new TodoService();
    private final Map<String, ServletAction> getActions = new HashMap<>();
    private final Map<String, ServletAction> postActions = new HashMap<>();

    @Override
    public void init() {
        getActions.put(null, this::getAllTasks);
        getActions.put("getTaskDetails", this::getTaskDetails);

        postActions.put("add", this::addTask);
        postActions.put("complete", this::completeTask);
        postActions.put("delete", this::deleteTask);
        postActions.put("edit", this::updateTask);
    }

    private void getAllTasks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TodoItem> todoList = todoService.getAllTasks();
        req.setAttribute("todoList", todoList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void getTaskDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        TodoItem todoItem = todoService.getTaskDetails(id);
        req.setAttribute("todoItem", todoItem);
        req.getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    private void addTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TodoItem todoItem = new TodoItem(
                0,
                req.getParameter("title"),
                req.getParameter("description"),
                false,
                LocalDate.parse(req.getParameter("targetDate"))
        );
        todoService.addTask(todoItem);
        resp.sendRedirect(req.getContextPath() + "/TodoList");
    }

    private void updateTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TodoItem todoItem = new TodoItem(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("title"),
                req.getParameter("description"),
                Boolean.parseBoolean(req.getParameter("status")),
                LocalDate.parse(req.getParameter("targetDate"))
        );
        todoService.updateTask(todoItem);
        resp.sendRedirect( req.getContextPath() + "/TodoList");
    }

    private void completeTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int completeId = Integer.parseInt(req.getParameter("id"));
        todoService.changeCompleteTask(completeId);
        resp.sendRedirect(req.getContextPath() + "/TodoList");
    }

    private void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int deleteId = Integer.parseInt(req.getParameter("id"));
        todoService.deleteTask(deleteId);
        resp.sendRedirect(req.getContextPath() + "/TodoList");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        getAction(req, resp, getActions);
    }

    private void getAction(HttpServletRequest req, HttpServletResponse resp, Map<String, ServletAction> getActions) throws IOException, ServletException {
        String action = req.getParameter("action");

        if (!getActions.containsKey(action)) {
            req.setAttribute("error", "Invalid action: " + action);
            return;
        }

        getActions.get(action).execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        getAction(req, resp, postActions);
    }
}
