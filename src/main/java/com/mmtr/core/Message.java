package com.mmtr.core;

import java.io.Serializable;

/**
 * Contains a message for a {@link com.mmtr.core.implement.MessageConsumerIF} to consume.
 *
 * @param <M> The message.
 */
public class Message<M extends Serializable>
{
	/**
	 * Creates a new message container containing the specified message.
	 *
	 * @param message The message to contain.
	 * @param <M> The message.
	 * @return The message container with the message.
	 */
	public static <M extends Serializable> Message<M> create(final M message)
	{
		return new Message<M>(message);
	}

	private final M message;

	/**
	 * Constructs a new instance of a message container.
	 *
	 * @param message The message.
	 */
	private Message(final M message)
	{
		this.message = message;
	}

	/**
	 * Returns the message to consume.
	 *
	 * @return The message.
	 */
	public M getMessage()
	{
		return message;
	}
}
