package checker;

import java.util.ArrayList;
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
import model.Statement;
import model.StatementAttrib;
import model.StatementBreak;
import model.StatementCallProcedure;
import model.StatementContinue;
import model.StatementDisplay;
import model.StatementIf;
import model.StatementReturn;
import model.StatementWhile;
import model.Term;
import model.Terminal;
import model.TerminalBool;
import model.TerminalBoolean;
import model.TerminalComp;
import model.TerminalId;
import model.TerminalInteger;
import model.TerminalNumber;
import model.TerminalOperations;
import model.VarDeclare;
import model.While;
import util.AST.AST;
import util.symbolsTable.IdentificationTable;

public class Checker implements IVisitor
{

	private IdentificationTable identificationTable = new IdentificationTable();

	public void check(AST sintaticAbstractTree) throws SemanticException
	{
		sintaticAbstractTree.visit(this, null);
	}

	public Object visitProgram(Program program, Object object) throws SemanticException
	{
		program.getDataDivisionScope().visit(this, object);
		program.getProcedureDivisionScope().visit(this, object);

		if (!(identificationTable.retrieve("MAIN") instanceof Procedure))
		{
			throw new SemanticException("The Procedure main function is not defined");
		}

		return null;
	}

	public Object visitDataDivisionScope(DataDivisionScope dataDivisionScope, Object object) throws SemanticException
	{

		List<VarDeclare> varDeclareList = dataDivisionScope.getVarDeclareList();

		if (varDeclareList != null)
		{
			for (VarDeclare varDeclare : varDeclareList)
			{
				varDeclare.visit(this, object);

			}
		}

		return null;
	}

