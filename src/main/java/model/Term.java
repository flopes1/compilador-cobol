package model;

import java.util.ArrayList;
import java.util.List;

import util.AST.AST;

public class Term extends AST{

	List<Terminal> lt = new ArrayList<Terminal>();
	List<Fator> fat = new ArrayList<Fator>();
	
	Term ter,ter2;
	Terminal op;
	
	public Term(List<Terminal> lt,List<Fator> fat) {
		this.lt = lt;
		this.fat = fat;
	
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
