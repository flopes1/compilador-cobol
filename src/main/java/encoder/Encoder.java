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
import model.Terminal;
import model.TerminalBool;
import model.TerminalBoolean;
import model.TerminalComp;
import model.TerminalId;
import model.TerminalInteger;
import model.TerminalNumber;
import model.TerminalOperations;
import model.VarDeclare;
import model.While;
import util.InstructionsCommons;
import util.AST.AST;

public class Encoder implements IVisitor
{

	private int nextInstr = 0;
	private int countCmp;
	private ObjectCode objectCode;

	// colocar os puts quando estiver visitando a lista de paramatros e as
	// variaveis locais.
	private Hashtable<String, Integer> vars;

	public Encoder()
	{
		objectCode = new ObjectCode();

	}

	public void encode(AST decotaredAST)
	{
		try
		{
			decotaredAST.visit(this, new ArrayList<AST>());
		}
		catch (SemanticException ex)
		{
			System.err.println(ex.toString());
		}
	}

	public void emit(String section, String instruction)
	{
		objectCode.addInstruction(section, new Instruction(instruction));
	}

	private void emit(String instruction)
	{
		emit(InstructionsCommons.TEXT_FIELD, instruction);
	}

	public Object visitProgram(Program program, Object object) throws SemanticException
	{
		emit("extern", "extern _printf");

		if (program.getDataDivisionScope() != null)
		{
			program.getDataDivisionScope().visit(this, object);
		}

		if (program.getProcedureDivisionScope() != null)
		{
			program.getProcedureDivisionScope().visit(this, object);
		}

		return null;
	}

	public Object visitDataDivisionScope(DataDivisionScope dataDivisionScope, Object object) throws SemanticException
	{

		List<VarDeclare> varDeclareList = dataDivisionScope.getVarDeclareList();

		for (VarDeclare varDeclare : varDeclareList)
		{
			this.emit(InstructionsCommons.DATA_FIELD, varDeclare.getTerminalId().getToken().getSpelling()
					+ InstructionsCommons.TYPE_BOOLEAN_INTEGER_INIT);
		}

		return null;
	}

	public Object visitProcedureDivisionScope(ProcedureDivisionScope procedureDivisionScope, Object object)
			throws SemanticException
	{
		List<Procedure> procedureList = procedureDivisionScope.getProcedureList();

		for (Procedure procedure : procedureList)
		{
			String procedureId = procedure.getTokenId().getToken().getSpelling();
			if (procedureId.equalsIgnoreCase(InstructionsCommons.MAIN))
			{
				procedureId = InstructionsCommons.DEFAULT_MAIN;
			}
			this.emit("_" + procedureId + ":");

			this.savePointersStates();

			procedure.visit(this, object);

			this.RestourePointersStates();

			this.emit(InstructionsCommons.RETURN);
		}

		return null;
	}

	private void savePointersStates()
	{
		this.emit(InstructionsCommons.PUSH + " " + InstructionsCommons.EBP);
		this.emit(InstructionsCommons.MOV + " " + InstructionsCommons.EBP + "," + InstructionsCommons.ESP);
	}

	private void RestourePointersStates()
	{
		this.emit(InstructionsCommons.MOV + " " + InstructionsCommons.ESP + "," + InstructionsCommons.EBP);
		this.emit(InstructionsCommons.POP + " " + InstructionsCommons.EBP);
	}

