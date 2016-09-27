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
		Properties.sourceCodeLocation = this.getClass().getClassLoader().getResource("text1.cobol").getFile();
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
				token = scanner.getNextToken();
			}
			catch (LexicalException e)
			{
				this.e = e;
			}

			assertEquals(e == null, true);
			assertEquals(token != null, true);
			
		} while (token.getKind() != 1000);
	}

}
