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
import model.Operator;
import model.Procedure;
import model.ProcedureDivisionScope;
import model.Program;
import model.Statement;
import model.Term;
import model.Terminal;
import model.TokenDataDivision;
import model.TokenDot;
import model.TokenId;
import model.TokenIdentificatorDivision;
import model.TokenProcedureDivision;
import model.TokenProgramId;
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
	 */ // TODO
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
	 */ // TODO
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
	 */ // TODO
	public AST parse() throws SyntacticException
	{

		AST abstractSintaticTree = null;
		this.initializeToken();

		abstractSintaticTree = this.parseProgram();
		accept(GrammarSymbols.EOT);

		return abstractSintaticTree;
	}

	/*
	 * private Program parseProgram() { Program program = null;
	 * 
	 * List<Command> commandList = new ArrayList<Command>();
	 * 
	 * do { Command command = parseCommand(); commandList.add(command);
	 * 
	 * } while (this.currentToken.getKind() != GrammarSymbols.EOT);
	 * 
	 * program = new Program(commandList);
	 * 
	 * return program; }
	 */

	/*
	 * private Command parseCommand() { return null; // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 */

	private Program parseProgram() throws SyntacticException
	{
		//null
		Program prog = null;
		Terminal datdiv = null,dot4 = null,procdiv = null,dot5 = null;
		DataDivisionScope d = null;
		ProcedureDivisionScope p = null;

		if (currentToken.getKind() == GrammarSymbols.IDENTIFICATOR_DIVISION)
		{
			Terminal iddiv= new TokenIdentificatorDivision(currentToken);
			acceptIt();
					
			Terminal dot= new TokenDot(currentToken);
			accept(GrammarSymbols.DOT);
			
			Terminal progid= new TokenProgramId(currentToken);
			accept(GrammarSymbols.PROGRAMID);
			
			Terminal dot2= new TokenDot(currentToken);
			accept(GrammarSymbols.DOT);
			
			Terminal id= new TokenId(currentToken);
			accept(GrammarSymbols.ID);
			
			Terminal dot3= new TokenDot(currentToken);
			accept(GrammarSymbols.DOT);
			

			if (currentToken.getKind() == GrammarSymbols.DATA_DIVISION)
			{
				datdiv= new TokenDataDivision(currentToken);
				acceptIt();
				
				dot4= new TokenDot(currentToken);
				accept(GrammarSymbols.DOT);
				d = parseDataDivisionScope();
				
			}

			if (currentToken.getKind() == GrammarSymbols.PROCEDURE_DIVISION)
			{
				procdiv = new TokenProcedureDivision(currentToken);
				acceptIt();
				
				dot5= new TokenDot(currentToken);
				accept(GrammarSymbols.DOT);
				p = parseProcedureDivisionScope();
			}
			prog = new Program(iddiv, dot3, progid, dot2, id, dot3, datdiv, dot4, d, procdiv, dot5, p);
		}
		else
		{
			throw new SyntacticException("Qual mensagem colocar?", currentToken);
		}

		return prog;
	}

	// TODO fazer vários parseISSO, parseAQUILO

	private VarDeclare parseVarDeclare() throws SyntacticException
	{
		
		
		accept(GrammarSymbols.ID);
		accept(GrammarSymbols.PIC);
		
		if (currentToken.getKind() == GrammarSymbols.INTEGER)
		{

			acceptIt();
		}
		else
		{
			accept(GrammarSymbols.BOOLEAN);

		}
		
		accept(GrammarSymbols.DOT);
		return null;

	}

	private DataDivisionScope parseDataDivisionScope() throws SyntacticException
	{
		while (true)
		{
			if (currentToken.getKind() == GrammarSymbols.EXIT)
			{
				break;
			}
			parseVarDeclare();

		}

		acceptIt();
		accept(GrammarSymbols.DOT);
		return null;
	}

	private Procedure parseProcedure() throws SyntacticException
	{
		
			accept(GrammarSymbols.ID);
			accept(GrammarSymbols.SECTION);
			accept(GrammarSymbols.DOT);
			while (currentToken.getKind() == GrammarSymbols.BOOLEAN || currentToken.getKind() == GrammarSymbols.INTEGER)
			{
				acceptIt();
				accept(GrammarSymbols.ID);
			}
			
			accept(GrammarSymbols.BEGIN_DECL);
			
			while (true)
			{
				
				if (currentToken.getKind() == GrammarSymbols.END_DECL)
				{
					break;
				}

				parseVarDeclare();
			}
			acceptIt(); 
			parseCommand();
			accept(GrammarSymbols.END_PROC);
			accept(GrammarSymbols.DOT);
			return null;

		

	}

	private ProcedureDivisionScope parseProcedureDivisionScope() throws SyntacticException
	{
		while (true)
		{
			if (currentToken.getKind() == GrammarSymbols.EXIT)
			{
				break;
			}
			parseProcedure();

		}
		acceptIt();
		accept(GrammarSymbols.DOT);
		return null;

	}

	private Command parseCommand() throws SyntacticException
	{
		do
		{
			parseStatement();
		
			if (currentToken.getKind() == GrammarSymbols.END_COM)
			{
				break;
			}

		} while (true);

		acceptIt(); 
		accept(GrammarSymbols.DOT);
		return null; 

	}

	private Attrib parseAttrib() throws SyntacticException
	{
		acceptIt();
		parseExpression();
		accept(GrammarSymbols.TO);
		accept(GrammarSymbols.ID);
		accept(GrammarSymbols.DOT);
		return null;
	}

	private CallProcedure parseCallProcedure() throws SyntacticException
	{
		acceptIt();
		accept(GrammarSymbols.ID);

		while (currentToken.getKind() == GrammarSymbols.USING) {
			acceptIt();
			accept(GrammarSymbols.ID);

		}
		return null;
	}

	private Statement parseStatement() throws SyntacticException
	{
		if (currentToken.getKind() == GrammarSymbols.IF)
		{
			acceptIt();
			parseCondition();
			accept(GrammarSymbols.THEN);
			parseCommand();

			if (currentToken.getKind() == GrammarSymbols.ELSE)
			{
				acceptIt();
				parseCommand();
			}

			accept(GrammarSymbols.END_IF);
			accept(GrammarSymbols.DOT);

		}
		else if (currentToken.getKind() == GrammarSymbols.RETURN)
		{
			acceptIt();
			parseExpression();
			accept(GrammarSymbols.DOT);
		}
		else if (currentToken.getKind() == GrammarSymbols.UNTIL)
		{
			parseWhile();
			accept(GrammarSymbols.END_WHILE);
			accept(GrammarSymbols.DOT);
		}
		else if (currentToken.getKind() == GrammarSymbols.MOVE)
		{
			parseAttrib();
		}
		else if (currentToken.getKind() == GrammarSymbols.PERFORM)
		{
			parseCallProcedure();
		}
		else if (currentToken.getKind() == GrammarSymbols.BREAK || currentToken.getKind() == GrammarSymbols.CONTINUE)
		{
			acceptIt();
		}
		else
		{
			accept(GrammarSymbols.DISPLAY);
			parseExpression();
			accept(GrammarSymbols.DOT);
		}
		return null;
	}

	private While parseWhile() throws SyntacticException
	{
		acceptIt();
		parseCondition();
		parseCommand();
		return null;
	}

	private Expression parseExpression() throws SyntacticException
	{
		parseOperator();
		if (currentToken.getKind() == GrammarSymbols.COMP)
		{
			acceptIt();
			parseOperator();
		}
		return null;
	}

	private Operator parseOperator() throws SyntacticException
	{
		parseTerm();
		while (currentToken.getKind() == GrammarSymbols.PLUS || currentToken.getKind() == GrammarSymbols.MINUS)
		{
			acceptIt();
			parseTerm();
		}
		return null;
	}

	private Term parseTerm() throws SyntacticException
	{
		parseFator();
		while (currentToken.getKind() == GrammarSymbols.MULTIPLICATION
				|| currentToken.getKind() == GrammarSymbols.DIVISION)
		{
			acceptIt();
			parseFator();
		}
		return null;
	}

	private Fator parseFator() throws SyntacticException
	{
		// ALTERANDO boolean por bool
		if (currentToken.getKind() == GrammarSymbols.BOOL)
		{
			acceptIt();
		}
		else if (currentToken.getKind() == GrammarSymbols.ID)
		{
			acceptIt();
		}
		else if (currentToken.getKind() == GrammarSymbols.NUMBER)
		{
			acceptIt();
		}
		else if (currentToken.getKind() == GrammarSymbols.LP)
		{
			acceptIt();
			parseExpression();
			accept(GrammarSymbols.RP);
		}
		else
		{
			accept(GrammarSymbols.PERFORM);
			parseCallProcedure();
		}
		return null;
	}

	private Condition parseCondition() throws SyntacticException
	{
		parseExpression();
		return null;
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
