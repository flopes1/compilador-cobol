package model;

import scanner.Token;
import util.AST.AST;

public abstract class Terminal extends AST {
	
	
	private Token token;

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}
	
	@Override
	public String toString(int level)
	{
		return null;
	}
	
	@Override
	public String toString()
	{
		return this.token != null ? this.token.getSpelling().toString() : "";
	}
	
	

}
