package model;

public class StatementReturn extends Statement{

	private Expression expression;
	
	public StatementReturn(Expression expression) {
		this.setExpression(expression);
	}
	
	@Override
	public String toString()
	{
		String string = "";
		
		string += this.expression.toString() + "\n";
		
		return string;
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
