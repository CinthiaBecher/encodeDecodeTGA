package encodeDecodeTGA;

public class Leaf extends Node{
	
	private char character;
	private int frequency;
	
	public Leaf (char character, int frequency) {
		this.character = character;
		this.frequency = frequency;
	}
	
	public char getCharacter() {
		return character;
	}
	
	public int getFrequency() {
		return frequency;
	}

	@Override
	public String toString() {
		return "" + frequency + "(" + character + ")";
	}
	
}