package model;

import java.util.List;

import checker.IVisitor;
import util.AST.AST;

public class Term extends AST
{

	private List<Fator> termfatorList = null;
	private List<Terminal> termOperatorList = null;

	public Term(List<Fator> fatorList, List<Terminal> multiOrDivOperatorList)
	{
		this.termOperatorList = multiOrDivOperatorList;
		this.termfatorList = fatorList;
	}

	public List<Fator> getTermfatorList()
	{
		return termfatorList;
	}

	public void setTermfatorList(List<Fator> termfatorList)
	{
		this.termfatorList = termfatorList;
	}

	public List<Terminal> getTermOperatorList()
	{
		return termOperatorList;
	}

	public void setTermOperatorList(List<Terminal> termOperatorList)
	{
		this.termOperatorList = termOperatorList;
	}

	@Override
	public String toString()
	{
		String string = "";

		if (termOperatorList != null)
		{
			for (Terminal terminal : termOperatorList)
			{
				string += terminal.toString() + "\n";
			}
		}
		if (this.termfatorList != null)
		{
			for (Fator fator : this.termfatorList)
			{
				string += fator.toString() + "\n";
			}
		}
		return string;
	}

	@Override
	public String toString(int level)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IVisitor visitor, Object object)
	{
		return visitor.visitTerm(this, object);
	}

}
