package model;

import checker.IVisitor;
import util.AST.AST;

public class Expression extends AST{
	
	private Operator mandatoryOperator, optionalOperator;
	private Terminal tokenComparator;
	
	public Expression(Operator mandatoryOperator, Terminal tokenComparator, Operator optionalOperator) {
		this.setMandatoryOperator(mandatoryOperator);
		this.setTokenComparator(tokenComparator);
		this.setOptionalOperator(optionalOperator);
	}
	
	@Override
	public String toString()
	{
		String string = "";
		
		string += this.mandatoryOperator != null ? this.mandatoryOperator.toString() : "" + "\n"
				+ this.tokenComparator != null ? this.tokenComparator.toString() : "" + "\n"
				+ this.optionalOperator != null ? this.optionalOperator.toString() : "";
		
		return string;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	public Operator getMandatoryOperator()
	{
		return mandatoryOperator;
	}

	public void setMandatoryOperator(Operator mandatoryOperator)
	{
		this.mandatoryOperator = mandatoryOperator;
	}

	public Operator getOptionalOperator()
	{
		return optionalOperator;
	}

	public void setOptionalOperator(Operator optionalOperator)
	{
		this.optionalOperator = optionalOperator;
	}

	public Terminal getTokenComparator()
	{
		return tokenComparator;
	}

	public void setTokenComparator(Terminal tokenComparator)
	{
		this.tokenComparator = tokenComparator;
	}

	@Override
	public Object visit(IVisitor visitor, Object object)
	{
		return visitor.visitExpression(this, object);
	}

}
