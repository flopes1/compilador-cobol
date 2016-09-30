package model;

import scanner.Token;

public class TokenPlus extends Terminal {
	
	public TokenPlus (Token t){
		super.setT(t);
	}
	
	@Override
	public String toString()
	{
		return super.getT().getSpelling().toString() + "\n";
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
