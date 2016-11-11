package model;

import checker.IVisitor;
import checker.SemanticException;
import scanner.Token;
import util.AST.AST;

public class TerminalId extends Terminal
{
	
	private AST declaredTerminalIdNode = null;

	public TerminalId(Token t)
	{
		super.setToken(t);
	}

	public AST getDeclaredTerminalIdNode()
	{
		return declaredTerminalIdNode;
	}

	public void setDeclaredTerminalIdNode(AST declaredTerminalIdNode)
	{
		this.declaredTerminalIdNode = declaredTerminalIdNode;
	}

	@Override
	public String toString()
	{
		return super.toString();
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
		return visitor.visitTerminalIdentificator(this, object);
	}

}
