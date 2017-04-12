package com.liuqh.netty;
/**
 * JDK8 可以用default关键字，修饰方法，该方法可以有实现
 * @author liuqinghua
 *
 */
public interface aaa {
	default void f(){
		System.out.println("abc");
	}
}
