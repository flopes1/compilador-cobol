package model;

import util.AST.AST;

public class CallProcedure extends AST{
	
	Terminal tper, tid, tusing, tid2;
	
	public CallProcedure(Terminal tper, Terminal tid, Terminal tusing, Terminal tid2) {
		this.tper = tper;
		this.tid = tid;
		this.tusing = tusing;
		this.tid2 = tid2;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
