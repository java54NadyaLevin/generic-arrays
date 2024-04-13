package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.employees.Employee;
import telran.util.Arrays;

public class Canvas extends Shape implements Iterable<Shape> {
	public Canvas(long id) {
		super(id);
		this.shapes = Arrays.copy(shapes);
	}

	private Shape[] shapes;

	public void addShape(Shape shape) {

	}

	public void removeShape(long id) {

	}

	public Iterator<Shape> iterator() {

		return new ShapeIterator();
	}

	private class ShapeIterator implements Iterator<Shape> {
		int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex < shapes.length;
		}

		@Override
		public Shape next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return shapes[currentIndex++];
		}

	}

	@Override
	public int square() {

		return 0;
	}

	@Override
	public int perimeter() {
		// TODO Auto-generated method stub
		return 0;
	}

}
