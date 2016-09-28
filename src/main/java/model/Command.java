package model;

import java.util.ArrayList;
import java.util.List;

import util.AST.AST;

public class Command extends AST
{
	List<Statement> ls = new ArrayList<Statement>();
	Terminal tendcom, tdot;
	
	public Command(List<Statement> ls, Terminal tec, Terminal tdot) {
		this.ls = ls;
		this.tendcom = tec;
		this.tdot = tdot;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
