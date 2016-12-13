package model;

public class Instruction
{

	private String instruction;
	private String instructionSection;

	public Instruction(String instructionSection, String instruction)
	{
		this.instruction = instruction;
		this.instructionSection = instructionSection;
	}

	public String getInstruction()
	{
		return instruction;
	}

	public void setInstruction(String instruction)
	{
		this.instruction = instruction;
	}

	public String getInstructionSection()
	{
		return instructionSection;
	}

	public void setInstructionSection(String instructionSection)
	{
		this.instructionSection = instructionSection;
	}

	@Override
	public String toString()
	{
		return this.instruction + "\n";
	}

}
