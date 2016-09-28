package model;

import java.util.ArrayList;
import java.util.List;

import util.AST.AST;

public class DataDivisionScope extends AST{
	
	List<VarDeclare> lvd = new ArrayList<VarDeclare>();
	Terminal texit, tdot;
	
	public DataDivisionScope(List<VarDeclare> lvd, Terminal texit, Terminal tdot) {
		this.lvd = lvd;
		this.texit = texit;
		this.tdot = tdot;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}
}
