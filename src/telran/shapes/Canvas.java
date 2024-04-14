package telran.shapes;

import java.util.Iterator;

import java.util.NoSuchElementException;

import telran.util.Arrays;

public class Canvas extends Shape implements Iterable<Shape> {
	private Shape[] shapes;

	public Shape[] getShapes() {
		return shapes;
	}

	public Canvas(long id, Shape[] shapes) {
		super(id);
		this.shapes = Arrays.copy(shapes);
	}

	public void addShape(Shape shape) {
		long id = shape.getId();
		if (Arrays.indexOf(shapes, shape) > -1) {
			throw new IllegalStateException(String.format("shape with id %d already exists", id));
		} else {
			shapes = Arrays.add(shapes, shape);
		}
	}

	public void removeShape(long id) {

		boolean isAvailable = false;
		for (Shape s : this) {
			if (s.getId() == id) {
				isAvailable = true;
				break;
			}
		}
		if (!isAvailable) {
			throw new IllegalStateException(String.format("no shape with id %d", id));
		} else {
			shapes = Arrays.removeIf(shapes, (s) -> s.getId() == id);
		}
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
		int canvasSquare = 0;
		for (Shape s : this) {
			canvasSquare += s.square();
		}
		return canvasSquare;
	}

	@Override
	public int perimeter() {
		int canvasPerimeter = 0;
		for (Shape s : this) {
			canvasPerimeter += s.perimeter();
		}
		return canvasPerimeter;
	}

}
