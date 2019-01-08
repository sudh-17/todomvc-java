package com.su.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;

public class DBAccessTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetConnection() {
		try {
			Connection con = DBAccess.getConnection();
			System.out.println(con == null);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testCloseAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteSQL() {
		Connection conn=DBAccess.getConnection();
        String sql="select * from pet";
        java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs=  stmt.executeQuery();
        
	}

}