	public Object visitProcedureDivisionScope(ProcedureDivisionScope procedureDivisionScope, Object object)
			throws SemanticException
	{

		List<Procedure> allExistingProcedures = procedureDivisionScope.getProcedureList();

		if (allExistingProcedures != null && allExistingProcedures.size() > 0)
		{
			for (Procedure procedure : allExistingProcedures)
			{
				procedure.visit(this, object);
			}
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

		TerminalId terminalIdentificator = (TerminalId) varDeclare.getTerminalId();
		terminalIdentificator.setDeclaredTerminalIdNode(varDeclare);

		return null;
	}

	public Object visitProcedure(Procedure procedure, Object object) throws SemanticException
	{
		Terminal tokenId = procedure.getTokenId();
		Terminal procedureType = procedure.getProcedureType();
		List<VarDeclare> varDeclareList = procedure.getVarDeclareList();
		List<Terminal> terminalList = procedure.getTerminalList();
		Command command = procedure.getCommand();

		String procedureIdentificator = procedure.getTokenId().getToken().getSpelling();

		// remover cast
		Procedure procedureStored = (Procedure) this.identificationTable.retrieve(procedureIdentificator);

		if (procedureStored != null)
		{
			throw new SemanticException(
					"The Procedure " + procedureStored.getTokenId().getToken().getSpelling() + " already defined.");
		}

		this.identificationTable.enter(tokenId.getToken().getSpelling(), procedure);

		((TerminalId) tokenId).setDeclaredTerminalIdNode(procedure);

		// abre o escopo
		this.identificationTable.openScope();

		if (terminalList != null)
		{
			for (Terminal terminal : terminalList)
			{
				if (!terminal.getToken().getSpelling().equals("INTEGER")
						&& !terminal.getToken().getSpelling().equals("BOOLEAN"))
				{
					identificationTable.enter(terminal.getToken().getSpelling(), terminal);
				}
			}
		}

		if (varDeclareList != null)
		{
			for (VarDeclare varDeclare : varDeclareList)
			{
				varDeclare.visit(this, object);
			}
		}

		// nao funciona--morre
		if (!(command.visit(this, object) == procedureType.getToken().getSpelling()))
		{
			throw new SemanticException("Incompatible type of return!");
		}

		// fecha o escopo
		this.identificationTable.closeScope();

		return null;
	}

	public Object visitCommand(Command command, Object object) throws SemanticException
	{
		List<Statement> statementList = command.getStatementList();

		for (Statement statement : statementList)
		{
			statement.visit(this, object);
		}
		return null;
	}

	public Object visitStatementIf(StatementIf statementIf, Object object) throws SemanticException
	{
		Condition condition = statementIf.getCond();
		List<Command> commandList = statementIf.getCommandList();

		condition.visit(this, object);

		this.identificationTable.openScope();

		for (Command command : commandList)
		{
			command.visit(this, object);
		}

		this.identificationTable.closeScope();

		return null;
	}

	public Object visitStatementWhile(StatementWhile statementWhile, Object object) throws SemanticException
	{
		While myWhile = statementWhile.getMeuWhile();

		this.identificationTable.openScope();

		myWhile.visit(this, object);

		this.identificationTable.closeScope();

		return null;
	}

	public Object visitStatementDisplay(StatementDisplay statementDisplay, Object object) throws SemanticException
	{
		Expression expression = statementDisplay.getExpression();

		expression.visit(this, object);

		return null;
	}

	public Object visitStatementReturn(StatementReturn statementReturn, Object object) throws SemanticException
	{
		Expression expression = statementReturn.getExpression();

		// Este If representa a decima restrição. Consideramos que não pode
		// haver um retorno dentro
		// de um while, para que esse comando não se comporte como um If.

		if (object != null)
		{
			if (!(object instanceof While))
			{
				throw new SemanticException("Return command can not be inside a While!");
			}
		}

		return expression.visit(this, object);
	}

	public Object visitStatementAttribution(StatementAttrib statementAttrib, Object object) throws SemanticException
	{
		Attrib attribute = statementAttrib.getAttrib();

		attribute.visit(this, object);
		return null;
	}

	public Object visitStatementCallProcedure(StatementCallProcedure statementCallProcedure, Object object)
			throws SemanticException
	{
		CallProcedure callProcedure = statementCallProcedure.getCallProcedure();

		callProcedure.visit(this, object);

		return null;
	}

	public Object visitStatementBreak(StatementBreak statementBreak, Object object) throws SemanticException
	{
		if (object != null)
		{
			if (!(object instanceof While))
			{
				throw new SemanticException("Break command must be inside a While!");
			}
		}
		return null;
	}

	public Object visitStatementContinue(StatementContinue statementContinue, Object object) throws SemanticException
	{
		if (object != null)
		{
			if (!(object instanceof While))
			{
				throw new SemanticException("Continue command must be inside a While!");
			}
		}
		return null;
	}

	public Object visitCondition(Condition condition, Object object) throws SemanticException
	{
		Expression expression = condition.getExpression();

		if (!expression.visit(this, object).equals("BOOLEAN"))
		{
			throw new SemanticException("Condition must be boolean type!");
		}

		return null;
	}

	public Object visitWhile(While whileCommand, Object object) throws SemanticException
	{
		Condition contidion = whileCommand.getCondition();
		Command command = whileCommand.getCommand();

		contidion.visit(this, object);

		this.identificationTable.openScope();

		command.visit(this, whileCommand);

		this.identificationTable.closeScope();

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

	public Object visitCallProcedure(CallProcedure callProcedure, Object object) throws SemanticException
	{

		List<Terminal> callProcedureTerminalItens = callProcedure.getTerminalList();

		if (callProcedureTerminalItens != null)
		{
			TerminalId procedureIdentificator = (TerminalId) callProcedureTerminalItens.get(0);

			Object storedProcedureInTable = this.identificationTable
					.retrieve(procedureIdentificator.getToken().getSpelling());

			if (storedProcedureInTable == null || !(storedProcedureInTable instanceof Procedure))
			{
				throw new SemanticException("The Procedure " + procedureIdentificator + " is not defined.");
			}

			Procedure storedProcedure = (Procedure) storedProcedureInTable;

			List<Terminal> calledProcedureArguments = storedProcedure.getTerminalList();

			calledProcedureArguments = this.refatArgumentsList(calledProcedureArguments);

			if ((callProcedureTerminalItens.size() - 1) != calledProcedureArguments.size())
			{
				throw new SemanticException(
						"The number of parameters must be equal to the number of arguments of the procedure.");
			}

			if (callProcedureTerminalItens.size() > 1)
			{
				for (int i = 1; i < callProcedureTerminalItens.size(); i++)
				{
					String typeOfCurrentArgumentPassed = (String) ((TerminalId) callProcedureTerminalItens.get(i))
							.visit(this, object);
					String typeOfCurrentArgumentOfProcedure = calledProcedureArguments.get(i - 1).getToken()
							.getSpelling();

					if (!typeOfCurrentArgumentPassed.equals(typeOfCurrentArgumentOfProcedure))
					{
						throw new SemanticException("The type of argument "
								+ ((TerminalId) callProcedureTerminalItens.get(i)).getToken().getSpelling()
								+ "must be equal to the procedure parameter. Check the type at index " + i);
					}

				}
			}

		}

		return null;
	}

	private List<Terminal> refatArgumentsList(List<Terminal> calledProcedureArguments)
	{
		List<Terminal> newArgumentList = new ArrayList<Terminal>();
		if (calledProcedureArguments != null && calledProcedureArguments.size() > 0)
		{
			for (int i = 0; i < calledProcedureArguments.size(); i += 2)
			{
				newArgumentList.add(calledProcedureArguments.get(i));
			}
		}
		return newArgumentList;
	}

	public Object visitExpression(Expression expression, Object object) throws SemanticException
	{

		String mandatoryOperator = (String) expression.getMandatoryOperator().visit(this, object);

		if (expression.getOptionalOperator() == null)
		{

			return mandatoryOperator;
		}
		else
		{
			String opitionalOperator = (String) expression.getOptionalOperator().visit(this, object);

			if (mandatoryOperator.equals("INTEGER") && opitionalOperator.equals("INTEGER"))
			{

				return "BOOLEAN";

			}
			else if (mandatoryOperator.equals("BOOLEAN") && opitionalOperator.equals("BOOLEAN"))
			{
				if (expression.getTokenComparator().getToken().getSpelling().equals("=")
						|| expression.getTokenComparator().getToken().getSpelling().equals("!="))
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

		for (Term term : termList)
		{
			if (termList.size() == 1 && term.visit(this, object).equals("BOOLEAN"))
			{
				return "BOOLEAN";
			}

			if (!term.visit(this, object).equals("INTEGER"))
			{
				throw new SemanticException("Type of Operator is invalid");
			}
		}

		return "INTEGER";
	}

	public Object visitTerm(Term term, Object object) throws SemanticException
	{

		List<Fator> fatorList = term.getTermfatorList();

		for (Fator fator : fatorList)
		{
			if (fatorList.size() == 1 && fator.visit(this, object).equals("BOOLEAN"))
			{
				return "BOOLEAN";
			}

			if (!fator.visit(this, object).equals("INTEGER"))
			{
				throw new SemanticException("Type of Fator is invalid");
			}
		}

		return "INTEGER";

	}

	public Object visitFatorCallProcedure(FatorCallProcedure fatorCallProcedure, Object object) throws SemanticException
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
		// return terminalBool.getToken().getSpelling();
		return "BOOLEAN";
	}

	public Object visitTerminalTypeInteger(TerminalInteger terminalInteger, Object object)
	{
		return "INTEGER";
	}

	public Object visitTerminalNumber(TerminalNumber terminalNumber, Object object)
	{
		// return terminalNumber.getToken().getSpelling();
		return "INTEGER";
	}

	public Object visitTerminalIdentificator(TerminalId terminalId, Object object) throws SemanticException
	{

		Object varDeclaration = this.identificationTable.retrieve(terminalId.getToken().getSpelling());

		if (varDeclaration == null)
		{
			throw new SemanticException("The Identifier " + terminalId.getToken().getSpelling() + " is not defined.");
		}
		String varType = null;
		if (varDeclaration instanceof VarDeclare)
		{
			VarDeclare declarationCommand = (VarDeclare) varDeclaration;
			varType = declarationCommand.getTerminalBooleanOrInteger().getToken().getSpelling();
		}

		return varType;
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
