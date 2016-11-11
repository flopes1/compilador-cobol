package model;

import java.util.List;

import checker.IVisitor;
import checker.SemanticException;
import util.AST.AST;

public class Operator extends AST
{

	private List<Term> operatorTermList = null;
	private List<Terminal> operatorTerminalList = null;
	private String type;

	public Operator(List<Terminal> operatorTerminalList, List<Term> operatorTermList)
	{
		this.operatorTermList = operatorTermList;
		this.operatorTerminalList = operatorTerminalList;
	}

	public List<Term> getOperatorTermList()
	{
		return operatorTermList;
	}

	public void setOperatorTermList(List<Term> operatorTermList)
	{
		this.operatorTermList = operatorTermList;
	}

	public List<Terminal> getOperatorTerminalList()
	{
		return operatorTerminalList;
	}

	public void setOperatorTerminalList(List<Terminal> operatorTerminalList)
	{
		this.operatorTerminalList = operatorTerminalList;
	}
	
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		String string = "";

		if (this.operatorTermList != null)
		{
			for (Term term : this.operatorTermList)
			{
				string += term.toString() + "\n";
			}
		}
		if (this.operatorTerminalList != null)
		{
			for (Terminal terminal : this.operatorTerminalList)
			{
				string += terminal.toString() + "\n";
			}
		}
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
		return visitor.visitOperator(this, object);
	}

}
