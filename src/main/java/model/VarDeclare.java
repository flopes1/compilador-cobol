package model;

import util.AST.AST;

public class VarDeclare extends AST{
	
	
	private Terminal tokenId, tokenBoolOrInt;
	
	public VarDeclare(Terminal id, Terminal intOrBool) {
		this.tokenId=id;
		this.tokenBoolOrInt = intOrBool;
	}
	
	
	@Override
	public String toString() {
		
		String string = "";
		string += this.tokenId.toString() + "\n" + this.tokenBoolOrInt.toString() + "\n";
		
		return string;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}
}
