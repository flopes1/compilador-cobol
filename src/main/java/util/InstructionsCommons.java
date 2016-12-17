package util;

public interface InstructionsCommons
{

	// Section constants
	static final String SECTION = "SECTION";
	static final String DATA_FIELD = "data";
	static final String DATA_SECTION = ".data";
	static final String TEXT_FIELD = "text";
	static final String TEXT_SECTION = ".text";
	static final String EXTERN = "extern";

	// Main constants
	static final String GLOBAL = "global";
	static final String DEFAULT_MAIN = "WinMain@16";
	static final String MAIN = "main";

	// Variable constants
	static final String TYPE_BOOLEAN_INTEGER_INIT = ":dd 0";
	static final String BOOLEAN_INTEGER_SIZE = "4";

	// General constants
	static final String INT_FORMAT = "intFormat";
	static final String INT_FORMAT_DEFAULT = "intFormat: db \"%d\", 10, 0";
	static final String PRINTF = "_printf";
	static final String IF = "_if_";
	static final String END_IF = "_end_if_";
	static final String ELSE = "_else_";
	static final String END_ELSE = "_end_else_";
	static final String WHILE = "_while_";
	static final String END_WHILE = "_end_while_";
	
	// Instruction constants
	static final String DWORD = "dword";
	static final String EBP = "ebp";
	static final String ESP = "esp";
	static final String EAX = "eax";
	static final String EBX = "ebx";

	// Instruction constants
	static final String CALL = "call";
	static final String RETURN = "ret";
	static final String PUSH = "push";
	static final String POP = "pop";
	static final String MOV = "mov";
	static final String ADD = "add";
	static final String SUB = "sub";
	static final String MULT = "imul";
	static final String DIV = "idiv";
	static final String JUMP = "jmp";
	static final String JUMP_NOT_EQUAL = "jne";
	static final String JUMP_EQUAL = "je";
	static final String COMPARE = "cmp";

}
