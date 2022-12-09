package Global;

import java.io.Serial;
import java.util.Objects;
import java.awt.Dimension;

public class Int2 implements java.io.Serializable, Cloneable {
	public int x;
	public int y;

	@Serial
	private static final long serialVersionUID = 4723952579491349524L;


	public Int2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Dimension getD() {
		return new Dimension(x, y);
	}

	public Int2 add(Int2 other) {
		return new Int2(this.x + other.x, this.y + other.y);
	}

	public Int2 sub(Int2 other) {
		return new Int2(this.x - other.x, this.y - other.y);
	}

	public Int2 mul(Int2 other) {
		return new Int2(this.x * other.x, this.y * other.y);
	}

	public Int2 div(Int2 other) {
		return new Int2(this.x / other.x, this.y / other.y);
	}

	public Int2 add(int other) {
		return new Int2(this.x + other, this.y + other);
	}

	public Int2 sub(int other) {
		return new Int2(this.x - other, this.y - other);
	}

	public Int2 mul(int other) {
		return new Int2(this.x * other, this.y * other);
	}

	public Int2 div(int other) {
		return new Int2(this.x / other, this.y / other);
	}

	public Int2 swap() {
		return new Int2(this.y, this.x);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Int2 int2 = (Int2) o;
		return x == int2.x && y == int2.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "Int2{" + "x=" + x + ", y=" + y + '}';
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}
}
