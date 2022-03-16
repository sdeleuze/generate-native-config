package com.example.generate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		System.out.println("Invoked method: " + method.getName());
		return "";
	}
}
