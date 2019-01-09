package com.su.controller;


import java.lang.reflect.Method;
import org.junit.Test;
import com.su.util.Mapping;

public class MyControllerTest {

	//MyController con = new MyController();
	
	@Test
	public void test() {
		/*Method[] a = con.getClass().getDeclaredMethods();
		for( Method b:a) {
			System.out.println(b.toString());
			Mapping ant = b.getDeclaredAnnotation(Mapping.class);
			System.out.println(ant.value());
			try {
				Object[] agrs = new Object[1];
				agrs[0] = "mm";
				b.invoke(con, agrs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		String str = "777/add.do";
		String sub = str.substring(str.lastIndexOf("/"));
		System.out.println(sub.contains("add.do"));
	}

}
