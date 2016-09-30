package model;

import util.AST.AST;;

public class Program extends AST
{
	private Terminal tokenId;
	private DataDivisionScope dataDivisionScope;
	private ProcedureDivisionScope procedureDivisionScope;
	
	public Program(Terminal tokenId, DataDivisionScope dataDivisionScope, ProcedureDivisionScope procedureDivisionScope)
	{
		this.tokenId = tokenId;
		this.dataDivisionScope = dataDivisionScope;
		this.procedureDivisionScope = procedureDivisionScope;
	}

	@Override
	public String toString()
	{
		String string = "";
		
		string += this.tokenId.toString() + "\n" +
		          this.dataDivisionScope.toString() + "\n" +
		          this.procedureDivisionScope.toString() + "\n";
		
		return string;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
