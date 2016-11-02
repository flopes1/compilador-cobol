package model;

import checker.IVisitor;
import checker.SemanticException;
import util.AST.AST;

public class VarDeclare extends AST
{

	private Terminal terminalId, terminalBooleanOrInteger;

	public VarDeclare(Terminal id, Terminal intOrBool)
	{
		this.terminalId = id;
		this.terminalBooleanOrInteger = intOrBool;
	}

	public Terminal getTerminalId()
	{
		return terminalId;
	}

	public Terminal getTerminalBooleanOrInteger()
	{
		return terminalBooleanOrInteger;
	}

	@Override
	public String toString()
	{

		String string = "";
		string += this.terminalId.getToken().getSpelling().toString() + "\n"
				+ this.terminalBooleanOrInteger.getToken().getSpelling().toString();

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
