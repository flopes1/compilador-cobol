package model;

public class StatementReturn extends Statement{

	Terminal ret,dot;
	Expression exp;
	
	public StatementReturn(Terminal ret,Expression exp, Terminal dot) {
		this.ret=ret;
		this.exp=exp;
		this.dot=dot;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
