package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		// Pre: nuevo is not a node in the list 
		if (length == 0) {
			last = (SNode<E>) nuevo;
		}
		first = (SNode<E>) nuevo;
		((SNode<E>) nuevo).setNext(first);
		length++;
		
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		// Pre: target is a node in the list
		// Pre: nuevo is not a node in the list
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext()); 
		((SNode<E>) target).setNext((SNode<E>) nuevo);
		//Revisar
		if (target == last) {
			nuevo = last;
		}
		length++; 
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// Pre: target is a node in the list
		// Pre: nuevo is not a node in the list
		if (target == first) {
			this.addFirstNode(nuevo);
			first = nuevo;
		}
		else if (target == last) {
			
		}
		else { 
			Node<E> prevNode = findNodePrevTo(target);  
			this.addNodeAfter(prevNode, nuevo); 
		}
		length++;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Node<E> getFirstNode() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException("getFirstNode() : Empty list."); 
		}
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException("getLastNode(): Empty list.");
		}
		else { 
			SNode<E> curr = first; 
			while (((SNode<E>) curr).getNext() != null) {
				curr = curr.getNext();
			}
			return curr; 
		}
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		// Pre: target is a node in the list
		SNode<E> aNode = ((SNode<E>) target).getNext(); 
		if (aNode == null)   {
			throw new NoSuchElementException("getNextNode(...) : target is the last node.");
		}
		else {
			return aNode;
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Node<E> getNodeBefore(Node<E> target) throws NoSuchElementException {
		// Pre: target is a node in the list
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
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int length() {
		return this.length;
	}

	public void removeNode(Node<E> target) {
		// Pre: target is a node in the list; hence, the list is not empty
		if (target == first) {
			first = first.getNext();
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

}
