/**
 * 
 */
package com.tool.GUI;

/**
 * @author Administrator
 *
 */
public class Test {
	static int g=1;

	/**
	 * @return the g
	 */
	public static int getG() {
		return g;
	}

	/**
	 * @param g the g to set
	 */
	public static void setG(int g) {
		Test.g = g;
	}
	
	public static void main(String[] args){
		Test.setG(2);
		Test.setG(3);
		
		System.out.println(Test.getG());
		
	}

}
