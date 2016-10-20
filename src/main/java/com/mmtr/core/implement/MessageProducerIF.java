package com.mmtr.core.implement;

import java.io.Serializable;
import java.util.Collection;

/**
 * An interface to be implemented by the class which will produce messages.
 *
 * @param <M> The message which will be produced.
 */
public interface MessageProducerIF<M extends Serializable>
{
	/**
	 * When invoked this will cause the message producer to produce a collection of messages. These messages are then
	 * passed to the message consumers to process. It is fine if the producer only produces is a single message at a
	 * time or as many as it wants. In the case that the message producer produces more than one there is no guarantee
	 * that the collection of messages produced will be consumed in order or even by the same consumer.
	 *
	 * @return A collection of messages produced. As soon as this returns 0 or the collection is empty then it is
	 * assumed there are no more messages to be produced and it is not invoked again.
	 */
	public Collection<M> produce();
}
