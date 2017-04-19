
public class Node {
	private int value;
	private Node left;
	private Node right;
	private Node parent;
	public Node(int val) {
		value = val;
		left = null;
		right = null;
		parent = null;
	}
	public Node(int val, Node l, Node r, Node p) {
		value = val;
		left = l;
		right = r;
		parent = p;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	
	
	
}
