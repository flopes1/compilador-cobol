package model;

import checker.IVisitor;
import scanner.Token;

public class TerminalOperations extends Terminal
{

	public TerminalOperations(Token token)
	{
		super.setToken(token);
	}

	@Override
	public String toString()
	{
		return super.toString();
	}

	@Override
	public String toString(int level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IVisitor visitor, Object object)
	{
		return visitor.visitTerminalOperations(this, object);
	}

}
