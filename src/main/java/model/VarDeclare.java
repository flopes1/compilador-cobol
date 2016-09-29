package model;

import util.AST.AST;

public class VarDeclare extends AST{
	
	
	Terminal id,pic,intOrBool,dot;
	
	public VarDeclare(Terminal id, Terminal pic, Terminal intOrBool, Terminal dot) {
		this.id=id;
		this.pic=pic;
		this.intOrBool = intOrBool;
		this.dot=dot;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}
}
