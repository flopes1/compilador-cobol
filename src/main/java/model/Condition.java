package model;

import util.AST.AST;

public class Condition extends AST{
	
	private Expression expression;
	
	public Condition(Expression expression) {
		this.setExpression(expression);
	}
	
	@Override
	public String toString()
	{
		return this.expression.toString();
	}
	
	@Override
	public String toString(int level) {
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
}
