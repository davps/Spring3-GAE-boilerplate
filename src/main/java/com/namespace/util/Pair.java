package com.namespace.util;

public class Pair<A,B> {
    public final A a;
    public final B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

	public A getA() {
		return a;
	}

	public B getB() {
		return b;
	}

	@Override
	public String toString() {
		return "Pair [a=" + a + ", b=" + b + "]";
	}
    
    
};
