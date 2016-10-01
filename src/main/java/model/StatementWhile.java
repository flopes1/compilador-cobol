package model;

public class StatementWhile extends Statement{

	private While meuWhile;
	
	public StatementWhile(While meuWhile) {
		this.setMeuWhile(meuWhile);
	}
	
	@Override
	public String toString()
	{
		String string = "";
		
		string += this.meuWhile.toString();
		
		return string;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}


	public While getMeuWhile()
	{
		return meuWhile;
	}


	public void setMeuWhile(While meuWhile)
	{
		this.meuWhile = meuWhile;
	}

}
