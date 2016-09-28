package parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import compiler.Properties;
import scanner.LexicalException;
import scanner.Scanner;
import scanner.Token;

public class ParserTest
{

	private Parser parser;
	private SyntacticException e = null;
	
	@Before
	public void initialize()
	{
		Properties.sourceCodeLocation = this.getClass().getClassLoader().getResource("text4.cobol").getFile();
		this.parser = new Parser();
	}

	@Test
	public void parse(){
		try {
			this.parser.parse();
		} catch (SyntacticException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
