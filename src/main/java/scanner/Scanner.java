package scanner;

import compiler.Properties;
import parser.GrammarSymbols;
import util.Arquivo;

/**
 * Scanner class
 * 
 * @version 2010-september-04
 * @discipline Compiladores
 * @author Gustavo H P Carvalho
 * @email gustavohpcarvalho@ecomp.poli.br
 */
public class Scanner
{

	// The file object that will be used to read the source code
	private Arquivo file;
	// The last char read from the source code
	private char currentChar;
	// The kind of the current token
	private int currentKind;
	// Buffer to append characters read from file
	private StringBuffer currentSpelling;
	// Current line and column in the source file
	private int line, column;

	/**
	 * Default constructor
	 */
	public Scanner()
	{
		this.file = new Arquivo(Properties.sourceCodeLocation);
		this.line = 0;
		this.column = 0;
		this.currentChar = this.file.readChar();
	}

	/**
	 * Returns the next token
	 * 
	 * @return
	 * @throws LexicalException
	 */ // TODO
	public Token getNextToken() throws LexicalException
	{

		// Initializes the string buffer
		// Ignores separators
		// Clears the string buffer
		// Scans the next token
		// Creates and returns a token for the lexema identified

		Token nextToken = null;

		try
		{

			this.currentSpelling = new StringBuffer();
			this.scanSeparator();
			this.currentSpelling = new StringBuffer();
			this.currentKind = this.scanToken();
			nextToken = new Token(this.currentKind, this.currentSpelling.toString(), this.line, this.column);

		}
		catch (LexicalException e)
		{

			throw e;

		}

		return nextToken;
	}

