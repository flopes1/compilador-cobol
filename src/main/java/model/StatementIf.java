package model;

public class StatementIf extends Statement{

	Terminal iff,thenn,els,endif,dot;
	Condition cond;
	Command com,com2;
	
	public StatementIf(Terminal iff, Condition cond, Terminal thenn, Command com, Terminal els, Command com2, Terminal endif, Terminal dot) {
		this.iff=iff;
		this.cond=cond;
		this.thenn=thenn;
		this.com=com;
		this.els=els;
		this.com2=com2;
		this.endif=endif;
		this.dot=dot;
		
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
