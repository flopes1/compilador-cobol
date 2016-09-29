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

public class ProgramsTest
{

	private List<Parser> parsers = null;
	private Exception e = null;

	@Before
	public void initialize()
	{
		parsers = this.getMockParsers();
	}

	@Test
	public void parseProgram()
	{
		int i = 1;
		try
		{
			for (Parser parser : this.parsers)
			{
				AST tree = parser.parse();
				i++;
				assertEquals(true, tree != null);
				
			}
		}
		catch (SyntacticException e)
		{
			System.err.println(i);
			this.e = e;
			System.err.println(e.toString());
		}
		
		assertEquals(true, e == null);
	}

	private List<Parser> getMockParsers()
	{
		List<Parser> parsers = new ArrayList<Parser>();

		for (int i = 0; i < 12; i++)
		{
			Properties.sourceCodeLocation = this.getClass().getClassLoader().getResource("program" + (i + 1) + ".cobol")
					.getFile();
			Parser p = new Parser();
			parsers.add(p);
		}

		return parsers;
	}

}
