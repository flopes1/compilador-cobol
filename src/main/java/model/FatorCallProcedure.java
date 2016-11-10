package model;

import checker.IVisitor;
import checker.SemanticException;

public class FatorCallProcedure extends Fator
{

	private CallProcedure callProcedure;

	public FatorCallProcedure(CallProcedure callProcedure)
	{
		this.setCallProcedure(callProcedure);
	}

	@Override
	public String toString()
	{
		return this.callProcedure.toString();
	}

	@Override
	public String toString(int level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public CallProcedure getCallProcedure()
	{
		return callProcedure;
	}

	public void setCallProcedure(CallProcedure callProcedure)
	{
		this.callProcedure = callProcedure;
	}

	@Override
	public Object visit(IVisitor visitor, Object object) throws SemanticException
	{
		return visitor.visitFatorCallProcedure(this, object);
	}

}
