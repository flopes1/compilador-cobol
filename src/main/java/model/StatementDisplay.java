package model;

import checker.IVisitor;

public class StatementDisplay extends Statement
{

	private Expression expression;

	public StatementDisplay(Expression expression)
	{
		this.setExpression(expression);
	}

	@Override
	public String toString()
	{
		String string = "";

		string += this.expression.toString();

		return string;
	}

	@Override
	public String toString(int level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Expression getExpression()
	{
		return expression;
	}

	public void setExpression(Expression expression)
	{
		this.expression = expression;
	}

	@Override
	public Object visit(IVisitor visitor, Object object)
	{
		return visitor.visitStatementDisplay(this, object);
	}

}