	public Object visitVariablesDeclare(VarDeclare varDeclare, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitProcedure(Procedure procedure, Object object) throws SemanticException
	{
		// manter essa atribuição
		countCmp = 1;

		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitCommand(Command command, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementIf(StatementIf statementIf, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementWhile(StatementWhile statementWhile, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementDisplay(StatementDisplay statementDisplay, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementReturn(StatementReturn statementReturn, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementAttribution(StatementAttrib statementAttrib, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementCallProcedure(StatementCallProcedure statementCallProcedure, Object object)
			throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementBreak(StatementBreak statementBreak, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitStatementContinue(StatementContinue statementContinue, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitCondition(Condition condition, Object object) throws SemanticException
	{
		Expression expression = condition.getExpression();

		expression.visit(this, object);

		return null;
	}

	public Object visitWhile(While whileCommand, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Fernando
		return null;
	}

	public Object visitAttrib(Attrib attrib, Object object) throws SemanticException
	{
		TerminalId terminalId = (TerminalId) attrib.getTokenId();
		VarDeclare varDeclare = (VarDeclare) terminalId.getDeclaredTerminalIdNode();
		attrib.getExpression().visit(this, object);
		switch (varDeclare.getScope())
		{
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

	public Object visitCallProcedure(CallProcedure callProcedure, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitExpression(Expression expression, Object object) throws SemanticException
	{
		@SuppressWarnings("unchecked")
		ArrayList<AST> list = (ArrayList<AST>) object;
		String functionId = "";

		for (AST ast : list)
		{
			if (ast instanceof Procedure)
			{
				functionId = ((Procedure) ast).getTokenId().getToken().getSpelling();
				break;
			}
		}

		if (expression.getOptionalOperator() == null)
		{
			expression.getMandatoryOperator().visit(this, object);
		}
		else
		{
			expression.getMandatoryOperator().visit(this, list);
			expression.getTokenComparator().visit(this, object);

			emit("pop ebx");
			emit("pop eax");
			emit("cmp eax, ebx");
			// switch (expression.getTokenComparator().getToken().getSpelling())
			// {
			// case "==":
			// emit(functionId + "jne _false_cmp_" + countCmp);
			// break;
			// case "!=":
			// emit(functionId + "je _false_cmp_" + countCmp);
			// break;
			// case "<":
			// emit(functionId + "jge _false_cmp_" + countCmp);
			// break;
			// case "<=":
			// emit(functionId + "jg _false_cmp_" + countCmp);
			// break;
			// case ">":
			// emit(functionId + "jle _false_cmp_" + countCmp);
			// break;
			// case ">=":
			// emit(functionId + "jl _false_cmp_" + countCmp);
			// break;
			// }
			emit("push dword 1");
			emit("jmp" + functionId + "_end_cmp_" + countCmp);
			emit(functionId + "_false_cmp_" + countCmp + ":");
			emit("push dword 0");
			emit(functionId + "_end_cmp_" + countCmp + ":");
			countCmp++;
		}
		return null;
	}

	public Object visitOperator(Operator operator, Object object) throws SemanticException
	{
		if (operator.getOperatorTerminalList().isEmpty())
		{
			operator.getOperatorTermList().get(0).visit(this, object);
		}
		else
		{
			int i = 0;
			for (Terminal t : operator.getOperatorTerminalList())
			{
				if (i == 0)
				{
					operator.getOperatorTermList().get(0).visit(this, object);
					i++;
				}
				t.visit(this, object);

				emit("pop ebx");
				emit("pop eax");
				if (t.getToken().getSpelling().equals("+"))
				{
					emit("add eax, ebx");
				}
				else
				{
					emit("sub eax, ebx");
				}
				emit("push eax");
			}
		}
		return null;
	}

	public Object visitTerm(Term term, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitFatorCallProcedure(FatorCallProcedure fatorCallProcedure, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitFatorBool(FatorBool fatorBool, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitFatorIdentificator(FatorId fatorId, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitFatorNumber(FatorNumber fatorNumber, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitFatorExpression(FatorExpression fatorExpression, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitTerminalTypeBoolean(TerminalBoolean terminalBoolean, Object object)
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitTerminalBool(TerminalBool terminalBool, Object object)
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitTerminalTypeInteger(TerminalInteger terminalInteger, Object object)
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitTerminalNumber(TerminalNumber terminalNumber, Object object)
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitTerminalIdentificator(TerminalId terminalId, Object object) throws SemanticException
	{
		// TODO Auto-generated method stub Filipe
		return null;
	}

	public Object visitTerminalComparation(TerminalComp terminalComp, Object object)
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

	public Object visitTerminalOperations(TerminalOperations terminalOperations, Object object)
	{
		// TODO Auto-generated method stub Marcos
		return null;
	}

}
