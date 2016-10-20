package com.mmtr.core.implement;


import com.mmtr.core.Message;
import com.mmtr.core.Result;

import java.io.Serializable;

/**
 * This interface is to be implemented by whichever class will consume messages by a producer.
 *
 * @param <M> The message type it consumes.
 * @param <R> The result type it generates.
 */
public interface MessageConsumerIF<M extends Serializable, R extends Serializable>
{
	/**
	 * Consumes a message and generates a result based on the processing of that message.
	 *
	 * @param message An instance of {@link Message} which contains the message to be consumed.
	 * @return The result of the message that was consumed.
	 */
	public Result<R> consume(Message<M> message);
}
