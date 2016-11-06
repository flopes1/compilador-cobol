package checker;

import java.util.List;

import model.Attrib;
import model.CallProcedure;
import model.Command;
import model.Condition;
import model.DataDivisionScope;
import model.Expression;
import model.Fator;
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

	public Object visitProgram(Program program, Object object) throws SemanticException
	{
		program.getDataDivisionScope().visit(this, object);
		program.getProcedureDivisionScope().visit(this, object);
		
		
		boolean main = false;
		
		
		if(identificationTable.retrieve("main") instanceof Procedure)
		{
			main = true;
		}
		
		
		if(main == false){
			throw new SemanticException("main not found");
		}
		
		return null;
	}

	public Object visitDataDivisionScope(DataDivisionScope dataDivisionScope, Object object) throws SemanticException
	{
		
		List<VarDeclare> varDeclareList = dataDivisionScope.getVarDeclareList();
		
		for (VarDeclare varDeclare : varDeclareList)
		{
			varDeclare.visit(this, object);
			
			
		}
		
		return null;
	}

	public Object visitProcedureDivisionScope(ProcedureDivisionScope procedureDivisionScope, Object object) throws SemanticException
	{

		List<Procedure> procedureList = procedureDivisionScope.getProcedureList();
		
		for (Procedure procedure : procedureList)
		{
			procedure.visit(this, object);
//			identificationTable.enter(procedure.getTokenId().getToken().getSpelling(), procedure);
		}
		
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

	public Object visitAttrib(Attrib attrib, Object object) throws SemanticException
	{
		if (!attrib.getExpression().visit(this, object).equals(attrib.getTokenId().visit(this, object)))
		{
			throw new SemanticException("Expression is not same type of ID");
		}
		return null;
	}

	public Object visitCallProcedure(CallProcedure callProcedure, Object object)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object visitExpression(Expression expression, Object object) throws SemanticException
	{

		if(expression.getOptionalOperator() == null)
		{
			if (!expression.getMandatoryOperator().visit(this, object).equals("BOOLEAN"))
			{
				throw new SemanticException("Type of Expression is invalid");
			}
			return "BOOLEAN";
		}else{
			
			if (expression.getMandatoryOperator().visit(this, object).equals("INTEGER") && expression.getOptionalOperator().visit(this, object).equals("INTEGER"))
			{
				if (expression.getTokenComparator().getToken().getSpelling().equals("=") || expression.getTokenComparator().getToken().getSpelling().equals("!=")|| 
					expression.getTokenComparator().getToken().getSpelling().equals(">")|| expression.getTokenComparator().getToken().getSpelling().equals("<")||
					expression.getTokenComparator().getToken().getSpelling().equals(">=")|| expression.getTokenComparator().getToken().getSpelling().equals("<=") )
				{
					return "BOOLEAN";
				}else{
					throw new SemanticException("Type of Expression is invalid");
				}
			}else if(expression.getMandatoryOperator().visit(this, object).equals("BOOLEAN") && expression.getOptionalOperator().visit(this, object).equals("BOOLEAN"))
			{
				if (expression.getTokenComparator().getToken().getSpelling().equals("=") || expression.getTokenComparator().getToken().getSpelling().equals("!="))
				{
					return "BOOLEAN";
				}
			}
			
			throw new SemanticException("Type of Expression is invalid");
		}
	}

	public Object visitOperator(Operator operator, Object object) throws SemanticException
	{
		List<Term> termList = operator.getOperatorTermList();
		boolean error = false;

		for (Term term : termList)
		{
			if(termList.size()==1 && term.visit(this, object).equals("BOOLEAN")){
				return "BOOLEAN";
			}
			
			if( !term.visit(this, object).equals("INTEGER")){
				error = true;
				break;
			}
		}
		
		if(error){
			throw new SemanticException("Type of Operator is invalid");
		}
		
		return "INTEGER";
	}

	public Object visitTerm(Term term, Object object) throws SemanticException
	{

		List<Fator> fatorList = term.getTermfatorList();
		boolean error = false;
		for (Fator fator : fatorList)
		{
			if(fatorList.size()==1 && fator.visit(this, object).equals("BOOLEAN")){
				return "BOOLEAN";
			}
			
			if( !fator.visit(this, object).equals("INTEGER")){
				error = true;
				break;
			}
		}
		
		if(error){
			throw new SemanticException("Type of Fator is invalid");
		}
		
		return "INTEGER";
		
		
	}

	public Object visitFatorCallProcedure(FatorCallProcedure fatorCallProcedure, Object object)
	{
		return fatorCallProcedure.getCallProcedure().visit(this, object);
	}

	public Object visitFatorBool(FatorBool fatorBool, Object object) throws SemanticException
	{
		return fatorBool.getTokenBool().visit(this, object);
	}

	public Object visitFatorIdentificator(FatorId fatorId, Object object) throws SemanticException
	{
		return fatorId.getTokenId().visit(this, object);
	}

	public Object visitFatorNumber(FatorNumber fatorNumber, Object object) throws SemanticException
	{
		return fatorNumber.getTokenNumber().visit(this, object);
	}

	public Object visitFatorExpression(FatorExpression fatorExpression, Object object) throws SemanticException
	{
		return fatorExpression.getExpression().visit(this, object);
	}

	public Object visitTerminalTypeBoolean(TerminalBoolean terminalBoolean, Object object)
	{
		return "BOOLEAN";
	}

	public Object visitTerminalBool(TerminalBool terminalBool, Object object)
	{
//		return terminalBool.getToken().getSpelling();
		return "BOOLEAN";
	}

	public Object visitTerminalTypeInteger(TerminalInteger terminalInteger, Object object)
	{
		return "INTEGER";
	}

	public Object visitTerminalNumber(TerminalNumber terminalNumber, Object object)
	{
//		return terminalNumber.getToken().getSpelling();
		return "INTEGER";
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
		
		return terminalComp.getToken().getSpelling();
	}

	public Object visitTerminalOperations(TerminalOperations terminalOperations, Object object)
	{
		
		return terminalOperations.getToken().getSpelling();
	}

}
