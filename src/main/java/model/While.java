package model;

import util.AST.AST;

public class While extends AST{

	
	Terminal until;
	Condition con;
	Command comand;
	
	public While(Terminal until, Condition con, Command comand) {
		this.until=until;
		this.con=con;
		this.comand=comand;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
