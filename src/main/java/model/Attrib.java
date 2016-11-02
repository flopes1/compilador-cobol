package model;

import checker.IVisitor;
import util.AST.AST;

public class Attrib extends AST{
	
	//teste
	
	private Expression expression;
	private Terminal tokenId;
	
	public Attrib(Expression expression, Terminal tokenId) {
		this.setExpression(expression);
		this.setTokenId(tokenId);
	}
	
	@Override
	public String toString()
	{
		String string = "";
		
		string += this.expression.toString() +"\n"
				+ this.tokenId.toString();
		
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


	public Terminal getTokenId()
	{
		return tokenId;
	}


	public void setTokenId(Terminal tokenId)
	{
		this.tokenId = tokenId;
	}

	@Override
	public Object visit(IVisitor visitor, Object object)
	{
		return visitor.visitAttrib(this, object);
	}

}
