package com.example.generate;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

public class NativeApplication {

	private static String RESOURCE_NAME =  "/test.txt";

	private static String TYPE_NAME = "java.io.InputStream";

	private static final Class<?>[] interfacesArrayPostInitialized = new Class<?>[1];

	public static void main(String[] args) throws ClassNotFoundException {
		// Test resource-config.json
		URL resource = NativeApplication.class.getResource(RESOURCE_NAME);
		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			System.out.println("File exists");
			InputStream inputStream = NativeApplication.class.getResourceAsStream(RESOURCE_NAME);
			String result = new BufferedReader(new InputStreamReader(inputStream))
					.lines().collect(Collectors.joining("\n"));
			System.out.println(result);
		}

		// Test reflect-config.json
		System.out.println("Constructor count for " + TYPE_NAME + " : " + Class.forName(TYPE_NAME).getDeclaredConstructors().length);

		// Test serialization-config.json
		try {
			FileOutputStream fileOut =
					new FileOutputStream("/tmp/string.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(new Foo("bar"));
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in /tmp/string.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}

		// Test proxy-config.json
		updateArray();
		Map proxyInstance = (Map) Proxy.newProxyInstance(
				DynamicInvocationHandler.class.getClassLoader(),
				interfacesArrayPostInitialized,
				new DynamicInvocationHandler());
		proxyInstance.put("hello", "world");
	}

	private static void updateArray() {
		interfacesArrayPostInitialized[0] = java.util.Map.class;
	}

}
