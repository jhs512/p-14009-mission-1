package org.example;

abstract class AbstAbst {
	int abst1 = 0;
	int abst2 = 0;

	abstract void overrideMe();

	int standAlone() {
		return 1;
	}
}

interface InterInter {
	int inter1 = 1; // == final int inter1 = 1;

	public abstract void implementateME();
}

class MyClass extends AbstAbst implements InterInter {
	String field1;
	int field2 = 0;

	MyClass(String s) {
		this.field1 = s;
	}

	// 내부의 static class는 인스턴스도 가능함.
	static class In {
		static int a = 0;
		int b;

		static void stMethod() {

		}

		void instanceMethod() {

		}
	}

	@Override
	void overrideMe() {
		System.out.println("Override Complete");
	}

	// interface의 모든 메소드는 public abstract가 기본적으로 붙음.
	// => 이렇게 되는 이유는 interface라는 말 그대로 해당 클래스에 특정 인터페이스를 구현하는 것이기 때문.
	@Override
	public void implementateME() {
		System.out.println("Implematation Complete");
	}
}

public class WhiteBoard {

	static void method() {
	}
}
