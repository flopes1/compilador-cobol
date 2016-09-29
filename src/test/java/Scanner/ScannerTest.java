package Scanner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import compiler.Properties;
import scanner.LexicalException;
import scanner.Scanner;
import scanner.Token;

public class ScannerTest
{

	private Scanner scanner;
	private LexicalException e = null;

	@Before
	public void initialize()
	{
		Properties.sourceCodeLocation = this.getClass().getClassLoader().getResource("program1.cobol").getFile();
		this.scanner = new Scanner();
	}

	@Test
	public void getNextToken()
	{

		Token token = null;

		do
		{
			try
			{
				token = this.scanner.getNextToken();
				//System.out.println(token.getSpelling());
			}
			catch (LexicalException e)
			{
				this.e = e;
			}

			assertEquals(true, this.e == null);
			assertEquals(true, token != null);

		} while (token.getKind() != 1000);
	}

}
