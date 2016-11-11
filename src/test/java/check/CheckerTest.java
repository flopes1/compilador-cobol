package check;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import checker.Checker;
import checker.SemanticException;
import compiler.Properties;
import parser.Parser;
import parser.SyntacticException;
import util.AST.AST;

public class CheckerTest
{

	private Parser parser = null;
	private Checker checker = null;
	private SyntacticException syntaticException = null;
	private SemanticException semanticException = null;

	@Before
	public void initialize()
	{
		Properties.sourceCodeLocation = this.getClass().getClassLoader().getResource("semantic-test7.cobol")
				.getFile();
		this.parser = new Parser();
		this.checker = new Checker();
	}

	@Test
	public void parse()
	{
		AST ast = null;
		try
		{
			ast = this.parser.parse();
			this.checker.check(ast);
			System.out.println(ast);

		}
		catch (SyntacticException e)
		{
			this.syntaticException = e;
		}
		catch (SemanticException e1)
		{
			this.semanticException = e1;
		}
		assertEquals(true, ast != null);
		assertEquals(true, this.syntaticException == null);
		assertEquals(true, this.semanticException == null);
	}

}
