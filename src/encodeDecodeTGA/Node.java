package encodeDecodeTGA;

public class Node {

	int frequency;

	Node left;
	Node right;
	
	public Node() {
		this.left = null;
		this.right = null;
	}

	public Node(int frequency) {
		this.frequency = frequency;
		this.left = null;
		this.right = null;
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

	public int getFrequency() {
		return frequency;
	}

	@Override
	public String toString() {
		return "" + frequency;
	}
	
	

}
