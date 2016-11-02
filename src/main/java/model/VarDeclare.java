package model;

import checker.IVisitor;
import util.AST.AST;

public class VarDeclare extends AST
{

	private Terminal tokenId, tokenBoolOrInt;

	public VarDeclare(Terminal id, Terminal intOrBool)
	{
		this.tokenId = id;
		this.tokenBoolOrInt = intOrBool;
	}

	@Override
	public String toString()
	{

		String string = "";
		string += this.tokenId.getToken().getSpelling().toString() + "\n"
				+ this.tokenBoolOrInt.getToken().getSpelling().toString();

		return string;
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
		return visitor.visitVariablesDeclare(this, object);
	}
}
