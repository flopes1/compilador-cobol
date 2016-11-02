package model;

import checker.IVisitor;

public class StatementContinue extends Statement {

	
	public StatementContinue() {
	}
	
	@Override
	public String toString()
	{
		String string = "";
		
		return string;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IVisitor visitor, Object object)
	{
		return visitor.visitStatementContinue(this, object);
	}

}
