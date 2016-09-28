package parser;

import java.util.ArrayList;
import java.util.List;

import model.Command;
import model.Program;
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
	//marcos
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

		Program prog = null;

		if (currentToken.getKind() == GrammarSymbols.IDENTIFICATOR_DIVISION)
		{
			acceptIt();
			accept(GrammarSymbols.DOT);
			accept(GrammarSymbols.PROGRAMID);
			accept(GrammarSymbols.DOT);
			accept(GrammarSymbols.ID);
			accept(GrammarSymbols.DOT);

			if (currentToken.getKind() == GrammarSymbols.DATA_DIVISION)
			{
				acceptIt();
				accept(GrammarSymbols.DOT);
				parseDataDivisionScope();
			}

			if (currentToken.getKind() == GrammarSymbols.PROCEDURE_DIVISION)
			{
				acceptIt();
				accept(GrammarSymbols.DOT);
				parseProcedureDivisionScope();
			}

		}
		else
		{
			throw new SyntacticException("Qual mensagem colocar?", currentToken);
		}

		return prog;
	}

	// TODO fazer vários parseISSO, parseAQUILO

	private void parseVarDeclare() throws SyntacticException
	{
		if (currentToken.getKind() == GrammarSymbols.ID)
		{
			acceptIt();
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
		}
	}

	private void parseDataDivisionScope() throws SyntacticException
	{
		while (true)
		{
			if (currentToken.getKind() == GrammarSymbols.EXIT)
			{
				break;
			}
			parseVarDeclare();
			accept(GrammarSymbols.DOT);

		}

		acceptIt();
		accept(GrammarSymbols.DOT);
	}

	private void parseProcedure() throws SyntacticException
	{
		if (currentToken.getKind() == GrammarSymbols.ID)
		{
			acceptIt();
			accept(GrammarSymbols.SECTION);
			accept(GrammarSymbols.DOT);
			while (currentToken.getKind() == GrammarSymbols.BOOLEAN || currentToken.getKind() == GrammarSymbols.INTEGER)
			{
				acceptIt();
				accept(GrammarSymbols.ID);
			}
			while (true)
			{
				//Nao vai rolar
				if (currentToken.getKind() == GrammarSymbols.DOT)
				{
					break;
				}

				parseVarDeclare();
			}
			acceptIt(); //Nao vai rolar
			parseCommand();
			accept(GrammarSymbols.END_PROC);
			accept(GrammarSymbols.DOT);

		}

	}

	private void parseProcedureDivisionScope() throws SyntacticException
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

	}

	private void parseCommand() throws SyntacticException
	{
		do
		{
			parseStatement();
			//Nao vai rolar
			if (currentToken.getKind() == GrammarSymbols.EXIT)
			{
				break;
			}

		} while (true);

		acceptIt(); //Nao vai rolar
		accept(GrammarSymbols.DOT); //Nao vai rolar

	}

	private void parseAttrib() throws SyntacticException
	{
		acceptIt();
		parseExpression();
		accept(GrammarSymbols.TO);
		accept(GrammarSymbols.ID);
		accept(GrammarSymbols.DOT);
	}

	private void parseCallProcedure() throws SyntacticException
	{
		acceptIt();
		accept(GrammarSymbols.ID);

		while (currentToken.getKind() == GrammarSymbols.USING) {
			acceptIt();
			accept(GrammarSymbols.ID);

		}
	}

	private void parseStatement() throws SyntacticException
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
	}

	private void parseWhile() throws SyntacticException
	{
		acceptIt();
		parseCondition();
		parseCommand();
	}

	private void parseExpression() throws SyntacticException
	{
		parseOperator();
		if (currentToken.getKind() == GrammarSymbols.COMP)
		{
			acceptIt();
			parseOperator();
		}
	}

	private void parseOperator() throws SyntacticException
	{
		parseTerm();
		while (currentToken.getKind() == GrammarSymbols.PLUS || currentToken.getKind() == GrammarSymbols.MINUS)
		{
			acceptIt();
			parseTerm();
		}
	}

	private void parseTerm() throws SyntacticException
	{
		parseFator();
		while (currentToken.getKind() == GrammarSymbols.MULTIPLICATION
				|| currentToken.getKind() == GrammarSymbols.DIVISION)
		{
			acceptIt();
			parseFator();
		}
	}

	private void parseFator() throws SyntacticException
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
	}

	private void parseCondition() throws SyntacticException
	{
		parseExpression();
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
