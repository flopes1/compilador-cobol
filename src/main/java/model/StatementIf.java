package model;

import java.util.List;

public class StatementIf extends Statement{

	private Condition condition;
	private List<Command> commandList = null;
	
	public StatementIf(Condition condition, List<Command> commandList) {
		this.setCond(condition);
		this.setCommandList(commandList);
	}
	
	@Override
	public String toString()
	{
		String string = "";
		
		string += this.condition.toString() + "\n";
		
		for (Command command : commandList)
		{
			string += command.toString() + "\n";
		}
		
		return string;
	}
	
	
	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	public Condition getCond()
	{
		return condition;
	}

	public void setCond(Condition cond)
	{
		this.condition = cond;
	}

	public List<Command> getCommandList()
	{
		return commandList;
	}

	public void setCommandList(List<Command> commandList)
	{
		this.commandList = commandList;
	}

}
