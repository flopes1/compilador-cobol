package model;

public class StatementCallProcedure extends Statement {

	private CallProcedure callProcedure;
	
	public StatementCallProcedure(CallProcedure cal) {
		this.setCallProcedure(cal);
	}
	
	@Override
	public String toString()
	{
		String string = "";
		
		string += this.callProcedure.toString() + "\n";
		
		return string;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	public CallProcedure getCallProcedure()
	{
		return callProcedure;
	}

	public void setCallProcedure(CallProcedure callProcedure)
	{
		this.callProcedure = callProcedure;
	}

}
