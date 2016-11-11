package model;

import checker.IVisitor;
import checker.SemanticException;

public class StatementWhile extends Statement
{

	private While attributeWhile;

	public StatementWhile(While attributeWhile)
	{
		this.setAttributeWhile(attributeWhile);
	}

	@Override
	public String toString()
	{
		String string = "";

		string += this.attributeWhile.toString();

		return string;
	}

	@Override
	public String toString(int level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public While getAttributeWhile()
	{
		return attributeWhile;
	}

	public void setAttributeWhile(While attributeWhile)
	{
		this.attributeWhile = attributeWhile;
	}

	@Override
	public Object visit(IVisitor visitor, Object object) throws SemanticException
	{
		return visitor.visitStatementWhile(this, object);
	}

}
