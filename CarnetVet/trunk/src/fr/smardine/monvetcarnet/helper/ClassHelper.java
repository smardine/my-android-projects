package fr.smardine.monvetcarnet.helper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClassHelper {

	public static Object createClassFromName(String p_className) {
		Class<?> clazz = null;
		Object object = null;
		try {
			clazz = Class.forName(p_className);
			Constructor<?> ctor = clazz.getConstructor();
			object = ctor.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object;
	}

	public static Object createInstanceClassFromClass(Class<?> p_class) {

		Object object = null;
		try {
			Constructor<?> ctor = p_class.getConstructor();
			object = ctor.newInstance();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object;
	}

}
