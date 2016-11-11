package check;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import compiler.Properties;
import parser.Parser;
import parser.SyntacticException;
import util.AST.AST;

public class CheckerTest
{

	private Parser parser = null;
	private SyntacticException e = null;

	@Before
	public void initialize()
	{
		Properties.sourceCodeLocation = this.getClass().getClassLoader().getResource("nome do seu teste.cobol").getFile();
		this.parser = new Parser();
	}
	
	@Test
	public void check()
	{
		try
		{
			AST ast = this.parser.parse();
			
		}
		catch (SyntacticException e)
		{
			this.e = e;
		}
		
		assertEquals(true, this.e == null);
	}
	
}
