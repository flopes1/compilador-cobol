package programs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import compiler.Properties;
import parser.Parser;
import parser.SyntacticException;
import util.AST.AST;

public class Program
{

	private List<Parser> parsers = null;
	private Exception e = null;

	@Before
	public void initialize()
	{
		parsers = this.getMockParsers();
	}

	@Test
	private void parseProgram()
	{
		try
		{

			for (Parser parser : this.parsers)
			{
				AST tree = parser.parse();
				
				assertEquals(true, tree != null);
				
			}
		}
		catch (SyntacticException e)
		{
			this.e = e;
		}
		
		assertEquals(true, e == null);
	}

	private List<Parser> getMockParsers()
	{
		List<Parser> parsers = new ArrayList<Parser>();

		for (int i = 0; i < 20; i++)
		{
			Properties.sourceCodeLocation = this.getClass().getClassLoader().getResource("program" + i + 1 + ".cobol")
					.getFile();
			Parser p = new Parser();
			parsers.add(p);
		}

		return parsers;
	}

}
