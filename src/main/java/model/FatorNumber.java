package model;

import checker.IVisitor;

public class FatorNumber extends Fator
{

	private Terminal tokenNumber;

	public FatorNumber(Terminal tokenNumber)
	{
		this.setTokenNumber(tokenNumber);
	}

	@Override
	public String toString()
	{
		return this.tokenNumber.toString();
	}

	@Override
	public String toString(int level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Terminal getTokenNumber()
	{
		return tokenNumber;
	}

	public void setTokenNumber(Terminal tokenNumber)
	{
		this.tokenNumber = tokenNumber;
	}

	@Override
	public Object visit(IVisitor visitor, Object object)
	{
		return visitor.visitFatorNumber(this, object);
	}

}
