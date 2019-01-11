package com.su.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.su.bean.Todo;
import com.su.dao.Model;
import com.su.dao.impl.ModelImpl;
import com.su.util.Mapping;

public class Controller {
	private Model model;
	public Controller(Model model) {
		this.model = model;
	}
	
	@Mapping(value="index.do")
	public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
    	req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	@Mapping(value="getAll.do")
	public void getAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/json;charset=utf-8");
    	resp.setHeader("cache-control", "no-cache");
    	//resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");//允许某个域名跨域访问
    	List<Todo> list = model.get();
    	out.print(JSONObject.toJSONString(list));
    	out.flush();
	}

	@Mapping(value="add.do")
	public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		String title = req.getParameter("title");
    	Todo todo = new Todo();
    	todo.setId(String.valueOf(new Date().getTime()));
    	todo.setCompleted(false);
    	todo.setTitle(title);
    	model.add(todo);
    	out.print(JSONObject.toJSONString(todo));
	}
	
	@Mapping(value="remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
    	model.remove(id);
    	PrintWriter out = resp.getWriter();
    	out.print(id);
	}
	
	@Mapping(value="find.do")
	public void find(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
    	Todo todo = model.find(id);
    	PrintWriter out = resp.getWriter();
    	out.print(JSONObject.toJSONString(todo));
	}
	
	@Mapping(value="update.do")
	public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
    	String title = req.getParameter("title");
    	String completed = req.getParameter("completed");
    	Todo todo = new Todo(id, title, Boolean.valueOf(completed));
    	model.update(todo);
    	PrintWriter out = resp.getWriter();
    	out.print(JSONObject.toJSONString(todo));
	}
}
