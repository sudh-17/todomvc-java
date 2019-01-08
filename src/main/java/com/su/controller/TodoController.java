package com.su.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.su.bean.Todo;
import com.su.dao.Model;


public class TodoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Model model;
	
	@Override
	public void init() throws ServletException {
		super.init();
		model = new Model();
	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		String uri = req.getRequestURI();
        PrintWriter out = resp.getWriter();
        if(uri.endsWith("index.do")) {
        	req.setCharacterEncoding("utf-8");
        	req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        else if(uri.endsWith("getAll.do")) {
        	resp.setContentType("text/json;charset=utf-8");  
        	resp.setHeader("cache-control", "no-cache");
        	List<Todo> list = model.get();
        	out.print(JSONObject.toJSONString(list));
        	out.flush();
        }
        else if(uri.endsWith("add.do")) {
        	String title = req.getParameter("title");
        	Todo todo = new Todo();
        	todo.setId(String.valueOf(new Date().getTime()));
        	todo.setCompleted(false);
        	todo.setTitle(title);
        	model.add(todo);
        	out.print(JSONObject.toJSONString(todo));
        }
        else if(uri.endsWith("remove.do")) {
        	String id = req.getParameter("id");
        	model.remove(id);
        	out.print(id);
        }
        else if(uri.endsWith("find.do")) {
        	String id = req.getParameter("id");
        	Todo todo = model.find(id);
        	out.print(JSONObject.toJSONString(todo));
        }
        else if(uri.endsWith("update.do")) {
        	String id = req.getParameter("id");
        	String title = req.getParameter("title");
        	String completed = req.getParameter("completed");
        	Todo todo = new Todo(id, title, Boolean.valueOf(completed));
        	model.update(todo);
        	out.print(JSONObject.toJSONString(todo));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req,resp);
    }
	
	
}
