package parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import compiler.Properties;
import scanner.LexicalException;
import scanner.Scanner;
import scanner.Token;
import util.AST.AST;

public class ParserTest
{

	private Parser parser;
	private SyntacticException e = null;

	@Before
	public void initialize()
	{
		Properties.sourceCodeLocation = this.getClass().getClassLoader().getResource("program1.cobol").getFile();
		this.parser = new Parser();
	}

	@Test
	public void parse()
	{
		AST ast = null;
		try
		{
			ast = this.parser.parse();
		}
		catch (SyntacticException e)
		{
			this.e = e;
		}
		assertEquals(true, ast != null);
		assertEquals(true, this.e == null);
	}

}
