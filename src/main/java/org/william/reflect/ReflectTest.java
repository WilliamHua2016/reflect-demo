package org.william.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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

		user = (User) constructorsArr[1].newInstance(21, "william");
		System.out.println(user);

		user = (User) constructorsArr[2].newInstance("william");
		System.out.println(user);
	}

	/**
	 * 获取某个类的全部属性
	 * 
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		Class<?> clazz = Class.forName("org.william.reflect.User");
		System.out.println("===============本类属性===============");
		// 取得本类的全部属性
		Field[] fields = clazz.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			// 权限修饰符
			int mo = fields[i].getModifiers();
			String priv = Modifier.toString(mo);
			Class<?> type = fields[i].getType();
			System.out.println(priv + " " + type.getName() + " "
					+ fields[i].getName() + ";");
		}

		System.out.println("==========实现的接口或者父类的属性==========");
		// 取得实现的接口或者父类的属性
		Field[] fieldArr = clazz.getFields();

		for (int i = 0; i < fieldArr.length; i++) {
			// 权限修饰符
			int mo = fieldArr[i].getModifiers();
			String priv = Modifier.toString(mo);
			Class<?> type = fieldArr[i].getType();
			System.out.println(priv + " " + type.getName() + " "
					+ fieldArr[i].getName() + ";");
		}
	}

	/**
	 * 获取某个类的全部方法
	 * 
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception {

		Class<?> clazz = Class.forName("org.william.reflect.User");
		Method[] methods = clazz.getDeclaredMethods();

		for (int i = 0; i < methods.length; i++) {
			Class<?> returnType = methods[i].getReturnType();
			Class<?>[] paramArr = methods[i].getParameterTypes();
			int mo = methods[i].getModifiers();
			System.out
					.println("method modifier:" + Modifier.toString(mo) + " ");
			System.out.println("method returnType:" + returnType.getName());
			System.out.println("method name:" + methods[i].getName() + " ");
			for (int j = 0; j < paramArr.length; ++j) {
				System.out.println("arg" + j + ":" + paramArr[j].getName());
				if (j < paramArr.length - 1) {
					System.out.print(",");
				}
			}
			System.out.println();
		}
	}

	/**
	 * 通过反射机制调用某个类的方法
	 */
	@Test
	public void test6() throws Exception {
		Class<?> clazz = Class.forName("org.william.reflect.Reflect");
		Method method = clazz.getMethod("reflect1");
		method.invoke(clazz.newInstance());

		Method method2 = clazz.getMethod("reflect2", int.class, String.class);
		method2.invoke(clazz.newInstance(), 19, "william");
	}

	/**
	 * 通过反射机制操作某个类的属性
	 */
	@Test
	public void test7() throws Exception {
		Class<?> clazz = Class.forName("org.william.reflect.User");
		Object object = clazz.newInstance();
		Field field = clazz.getDeclaredField("name");
		field.setAccessible(true);
		field.set(object, "william");
		System.out.println(field.get(object));
	}
}
