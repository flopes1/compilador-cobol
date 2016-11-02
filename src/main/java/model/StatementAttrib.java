package model;

import checker.IVisitor;

public class StatementAttrib extends Statement
{

	private Attrib attrib;

	public StatementAttrib(Attrib attrib)
	{
		this.setAttrib(attrib);
	}

	@Override
	public String toString(int level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Attrib getAttrib()
	{
		return attrib;
	}

	public void setAttrib(Attrib at)
	{
		this.attrib = at;
	}

	@Override
	public String toString()
	{
		return this.attrib.toString();
	}

	@Override
	public Object visit(IVisitor visitor, Object object)
	{
		return visitor.visitStatementAttribution(this, object);
	}

}
