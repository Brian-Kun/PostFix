package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure
 * to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
	
	LLNode<T> head = null;
	int size = 0;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if(!this.isEmpty()){
			T answer = this.head.getData();
			this.head = head.getNext();
			size--;
			return answer;
		} else {
			throw new StackUnderflowException("pop from empty");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T top() throws StackUnderflowException {
		if(!this.isEmpty()){
			return this.head.getData();
		}else{
			throw new StackUnderflowException();
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return (head == null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T elem) {
		LLNode<T> newNode = new LLNode<T>(elem);
		newNode.setNext(head);
		head = newNode;
		size++;
		
	}

}
