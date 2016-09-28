package model;

import scanner.Token;
import util.AST.AST;

public abstract class Terminal extends AST {
	
	
	private Token t;

	public Token getT() {
		return t;
	}

	public void setT(Token t) {
		this.t = t;
	}
	
	

}
