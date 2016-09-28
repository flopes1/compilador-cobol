package model;

import util.AST.AST;

public class Attrib extends AST{
	
	Expression e;
	Terminal tm, tto, tid, tdot;
	
	public Attrib(Terminal tm, Expression e, Terminal tto, Terminal tid, Terminal tdot) {
		this.tm = tm;
		this.e = e;
		this.tto = tto;
		this.tid = tid;
		this.tdot = tdot;
	}
	
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
