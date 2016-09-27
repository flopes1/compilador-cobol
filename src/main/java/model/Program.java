package model;

import java.util.ArrayList;
import java.util.List;

import util.AST.AST;;

public class Program extends AST
{

	private List<Command> commands = new ArrayList<Command>();
	
	public Program(List<Command> commands)
	{
		this.commands = commands;
	}
	
	@Override
	public String toString(int level)
	{
		String tr = "";
		
		for (Command command : commands)
		{
			tr = command.toString() + "\n";
		}
		return tr;
	}

}
