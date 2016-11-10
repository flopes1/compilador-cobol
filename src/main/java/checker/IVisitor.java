package checker;

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

public interface IVisitor
{

	Object visitProgram(Program program, Object object) throws SemanticException;

	Object visitDataDivisionScope(DataDivisionScope dataDivisionScope, Object object) throws SemanticException;

	Object visitProcedureDivisionScope(ProcedureDivisionScope procedureDivisionScope, Object object) throws SemanticException;

	Object visitVariablesDeclare(VarDeclare varDeclare, Object object) throws SemanticException;

	Object visitProcedure(Procedure procedure, Object object) throws SemanticException;

	Object visitCommand(Command command, Object object) throws SemanticException;

	Object visitStatementIf(StatementIf statementIf, Object object) throws SemanticException;

	Object visitStatementWhile(StatementWhile statementWhile, Object object) throws SemanticException;

	Object visitStatementDisplay(StatementDisplay statementDisplay, Object object) throws SemanticException;

	Object visitStatementReturn(StatementReturn statementReturn, Object object) throws SemanticException;

	Object visitStatementAttribution(StatementAttrib statementAttrib, Object object) throws SemanticException;

	Object visitStatementCallProcedure(StatementCallProcedure statementCallProcedure, Object object) throws SemanticException;

	Object visitStatementBreak(StatementBreak statementBreak, Object object) throws SemanticException;

	Object visitStatementContinue(StatementContinue statementContinue, Object object) throws SemanticException;

	Object visitCondition(Condition condition, Object object) throws SemanticException;

	Object visitWhile(While whileCommand, Object object) throws SemanticException;

	Object visitAttrib(Attrib attrib, Object object) throws SemanticException;

	Object visitCallProcedure(CallProcedure callProcedure, Object object) throws SemanticException;

	Object visitExpression(Expression expression, Object object) throws SemanticException;

	Object visitOperator(Operator operator, Object object) throws SemanticException;

	Object visitTerm(Term term, Object object) throws SemanticException;

	Object visitFatorCallProcedure(FatorCallProcedure fatorCallProcedure, Object object) throws SemanticException;

	Object visitFatorBool(FatorBool fatorBool, Object object) throws SemanticException;

	Object visitFatorIdentificator(FatorId fatorId, Object object) throws SemanticException;

	Object visitFatorNumber(FatorNumber fatorNumber, Object object) throws SemanticException;

	Object visitFatorExpression(FatorExpression fatorExpression, Object object) throws SemanticException;
	
	Object visitTerminalTypeBoolean(TerminalBoolean terminalBoolean, Object object);

	Object visitTerminalBool(TerminalBool terminalBool, Object object);

	Object visitTerminalTypeInteger(TerminalInteger terminalInteger, Object object);

	Object visitTerminalNumber(TerminalNumber terminalNumber, Object object);

	Object visitTerminalIdentificator(TerminalId terminalId, Object object) throws SemanticException;

	Object visitTerminalComparation(TerminalComp terminalComp, Object object);

	Object visitTerminalOperations(TerminalOperations terminalOperations, Object object);

}
