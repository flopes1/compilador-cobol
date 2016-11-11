package model;

import java.util.ArrayList;
import java.util.List;

import checker.IVisitor;
import checker.SemanticException;
import util.AST.AST;

public class Procedure extends AST
{

	private Terminal procedureIdentificator, procedureType;
	private List<VarDeclare> varDeclareList = new ArrayList<VarDeclare>();
	private List<VarDeclare> parametersList;
	private Command command;
	private Boolean hasReturn;

	public Procedure(Terminal procedureType, Terminal tokenId, List<VarDeclare> parametersList,
			List<VarDeclare> varDeclareList, Command command)
	{
		this.setProcedureType(procedureType);
		this.setTokenId(tokenId);
		this.setVarDeclareList(varDeclareList);
		this.setTerminalList(parametersList);
		this.setCommand(command);
	}

	public Boolean getHasReturn()
	{
		return hasReturn;
	}

	public void setHasReturn(Boolean hasReturn)
	{
		this.hasReturn = hasReturn;
	}

	@Override
	public String toString()
	{
		String string = "";

		string += this.procedureIdentificator.toString() + "\n";

		if (parametersList != null)
		{
			for (VarDeclare terminal : this.parametersList)
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

	public List<VarDeclare> getProcedureParametersList()
	{
		return parametersList;
	}

	public void setTerminalList(List<VarDeclare> procedureParameterList)
	{
		this.parametersList = procedureParameterList;
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
		return procedureIdentificator;
	}

	public void setTokenId(Terminal tokenId)
	{
		this.procedureIdentificator = tokenId;
	}

	public Terminal getProcedureType()
	{
		return procedureType;
	}

	public void setProcedureType(Terminal procedureType)
	{
		this.procedureType = procedureType;
	}

	@Override
	public Object visit(IVisitor visitor, Object object) throws SemanticException
	{
		return visitor.visitProcedure(this, object);
	}

}
