package model;

import java.util.ArrayList;
import java.util.List;

import util.AST.AST;

public class Procedure extends AST
{

	private Terminal tokenId;
	private List<VarDeclare> varDeclareList = new ArrayList<VarDeclare>();
	private List<Terminal> terminalList;
	private Command command;

	public Procedure(Terminal tokenId, List<Terminal> terminalList, List<VarDeclare> varDeclareList, Command command)
	{
		this.setTokenId(tokenId);
		this.setVarDeclareList(varDeclareList);
		this.setTerminalList(terminalList);
		this.setCommand(command);
	}

	@Override
	public String toString()
	{
		String string = "";

		string += this.tokenId.toString() + "\n";

		if (terminalList != null)
		{
			for (Terminal terminal : this.terminalList)
			{
				string += terminal.toString() + "\n";
			}
		}
		if (varDeclareList != null)
		{
			for (VarDeclare varDeclare : this.varDeclareList)
			{
				string += varDeclare.toString() + "\n";
			}
		}
		string += this.command != null ? this.command.toString() : "";

		return string;
	}

	@Override
	public String toString(int level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Command getCommand()
	{
		return command;
	}

	public void setCommand(Command command)
	{
		this.command = command;
	}

	public List<Terminal> getTerminalList()
	{
		return terminalList;
	}

	public void setTerminalList(List<Terminal> terminalList)
	{
		this.terminalList = terminalList;
	}

	public List<VarDeclare> getVarDeclareList()
	{
		return varDeclareList;
	}

	public void setVarDeclareList(List<VarDeclare> varDeclareList)
	{
		this.varDeclareList = varDeclareList;
	}

	public Terminal getTokenId()
	{
		return tokenId;
	}

	public void setTokenId(Terminal tokenId)
	{
		this.tokenId = tokenId;
	}
}
