package model;

public class FatorExpression extends Fator {
	
	Terminal tlp, trp;
	Expression e;
	
	public FatorExpression(Terminal tlp, Expression e, Terminal trp) {
		this.tlp = tlp;
		this.e = e;
		this.trp = trp;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
