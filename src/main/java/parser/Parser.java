package parser;

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
import scanner.LexicalException;
import scanner.Scanner;
import scanner.Token;
import util.AST.AST;

/**
 * Parser class
 * 
 * @version 2010-august-29
 * @discipline Projeto de Compiladores
 * @author Gustavo H P Carvalho
 * @email gustavohpcarvalho@ecomp.poli.br
 */
public class Parser
{

	// The current token
	private Token currentToken = null;
	// The scanner
	private Scanner scanner = null;

	/**
	 * Parser constructor
	 */
	public Parser()
	{
		// Initializes the scanner object
		this.scanner = new Scanner();
	}

	/**
	 * Veririfes if the current token kind is the expected one
	 * 
	 * @param kind
	 * @throws SyntacticException
	 */
	private void accept(int kind) throws SyntacticException
	{
		// If the current token kind is equal to the expected
		// Gets next token
		// If not
		// Raises an exception
		if (this.currentToken.getKind() == kind)
		{

			this.acceptIt();
		}
		else
		{
			throw new SyntacticException("Illegal Token", this.currentToken);
		}
	}

	/**
	 * Gets next token
	 */
	private void acceptIt()
	{
		try
		{
			this.currentToken = this.scanner.getNextToken();
		}
		catch (LexicalException e)
		{
			System.err.println(e.toString());
		}
	}

	/**
	 * Verifies if the source program is syntactically correct
	 * 
	 * @throws SyntacticException
	 */
	public AST parse() throws SyntacticException
	{

		AST abstractSintaticTree = null;
		this.initializeToken();

		abstractSintaticTree = this.parseProgram();
		accept(GrammarSymbols.EOT);

		return abstractSintaticTree;
	}

	private Program parseProgram() throws SyntacticException
	{
		Program program = null;
		DataDivisionScope dataDivisionScope = null;
		ProcedureDivisionScope procedureDivisionScope = null;

		if (currentToken.getKind() == GrammarSymbols.IDENTIFICATOR_DIVISION)
		{
			acceptIt();

			accept(GrammarSymbols.DOT);

			accept(GrammarSymbols.PROGRAMID);

			accept(GrammarSymbols.DOT);

			Terminal id = new TerminalId(currentToken);

			accept(GrammarSymbols.ID);

			accept(GrammarSymbols.DOT);

			if (currentToken.getKind() == GrammarSymbols.DATA_DIVISION)
			{
				acceptIt();

				accept(GrammarSymbols.DOT);
				dataDivisionScope = parseDataDivisionScope();

			}

			if (currentToken.getKind() == GrammarSymbols.PROCEDURE_DIVISION)
			{
				acceptIt();

				accept(GrammarSymbols.DOT);
				procedureDivisionScope = parseProcedureDivisionScope();
			}

			program = new Program(id, dataDivisionScope, procedureDivisionScope);
		}
		else
		{
			throw new SyntacticException("A Cobol Program dont begins with", this.currentToken);
		}

		return program;
	}

	private VarDeclare parseVarDeclare() throws SyntacticException
	{
		VarDeclare varDeclare = null;
		Terminal intOrBoolTerminal = null;

		Terminal id = new TerminalId(currentToken);
		accept(GrammarSymbols.ID);

		accept(GrammarSymbols.PIC);

		if (currentToken.getKind() == GrammarSymbols.INTEGER)
		{
			intOrBoolTerminal = new TerminalInteger(currentToken);
			acceptIt();
		}
		else
		{
			intOrBoolTerminal = new TerminalBoolean(currentToken);
			accept(GrammarSymbols.BOOLEAN);

		}

		accept(GrammarSymbols.DOT);
		varDeclare = new VarDeclare(id, intOrBoolTerminal);

		return varDeclare;

	}

	private DataDivisionScope parseDataDivisionScope() throws SyntacticException
	{
		DataDivisionScope dataDivisionScope = null;
		List<VarDeclare> varDeclareList = new ArrayList<VarDeclare>();

		while (true)
		{
			if (currentToken.getKind() == GrammarSymbols.EXIT)
			{
				break;
			}

			varDeclareList.add(parseVarDeclare());

		}
		acceptIt();
		accept(GrammarSymbols.DOT);

		dataDivisionScope = new DataDivisionScope(varDeclareList);

		return dataDivisionScope;
	}

