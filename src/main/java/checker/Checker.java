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
import model.StatementBreak;
import model.StatementCallProcedure;
import model.StatementContinue;
import model.StatementDisplay;
import model.StatementIf;
import model.StatementReturn;
import model.StatementWhile;
import model.Term;
import model.TerminalBool;
import model.TerminalBoolean;
import model.TerminalComp;
import model.TerminalId;
import model.TerminalInteger;
import model.TerminalNumber;
import model.TerminalOperations;
import model.VarDeclare;
import model.While;
import util.symbolsTable.IdentificationTable;

public class Checker implements IVisitor
{

	private IdentificationTable identificationTable = new IdentificationTable();

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

	public Object visitVariablesDeclare(VarDeclare varDeclare, Object object) throws SemanticException
	{
		if (this.identificationTable.retrieve(varDeclare.getTerminalId().getToken().getSpelling()) != null)
		{
			throw new SemanticException(
					"Identifier " + varDeclare.getTerminalId().getToken().getSpelling() + " already defined.");
		}

		this.identificationTable.enter(varDeclare.getTerminalId().getToken().getSpelling(), varDeclare);
		varDeclare.getTerminalBooleanOrInteger().visit(this, object);
		varDeclare.getTerminalId().visit(this, object);

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

	public Object visitStatementBreak(StatementBreak statementBreak, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitStatementContinue(StatementContinue statementContinue, Object object)
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

	public Object visitExpression(Expression expression, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitOperator(Operator operator, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitTerm(Term term, Object object)
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

	public Object visitFatorIdentificator(FatorId fatorId, Object object) throws SemanticException
	{
		return fatorId.getTokenId().visit(this, object);
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

	public Object visitTerminalTypeBoolean(TerminalBoolean terminalBoolean, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitTerminalBool(TerminalBool terminalBool, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitTerminalTypeInteger(TerminalInteger terminalInteger, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitTerminalNumber(TerminalNumber terminalNumber, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitTerminalIdentificator(TerminalId terminalId, Object object) throws SemanticException
	{

		Object varDeclaration = this.identificationTable.retrieve(terminalId.getToken().getSpelling());
		
		if(varDeclaration == null)
		{
			throw new SemanticException(
					"The Identifier " + terminalId.getToken().getSpelling() + " is not defined.");
		}
		
		VarDeclare declarationCommand = (VarDeclare) varDeclaration;
		return declarationCommand.getTerminalBooleanOrInteger().getToken().getSpelling();
	}

	public Object visitTerminalComparation(TerminalComp terminalComp, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitTerminalOperations(TerminalOperations terminalOperations, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
