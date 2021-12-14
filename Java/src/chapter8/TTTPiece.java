package chapter8;

public enum TTTPiece implements Piece {
	X, O, E; // E is Empty

	@Override
	public TTTPiece opposite() {
		switch (this) {
		case X:
			return TTTPiece.O;
		case O:
			return TTTPiece.X;
		default: // E, empty
			return TTTPiece.E;
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case X:
			return "X";
		case O:
			return "O";
		default: // E, empty
			return " ";
		}

	}

}