	private Procedure parseProcedure() throws SyntacticException
	{
		Procedure procedure = null;
		Terminal procedureType = null;

		if (currentToken.getKind() == GrammarSymbols.BOOLEAN || currentToken.getKind() == GrammarSymbols.INTEGER)
		{
			if (currentToken.getKind() == GrammarSymbols.BOOLEAN)
			{
				procedureType = new TerminalBoolean(currentToken);
				accept(GrammarSymbols.BOOLEAN);
			}
			else
			{
				procedureType = new TerminalInteger(currentToken);
				accept(GrammarSymbols.INTEGER);
			}
		}

		Terminal tokenId = new TerminalId(currentToken);

		accept(GrammarSymbols.ID);
		accept(GrammarSymbols.SECTION);
		accept(GrammarSymbols.DOT);

		Terminal boolOrIntTerminal = null;
		Terminal varTypeTokenId = null;
		List<Terminal> terminalParametersList = new ArrayList<Terminal>();

		while (currentToken.getKind() == GrammarSymbols.BOOLEAN || currentToken.getKind() == GrammarSymbols.INTEGER)
		{
			if (currentToken.getKind() == GrammarSymbols.BOOLEAN)
			{
				boolOrIntTerminal = new TerminalBoolean(currentToken);
			}
			else
			{
				boolOrIntTerminal = new TerminalInteger(currentToken);
			}

			acceptIt();

			varTypeTokenId = new TerminalId(currentToken);
			accept(GrammarSymbols.ID);

			terminalParametersList.add(boolOrIntTerminal);
			terminalParametersList.add(varTypeTokenId);
		}

		List<VarDeclare> varDeclareList = new ArrayList<VarDeclare>();

		accept(GrammarSymbols.BEGIN_DECL);

		while (true)
		{

			if (currentToken.getKind() == GrammarSymbols.END_DECL)
			{
				break;
			}

			varDeclareList.add(parseVarDeclare());

		}

		acceptIt();
		Command command = parseCommand();
		accept(GrammarSymbols.END_PROC);
		accept(GrammarSymbols.DOT);

		procedure = new Procedure(procedureType, tokenId, terminalParametersList, varDeclareList, command);

		return procedure;
	}

	private ProcedureDivisionScope parseProcedureDivisionScope() throws SyntacticException
	{
		ProcedureDivisionScope procedureDivisionScope = null;
		List<Procedure> procedureList = new ArrayList<Procedure>();

		while (true)
		{
			if (currentToken.getKind() == GrammarSymbols.EXIT)
			{
				break;
			}
			procedureList.add(parseProcedure());

		}
		acceptIt();
		accept(GrammarSymbols.DOT);

		procedureDivisionScope = new ProcedureDivisionScope(procedureList);

		return procedureDivisionScope;

	}

	// TODO Refactor, toString, modificadores de acesso, construtores, corrigir
	// nomenclatura de atributos
	private Command parseCommand() throws SyntacticException
	{
		Command command = null;
		List<Statement> statementList = new ArrayList<Statement>();
		do
		{
			statementList.add(parseStatement());

			if (currentToken.getKind() == GrammarSymbols.END_COM)
			{
				break;
			}

		} while (true);

		acceptIt();
		accept(GrammarSymbols.DOT);

		command = new Command(statementList);

		return command;
	}

	private Attrib parseAttrib() throws SyntacticException
	{
		Attrib attrib = null;

		acceptIt();
		Expression expression = parseExpression();
		accept(GrammarSymbols.TO);
		Terminal tokenId = new TerminalId(currentToken);
		accept(GrammarSymbols.ID);
		accept(GrammarSymbols.DOT);

		attrib = new Attrib(expression, tokenId);

		return attrib;
	}

	private CallProcedure parseCallProcedure() throws SyntacticException
	{
		CallProcedure callProcedure = null;

		acceptIt();
		Terminal tokenId = new TerminalId(currentToken);
		accept(GrammarSymbols.ID);

		List<Terminal> terminalIdList = new ArrayList<Terminal>();

		terminalIdList.add(tokenId);

		while (currentToken.getKind() == GrammarSymbols.USING)
		{
			acceptIt();
			Terminal possibleTokenId = new TerminalId(currentToken);
			accept(GrammarSymbols.ID);

			terminalIdList.add(possibleTokenId);
		}

		callProcedure = new CallProcedure(terminalIdList);

		return callProcedure;
	}

	private Statement parseStatement() throws SyntacticException
	{
		Statement statement = null;

		List<Command> commandList = new ArrayList<Command>();

		if (currentToken.getKind() == GrammarSymbols.IF)
		{
			acceptIt();
			Condition condition = parseCondition();
			accept(GrammarSymbols.THEN);
			Command command = parseCommand();

			commandList.add(command);

			Command possibleElseCommand = null;

			if (currentToken.getKind() == GrammarSymbols.ELSE)
			{
				acceptIt();
				possibleElseCommand = parseCommand();
				commandList.add(possibleElseCommand);
			}
			accept(GrammarSymbols.END_IF);
			accept(GrammarSymbols.DOT);

			statement = new StatementIf(condition, commandList);
		}
		else if (currentToken.getKind() == GrammarSymbols.RETURN)
		{
			acceptIt();
			Expression expression = parseExpression();
			accept(GrammarSymbols.DOT);

			statement = new StatementReturn(expression);
		}
		else if (currentToken.getKind() == GrammarSymbols.UNTIL)
		{
			While meuWhile = parseWhile();
			accept(GrammarSymbols.END_WHILE);
			accept(GrammarSymbols.DOT);

			statement = new StatementWhile(meuWhile);
		}
		else if (currentToken.getKind() == GrammarSymbols.MOVE)
		{
			Attrib attrib = parseAttrib();

			statement = new StatementAttrib(attrib);
		}
		else if (currentToken.getKind() == GrammarSymbols.PERFORM)
		{
			CallProcedure callProcedure = parseCallProcedure();

			statement = new StatementCallProcedure(callProcedure);
		}
		else if (currentToken.getKind() == GrammarSymbols.BREAK)
		{
			acceptIt();

			statement = new StatementBreak();
		}
		else if (currentToken.getKind() == GrammarSymbols.CONTINUE)
		{
			acceptIt();

			statement = new StatementContinue();

		}
		else
		{
			accept(GrammarSymbols.DISPLAY);
			Expression expression = parseExpression();
			accept(GrammarSymbols.DOT);

			statement = new StatementDisplay(expression);
		}

		return statement;
	}

