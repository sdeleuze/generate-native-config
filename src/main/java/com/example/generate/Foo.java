package com.example.generate;

import java.io.Serializable;

public class Foo implements Serializable {

	private final String bar;

	public Foo(String bar) {
		this.bar = bar;
	}

	public String getBar() {
		return bar;
	}
}
