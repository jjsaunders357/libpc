package com.pheiffware.lib.log;

public class PCLogHandler implements LogHandler
{

	@Override
	public void error(String message, Exception e)
	{
		System.err.println(message);
		e.printStackTrace();
	}

	@Override
	public void info(String message)
	{
		System.out.println(message);
	}

}