	private While parseWhile() throws SyntacticException
	{
		While meuWhile = null;
		acceptIt();
		Condition condition = parseCondition();
		Command command = parseCommand();

		meuWhile = new While(condition, command);

		return meuWhile;
	}

	private Expression parseExpression() throws SyntacticException
	{
		Expression expression = null;
		Terminal tokenComparator = null;
		Operator possibleOperator = null;

		Operator operator = parseOperator();

		if (currentToken.getKind() == GrammarSymbols.COMP)
		{
			tokenComparator = new TerminalComp(currentToken);
			acceptIt();
			possibleOperator = parseOperator();
		}

		expression = new Expression(operator, tokenComparator, possibleOperator);

		return expression;
	}

	private Operator parseOperator() throws SyntacticException
	{
		Operator operator = null;
		Term mandatoryTerm = parseTerm();

		List<Term> operatorTermList = new ArrayList<Term>();
		List<Terminal> operatorTerminalList = new ArrayList<Terminal>();

		operatorTermList.add(mandatoryTerm);

		while (currentToken.getKind() == GrammarSymbols.PLUS || currentToken.getKind() == GrammarSymbols.MINUS)
		{
			Terminal terminalPlusOrMinus = null;
			Term nextTerm = null;

			terminalPlusOrMinus = new TerminalOperations(currentToken);
			operatorTerminalList.add(terminalPlusOrMinus);

			acceptIt();

			nextTerm = parseTerm();
			operatorTermList.add(nextTerm);
		}

		operator = new Operator(operatorTerminalList, operatorTermList);

		return operator;
	}

	private Term parseTerm() throws SyntacticException
	{
		Term term = null;
		Fator mandatoryFator = parseFator();
		List<Terminal> multiOrDivOperatorList = new ArrayList<Terminal>();
		List<Fator> fatorList = new ArrayList<Fator>();

		fatorList.add(mandatoryFator);

		while (currentToken.getKind() == GrammarSymbols.MULTIPLICATION
				|| currentToken.getKind() == GrammarSymbols.DIVISION)
		{
			Fator nextFator = null;
			Terminal terminalMultiOrDiv = null;

			terminalMultiOrDiv = new TerminalOperations(currentToken);

			multiOrDivOperatorList.add(terminalMultiOrDiv);

			acceptIt();

			nextFator = parseFator();
			fatorList.add(nextFator);

		}

		term = new Term(fatorList, multiOrDivOperatorList);

		return term;
	}

	private Fator parseFator() throws SyntacticException
	{
		Fator fator = null;

		if (currentToken.getKind() == GrammarSymbols.BOOL)
		{
			Terminal tokenBool = new TerminalBool(currentToken);
			acceptIt();
			fator = new FatorBool(tokenBool);
		}
		else if (currentToken.getKind() == GrammarSymbols.ID)
		{
			Terminal tokenId = new TerminalId(currentToken);
			acceptIt();

			fator = new FatorId(tokenId);
		}
		else if (currentToken.getKind() == GrammarSymbols.NUMBER)
		{
			Terminal tokenNumber = new TerminalNumber(currentToken);
			acceptIt();
			fator = new FatorNumber(tokenNumber);
		}
		else if (currentToken.getKind() == GrammarSymbols.LP)
		{
			acceptIt();
			Expression expression = parseExpression();
			accept(GrammarSymbols.RP);

			fator = new FatorExpression(expression);
		}
		else
		{
			CallProcedure callProcedure = parseCallProcedure();

			fator = new FatorCallProcedure(callProcedure);
		}

		return fator;
	}

	private Condition parseCondition() throws SyntacticException
	{
		Condition condition = null;
		Expression expression = parseExpression();

		condition = new Condition(expression);

		return condition;
	}

	private void initializeToken()
	{
		if (this.currentToken == null)
		{
			try
			{
				this.currentToken = this.scanner.getNextToken();
			}
			catch (LexicalException e)
			{
				System.err.println(e.toString());
			}
		}
	}

}
