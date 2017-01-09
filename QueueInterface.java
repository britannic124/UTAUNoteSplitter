public interface QueueInterface {

  /** Determines whether a queue is empty.
      @returns true if the queue is empty; otherwise returns false.
  */
  public boolean isEmpty();

  /** Adds an item at the back of a queue.
      @param newItem is the item to be inserted.
      @throwsQueueException if newItem cannot be added to the queue.
  */
  public void enqueue(Object newItem) throws QueueException;

  /** Retrieves and removes the front of a queue.
      @return the item that was added to the queue earliest 
         is returned 
      @throws QueueException if the operation is impossible
  */
  public Object dequeue() throws QueueException;

  /** Removes all items of a queue.
  */
  public void dequeueAll();

  /** Retrieves the item at the front of a queue.
      @return item at front of queue (do not remove)
      @throws QueueException if the operation is impossible
  */
  public Object peek() throws QueueException;
}  // end QueueInterface
