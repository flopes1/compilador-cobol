package model;

import java.util.List;
import util.AST.AST;

public class Operator extends AST{
	
	Term termo;
	List<Object[]> list;
	
	public Operator(Term term,List<Object[]> ltermo) {
		this.termo = term;
		this.list = ltermo;
	
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
