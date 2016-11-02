package model;

import checker.IVisitor;
import scanner.Token;

public class TerminalBoolean extends Terminal
{

	public TerminalBoolean(Token t)
	{
		super.setToken(t);
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
		return visitor.visitTerminalTypeBoolean(this, object);
	}

}