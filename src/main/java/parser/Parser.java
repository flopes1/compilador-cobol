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

	private Program parseProgram()
	{
		Program program = null;

		List<Command> commandList = new ArrayList<Command>();

		do
		{
			Command command = parseCommand();
			commandList.add(command);

		} while (this.currentToken.getKind() != GrammarSymbols.EOT);

		program = new Program(commandList);

		return program;
	}

	private Command parseCommand()
	{
		return null;
		// TODO Auto-generated method stub

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
