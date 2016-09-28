package model;

import util.AST.AST;;

public class Program extends AST
{
	private DataDivisionScope dds;
	private ProcedureDivisionScope pds;
	
	
	public Program(DataDivisionScope d, ProcedureDivisionScope p) {
		this.dds = d;
		this.pds = p;
	}
	
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
