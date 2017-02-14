package org.william.reflect;

import java.io.Serializable;

/**
 * 
 * @author huawai
 * 
 */
public class Reflect implements Serializable {

	private static final long serialVersionUID = 1L;

	public void reflect1() {
		System.out.println("Java 反射机制 - 调用某个类的方法1.");
	}

	public void reflect2(int age, String name) {
		System.out.println("Java 反射机制 - 调用某个类的方法2.");
		System.out.println("age -> " + age + ". name -> " + name);
	}

}
