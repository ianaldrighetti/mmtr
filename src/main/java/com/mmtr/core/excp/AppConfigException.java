package com.mmtr.core.excp;

/**
 * An exception if an application configuration error is encountered.
 */
public class AppConfigException extends Exception
{
	private final String property;

	public AppConfigException(final String message, final String property)
	{
		super(message);

		this.property = property;
	}

	public AppConfigException(final String message, final String property, final Throwable cause)
	{
		super(message, cause);

		this.property = property;
	}
}
