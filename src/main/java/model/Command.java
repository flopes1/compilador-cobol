package model;

import java.util.ArrayList;
import java.util.List;

import util.AST.AST;

public class Command extends AST
{
	private List<Statement> statementList = new ArrayList<Statement>();
	
	public Command(List<Statement> statementList) {
		this.setStatementList(statementList);
	}
	
	@Override
	public String toString()
	{
		String string = "";
		
		for (Statement statement : statementList)
		{
			string += statement.toString() + "\n";
		}
		
		return string;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Statement> getStatementList()
	{
		return statementList;
	}

	public void setStatementList(List<Statement> statementList)
	{
		this.statementList = statementList;
	}
	
}
