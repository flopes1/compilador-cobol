package model;

import java.util.ArrayList;
import java.util.List;

import util.AST.AST;

public class ProcedureDivisionScope extends AST{
	
	List<Procedure> lp = new ArrayList<Procedure>();
	Terminal exit,dot;
	
	public ProcedureDivisionScope(List<Procedure> lp, Terminal exit, Terminal dot) {
		this.lp=lp;
		this.exit=exit;
		this.dot=dot;
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}
}
