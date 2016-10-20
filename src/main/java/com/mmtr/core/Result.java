package com.mmtr.core;

import java.io.Serializable;

/**
 * Represents a result of processing a {@link Message}.
 *
 * @param <R> The type which this Result represents.
 */
public class Result<R extends Serializable>
{
	/**
	 * Creates a new instance of a {@link Result}.
	 *
	 * @param successful This should be true if the message was consumed successfully and the result is complete.
	 * @param result The result of the consumption of the message.
	 * @param <R> The result type.
	 * @return A {@link Result} comprised of the information supplied.
	 */
	public static <R extends Serializable> Result<R> create(final boolean successful, final R result)
	{
		return new Result<R>(successful, result);
	}

	private final boolean successful;
	private final R result;

	/**
	 * Initializes a new result instance.
	 *
	 * @param successful This should be true if the message was consumed successfully and the result is complete.
	 * @param result The result of the consumption of the message.
	 */
	private Result(final boolean successful, final R result)
	{
		this.successful = successful;
		this.result = result;
	}

	/**
	 * Indicates whether the message consumption was successful and that the result should be included to be processed
	 * with the other results.
	 *
	 * @return Whether the consumption of the message and result generation was successful.
	 */
	public boolean isSuccessful()
	{
		return successful;
	}

	/**
	 * The result generated from the consumed message.
	 *
	 * @return The result.
	 */
	public R getResult()
	{
		return result;
	}
}
