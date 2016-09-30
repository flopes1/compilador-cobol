package model;

import scanner.Token;

public class TokenId extends Terminal {
	
	public TokenId (Token t){
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
