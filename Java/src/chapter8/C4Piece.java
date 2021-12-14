package chapter8;

public enum C4Piece implements Piece {
	B, R, E; // E is Empty

	@Override
	public C4Piece opposite() {
		switch (this) {
		case B:
			return C4Piece.R;
		case R:
			return C4Piece.B;
		default: // E, empty
			return C4Piece.E;
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case B:
			return "B";
		case R:
			return "R";
		default: // E, empty
			return " ";
		}

	}

}
