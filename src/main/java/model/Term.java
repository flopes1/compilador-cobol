package model;

import java.util.List;

import util.AST.AST;

public class Term extends AST{

	Fator fator;
	
	public Term(Fator fator) {
		this.fator = fator;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
