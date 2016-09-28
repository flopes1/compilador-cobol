package model;

import util.AST.AST;

public class Expression extends AST{
	
	Operator op1, op2;
	Terminal tcomp;
	
	public Expression(Operator op1, Terminal tcomp, Operator op2) {
		this.op1 = op1;
		this.tcomp = tcomp;
		this.op2 = op2;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
