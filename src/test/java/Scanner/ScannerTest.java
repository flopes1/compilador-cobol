package Scanner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import compiler.Properties;
import scanner.LexicalException;
import scanner.Scanner;
import scanner.Token;

public class ScannerTest
{

	private List<Scanner> scanners = new ArrayList<Scanner>();
	private LexicalException e = null;

	@Before
	public void initialize()
	{
		this.scanners = this.getMockScanners();
	}

	@Test
	public void getNextToken()
	{
		for (Scanner scanner : this.scanners)
		{

			Token token = null;

			do
			{
				try
				{
					token = scanner.getNextToken();
					System.out.println(token.getSpelling());
				}
				catch (LexicalException e)
				{
					this.e = e;
				}

				assertEquals(true, this.e == null);
				assertEquals(true, token != null);

			} while (token.getKind() != 1000);
			System.err.println("------------------------------------------------------");
		}

	}

	private List<Scanner> getMockScanners()
	{
		List<Scanner> scanners = new ArrayList<Scanner>();

		for (int i = 0; i < 20; i++)
		{
			Properties.sourceCodeLocation = this.getClass().getClassLoader().getResource("program" + (i+1) + ".cobol")
					.getFile();
			Scanner scanner = new Scanner();
			scanners.add(scanner);
		}

		return scanners;
	}

}
