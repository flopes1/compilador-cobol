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
		this.objectCode = new ObjectCode();
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
		this.objectCode.addInstruction(section, new Instruction(instruction));
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
		// Filipe
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
		// Filipe
		List<Procedure> procedureList = procedureDivisionScope.getProcedureList();

		for (Procedure procedure : procedureList)
		{
			String procedureId = procedure.getTokenId().getToken().getSpelling();
			if (procedureId.equalsIgnoreCase(InstructionsCommons.MAIN))
			{
				procedureId = InstructionsCommons.DEFAULT_MAIN;
			}
			this.emit("_" + procedureId.toLowerCase() + ":");

			this.savePointersStates();

			procedure.visit(this, object);

			this.restourePointersStates();

			this.emit(InstructionsCommons.RETURN);
		}

		return null;
	}

	private void savePointersStates()
	{
		this.emit(InstructionsCommons.PUSH + " " + InstructionsCommons.EBP);
		this.emit(InstructionsCommons.MOV + " " + InstructionsCommons.EBP + ", " + InstructionsCommons.ESP);
	}

	private void restourePointersStates()
	{
		this.emit(InstructionsCommons.MOV + " " + InstructionsCommons.ESP + ", " + InstructionsCommons.EBP);
		this.emit(InstructionsCommons.POP + " " + InstructionsCommons.EBP);
	}

	public Object visitVariablesDeclare(VarDeclare varDeclare, Object object) throws SemanticException
	{
		// Filipe
		this.emit(InstructionsCommons.SUB + " " + InstructionsCommons.ESP + ", "
				+ InstructionsCommons.BOOLEAN_INTEGER_SIZE);
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
		VarDeclare varDeclare = (VarDeclare)terminalId.getDeclaredTerminalIdNode();
		attrib.getExpression().visit(this, object);
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

	public Object visitCallProcedure(CallProcedure callProcedure, Object object) throws SemanticException
	{
		// Filipe
		String procedureName = callProcedure.getTerminalList().get(0).getToken().getSpelling();

		emit(InstructionsCommons.CALL + " _" + procedureName.toLowerCase());

		List<VarDeclare> procedureParameters = callProcedure.getParametersValidatedList();

		emit(InstructionsCommons.ADD + " " + InstructionsCommons.ESP + ", "
				+ ((Integer.parseInt(InstructionsCommons.BOOLEAN_INTEGER_SIZE)) * procedureParameters.size()));

		return null;
	}
	
	private int returnValue(String str){
		
		if(str == ">"){
			return 1;
		}else if(str == "<"){
			return 2;
		}else if(str == ">="){
			return 3;
		}else if(str == "<="){
			return 4;
		}else if(str == "="){
			return 5;
		}else if(str == "!="){
			return 6;
		}
		return 0;
	}

	public Object visitExpression(Expression expression, Object object) throws SemanticException
	{
		@SuppressWarnings("unchecked")
		ArrayList<AST> list = (ArrayList<AST>) object;
		String functionId = "";
		
		for (AST ast : list) {
			if (ast instanceof Procedure) {
				functionId = ((Procedure) ast).getTokenId().getToken().getSpelling();
				break;
			}
		}
		
		if (expression.getOptionalOperator() == null) {
			expression.getMandatoryOperator().visit(this, object);
		} else {
			expression.getMandatoryOperator().visit(this, list);
			expression.getOptionalOperator().visit(this, object);
			
			emit("pop ebx");
			emit("pop eax");
			emit("cmp eax, ebx");
			switch (returnValue(expression.getTokenComparator().getToken().getSpelling())) {
			case 5:
				emit( "jne "+ functionId +"_false_cmp_" + countCmp);
				break;
			case 6:
				emit("je "+ functionId +"_false_cmp_" + countCmp);
				break;
			case 2:
				emit("jge "+ functionId +"_false_cmp_" + countCmp);
				break;
			case 4:
				emit("jg "+ functionId +"_false_cmp_" + countCmp);
				break;
			case 1:
				emit("jle "+ functionId +"_false_cmp_" + countCmp);
				break;
			case 3:
				emit("jl "+ functionId +"_false_cmp_" + countCmp);
				break;
			}
			emit("push dword 1");
			emit("jmp" + functionId + "_end_cmp_" + countCmp);
			emit(functionId + "_false_cmp_" + countCmp + ":");
			emit("push dword 0");
			emit(functionId + "_end_cmp_" + countCmp + ":");
			countCmp++;
			
		}
		
		//dá um push 1 se for verdadeiro e push 0 se for falso
		return null;
	}

	public Object visitOperator(Operator operator, Object object) throws SemanticException
	{
		if (operator.getOperatorTerminalList().isEmpty()) {
			operator.getOperatorTermList().get(0).visit(this, object);
		} else {
			
			
			for (int i = 0; i < operator.getOperatorTermList().size(); i++)
			{
				if(i==0){
					operator.getOperatorTermList().get(0).visit(this, object);
					 i++;
				}
				operator.getOperatorTermList().get(i).visit(this, object);
				
				emit("pop ebx");
				emit("pop eax");
				if (operator.getOperatorTerminalList().get(i-1).getToken().getSpelling().equals("+")) {
					emit("add eax, ebx");
				} else {
					emit("sub eax, ebx");
				}
				emit("push eax");
				
				i++;
				
			}
			
		}
		return null;
	}

	public Object visitTerm(Term term, Object object) throws SemanticException
	{
		if (term.getTermOperatorList().isEmpty()) {
			term.getTermfatorList().get(0).visit(this, object);
		} else {
			
			
			for (int i = 0; i < term.getTermfatorList().size(); i++)
			{
				if(i==0){
					term.getTermfatorList().get(0).visit(this, object);
					 i++;
				}
				term.getTermfatorList().get(i).visit(this, object);
				
				emit("pop ebx");
				emit("pop eax");
				if (term.getTermOperatorList().get(i-1).getToken().getSpelling().equals("*")) {
					emit("imul eax, ebx");
				} else {
					emit("div eax, ebx");
				}
				emit("push eax");
				
				i++;
				
			}
			
		}
		return null;
	}

	public Object visitFatorCallProcedure(FatorCallProcedure fatorCallProcedure, Object object) throws SemanticException
	{
		@SuppressWarnings("unchecked")
		ArrayList<AST> list = (ArrayList<AST>) object;
		
		list.add(fatorCallProcedure);
		fatorCallProcedure.getCallProcedure().visit(this, list);
		list.remove(fatorCallProcedure);
		return null;
	}

	public Object visitFatorBool(FatorBool fatorBool, Object object) throws SemanticException
	{
		return fatorBool.getTokenBool().visit(this, object);
	}

	public Object visitFatorIdentificator(FatorId fatorId, Object object) throws SemanticException
	{
		return fatorId.getTokenId().visit(this, object);
	}

	public Object visitFatorNumber(FatorNumber fatorNumber, Object object) throws SemanticException
	{
		return fatorNumber.getTokenNumber().visit(this, object);
	}

	public Object visitFatorExpression(FatorExpression fatorExpression, Object object) throws SemanticException
	{

		fatorExpression.getExpression().visit(this, object);
		
		return null;
	}

	public Object visitTerminalTypeBoolean(TerminalBoolean terminalBoolean, Object object)
	{
		// Filipe nao tem o que fazer
		return null;
	}

	public Object visitTerminalBool(TerminalBool terminalBool, Object object)
	{
		// Filipe
		return (terminalBool.getToken().getSpelling() == "true") ? 1 : 0;
	}

	public Object visitTerminalTypeInteger(TerminalInteger terminalInteger, Object object)
	{
		// Filipe nao tem o que fazer
		return null;
	}

	public Object visitTerminalNumber(TerminalNumber terminalNumber, Object object)
	{
		// Filipe
		return terminalNumber.getToken().getSpelling();
	}

	public Object visitTerminalIdentificator(TerminalId terminalId, Object object) throws SemanticException
	{
		// Filipe
		return terminalId.getToken().getSpelling();
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
