package com.su.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.su.bean.Todo;
import com.su.controller.Controller;
import com.su.dao.Model;
import com.su.util.Mapping;


public class Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Model model;
	private Controller controller;
	
	@Override
	public void init() throws ServletException {
		super.init();
		model = new Model();
		controller = new Controller(model);
	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"));
		Method[] methods = controller.getClass().getDeclaredMethods();
		for( Method m:methods) {
			Mapping ant = m.getDeclaredAnnotation(Mapping.class);
			if(action.contains(ant.value())) {				
				try {
					Object[] agrs = new Object[2];
					agrs[0] = req;
					agrs[1] = resp;
					m.invoke(controller, agrs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req,resp);
    }
	
	
}