	/**
	 * Returns if a character is a separator
	 * 
	 * @param c
	 * @return
	 */
	private boolean isSeparator(char c)
	{
		if (c == '#' || c == ' ' || c == '\n' || c == '\t')
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Reads (and ignores) a separator
	 * 
	 * @throws LexicalException
	 */ // TODO
	private void scanSeparator() throws LexicalException
	{

		// If it is a comment line
		// Gets next char
		// Reads characters while they are graphics or '\t'
		// A command line should finish with a \n

		while ((this.isSeparator(this.currentChar) || !this.isGraphic(this.currentChar)) && this.currentChar != '\0')
		{

			if (this.currentChar == '#')
			{
				while (this.currentChar != '\n')
				{
					this.getNextChar();
				}
			}

			this.getNextChar();
		}
	}

	/**
	 * Gets the next char
	 */
	private void getNextChar()
	{
		// Appends the current char to the string buffer
		this.currentSpelling.append(this.currentChar);
		// Reads the next one
		this.currentChar = this.file.readChar();
		// Increments the line and column
		this.incrementLineColumn();
	}

	/**
	 * Increments line and column
	 */
	private void incrementLineColumn()
	{
		// If the char read is a '\n', increments the line variable and assigns
		// 0 to the column
		if (this.currentChar == '\n')
		{
			this.line++;
			this.column = 0;
			// If the char read is not a '\n'
		}
		else
		{
			// If it is a '\t', increments the column by 4
			if (this.currentChar == '\t')
			{
				this.column = this.column + 4;
				// If it is not a '\t', increments the column by 1
			}
			else
			{
				this.column++;
			}
		}
	}

	/**
	 * Returns if a char is a digit (between 0 and 9)
	 * 
	 * @param c
	 * @return
	 */
	private boolean isDigit(char c)
	{
		if (c >= '0' && c <= '9')
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Returns if a char is a letter (between a and z or between A and Z)
	 * 
	 * @param c
	 * @return
	 */
	private boolean isLetter(char c)
	{
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Returns if a char is a graphic (any ASCII visible character)
	 * 
	 * @param c
	 * @return
	 */
	private boolean isGraphic(char c)
	{
		if (c >= ' ' && c <= '~')
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Scans the next token Simulates the DFA that recognizes the language
	 * described by the lexical grammar
	 * 
	 * @return
	 * @throws LexicalException
	 */ // TODO
	private int scanToken() throws LexicalException
	{
		// The initial automata state is 0
		// While loop to simulate the automata
		int state = 0;

		while (true)
		{
			switch (state)
			{
				case 0:
				{
					if (this.currentChar == '\0')
					{
						state = 1;
					}
					else if (isLetter(this.currentChar))
					{
						state = 2;
						this.getNextChar();
					}
					else if (isDigit(this.currentChar))
					{
						state = 3;
						this.getNextChar();
					}
					else if (this.currentChar == '.')
					{
						state = 4;
						this.getNextChar();
					}
					else if (this.currentChar == '!')
					{
						state = 6;
						this.getNextChar();
					}
					else if (this.currentChar == '>' || this.currentChar == '<')
					{
						state = 5;
						this.getNextChar();

						if (this.currentChar == '=')
						{
							this.getNextChar();
						}
					}
					else if (this.currentChar == '=')
					{
						state = 5;
						this.getNextChar();
						
						if (this.currentChar == '=')
						{
							this.getNextChar();
						}
					}
					else if (this.currentChar == '+')
					{
						state = 7;
						this.getNextChar();
					}
					else if (this.currentChar == '-')
					{
						state = 8;
						this.getNextChar();
					}
					else if (this.currentChar == '*')
					{
						state = 9;
						this.getNextChar();
					}
					else if (this.currentChar == '/')
					{
						state = 10;
						this.getNextChar();
					}
					else if (this.currentChar == '(')
					{
						state = 11;
						this.getNextChar();
					}
					else if (this.currentChar == ')')
					{
						state = 12;
						this.getNextChar();
					}
					else
					{
						state = 13;
					}
					break;
				}
				case 1:
					return GrammarSymbols.EOT;

				case 2:
				{
					while (this.isLetter(this.currentChar) || this.isDigit(this.currentChar))
					{
						this.getNextChar();
					}
					return this.getTokenWithStringKind();
				}
				case 3:
				{
					while (this.isDigit(this.currentChar))
					{
						this.getNextChar();
					}
					return GrammarSymbols.NUMBER;
				}
				case 4:
					return GrammarSymbols.DOT;
				case 5:
				{
					return GrammarSymbols.COMP;
				}
				case 6:
				{
					if (this.currentChar == '=')
					{
						state = 5;
						this.getNextChar();
					}
					else
					{
						state = 13;
					}
					break;
				}
				case 7:
					return GrammarSymbols.PLUS;
				case 8:
					return GrammarSymbols.MINUS;
				case 9:
					return GrammarSymbols.MULTIPLICATION;
				case 10:
					return GrammarSymbols.DIVISION;
				case 11:
					return GrammarSymbols.LP;
				case 12:
					return GrammarSymbols.RP;
				case 13:
					throw new LexicalException("Invalid character", this.currentChar, this.line, this.column);
			}
		}
	}

	private int getTokenWithStringKind()
	{

		if (this.currentSpelling.toString().equalsIgnoreCase("IDENTIFICATORDIVISION"))
		{
			return GrammarSymbols.IDENTIFICATOR_DIVISION;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("DATADIVISION"))
		{
			return GrammarSymbols.DATA_DIVISION;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("PROCEDUREDIVISION"))
		{
			return GrammarSymbols.PROCEDURE_DIVISION;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("PROGRAMID"))
		{
			return GrammarSymbols.PROGRAMID;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("TRUE"))
		{
			return GrammarSymbols.BOOL;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("FALSE"))
		{
			return GrammarSymbols.BOOL;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("BOOLEAN"))
		{
			return GrammarSymbols.BOOLEAN;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("INTEGER"))
		{
			return GrammarSymbols.INTEGER;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("EXIT"))
		{
			return GrammarSymbols.EXIT;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("MOVE"))
		{
			return GrammarSymbols.MOVE;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("TO"))
		{
			return GrammarSymbols.TO;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("PERFORM"))
		{
			return GrammarSymbols.PERFORM;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("USING"))
		{
			return GrammarSymbols.USING;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("UNTIL"))
		{
			return GrammarSymbols.UNTIL;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("PIC"))
		{
			return GrammarSymbols.PIC;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("SECTION"))
		{
			return GrammarSymbols.SECTION;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("RETURN"))
		{
			return GrammarSymbols.RETURN;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("DISPLAY"))
		{
			return GrammarSymbols.DISPLAY;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("IF"))
		{
			return GrammarSymbols.IF;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("THEN"))
		{
			return GrammarSymbols.THEN;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("ELSE"))
		{
			return GrammarSymbols.ELSE;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("ENDIF"))
		{
			return GrammarSymbols.END_IF;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("ENDWHILE"))
		{
			return GrammarSymbols.END_WHILE;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("ENDPROC"))
		{
			return GrammarSymbols.END_PROC;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("BREAK"))
		{
			return GrammarSymbols.BREAK;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("CONTINUE"))
		{
			return GrammarSymbols.CONTINUE;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("BEGINDECLARATIONS"))
		{
			return GrammarSymbols.BEGIN_DECL;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("ENDDECLARATIONS"))
		{
			return GrammarSymbols.END_DECL;
		}
		else if (this.currentSpelling.toString().equalsIgnoreCase("ENDCOM"))
		{
			return GrammarSymbols.END_COM;
		}
		else
		{
			return GrammarSymbols.ID;
		}

	}

}
