package model;

public class FatorId extends Fator {
	
	private Terminal tokenId;
	
	public FatorId(Terminal tokenId) {
		this.setTokenId(tokenId);
	}
	
	@Override
	public String toString()
	{
		return this.tokenId.toString() + "\n";
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	public Terminal getTokenId()
	{
		return tokenId;
	}

	public void setTokenId(Terminal tokenId)
	{
		this.tokenId = tokenId;
	}

}
