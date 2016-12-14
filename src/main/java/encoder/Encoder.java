package encoder;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import checker.IVisitor;
import checker.SemanticException;
import model.Attrib;
import model.CallProcedure;
import model.Command;
import model.Condition;
import model.DataDivisionScope;
import model.Expression;
import model.FatorBool;
import model.FatorCallProcedure;
import model.FatorExpression;
import model.FatorId;
import model.FatorNumber;
import model.Instruction;
import model.Operator;
import model.Procedure;
import model.ProcedureDivisionScope;
import model.Program;
import model.StatementAttrib;
import model.StatementBreak;
import model.StatementCallProcedure;
import model.StatementContinue;
import model.StatementDisplay;
import model.StatementIf;
import model.StatementReturn;
import model.StatementWhile;
import model.Term;
import model.TerminalBool;
import model.TerminalBoolean;
import model.TerminalComp;
import model.TerminalId;
import model.TerminalInteger;
import model.TerminalNumber;
import model.TerminalOperations;
import model.VarDeclare;
import model.While;
import util.AST.AST;

public class Encoder implements IVisitor
{

	private List<Instruction> instructionList = null;
	private int nextInstr = 0;
	
	//colocar os puts quando estiver visitando a lista de paramatros e as variaveis locais.
	private Hashtable<String, Integer> vars;

	public Encoder()
	{
		this.instructionList = new ArrayList<Instruction>();
	}

	public void encode(AST decotaredAST)
	{
		try
		{
			decotaredAST.visit(this, null);
		}
		catch (SemanticException ex)
		{
			System.err.println(ex.toString());
		}
	}

	private void emit(String instructionSection, String instruction)
	{
		Instruction newInstruction = new Instruction(instructionSection, instruction);
		this.instructionList.add(newInstruction);
	}

	private void emit(String instruction)
	{
		emit("text", instruction);
	}

	public Object visitProgram(Program program, ArrayList<AST> list) throws SemanticException
	{
		emit("extern", "extern _printf");
		
		if (program.getDataDivisionScope() != null)
		{
			program.getDataDivisionScope().visit(this, list);
		}

		if (program.getProcedureDivisionScope() != null)
		{
			program.getProcedureDivisionScope().visit(this, list);
		}

		

		return null;
	}

	public Object visitDataDivisionScope(DataDivisionScope dataDivisionScope, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitProcedureDivisionScope(ProcedureDivisionScope procedureDivisionScope, ArrayList<AST> list)
			throws SemanticException
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitVariablesDeclare(VarDeclare varDeclare, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitProcedure(Procedure procedure, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitCommand(Command command, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementIf(StatementIf statementIf, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementWhile(StatementWhile statementWhile, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementDisplay(StatementDisplay statementDisplay, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementReturn(StatementReturn statementReturn, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementAttribution(StatementAttrib statementAttrib, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementCallProcedure(StatementCallProcedure statementCallProcedure, ArrayList<AST> list)
			throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementBreak(StatementBreak statementBreak, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementContinue(StatementContinue statementContinue, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitCondition(Condition condition, ArrayList<AST> list) throws SemanticException
	{
		Expression expression = condition.getExpression();

		expression.visit(this, list);
		
		return null;
	}

	public Object visitWhile(While whileCommand, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitAttrib(Attrib attrib, ArrayList<AST> list) throws SemanticException
	{
		TerminalId terminalId = (TerminalId) attrib.getTokenId();
		VarDeclare varDeclare = (VarDeclare)terminalId.getDeclaredTerminalIdNode();
		attrib.getExpression().visit(this, list);
		switch (varDeclare.getScope()) {
		case 0:
			emit("pop dword [" + attrib.getTokenId().getToken().getSpelling() + "]");
			break;
		case 1:
			emit("pop dword [ebp+" + vars.get(attrib.getTokenId().getToken().getSpelling()) + "]");
			break;
		default:
			emit("pop dword [ebp" + vars.get(attrib.getTokenId().getToken().getSpelling()) + "]");
		}
		return null;
		
	}

	public Object visitCallProcedure(CallProcedure callProcedure, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitExpression(Expression expression, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitOperator(Operator operator, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitTerm(Term term, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitFatorCallProcedure(FatorCallProcedure fatorCallProcedure, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitFatorBool(FatorBool fatorBool, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitFatorIdentificator(FatorId fatorId, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitFatorNumber(FatorNumber fatorNumber, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitFatorExpression(FatorExpression fatorExpression, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitTerminalTypeBoolean(TerminalBoolean terminalBoolean, ArrayList<AST> list)
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitTerminalBool(TerminalBool terminalBool, ArrayList<AST> list)
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitTerminalTypeInteger(TerminalInteger terminalInteger, ArrayList<AST> list)
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitTerminalNumber(TerminalNumber terminalNumber, ArrayList<AST> list)
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitTerminalIdentificator(TerminalId terminalId, ArrayList<AST> list) throws SemanticException
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitTerminalComparation(TerminalComp terminalComp, ArrayList<AST> list)
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitTerminalOperations(TerminalOperations terminalOperations, ArrayList<AST> list)
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

}
