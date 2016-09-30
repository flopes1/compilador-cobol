package model;

import java.util.List;

import util.AST.AST;

public class Operator extends AST
{

	private List<Term> operatorTermList = null;
	private List<Terminal> operatorTerminalList = null;

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


	@Override
	public String toString()
	{
		String string = "";

		for (Term term : this.operatorTermList)
		{
			string += term.toString() + "\n";
		}

		for (Terminal terminal : this.operatorTerminalList)
		{
			string += terminal.toString() + "\n";
		}

		return string;
	}

	@Override
	public String toString(int level)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
