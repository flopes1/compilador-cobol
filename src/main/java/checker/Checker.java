package checker;

import model.Attrib;
import model.CallProcedure;
import model.Command;
import model.Condition;
import model.DataDivisionScope;
import model.Expression;
import model.FatorBool;
import model.FatorCallProcedure;
import model.FatorExpression;
import model.FatorId;
import model.FatorNumber;
import model.Operator;
import model.Procedure;
import model.ProcedureDivisionScope;
import model.Program;
import model.StatementAttrib;
import model.StatementBreakContinue;
import model.StatementCallProcedure;
import model.StatementDisplay;
import model.StatementIf;
import model.StatementReturn;
import model.StatementWhile;
import model.Term;
import model.TerminalBool;
import model.TerminalBoolean;
import model.TerminalId;
import model.TerminalInteger;
import model.TerminalNumber;
import model.VarDeclare;
import model.While;

public class Checker implements IVisitor
{

	public Object visitProgram(Program program, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitDataDivisionScope(DataDivisionScope dataDivisionScope, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitProcedureDivisionScope(ProcedureDivisionScope procedureDivisionScope, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitVariablesDeclare(VarDeclare varDeclare, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitProcedure(Procedure procedure, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitCommand(Command command, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitStatementIf(StatementIf statementIf, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitStatementWhile(StatementWhile statementWhile, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitStatementDisplay(StatementDisplay statementDisplay, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitStatementReturn(StatementReturn statementReturn, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitStatementAttribution(StatementAttrib statementAttrib, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitStatementCallProcedure(StatementCallProcedure statementCallProcedure, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitStatementBreak(StatementBreakContinue statementBreakContinue, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitStatementContinue(StatementBreakContinue statementBreakContinue, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitCondition(Condition condition, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitWhile(While whileCommand, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitAttrib(Attrib attrib, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitCallProcedure(CallProcedure callProcedure, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitExpressionSimple(Expression expressionSimple, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitExpressionComparation(Expression expressionComparation, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitOperatorSimple(Operator operator, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitOperatorComposite(Operator operator, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitTermSimple(Term termSimple, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitTermComposite(Term termSimple, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitFatorCallProcedure(FatorCallProcedure fatorCallProcedure, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitFatorBool(FatorBool fatorBool, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitFatorIdentificator(FatorId fatorId, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitFatorNumber(FatorNumber fatorNumber, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitFatorExpression(FatorExpression fatorExpression, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitFatorTypeBoolean(TerminalBoolean tokenBoolean, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitFatorTypeInteger(TerminalInteger tokenInteger, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
