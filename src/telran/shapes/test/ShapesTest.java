package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.shapes.Canvas;
import telran.shapes.Rectangle;
import telran.shapes.Shape;
import telran.shapes.Square;

class ShapesTest {
	private static final long ID1 = 1;
	private static final long ID2 = 2;
	private static final long ID3 = 3;
	private static final long ID4 = 4;
	private static final long ID5 = 5;
	private static final long ID6 = 6;
	private static final int WIDTH1 = 12;
	private static final int WIDTH2 = 8;
	private static final int WIDTH3 = 8;
	private static final int HEIGHT1 = 3;
	private static final int HEIGHT2 = 3;

	Shape rec1 = new Rectangle(ID1, WIDTH1, HEIGHT1);
	Shape rec2 = new Rectangle(ID2, WIDTH2, HEIGHT2);
	Shape square = new Square(ID3, WIDTH3);
	Shape canvas1 = new Canvas(ID4, new Shape[] { rec1, square });
	Shape canvas2 = new Canvas(ID5, new Shape[] { rec1, square, canvas1 });
	Canvas canvas;

	@BeforeEach
	void setCanvas() {
		canvas = new Canvas(ID4, new Shape[] { rec1, square });
	}

	@Test
	void addShapesTest() {
		Shape[] expected = { rec1, square, canvas1 };
		canvas.addShape(canvas1);
		int index = 0;
		for (Shape s : canvas) {
			assertTrue(expected[index++].equals(s));
		}
		assertThrowsExactly(IllegalStateException.class, () -> canvas.addShape(rec1));

	}

	@Test
	void removeShapeTest() {
		canvas.removeShape(ID1);
		assertDoesNotThrow(() -> canvas.addShape(rec1));
		assertThrowsExactly(IllegalStateException.class, () -> canvas.removeShape(ID2));
	}

	@Test
	void shapesSquareTest() {
		canvas.addShape(canvas1);
		int expected = rec1.square() + square.square() + canvas1.square();
		assertEquals(expected, canvas.square());
	}

	@Test
	void shapesPerimeterTest() {
		int expected = rec1.perimeter() + square.perimeter();
		assertEquals(expected, canvas.perimeter());
	}

}
