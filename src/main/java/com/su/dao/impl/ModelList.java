package com.su.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.su.bean.Todo;
import com.su.dao.Model;

public class ModelList implements Model {

	private List<Todo> list;
	
	public ModelList() {
		list = new ArrayList<Todo>();
		list.add(new Todo("1","aaaa",false));
		list.add(new Todo("2","bbbb",false));
		list.add(new Todo("3","cccc",false));
		list.add(new Todo("4","dddd",false));
		list.add(new Todo("5","ffff",false));
	}
	
	@Override
	public List<Todo> get() {
		return list;
	}

	@Override
	public boolean add(Todo todo) {
		return list.add(todo);
	}

	@Override
	public boolean remove(String id) {
		for(int i = 0 ;i<list.size();i++){
			if(id.equals(list.get(i).getId())) {
				list.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public Todo find(String id) {
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).getId().equals(id)) {
				return list.get(i);
			}
		}
		return null;
	}

	@Override
	public boolean update(Todo todo) {
		for(int i = 0;i <list.size(); i++) {
			if(list.get(i).getId().equals(todo.getId())) {
				list.get(i).setTitle(todo.getTitle());
				list.get(i).setCompleted(todo.isCompleted());
				return true;
			}
		}
		return false;
	}

}
