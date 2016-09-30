package model;

import java.util.ArrayList;
import java.util.List;

import util.AST.AST;

public class ProcedureDivisionScope extends AST{
	
	private List<Procedure> procedureList = new ArrayList<Procedure>();
	
	public ProcedureDivisionScope(List<Procedure> procedureList) {
		this.setProcedureList(procedureList);
	}
	
	@Override
	public String toString() {
		String string = "";
		
		for (Procedure procedure : procedureList)
		{
			string += procedure.toString() + "\n";
		}
		
		return string;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Procedure> getProcedureList()
	{
		return procedureList;
	}

	public void setProcedureList(List<Procedure> procedureList)
	{
		this.procedureList = procedureList;
	}
}
