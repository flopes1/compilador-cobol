package model;

import checker.IVisitor;
import checker.SemanticException;
import util.AST.AST;

public class VarDeclare extends AST
{

	private Terminal tokenId, tokenBoolOrInt;

	public VarDeclare(Terminal id, Terminal intOrBool)
	{
		this.tokenId = id;
		this.tokenBoolOrInt = intOrBool;
	}
	
	public Terminal getTokenId()
	{
		return tokenId;
	}

	public Terminal getTokenBoolOrInt()
	{
		return tokenBoolOrInt;
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
	public Object visit(IVisitor visitor, Object object) throws SemanticException
	{
		return visitor.visitVariablesDeclare(this, object);
	}
}
