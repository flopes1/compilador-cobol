package parser;

import java.util.ArrayList;
import java.util.List;

import model.Attrib;
import model.CallProcedure;
import model.Command;
import model.Condition;
import model.DataDivisionScope;
import model.Expression;
import model.Fator;
import model.FatorBool;
import model.FatorCallProcedure;
import model.FatorExpression;
import model.FatorId;
import model.FatorNumber;
import model.Operator;
import model.Procedure;
import model.ProcedureDivisionScope;
import model.Program;
import model.Statement;
import model.StatementAttrib;
import model.StatementBreakContinue;
import model.StatementCallProcedure;
import model.StatementDisplay;
import model.StatementIf;
import model.StatementReturn;
import model.StatementWhile;
import model.Term;
import model.Terminal;
import model.TokenBeginDecl;
import model.TokenBool;
import model.TokenBoolean;
import model.TokenBreak;
import model.TokenComp;
import model.TokenDataDivision;
import model.TokenDisplay;
import model.TokenDiv;
import model.TokenDot;
import model.TokenElse;
import model.TokenEndCom;
import model.TokenEndDecl;
import model.TokenEndProc;
import model.TokenEndWhile;
import model.TokenEndif;
import model.TokenExit;
import model.TokenId;
import model.TokenIdentificatorDivision;
import model.TokenIf;
import model.TokenInteger;
import model.TokenLP;
import model.TokenMinus;
import model.TokenMove;
import model.TokenMult;
import model.TokenNumber;
import model.TokenPerform;
import model.TokenPic;
import model.TokenPlus;
import model.TokenProcedureDivision;
import model.TokenProgramId;
import model.TokenRP;
import model.TokenReturn;
import model.TokenSection;
import model.TokenThen;
import model.TokenTo;
import model.TokenUntil;
import model.VarDeclare;
import model.While;
import scanner.LexicalException;
import scanner.Scanner;
import scanner.Token;
import util.AST.AST;

/**
 * Parser class
 * 
 * @version 2010-august-29
 * @discipline Projeto de Compiladores
 * @author Gustavo H P Carvalho
 * @email gustavohpcarvalho@ecomp.poli.br
 */
public class Parser {

	// The current token
	private Token currentToken = null;
	// The scanner
	private Scanner scanner = null;

	/**
	 * Parser constructor
	 */
	public Parser() {
		// Initializes the scanner object
		this.scanner = new Scanner();
	}

	/**
	 * Veririfes if the current token kind is the expected one
	 * 
	 * @param kind
	 * @throws SyntacticException
	 */ // TODO
	private void accept(int kind) throws SyntacticException {
		// If the current token kind is equal to the expected
		// Gets next token
		// If not
		// Raises an exception
		if (this.currentToken.getKind() == kind) {

			this.acceptIt();
		} else {
			throw new SyntacticException("Illegal Token", this.currentToken);
		}
	}

