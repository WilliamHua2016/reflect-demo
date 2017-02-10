package org.william.reflect;

import java.lang.reflect.Constructor;

import org.junit.Test;

public class ReflectTest {

	/**
	 * 通过一个对象获得完整的包名和类名
	 */
	@Test
	public void test() {
		Reflect reflect = new Reflect();
		System.out.println(reflect.getClass().getName());
	}

	/**
	 * 实例化Class类对象
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public void test1() throws ClassNotFoundException {
		Class<?> class1 = null;
		Class<?> class2 = null;
		Class<?> class3 = null;

		class1 = Class.forName("org.william.reflect.Reflect");
		class2 = new Reflect().getClass();
		class3 = Reflect.class;
		System.out.println("类名称   " + class1);
		System.out.println("类名称   " + class2);
		System.out.println("类名称   " + class3);
	}

	/**
	 * 获取一个对象的父类与实现的接口
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public void test2() throws ClassNotFoundException {

		Class<?> clazz = Class.forName("org.william.reflect.Reflect");
		Class<?> parentClass = clazz.getSuperclass();
		System.out.println("class 的父类为：" + parentClass.getName());

		Class<?>[] intes = clazz.getInterfaces();
		System.out.println("clazz实现的接口有：");
		for (int i = 0; i < intes.length; i++) {
			System.out.println((i + 1) + "：" + intes[i].getName());
		}
	}

	/**
	 * 通过反射机制实例化一个类的对象
	 * 
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		Class<?> clazz = Class.forName("org.william.reflect.User");
		// 第一种方法，实例化默认构造方法，调用set赋值
		User user = (User) clazz.newInstance();
		user.setAge(20);
		user.setName("william");
		System.out.println(user);
		
		 // 第二种方法 取得全部的构造函数 使用构造函数赋值
		Constructor<?>[] constructorsArr = clazz.getConstructors();
		  // 查看每个构造方法需要的参数
        for (int i = 0; i < constructorsArr.length; i++) {
            Class<?> clazzs[] = constructorsArr[i].getParameterTypes();
            System.out.print("cons[" + i + "] (");
            for (int j = 0; j < clazzs.length; j++) {
                if (j == clazzs.length - 1)
                    System.out.print(clazzs[j].getName());
                else
                    System.out.print(clazzs[j].getName() + ",");
            }
            System.out.println(")");
        }
        
        user = (User) constructorsArr[1].newInstance(21,"william");
        System.out.println(user);
        
        user = (User)constructorsArr[2].newInstance("william");
        System.out.println(user);
	}

}
