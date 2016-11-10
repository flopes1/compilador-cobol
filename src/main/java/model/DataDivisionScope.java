package model;

import java.util.ArrayList;
import java.util.List;

import checker.IVisitor;
import checker.SemanticException;
import util.AST.AST;

public class DataDivisionScope extends AST
{

	private List<VarDeclare> varDeclareList = new ArrayList<VarDeclare>();

	public DataDivisionScope(List<VarDeclare> varDeclareList)
	{
		this.varDeclareList = varDeclareList;
	}

	@Override
	public String toString()
	{
		String string = "";

		if (varDeclareList != null)
		{
			for (VarDeclare varDeclare : this.varDeclareList)
			{
				string += varDeclare.toString() + "\n";
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
		return visitor.visitDataDivisionScope(this, object);
	}
	public List<VarDeclare> getVarDeclareList()
	{
		return varDeclareList;
	}
	
	public void setVarDeclareList(List<VarDeclare> varDeclareList)
	{
		this.varDeclareList = varDeclareList;
	}
	
}
