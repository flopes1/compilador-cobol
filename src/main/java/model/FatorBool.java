package model;

import checker.IVisitor;

public class FatorBool extends Fator
{

	private Terminal tokenBool;

	public FatorBool(Terminal tokenBool)
	{
		this.setTokenBool(tokenBool);
	}

	@Override
	public String toString()
	{
		return this.tokenBool.toString();
	}

	@Override
	public String toString(int level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Terminal getTokenBool()
	{
		return tokenBool;
	}

	public void setTokenBool(Terminal tokenBool)
	{
		this.tokenBool = tokenBool;
	}

	@Override
	public Object visit(IVisitor visitor, Object object)
	{
		return visitor.visitFatorBool(this, object);
	}

}
