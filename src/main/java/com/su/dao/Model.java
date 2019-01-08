package com.su.dao;

import java.util.ArrayList;
import java.util.List;

import com.su.bean.Todo;

public class Model {

	private List<Todo> todos;

	public Model() {
		todos = new ArrayList<Todo>();
		todos.add(new Todo("1","a",false));
		todos.add(new Todo("2","b",false));
		todos.add(new Todo("3","c",true));
	}
	
	public List<Todo> get(){
		return this.todos;
	}
	
	public boolean add(Todo todo) {
		if(todo == null) {
			return false;
		}
		return todos.add(todo);
	}
	
	public boolean remove(String id) {
		boolean res = false;
		for(Todo todo: todos) {
			if(todo.getId().equals(id)) {
				todos.remove(todo);
				res = true;
				break;
			}
		}
		return res;
	}
	
	public Todo find(String id) {
		for(Todo todo: todos) {
			if(todo.getId().equals(id)) {
				return todo;
			}
		}
		return null;
	}
	
	public boolean update(Todo todo) {
		for(int i = 0;i< todos.size();i++) {
			if(todos.get(i).getId().equals(todo.getId())) {
				todos.get(i).setTitle(todo.getTitle());
				todos.get(i).setCompleted(todo.isCompleted());
				return true;
			}
		}
		return false;
	}
}
