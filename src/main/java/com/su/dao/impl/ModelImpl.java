package com.su.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.su.bean.Todo;
import com.su.dao.DBAccess;
import com.su.dao.Model;

public class ModelImpl implements Model{

	public ModelImpl() {
		
	}
	
	public List<Todo> get(){
		Connection conn = null;
		List<Todo> todos = new ArrayList<Todo>();
		try {
			conn = DBAccess.getConnection();
			String sql="select * from todos";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs=  stmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String title = rs.getString("title");
				String completed = rs.getString("completed");
				Todo todo = new Todo(id, title, Boolean.valueOf(completed));
				todos.add(todo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return todos;
	}
	
	public boolean add(Todo todo) {
		if(todo == null) {
			return false;
		}
		Connection conn = null;
		boolean res = false;
		try {
			conn = DBAccess.getConnection();
			String sql="insert into todos values(?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, todo.getId());
			stmt.setString(2, todo.getTitle());
			stmt.setString(3, String.valueOf(todo.isCompleted()));
			res = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public boolean remove(String id) {
		if(id == null) {
			return false;
		}
		Connection conn = null;
		boolean res = false;
		try {
			conn = DBAccess.getConnection();
			String sql="DELETE FROM todos WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			res = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public Todo find(String id) {
		if(id == null) {
			return null;
		}
		Connection conn = null;
		Todo todo = null;
		try {
			conn = DBAccess.getConnection();
			String sql="SELECT * FROM todos WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			todo = new Todo();
			while(rs.next()) {
				todo.setId(rs.getString("id"));
				todo.setTitle(rs.getString("title"));
				todo.setCompleted(Boolean.valueOf(rs.getString("completed")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return todo;
	}
	
	public boolean update(Todo todo) {
		Connection conn = null;
		boolean res = false;
		try {
			conn = DBAccess.getConnection();
			String sql="UPDATE todos SET title=?,completed = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, todo.getTitle());
			stmt.setString(2, String.valueOf(todo.isCompleted()));
			stmt.setString(3, todo.getId());
			res = stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}
