package model;

import java.util.List;
import java.util.ArrayList;

import util.AST.AST;

public class Operator extends AST{
	
	List<Terminal> lt = new ArrayList<Terminal>();
	List<Term> lterm = new ArrayList<Term>();
	
	Term ter,ter2;
	Terminal op;
	
	public Operator(List<Terminal> lt,List<Term> lterm) {
		this.lt = lt;
		this.lterm = lterm;
	
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
