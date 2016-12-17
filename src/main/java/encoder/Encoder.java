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
import model.Statement;
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

	private int countCmp;
	private int countIf = 0;
	private int countElse = 0;
	private int countWhile = 0;
	private CodeGenerator codeGenerator;

	// parametros e variaveis locais
	private Hashtable<String, Integer> localVariables;
	// variaveis globais
	private Hashtable<String, Integer> globalVariables;

	public Encoder()
	{
		this.codeGenerator = new CodeGenerator();
		this.localVariables = new Hashtable<>();
		this.globalVariables = new Hashtable<>();
	}

	public void encode(AST decotaredAST)
	{
		try
		{
			decotaredAST.visit(this, new ArrayList<AST>());
			this.codeGenerator.generateAssemblyCode();
			this.codeGenerator.saveAssemblyCode();
		}
		catch (Exception ex)
		{
			System.err.println(ex.toString());
		}
	}

	public void emit(String section, String instruction)
	{
		this.codeGenerator.includeInstruction(section, new Instruction(instruction));
	}

	private void emit(String instruction)
	{
		emit(InstructionsCommons.TEXT_FIELD, instruction);
	}

	public Object visitProgram(Program program, Object object) throws SemanticException
	{
		// Marcos
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
			// varDeclare.setScope(identificationTable.getScope());
			TerminalId terminalIdentificator = (TerminalId) varDeclare.getTerminalId();
			terminalIdentificator.setDeclaredTerminalIdNode(varDeclare);
			varDeclare.setTerminalId(terminalIdentificator);
			
			String varName = varDeclare.getTerminalId().getToken().getSpelling();
			this.emit(InstructionsCommons.DATA_FIELD, varName + " " + InstructionsCommons.TYPE_BOOLEAN_INTEGER_INIT);
			this.globalVariables.put(varName, 0);
		}

		return null;
	}

	public Object visitProcedureDivisionScope(ProcedureDivisionScope procedureDivisionScope, Object object)
			throws SemanticException
	{
		// Filipe
		List<Procedure> procedureList = procedureDivisionScope.getProcedureList();
		List<AST> astParam = (List<AST>) object;

		for (Procedure procedure : procedureList)
		{
			String procedureId = procedure.getTokenId().getToken().getSpelling();
			if (procedureId.equalsIgnoreCase(InstructionsCommons.MAIN))
			{
				procedureId = InstructionsCommons.DEFAULT_MAIN;
				this.emit(procedureId + ":");
			}
			else
			{
				this.emit("_" + procedureId.toLowerCase() + ":");
			}

			this.savePointersStates();

			astParam.add(procedure);

			procedure.visit(this, astParam);

			astParam.remove(procedure);

			this.restourePointersStates();

			this.emit(InstructionsCommons.RETURN);

			this.countElse = this.countIf = this.countWhile = 0;
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
		// Filipe
		this.countCmp = 1;

		List<VarDeclare> procedureParametersList = procedure.getProcedureParametersList();
		int count = 4;

		for (VarDeclare varDeclare : procedureParametersList)
		{
			// varDeclare.setScope(identificationTable.getScope());
			TerminalId terminalIdentificator = (TerminalId) varDeclare.getTerminalId();
			terminalIdentificator.setDeclaredTerminalIdNode(varDeclare);
			varDeclare.setTerminalId(terminalIdentificator);

			count += 4;
			this.localVariables.put(varDeclare.getTerminalId().getToken().getSpelling(), count);
		}

		List<VarDeclare> varDeclareList = procedure.getVarDeclareList();
		count = 0;
		for (VarDeclare varDeclare : varDeclareList)
		{
			// varDeclare.setScope(identificationTable.getScope());
			TerminalId terminalIdentificator = (TerminalId) varDeclare.getTerminalId();
			terminalIdentificator.setDeclaredTerminalIdNode(varDeclare);
			varDeclare.setTerminalId(terminalIdentificator);
			varDeclare.visit(this, object);
			count -= 4;
			this.localVariables.put(varDeclare.getTerminalId().getToken().getSpelling(), count);
		}

		procedure.getCommand().visit(this, object);

		return null;
	}

	public Object visitCommand(Command command, Object object) throws SemanticException
	{
		// Filipe

		List<Statement> statementList = command.getStatementList();

		for (Statement currentStatement : statementList)
		{
			currentStatement.visit(this, object);
		}
		return null;
	}

	public Object visitStatementIf(StatementIf statementIf, Object object) throws SemanticException
	{
		// Filipe
		this.countIf++;

		List<AST> astList = (List<AST>) object;

		Object element = astList.get(0);
		String procedureId = "";

		if (element instanceof Procedure)
		{
			Procedure procedure = ((Procedure) astList.get(0));
			procedureId = procedure.getTokenId().getToken().getSpelling();
		}

		List<Command> ifCommandList = statementIf.getCommandList();

		// this.emit(InstructionsCommons.IF + this.countIf + ":");

		statementIf.getCond().visit(this, object);

		this.emit(InstructionsCommons.PUSH + " " + InstructionsCommons.DWORD + " " + "1");
		this.emit(InstructionsCommons.POP + " " + InstructionsCommons.EBX);
		this.emit(InstructionsCommons.POP + " " + InstructionsCommons.EAX);
		this.emit(InstructionsCommons.COMPARE + " " + InstructionsCommons.EAX + ", " + InstructionsCommons.EBX);
		this.emit(InstructionsCommons.JUMP_NOT_EQUAL + " " + InstructionsCommons.END_IF + procedureId + this.countIf);

		ifCommandList.get(0).visit(this, object);

		if (ifCommandList.size() > 1)
		{
			this.emit(InstructionsCommons.JUMP + " " + InstructionsCommons.END_ELSE + procedureId + ++this.countElse);
		}

		this.emit(InstructionsCommons.END_IF + procedureId + this.countIf + ":");
		
		if (ifCommandList.size() > 1)
		{
			this.emit(InstructionsCommons.JUMP + " " + InstructionsCommons.ELSE + procedureId + this.countElse);
			this.emit(InstructionsCommons.ELSE + procedureId + this.countElse + ":");
			ifCommandList.get(1).visit(this, object);
			this.emit(InstructionsCommons.END_ELSE + procedureId + this.countElse + ":");
		}

		return null;
	}

	public Object visitStatementWhile(StatementWhile statementWhile, Object object) throws SemanticException
	{
		// Filipe
		statementWhile.getAttributeWhile().visit(this, object);
		return null;
	}

	public Object visitStatementDisplay(StatementDisplay statementDisplay, Object object) throws SemanticException
	{
		// Filipe

		statementDisplay.getExpression().visit(this, object);
		this.emit(InstructionsCommons.PUSH + " " + InstructionsCommons.DWORD + " " + InstructionsCommons.INT_FORMAT);
		this.emit(InstructionsCommons.CALL + " " + InstructionsCommons.PRINTF);
		this.emit(InstructionsCommons.ADD + " " + InstructionsCommons.ESP + ", "
				+ (Integer.parseInt(InstructionsCommons.BOOLEAN_INTEGER_SIZE) * 2));

		return null;
	}

	public Object visitStatementReturn(StatementReturn statementReturn, Object object) throws SemanticException
	{
		// Filipe
		if (statementReturn != null && statementReturn.getExpression() != null)
		{
			this.emit(InstructionsCommons.POP + " " + InstructionsCommons.EAX);
		}
		return null;
	}

	public Object visitStatementAttribution(StatementAttrib statementAttrib, Object object) throws SemanticException
	{
		// Filipe
		statementAttrib.getAttrib().visit(this, object);
		return null;
	}

	public Object visitStatementCallProcedure(StatementCallProcedure statementCallProcedure, Object object)
			throws SemanticException
	{
		// Filipe
		statementCallProcedure.getCallProcedure().visit(this, object);
		return null;
	}

	public Object visitStatementBreak(StatementBreak statementBreak, Object object) throws SemanticException
	{
		// Filipe

		List<AST> astList = (List<AST>) object;

		Object element = astList.get(0);
		String procedureId = "";

		if (element instanceof Procedure)
		{
			Procedure procedure = ((Procedure) astList.get(0));
			procedureId = procedure.getTokenId().getToken().getSpelling();
		}

		this.emit(InstructionsCommons.JUMP + " " + InstructionsCommons.END_WHILE + procedureId + this.countWhile);

		return null;
	}

	public Object visitStatementContinue(StatementContinue statementContinue, Object object) throws SemanticException
	{
		// Filipe

		List<AST> astList = (List<AST>) object;

		Object element = astList.get(0);
		String procedureId = "";

		if (element instanceof Procedure)
		{
			Procedure procedure = ((Procedure) astList.get(0));
			procedureId = procedure.getTokenId().getToken().getSpelling();
		}

		this.emit(InstructionsCommons.JUMP + " " + InstructionsCommons.WHILE + procedureId + this.countWhile);

		return null;
	}

	public Object visitCondition(Condition condition, Object object) throws SemanticException
	{
		// Marcos
		Expression expression = condition.getExpression();

		expression.visit(this, object);

		return null;
	}

	public Object visitWhile(While whileCommand, Object object) throws SemanticException
	{
		// Filipe

		this.countWhile++;

		List<AST> astList = (List<AST>) object;

		Object element = astList.get(0);
		String procedureId = "";

		if (element instanceof Procedure)
		{
			Procedure procedure = ((Procedure) astList.get(0));
			procedureId = procedure.getTokenId().getToken().getSpelling();
		}

		this.emit(InstructionsCommons.WHILE + procedureId + this.countWhile + ":");

		whileCommand.getCondition().visit(this, object);

		this.emit(InstructionsCommons.PUSH + " " + InstructionsCommons.DWORD + " " + "1");
		this.emit(InstructionsCommons.POP + " " + InstructionsCommons.EBX);
		this.emit(InstructionsCommons.POP + " " + InstructionsCommons.EAX);
		this.emit(InstructionsCommons.COMPARE + " " + InstructionsCommons.EAX + ", " + InstructionsCommons.EBX);
		this.emit(InstructionsCommons.JUMP_NOT_EQUAL + " " + InstructionsCommons.END_WHILE + procedureId
				+ this.countWhile);

		whileCommand.getCommand().visit(this, object);

		this.emit(InstructionsCommons.JUMP + " " + InstructionsCommons.WHILE + procedureId + this.countWhile);

		this.emit(InstructionsCommons.END_WHILE + procedureId + this.countWhile + ":");

		return null;
	}

	public Object visitAttrib(Attrib attrib, Object object) throws SemanticException
	{
		// Marcos
		TerminalId terminalId = (TerminalId) attrib.getTokenId();
		VarDeclare varDeclare = (VarDeclare) terminalId.getDeclaredTerminalIdNode();
		attrib.getExpression().visit(this, object);
		
		Integer item = this.localVariables.get(attrib.getTokenId().getToken().getSpelling());
		
		if(item == null)
		{
			item = this.globalVariables.get(attrib.getTokenId().getToken().getSpelling());
		}
		
		if (item > 0)
		{
			emit("pop dword [ebp+" + localVariables.get(attrib.getTokenId().getToken().getSpelling()) + "]");

		}
		else
		{
			switch (varDeclare.getScope())
			{
				case 0:
					emit("pop dword [" + attrib.getTokenId().getToken().getSpelling() + "]");
					break;
				case 1:
					emit("pop dword [ebp" + localVariables.get(attrib.getTokenId().getToken().getSpelling()) + "]");
					break;

			}
		}
		return null;

	}

	public Object visitCallProcedure(CallProcedure callProcedure, Object object) throws SemanticException
	{
		// Filipe

		List<Terminal> callProcedureTerminals = callProcedure.getTerminalList();
		String procedureName = callProcedureTerminals.get(0).getToken().getSpelling();

		for (int i = 1; i < callProcedureTerminals.size(); i++)
		{
			callProcedureTerminals.get(i).visit(this, object);
		}

		emit(InstructionsCommons.CALL + " _" + procedureName.toLowerCase());

		List<VarDeclare> procedureParameters = callProcedure.getParametersValidatedList();

		emit(InstructionsCommons.ADD + " " + InstructionsCommons.ESP + ", "
				+ ((Integer.parseInt(InstructionsCommons.BOOLEAN_INTEGER_SIZE)) * procedureParameters.size()));

		return null;
	}

	private int returnValue(String str)
	{

		if (str.equals(">"))
		{
			return 1;
		}
		else if (str.equals("<"))
		{
			return 2;
		}
		else if (str.equals(">="))
		{
			return 3;
		}
		else if (str.equals("<="))
		{
			return 4;
		}
		else if (str.equals("=="))
		{
			return 5;
		}
		else if (str.equals("!="))
		{
			return 6;
		}
		return 0;
	}

	public Object visitExpression(Expression expression, Object object) throws SemanticException
	{
		// Marcos
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
			expression.getOptionalOperator().visit(this, object);

			emit("pop ebx");
			emit("pop eax");
			emit("cmp eax, ebx");

			switch (returnValue(expression.getTokenComparator().getToken().getSpelling()))
			{
				case 5:
					emit("jne " + "_false_cmp_" + functionId + countCmp);
					break;
				case 6:
					emit("je " + "_false_cmp_" + functionId + countCmp);
					break;
				case 2:
					emit("jge " + "_false_cmp_" + functionId + countCmp);
					break;
				case 4:
					emit("jg " + "_false_cmp_" + functionId + countCmp);
					break;
				case 1:
					emit("jle " + "_false_cmp_" + functionId + countCmp);
					break;
				case 3:
					emit("jl " + "_false_cmp_" + functionId + countCmp);
					break;

			}

			emit("push dword 1");
			emit("jmp " + "_end_cmp_" + functionId + countCmp);
			emit("_false_cmp_" + functionId + countCmp + ":");
			emit("push dword 0");
			emit("_end_cmp_" + functionId + countCmp + ":");
			countCmp++;

		}

		// d� um push 1 se for verdadeiro e push 0 se for falso
		return null;
	}

	public Object visitOperator(Operator operator, Object object) throws SemanticException
	{
		// Marcos
		if (operator.getOperatorTerminalList().isEmpty())
		{
			operator.getOperatorTermList().get(0).visit(this, object);
		}
		else
		{

			for (int i = 0; i < operator.getOperatorTermList().size(); i++)
			{
				if (i == 0)
				{
					operator.getOperatorTermList().get(0).visit(this, object);
					i++;
				}
				operator.getOperatorTermList().get(i).visit(this, object);

				emit("pop ebx");
				emit("pop eax");
				if (operator.getOperatorTerminalList().get(i - 1).getToken().getSpelling().equals("+"))
				{
					emit("add eax, ebx");
				}
				else
				{
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
		// Marcos
		if (term.getTermOperatorList().isEmpty())
		{
			term.getTermfatorList().get(0).visit(this, object);
		}
		else
		{

			for (int i = 0; i < term.getTermfatorList().size(); i++)
			{
				if (i == 0)
				{
					term.getTermfatorList().get(0).visit(this, object);
					i++;
				}
				term.getTermfatorList().get(i).visit(this, object);

				emit("pop ebx");
				emit("pop eax");
				if (term.getTermOperatorList().get(i - 1).getToken().getSpelling().equals("*"))
				{
					emit("imul eax, ebx");
				}
				else
				{
					emit("idiv eax, ebx");
				}
				emit("push eax");

				i++;

			}

		}
		return null;
	}

	public Object visitFatorCallProcedure(FatorCallProcedure fatorCallProcedure, Object object) throws SemanticException
	{
		// Marcos
		@SuppressWarnings("unchecked")
		ArrayList<AST> list = (ArrayList<AST>) object;

		list.add(fatorCallProcedure);
		fatorCallProcedure.getCallProcedure().visit(this, list);
		list.remove(fatorCallProcedure);
		return null;
	}

	public Object visitFatorBool(FatorBool fatorBool, Object object) throws SemanticException
	{
		// Marcos
		fatorBool.getTokenBool().visit(this, object);
		return null;
	}

	public Object visitFatorIdentificator(FatorId fatorId, Object object) throws SemanticException
	{
		// Marcos
		fatorId.getTokenId().visit(this, object);
		return null;
	}

	public Object visitFatorNumber(FatorNumber fatorNumber, Object object) throws SemanticException
	{
		// Marcos
		fatorNumber.getTokenNumber().visit(this, object);
		return null;
	}

	public Object visitFatorExpression(FatorExpression fatorExpression, Object object) throws SemanticException
	{
		// Marcos
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
		int valueToPush = (terminalBool.getToken().getSpelling().equalsIgnoreCase("true")) ? 1 : 0;
		this.emit(InstructionsCommons.PUSH + " " + InstructionsCommons.DWORD + " " + valueToPush);

		return null;
	}

	public Object visitTerminalTypeInteger(TerminalInteger terminalInteger, Object object)
	{
		// Filipe nao tem o que fazer
		return null;
	}

	public Object visitTerminalNumber(TerminalNumber terminalNumber, Object object)
	{
		// Filipe
		String numberToPush = terminalNumber.getToken().getSpelling();
		this.emit(InstructionsCommons.PUSH + " " + InstructionsCommons.DWORD + " " + numberToPush);

		return null;
	}

	public Object visitTerminalIdentificator(TerminalId terminalId, Object object) throws SemanticException
	{
		// Filipe
		String idName = terminalId.getToken().getSpelling();

		if (this.globalVariables.containsKey(idName))
		{
			this.emit(InstructionsCommons.PUSH + " " + InstructionsCommons.DWORD + " " + "[" + idName + "]");
		}
		else
		{
			int ebpLocator = this.localVariables.get(idName);

			if (ebpLocator < 0)
			{
				this.emit(InstructionsCommons.PUSH + " " + InstructionsCommons.DWORD + " " + "["
						+ InstructionsCommons.EBP + ebpLocator + "]");
			}
			else
			{

				this.emit(InstructionsCommons.PUSH + " " + InstructionsCommons.DWORD + " " + "["
						+ InstructionsCommons.EBP + "+" + ebpLocator + "]");
			}
		}

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
