package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		first = (SNode<E>) nuevo;
		((SNode<E>) nuevo).setNext(first);
		length++;
		if (length == 1) {
			last = first;
		}
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext()); 
		((SNode<E>) target).setNext((SNode<E>) nuevo);
		
		if (target == last) {
			nuevo = last;
		}
		length++; 
		
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		if (target == first) {
			addFirstNode(nuevo);
		}
		else { 
			SNode<E> prevNode = (SNode<E>)getNodeBefore(target);
			prevNode.setNext((SNode<E>) nuevo);
			((SNode<E>) nuevo).setNext((SNode<E>)target);
		}
		length++;
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirstNode() : Empty list."); 
		}
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLastNode(): Empty list.");
		}
		return last;
	}
	
	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		SNode<E> aNode = ((SNode<E>) target).getNext(); 
		if (aNode == null)   {
			throw new NoSuchElementException("getNextNode(...) : target is the last node.");
		}
		else {
			return aNode;
		}
	}

	public Node<E> getNodeBefore(Node<E> target) throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getNodeBefore(...) : Empty List.");
		}
		SNode<E> previous = null;
		if (target == first) {
			throw new NoSuchElementException("getNodeBefore(...) : List contains only one element");
		}
		previous = first;
		while (previous != null && previous.getNext() != target) {
			previous = previous.getNext();
		}
		return previous;	
	}

	public int length() {
		return this.length;
	}

	public void removeNode(Node<E> target) {
		if (target == first) {
			first = first.getNext();
		}
		else if(target == last){
			last = (SNode<E>)this.getNodeBefore(target);
			//same as else, only sets "last" pointer
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((SNode<E>) target).getNext()); 
		}
		else { 
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((SNode<E>) target).getNext()); 
		}
		((SNode<E>) target).clean();   // clear all references from target
		length--; 
		
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}
	
	public Object[] toArray() {
		Object[] a = new Object[length];
		int i = 0;
		for (SNode<E> n = first; i < length; n = n.getNext()) {
            a[i]=n.getElement();
            i++;
		}
        return a;
	}

}
