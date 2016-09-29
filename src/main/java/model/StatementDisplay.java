package model;

public class StatementDisplay extends Statement{

	
	Terminal dis,dot;
	Expression exp;
	
	public StatementDisplay(Terminal dis,Expression exp, Terminal dot) {
		this.dis=dis;
		this.exp=exp;
		this.dot=dot;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
