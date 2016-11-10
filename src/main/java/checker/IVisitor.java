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

	Object visitProgram(Program program, Object object);

	Object visitDataDivisionScope(DataDivisionScope dataDivisionScope, Object object);

	Object visitProcedureDivisionScope(ProcedureDivisionScope procedureDivisionScope, Object object) throws SemanticException;

	Object visitVariablesDeclare(VarDeclare varDeclare, Object object) throws SemanticException;

	Object visitProcedure(Procedure procedure, Object object) throws SemanticException;

	Object visitCommand(Command command, Object object);

	Object visitStatementIf(StatementIf statementIf, Object object);

	Object visitStatementWhile(StatementWhile statementWhile, Object object);

	Object visitStatementDisplay(StatementDisplay statementDisplay, Object object);

	Object visitStatementReturn(StatementReturn statementReturn, Object object);

	Object visitStatementAttribution(StatementAttrib statementAttrib, Object object);

	Object visitStatementCallProcedure(StatementCallProcedure statementCallProcedure, Object object) throws SemanticException;

	Object visitStatementBreak(StatementBreak statementBreak, Object object);

	Object visitStatementContinue(StatementContinue statementContinue, Object object);

	Object visitCondition(Condition condition, Object object);

	Object visitWhile(While whileCommand, Object object);

	Object visitAttrib(Attrib attrib, Object object);

	Object visitCallProcedure(CallProcedure callProcedure, Object object) throws SemanticException;

	Object visitExpression(Expression expression, Object object);

	Object visitOperator(Operator operator, Object object);

	Object visitTerm(Term term, Object object);

	Object visitFatorCallProcedure(FatorCallProcedure fatorCallProcedure, Object object);

	Object visitFatorBool(FatorBool fatorBool, Object object);

	Object visitFatorIdentificator(FatorId fatorId, Object object) throws SemanticException;

	Object visitFatorNumber(FatorNumber fatorNumber, Object object);

	Object visitFatorExpression(FatorExpression fatorExpression, Object object);

	Object visitTerminalTypeBoolean(TerminalBoolean terminalBoolean, Object object);

	Object visitTerminalBool(TerminalBool terminalBool, Object object);

	Object visitTerminalTypeInteger(TerminalInteger terminalInteger, Object object);

	Object visitTerminalNumber(TerminalNumber terminalNumber, Object object);

	Object visitTerminalIdentificator(TerminalId terminalId, Object object) throws SemanticException;

	Object visitTerminalComparation(TerminalComp terminalComp, Object object);

	Object visitTerminalOperations(TerminalOperations terminalOperations, Object object);

}
