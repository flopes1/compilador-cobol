package model;

import java.util.List;

import util.AST.AST;

public class CallProcedure extends AST{
	
	private List<Terminal> terminalList;
	
	public CallProcedure(List<Terminal> terminalList) {
		this.setTerminalList(terminalList);
	}
	
	@Override
	public String toString()
	{
		String string = "";
		
		for (Terminal terminal : this.terminalList)
		{
			string += terminal.toString() + "\n";
		}
		
		return string;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Terminal> getTerminalList()
	{
		return terminalList;
	}

	public void setTerminalList(List<Terminal> terminalList)
	{
		this.terminalList = terminalList;
	}

}
