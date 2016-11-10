package model;

import checker.IVisitor;
import checker.SemanticException;

public class FatorId extends Fator
{

	private Terminal tokenId;

	public FatorId(Terminal tokenId)
	{
		this.setTokenId(tokenId);
	}

	@Override
	public String toString()
	{
		return this.tokenId.toString();
	}

	@Override
	public String toString(int level)
	{
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

	@Override
	public Object visit(IVisitor visitor, Object object) throws SemanticException
	{
		return visitor.visitFatorIdentificator(this, object);
	}

}
