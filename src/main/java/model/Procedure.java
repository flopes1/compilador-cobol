package model;

import java.util.ArrayList;
import java.util.List;

import util.AST.AST;

public class Procedure extends AST{
	
	private Terminal id, sec, dot, begindecl, enddecl, endproc, dot2;
	private List<VarDeclare> lvd = new ArrayList<VarDeclare>();
	private List<Terminal> argumentList;
	private Command com;
	
	public Procedure(Terminal id,Terminal sec, Terminal dot, List<Terminal> terminalList, Terminal begindecl,
			List<VarDeclare> lvd, Terminal enddecl, Command com, Terminal endproc, Terminal dot2) {
		this.id = id;
		this.sec = sec;
		this.dot = dot;
		this.argumentList = terminalList;
		this.begindecl = begindecl;
		this.lvd = lvd;
		this.enddecl = enddecl;
		this.com = com;
		this.endproc = endproc;
		this.dot2 = dot2;
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
