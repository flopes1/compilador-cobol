package parser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import checker.Checker;
import checker.SemanticException;
import compiler.Properties;
import util.AST.AST;

public class ParserTest
{

	private Parser parser;
	private Checker checker;
	private SyntacticException syntaticException = null;
	private SemanticException semanticException = null;

	@Before
	public void initialize()
	{
		Properties.sourceCodeLocation = this.getClass().getClassLoader().getResource("Nome do seu teste semantico.cobol").getFile();
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
