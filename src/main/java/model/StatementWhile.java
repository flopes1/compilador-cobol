package model;

public class StatementWhile extends Statement{

	Terminal endwhi,dot;
	While whi;
	
	public StatementWhile(While whi, Terminal endwhi, Terminal dot) {
		this.whi=whi;
		this.endwhi=endwhi;
		this.dot=dot;
	}
	
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
