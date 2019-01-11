package com.su.dao;

import java.util.List;

import com.su.bean.Todo;

public interface Model {

	public List<Todo> get();
	
	public boolean add(Todo todo);
	
	public boolean remove(String id);
	
	public Todo find(String id);
	
	public boolean update(Todo todo);
}