	/**
	 * Gets next token
	 */ // TODO
	private void acceptIt() {
		try {
			this.currentToken = this.scanner.getNextToken();
		} catch (LexicalException e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * Verifies if the source program is syntactically correct
	 * 
	 * @throws SyntacticException
	 */ // TODO
	public AST parse() throws SyntacticException {

		AST abstractSintaticTree = null;
		this.initializeToken();

		abstractSintaticTree = this.parseProgram();
		accept(GrammarSymbols.EOT);

		return abstractSintaticTree;
	}

	/*
	 * private Program parseProgram() { Program program = null;
	 * 
	 * List<Command> commandList = new ArrayList<Command>();
	 * 
	 * do { Command command = parseCommand(); commandList.add(command);
	 * 
	 * } while (this.currentToken.getKind() != GrammarSymbols.EOT);
	 * 
	 * program = new Program(commandList);
	 * 
	 * return program; }
	 */

	/*
	 * private Command parseCommand() { return null; // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 */

	private Program parseProgram() throws SyntacticException {
		// null
		Program prog = null;
		Terminal datdiv = null, dot4 = null, procdiv = null, dot5 = null;
		DataDivisionScope d = null;
		ProcedureDivisionScope p = null;

		if (currentToken.getKind() == GrammarSymbols.IDENTIFICATOR_DIVISION) {
			Terminal iddiv = new TokenIdentificatorDivision(currentToken);
			acceptIt();

			Terminal dot = new TokenDot(currentToken);
			accept(GrammarSymbols.DOT);

			Terminal progid = new TokenProgramId(currentToken);
			accept(GrammarSymbols.PROGRAMID);

			Terminal dot2 = new TokenDot(currentToken);
			accept(GrammarSymbols.DOT);

			Terminal id = new TokenId(currentToken);
			accept(GrammarSymbols.ID);

			Terminal dot3 = new TokenDot(currentToken);
			accept(GrammarSymbols.DOT);

			if (currentToken.getKind() == GrammarSymbols.DATA_DIVISION) {
				datdiv = new TokenDataDivision(currentToken);
				acceptIt();

				dot4 = new TokenDot(currentToken);
				accept(GrammarSymbols.DOT);
				d = parseDataDivisionScope();

			}

			if (currentToken.getKind() == GrammarSymbols.PROCEDURE_DIVISION) {
				procdiv = new TokenProcedureDivision(currentToken);
				acceptIt();

				dot5 = new TokenDot(currentToken);
				accept(GrammarSymbols.DOT);
				p = parseProcedureDivisionScope();
			}
			prog = new Program(iddiv, dot3, progid, dot2, id, dot3, datdiv, dot4, d, procdiv, dot5, p);
		} else {
			throw new SyntacticException("Qual mensagem colocar?", currentToken);
		}

		return prog;
	}

	// TODO fazer v�rios parseISSO, parseAQUILO

	private VarDeclare parseVarDeclare() throws SyntacticException {
		VarDeclare var = null;
		Terminal intOrBool = null;

		Terminal id = new TokenId(currentToken);
		accept(GrammarSymbols.ID);

		Terminal pic = new TokenPic(currentToken);
		accept(GrammarSymbols.PIC);

		if (currentToken.getKind() == GrammarSymbols.INTEGER) {
			intOrBool = new TokenInteger(currentToken);
			acceptIt();
		} else {
			intOrBool = new TokenBoolean(currentToken);
			accept(GrammarSymbols.BOOLEAN);

		}

		Terminal dot = new TokenDot(currentToken);
		accept(GrammarSymbols.DOT);
		var = new VarDeclare(id, pic, intOrBool, dot);
		return var;

	}

	@SuppressWarnings("null")
	private DataDivisionScope parseDataDivisionScope() throws SyntacticException {
		DataDivisionScope datdiv = null;
		List<VarDeclare> lvd = new ArrayList<VarDeclare>();
		while (true) {
			if (currentToken.getKind() == GrammarSymbols.EXIT) {
				break;
			}

			lvd.add(parseVarDeclare());

		}
		Terminal texit = new TokenExit(currentToken);
		acceptIt();
		Terminal tdot = new TokenDot(currentToken);
		accept(GrammarSymbols.DOT);

		datdiv = new DataDivisionScope(lvd, texit, tdot);

		return datdiv;
	}

	@SuppressWarnings("null")
	private Procedure parseProcedure() throws SyntacticException {
		Procedure pro = null;
		Terminal id = new TokenId(currentToken);
		accept(GrammarSymbols.ID);
		Terminal sec = new TokenSection(currentToken);
		accept(GrammarSymbols.SECTION);
		Terminal dot = new TokenDot(currentToken);
		accept(GrammarSymbols.DOT);

		Terminal boolOrInt = null;
		Terminal id2 = null;
		List<VarDeclare> lvd = new ArrayList<VarDeclare>();

		while (currentToken.getKind() == GrammarSymbols.BOOLEAN || currentToken.getKind() == GrammarSymbols.INTEGER) {
			if (currentToken.getKind() == GrammarSymbols.BOOLEAN) {
				boolOrInt = new TokenBoolean(currentToken);
			} else {
				boolOrInt = new TokenInteger(currentToken);
			}

			acceptIt();
			id2 = new TokenId(currentToken);
			accept(GrammarSymbols.ID);

		}

		Terminal[] dupla = new Terminal[2];
		List<Terminal[]> lt = new ArrayList<Terminal[]>();

		dupla[0] = boolOrInt;
		dupla[1] = id2;

		lt.add(dupla);

		Terminal begindecl = new TokenBeginDecl(currentToken);
		accept(GrammarSymbols.BEGIN_DECL);

		while (true) {

			if (currentToken.getKind() == GrammarSymbols.END_DECL) {
				break;
			}

			lvd.add(parseVarDeclare());

		}
		Terminal enddecl = new TokenEndDecl(currentToken);
		acceptIt();
		Command com = parseCommand();
		Terminal endproc = new TokenEndProc(currentToken);
		accept(GrammarSymbols.END_PROC);
		Terminal dot2 = new TokenDot(currentToken);
		accept(GrammarSymbols.DOT);

		pro = new Procedure(id, sec, dot, lt, begindecl, lvd, enddecl, com, endproc, dot2);

		return pro;
	}

	private ProcedureDivisionScope parseProcedureDivisionScope() throws SyntacticException {
		ProcedureDivisionScope pds = null;
		List<Procedure> lp = new ArrayList<Procedure>();

		while (true) {
			if (currentToken.getKind() == GrammarSymbols.EXIT) {
				break;
			}
			lp.add(parseProcedure());

		}
		Terminal exit = new TokenExit(currentToken);
		acceptIt();
		Terminal dot = new TokenDot(currentToken);
		accept(GrammarSymbols.DOT);

		pds = new ProcedureDivisionScope(lp, exit, dot);

		return pds;

	}

	private Command parseCommand() throws SyntacticException {
		Command com = null;
		List<Statement> ls = new ArrayList<Statement>();
		do {
			ls.add(parseStatement());

			if (currentToken.getKind() == GrammarSymbols.END_COM) {
				break;
			}

		} while (true);

		Terminal tec = new TokenEndCom(currentToken);
		acceptIt();
		Terminal tdot = new TokenDot(currentToken);
		accept(GrammarSymbols.DOT);

		com = new Command(ls, tec, tdot);

		return com;
	}

	private Attrib parseAttrib() throws SyntacticException {
		Attrib at = null;

		Terminal tmov = new TokenMove(currentToken);
		acceptIt();
		Expression e = parseExpression();
		Terminal tto = new TokenTo(currentToken);
		accept(GrammarSymbols.TO);
		Terminal tid = new TokenId(currentToken);
		accept(GrammarSymbols.ID);
		Terminal tdot = new TokenDot(currentToken);
		accept(GrammarSymbols.DOT);

		at = new Attrib(tmov, e, tto, tid, tdot);

		return at;
	}

	private CallProcedure parseCallProcedure() throws SyntacticException {
		CallProcedure cp = null;

		Terminal tperf = new TokenPerform(currentToken);
		acceptIt();
		Terminal tid = new TokenId(currentToken);
		accept(GrammarSymbols.ID);

		Terminal[] dupla = new Terminal[2];
		List<Terminal[]> lt = new ArrayList<Terminal[]>();

		while (currentToken.getKind() == GrammarSymbols.USING) {
			Terminal tusing = new TokenId(currentToken);
			acceptIt();
			Terminal tid2 = new TokenId(currentToken);
			accept(GrammarSymbols.ID);

			dupla[0] = tusing;
			dupla[1] = tid2;

			lt.add(dupla);

		}
		cp = new CallProcedure(tperf, tid, lt);
		return cp;
	}

	private Statement parseStatement() throws SyntacticException {
		Statement sta = null;

		if (currentToken.getKind() == GrammarSymbols.IF) {
			Terminal iff = new TokenIf(currentToken);
			acceptIt();
			Condition cond = parseCondition();
			Terminal thenn = new TokenThen(currentToken);
			accept(GrammarSymbols.THEN);
			Command com = parseCommand();

			Terminal els = null;
			Command com2 = null;

			if (currentToken.getKind() == GrammarSymbols.ELSE) {
				els = new TokenElse(currentToken);
				acceptIt();
				com2 = parseCommand();
			}
			Terminal endif = new TokenEndif(currentToken);
			accept(GrammarSymbols.END_IF);
			Terminal dot = new TokenDot(currentToken);
			accept(GrammarSymbols.DOT);

			sta = new StatementIf(iff, cond, thenn, com, els, com2, endif, dot);
		} else if (currentToken.getKind() == GrammarSymbols.RETURN) {
			Terminal ret = new TokenReturn(currentToken);
			acceptIt();
			Expression exp = parseExpression();
			Terminal dot = new TokenDot(currentToken);
			accept(GrammarSymbols.DOT);

			sta = new StatementReturn(ret, exp, dot);
		} else if (currentToken.getKind() == GrammarSymbols.UNTIL) {
			While whi = parseWhile();
			Terminal endwhi = new TokenEndWhile(currentToken);
			accept(GrammarSymbols.END_WHILE);
			Terminal dot = new TokenDot(currentToken);
			accept(GrammarSymbols.DOT);

			sta = new StatementWhile(whi, endwhi, dot);
		} else if (currentToken.getKind() == GrammarSymbols.MOVE) {
			Attrib at = parseAttrib();

			sta = new StatementAttrib(at);
		} else if (currentToken.getKind() == GrammarSymbols.PERFORM) {
			CallProcedure cal = parseCallProcedure();

			sta = new StatementCallProcedure(cal);
		} else if (currentToken.getKind() == GrammarSymbols.BREAK
				|| currentToken.getKind() == GrammarSymbols.CONTINUE) {
			Terminal boolid;

			if (currentToken.getKind() == GrammarSymbols.BREAK) {
				boolid = new TokenBreak(currentToken);
			} else {
				boolid = new TokenEndWhile(currentToken);
			}

			acceptIt();

			sta = new StatementBreakContinue(boolid);
		} else {
			Terminal dis = new TokenDisplay(currentToken);
			accept(GrammarSymbols.DISPLAY);
			Expression exp = parseExpression();
			Terminal dot = new TokenDot(currentToken);
			accept(GrammarSymbols.DOT);

			sta = new StatementDisplay(dis, exp, dot);
		}

		return sta;
	}

	private While parseWhile() throws SyntacticException {
		While whi = null;
		Terminal until = new TokenUntil(currentToken);
		acceptIt();
		Condition con = parseCondition();
		Command comand = parseCommand();

		whi = new While(until, con, comand);

		return whi;
	}

	private Expression parseExpression() throws SyntacticException {
		Expression e = null;
		Terminal tcomp = null;
		Operator op2 = null;

		Operator op1 = parseOperator();
		if (currentToken.getKind() == GrammarSymbols.COMP) {
			tcomp = new TokenComp(currentToken);
			acceptIt();
			op2 = parseOperator();
		}

		e = new Expression(op1, tcomp, op2);

		return e;
	}

	private Operator parseOperator() throws SyntacticException {
		Operator op = null;
		Term t = parseTerm();
		Terminal tplusorminus = null;
		Term t2 = null;
		while (currentToken.getKind() == GrammarSymbols.PLUS || currentToken.getKind() == GrammarSymbols.MINUS) {
			if (currentToken.getKind() == GrammarSymbols.PLUS) {
				tplusorminus = new TokenPlus(currentToken);
			} else {
				tplusorminus = new TokenMinus(currentToken);
			}

			acceptIt();
			t2 = parseTerm();
		}

		Object[] dupla = new Object[2];
		List<Object[]> listOpAndTerm = new ArrayList<Object[]>();

		dupla[0] = tplusorminus;
		dupla[1] = t2;

		listOpAndTerm.add(dupla);

		op = new Operator(t, listOpAndTerm);

		return op;
	}

	private Term parseTerm() throws SyntacticException {
		Term t = null;
		Fator f = parseFator();
		Terminal tmultordiv = null;
		Fator f2 = null;

		while (currentToken.getKind() == GrammarSymbols.MULTIPLICATION
				|| currentToken.getKind() == GrammarSymbols.DIVISION) {
			if (currentToken.getKind() == GrammarSymbols.PLUS) {
				tmultordiv = new TokenMult(currentToken);
			} else {
				tmultordiv = new TokenDiv(currentToken);
			}

			acceptIt();
			f2 = parseFator();
		}

		Object[] dupla = new Object[2];
		List<Object[]> listOpAndFator = new ArrayList<Object[]>();

		dupla[0] = tmultordiv;
		dupla[1] = f2;

		listOpAndFator.add(dupla);

		t = new Term(f, listOpAndFator);

		return t;
	}

	private Fator parseFator() throws SyntacticException {
		Fator f = null;

		if (currentToken.getKind() == GrammarSymbols.BOOL) {
			Terminal tbool = new TokenBool(currentToken);
			acceptIt();
			f = new FatorBool(tbool);
		} else if (currentToken.getKind() == GrammarSymbols.ID) {
			Terminal tid = new TokenId(currentToken);
			acceptIt();

			f = new FatorId(tid);
		} else if (currentToken.getKind() == GrammarSymbols.NUMBER) {
			Terminal tnum = new TokenNumber(currentToken);
			acceptIt();
			f = new FatorNumber(tnum);
		} else if (currentToken.getKind() == GrammarSymbols.LP) {
			Terminal tlp = new TokenLP(currentToken);
			acceptIt();
			Expression e = parseExpression();
			Terminal trp = new TokenRP(currentToken);
			accept(GrammarSymbols.RP);

			f = new FatorExpression(tlp, e, trp);
		} else {
			CallProcedure cp = parseCallProcedure();

			f = new FatorCallProcedure(cp);
		}

		return f;
	}

	private Condition parseCondition() throws SyntacticException {
		Condition c = null;
		Expression e = parseExpression();

		c = new Condition(e);

		return c;
	}

	private void initializeToken() {
		if (this.currentToken == null) {
			try {
				this.currentToken = this.scanner.getNextToken();
			} catch (LexicalException e) {
				System.err.println(e.toString());
			}
		}
	}

}
