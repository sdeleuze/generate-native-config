package com.example.generate;


import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.context.nativex.FileNativeConfigurationGenerator;
import org.springframework.context.nativex.NativeConfigurationGenerator;

public class GenerateNativeConfigApplication {

	public static void main(String[] args) {
		RuntimeHints runtimeHints = new RuntimeHints();
		runtimeHints.resources().registerPattern("test.txt");
		runtimeHints.javaSerialization().registerType(Foo.class).registerType(String.class);
		runtimeHints.proxies().registerJdkProxy(Map.class);
		runtimeHints.reflection().registerType(InputStream.class, builder -> builder.withMembers(MemberCategory.INVOKE_DECLARED_CONSTRUCTORS));

		NativeConfigurationGenerator generator = new FileNativeConfigurationGenerator(Path.of("target/classes"));
		generator.generate(runtimeHints);

	}

}
