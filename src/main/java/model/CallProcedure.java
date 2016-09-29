package model;

import java.util.List;

import util.AST.AST;

public class CallProcedure extends AST{
	
	Terminal tper, tid;
	List<Terminal[]> lt;
	
	public CallProcedure(Terminal tper, Terminal tid, List<Terminal[]> lt) {
		this.lt = lt;
		this.tper = tper;
		this.tid = tid;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
