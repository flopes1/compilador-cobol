package model;

import java.util.List;

import util.AST.AST;

public class Term extends AST{

	Fator fator;
	List<Object[]> list;
	
	public Term(Fator fator,List<Object[]> list) {
		this.fator = fator;
		this.list = list;
	
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
