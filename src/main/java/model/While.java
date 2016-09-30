package model;

import util.AST.AST;

public class While extends AST{

	private Condition condition;
	private Command command;
	
	public While(Condition condition, Command command) {
		this.setCondition(condition);
		this.setCommand(command);
	}
	
	@Override
	public String toString()
	{
		String string = "";
		
		string += this.condition.toString() + "\n"
				+ this.command.toString() + "\n";
		
		return string;
	}
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	public Condition getCondition()
	{
		return condition;
	}

	public void setCondition(Condition condition)
	{
		this.condition = condition;
	}

	public Command getCommand()
	{
		return command;
	}

	public void setCommand(Command command)
	{
		this.command = command;
	}

}
