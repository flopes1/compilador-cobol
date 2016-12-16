package encoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import model.Instruction;
import util.InstructionsCommons;

public class CodeGenerator
{
	private StringBuffer assemblyCode;
	private StringBuffer externField;
	private StringBuffer dataField;
	private StringBuffer textField;

	public CodeGenerator()
	{
		this.assemblyCode = new StringBuffer();
		this.externField = new StringBuffer();
		this.dataField = new StringBuffer();
		this.textField = new StringBuffer();
	}

	public void includeInstruction(String section, Instruction instruction)
	{
		if (section.equalsIgnoreCase(InstructionsCommons.DATA_FIELD))
		{
			this.dataField.append(instruction.toString());
		}
		else if (section.equalsIgnoreCase(InstructionsCommons.EXTERN))
		{
			this.externField.append(instruction.toString());
		}
		else if (section.equalsIgnoreCase(InstructionsCommons.TEXT_FIELD))
		{
			this.textField.append(instruction.toString());
		}
	}

	public void generateAssemblyCode()
	{
		this.assemblyCode.append(this.externField);
		this.assemblyCode.append("\n");
		this.assemblyCode.append(InstructionsCommons.SECTION + " " + InstructionsCommons.DATA_SECTION + "\n\n");
		this.assemblyCode.append(this.dataField);
		this.assemblyCode.append("\n");
		this.assemblyCode.append(InstructionsCommons.SECTION + " " + InstructionsCommons.TEXT_SECTION + "\n\n");
		this.assemblyCode.append(InstructionsCommons.GLOBAL + " " + InstructionsCommons.DEFAULT_MAIN + "\n\n");
		this.assemblyCode.append(this.textField);
		this.assemblyCode.append("\n");
	}

	public void saveAssemblyCode()
	{

		File outputFile = this.getOutputFile();

		if (outputFile != null)
		{
			PrintWriter out = null;

			try
			{
				int savedItens = 0;

				File[] allFiles = outputFile.listFiles();

				if (allFiles != null && allFiles.length > 0)
				{
					for (int i = 0; i < allFiles.length; i++)
					{
						if (allFiles[i] != null && allFiles[i].isFile())
						{
							savedItens++;
						}
					}
				}

				String programFileName = outputFile.getCanonicalPath() + "\\" + "program" + savedItens + ".asm";

				out = new PrintWriter(programFileName);
				out.println(this.assemblyCode.toString());
			}
			catch (FileNotFoundException e)
			{
				System.err.println(e.getMessage());
			}
			catch (IOException e)
			{
				System.err.println(e.getMessage());
			}
			finally
			{
				if (out != null)
				{
					out.close();
				}
			}
		}

	}

	private File getOutputFile()
	{
		File auxFile = null;
		try
		{
			auxFile = new File(".");
			String rootProjectDir = auxFile.getCanonicalPath();
			String outputDir = rootProjectDir + "\\" + "Output\\";
			auxFile = new File(outputDir);

			if (!auxFile.exists())
			{
				auxFile.mkdirs();
			}
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}

		return auxFile;
	}
}
