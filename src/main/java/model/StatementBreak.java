package model;

import checker.IVisitor;
import checker.SemanticException;

public class StatementBreak extends Statement {

	
	public StatementBreak() {
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
	public Object visit(IVisitor visitor, Object object) throws SemanticException
	{
		return visitor.visitStatementBreak(this, object);
	}

}
