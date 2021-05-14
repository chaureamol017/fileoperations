package com.myproject.test.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
 
public class InvokeGetterSetterMain {
	public static void main(String[] args) {
		InvokeGetterSetterMain igsm=new InvokeGetterSetterMain();
		Employee emp1=new Employee();
		igsm.invokeGetter(emp1, "available");
		igsm.invokeGetter(emp1, "New");

		igsm.invokeSetter(emp1, "name", "John");
		igsm.invokeSetter(emp1, "age", 25);
		igsm.invokeSetter(emp1, "available", true);
		igsm.invokeSetter(emp1, "New", true);
 
		igsm.invokeGetter(emp1, "name");
		igsm.invokeGetter(emp1, "age");
		igsm.invokeGetter(emp1, "available");
		igsm.invokeGetter(emp1, "New");
 
	}
 
	public void invokeSetter(Object obj, String propertyName, Object variableValue) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(propertyName, obj.getClass());
			Method setter = pd.getWriteMethod();
			try {
				setter.invoke(obj,variableValue);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
 
	}
 
	public void invokeGetter(Object obj, String variableName) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(variableName, obj.getClass());
			Method getter = pd.getReadMethod();
			Object f = getter.invoke(obj);
			System.out.println(f);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
			e.printStackTrace();
		}
	}
}