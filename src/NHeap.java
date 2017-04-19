import java.util.*;
import java.lang.Math;

public class NHeap {
	private int track;// to track efficiency
	
	private int depth;
	private int size;
	private Node root;
	public NHeap() {
		root = null;
		size = 0;
		depth = 0;
		
		track = 0;
	}
	
	public NHeap(int[] nums){
		
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	public int top() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException ("attempt to access top of empty heap");
		}
		else
			return root.getValue();
	}
	
	public void remove() throws NoSuchElementException{
		if(isEmpty())
			throw new NoSuchElementException ("attempt to remove from empty heap");
		if(size==1){
			size=0;
			root = null;
			return;
		}
		track = 0;
		Node last = findLast(root, size, depth);
		root.setValue(last.getValue());
		Node par = last.getParent();
		if(size%2 == 0){
			par.setLeft(null);
		}
		else{
			par.setRight(null);
		}
		if((size&(--size)) == 0){
			depth --;
		}
		fixPositionDown(root);
		System.out.println("\nRemove took: "+track+" swaps.");
		track = 0;
	}
	
	public void add(int item){
		if(size==0){
			root = new Node(item);
			size++;
			return;
		}
		track = 0;
		Node nextParent = findLastParent(root, size, depth);
		Node add = new Node(item, null, null, nextParent);
		
		if(nextParent.getLeft() == null){
			nextParent.setLeft(add);
		}
		else{
			nextParent.setRight(add);
		}
		if((size&(++size)) == 0){
			depth ++;
		}
		
		fixPositionUp(add);
		System.out.println("\nAdd took: "+track+" swaps.");
		track = 0;
	}
	private void fixPositionUp(Node cur){
		if(cur==null || cur.getParent() == null)
			return;
		if(cur.getValue()>cur.getParent().getValue()){
			int temp = cur.getParent().getValue();
			cur.getParent().setValue(cur.getValue());
			cur.setValue(temp);
			fixPositionUp(cur.getParent());
			track++;
		}
	}
	private void fixPositionDown(Node cur){
		if(cur==null || (cur.getLeft()==null && cur.getRight() == null))
			return;
		track++;
		if(cur.getRight()==null)
		{
			if(cur.getValue() < cur.getLeft().getValue()){
				int temp = cur.getLeft().getValue();
				cur.getLeft().setValue(cur.getValue());
				cur.setValue(temp);
				fixPositionDown(cur);
				fixPositionDown(cur.getLeft());
			}
		}
		else if(cur.getLeft()==null)
		{
			if(cur.getValue() < cur.getRight().getValue()){
				int temp = cur.getRight().getValue();
				cur.getRight().setValue(cur.getValue());
				cur.setValue(temp);
				fixPositionDown(cur);
				fixPositionDown(cur.getRight());
			}
		}
		else if(cur.getLeft().getValue() > cur.getRight().getValue() && cur.getValue() < cur.getLeft().getValue()){
			int temp = cur.getLeft().getValue();
			cur.getLeft().setValue(cur.getValue());
			cur.setValue(temp);
			fixPositionDown(cur);
			fixPositionDown(cur.getLeft());
		}
		else if(cur.getLeft().getValue() < cur.getRight().getValue() && cur.getValue() < cur.getRight().getValue()){
			int temp = cur.getRight().getValue();
			cur.getRight().setValue(cur.getValue());
			cur.setValue(temp);
			fixPositionDown(cur);
			fixPositionDown(cur.getRight());
		}
		else{
			track--;
		}
		
	}
	
	private Node findLastParent(Node cur, int s, int d){
		if(s<=2)
			return cur;
		int twoPow = (int)(Math.pow(2, d));
		if((s+1)%twoPow < twoPow/2){
			if((s&(s+1)) == 0)
				return findLastParent(cur.getLeft(), s-twoPow, d-1);
			return findLastParent(cur.getLeft(), s-(int)(Math.pow(2, d-1)), d-1);
		}
		return findLastParent(cur.getRight(), s-twoPow, d-1);
		
		
	}
	
	private Node findLast(Node cur, int s, int d){
		if(s<=1)
			return cur;
		int twoPow = (int)(Math.pow(2, d));
		if(s%twoPow < twoPow/2){
			if((s&(s+1)) == 0)
				return findLast(cur.getLeft(), s-twoPow, d-1);
			return findLast(cur.getLeft(), s-(int)(Math.pow(2, d-1)), d-1);
		}
		return findLast(cur.getRight(), s-twoPow, d-1);
		
		
	}
	
	public void display(){
		System.out.println("Heap with size "+size+" and depth "+depth);
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()){
			Node current = queue.remove();
			System.out.print(current.getValue() + " ");
			if(current.getLeft() != null){
				queue.add(current.getLeft());
			}
			if(current.getRight() != null){
				queue.add(current.getRight());
			}
		}
		System.out.println();
	}

	public int getSize(){
		return size;
	}
	public int getDepth(){
		return depth;
	}
	
	public static void main(String[] args){
		NHeap heap = new NHeap();
		System.out.println("Is empty? :" + heap.isEmpty());
		heap.add(8);
		heap.add(7);
		heap.add(3);
		heap.add(9);
		heap.add(5);
		heap.add(2);
		heap.display();
		System.out.println("Is empty? :" + heap.isEmpty());
		heap.add(11);
		heap.display();
//		heap.add(10);
//		heap.display();
		heap.remove();
		heap.display();
		heap.remove();
		heap.display();
		heap.add(42);
		heap.display();
		heap.add(12);
		heap.display();
		heap.add(1);
		heap.display();
		heap.remove();
		heap.display();
	}
}
