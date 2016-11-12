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
		if (program.getDataDivisionScope() != null)
		{
			program.getDataDivisionScope().visit(this, object);
		}

		if (program.getProcedureDivisionScope() != null)
		{
			program.getProcedureDivisionScope().visit(this, object);
		}

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
		Terminal procedureName = procedure.getTokenId();
		Terminal procedureType = procedure.getProcedureType(); // tipo de
																// retorno da
																// funcao, pode
																// ser void
		List<VarDeclare> varDeclareList = procedure.getVarDeclareList(); // decl
																			// de
																			// variaves
																			// locais
		List<VarDeclare> parametersList = procedure.getProcedureParametersList(); // parametros
		Command command = procedure.getCommand();

		String procedureIdentificator = procedure.getTokenId().getToken().getSpelling();

		Object procedureStoredInTable = this.identificationTable.retrieve(procedureIdentificator);

		if (procedureStoredInTable != null)
		{
			throw new SemanticException("The Procedure " + procedureIdentificator + " is already defined.");
		}

		this.identificationTable.enter(procedureName.getToken().getSpelling(), procedure);

		((TerminalId) procedureName).setDeclaredTerminalIdNode(procedure);

		this.identificationTable.openScope();

		if (parametersList != null)
		{
			// iteracao para colocar as variaveis parametros no escopo
			for (VarDeclare parameter : parametersList)
			{
				parameter.visit(this, object);
			}
		}

		if (varDeclareList != null)
		{
			// iteracao para colocar as variaveis locais no escopo
			for (VarDeclare varDeclare : varDeclareList)
			{
				varDeclare.visit(this, object);
			}
		}

		if (procedureType != null)
		{

			List<Statement> statementList = command.getStatementList();

			for (Statement statement : statementList)
			{
				if (statement instanceof StatementReturn)
				{
					StatementReturn statementReturn = (StatementReturn) statement;
					String expressionResult = (String) statementReturn.getExpression().visit(this, object);

					if (!(expressionResult.equals(procedureType.getToken().getSpelling())))
					{
						throw new SemanticException("Incompatible return type");
					}
				}
			}

		}
		else
		{
			command.visit(this, object);
		}

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
		While myWhile = statementWhile.getAttributeWhile();

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

		String expressionResult = (String) expression.visit(this, object);

		if (!expressionResult.equals("BOOLEAN"))
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
		String expressionResult = (String) attrib.getExpression().visit(this, object);
		if (!expressionResult.equals(attrib.getTokenId().visit(this, object)))
		{
			throw new SemanticException("Expression is not same type of ID");
		}
		return null;
	}

	public Object visitCallProcedure(CallProcedure callProcedure, Object object) throws SemanticException
	{

		List<Terminal> callProcedureTerminalItens = callProcedure.getTerminalList();
		String procedureReturnType = "";

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

			procedureReturnType = storedProcedure.getProcedureType().getToken().getSpelling();

			List<VarDeclare> calledProcedureArguments = storedProcedure.getProcedureParametersList();

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
					String typeOfCurrentArgumentOfProcedure = calledProcedureArguments.get(i - 1)
							.getTerminalBooleanOrInteger().getToken().getSpelling();

					if (!typeOfCurrentArgumentPassed.equals(typeOfCurrentArgumentOfProcedure))
					{
						throw new SemanticException("The type of argument "
								+ ((TerminalId) callProcedureTerminalItens.get(i)).getToken().getSpelling()
								+ "must be equal to the procedure parameter. Check the type at index " + i);
					}

				}
			}

		}

		return procedureReturnType;
	}

	public Object visitExpression(Expression expression, Object object) throws SemanticException
	{

		Object operatorResult = expression.getMandatoryOperator().visit(this, object);
		String mandatoryOperator = (operatorResult != null && operatorResult instanceof String)
				? (String) operatorResult : "";

	
				
		if (expression.getOptionalOperator() == null)
		{
			
			
			expression.setType(mandatoryOperator);
			return mandatoryOperator;
		}
		else
		{
			Object optionalOperatorResult = expression.getOptionalOperator().visit(this, object);
			String opitionalOperator = (optionalOperatorResult != null && optionalOperatorResult instanceof String)
					? (String) optionalOperatorResult : "";
					
						

			if (mandatoryOperator.equals("INTEGER") && opitionalOperator.equals("INTEGER"))
			{
				expression.setType("BOOLEAN");
				return "BOOLEAN";

			}
			else if (mandatoryOperator.equals("BOOLEAN") && opitionalOperator.equals("BOOLEAN"))
			{
				if (expression.getTokenComparator().getToken().getSpelling().equals("==")
						|| expression.getTokenComparator().getToken().getSpelling().equals("!="))
				{
					expression.setType("BOOLEAN");
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
			Object termResult = term.visit(this, object);
			String termResultValue = (termResult != null && (termResult instanceof String)) ? (String) termResult : "";

			if (termList.size() == 1 && termResultValue.equals("BOOLEAN"))
			{
				operator.setType("BOOLEAN");
				return "BOOLEAN";
			}

	
			
			if (!termResultValue.equals("INTEGER"))
			{
				throw new SemanticException("Type of Operator is invalid");
			}
		}
		operator.setType("INTEGER");
		return "INTEGER";
	}

	public Object visitTerm(Term term, Object object) throws SemanticException
	{

		List<Fator> fatorList = term.getTermfatorList();

		for (Fator fator : fatorList)
		{
			Object fatorResult = fator.visit(this, object);
			String fatorResultValue = (fatorResult != null && (fatorResult instanceof String)) ? (String) fatorResult
					: "";

			if (fatorList.size() == 1 && fatorResultValue.equals("BOOLEAN"))
			{
				term.setType("BOOLEAN");
				return "BOOLEAN";
			}
			

			if (!fatorResultValue.equals("INTEGER"))
			{
				throw new SemanticException("Type of Fator is invalid");
			}
		}
		term.setType("INTEGER");
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
