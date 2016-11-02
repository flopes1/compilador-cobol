package model;

import checker.IVisitor;

public class StatementCallProcedure extends Statement
{

	private CallProcedure callProcedure;

	public StatementCallProcedure(CallProcedure cal)
	{
		this.setCallProcedure(cal);
	}

	@Override
	public String toString()
	{
		String string = "";

		string += this.callProcedure.toString();

		return string;
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
	public Object visit(IVisitor visitor, Object object)
	{
		return visitor.visitStatementCallProcedure(this, object);
	}

}
