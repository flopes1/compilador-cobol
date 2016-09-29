package model;

import util.AST.AST;;

public class Program extends AST
{
	Terminal iddiv,dot,progid,dot2,id,dot3,datdiv,dot4,procdiv,dot5,eot;
	DataDivisionScope dds;
	ProcedureDivisionScope pds;
	
	
	public Program(Terminal iddiv,Terminal dot,Terminal progid,Terminal dot2,
			Terminal id,Terminal dot3,Terminal datdiv,DataDivisionScope d,Terminal dot4,Terminal procdiv,
			ProcedureDivisionScope p,Terminal dot5,Terminal eot) {
		
		this.iddiv = iddiv;
		this.dot = dot;
		this.progid = progid;
		this.dot2 = dot2;
		this.id = id;
		this.dot3 = dot3;
		this.datdiv = datdiv;
		this.dot4 = dot4;
		this.procdiv = procdiv;
		this.dot5 = dot5;
		this.eot = eot;
		this.dds = d;
		this.pds = p;
	}
	
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
