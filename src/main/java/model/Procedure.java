package model;

import java.util.ArrayList;
import java.util.List;

import util.AST.AST;

public class Procedure extends AST{
	
	Terminal id, sec, dot, boolint,id2, bebindecl, enddecl,endproc,dot2;
	List<VarDeclare> lvd = new ArrayList<VarDeclare>();
	Command com; 
	
	public Procedure(Terminal id,Terminal sec,Terminal dot,Terminal boolint,Terminal id2,Terminal bebindecl,List<VarDeclare> lvd,Terminal enddecl,Command com,Terminal endproc,Terminal dot2) {
		this.id=id;
		this.id2=id2;
		this.sec=sec;
		this.dot=dot;
		this.dot2=dot2;
		this.boolint = boolint;
		this.bebindecl = bebindecl;
		this.bebindecl=enddecl;
		this.endproc=endproc;
		
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
