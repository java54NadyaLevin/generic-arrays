package telran.employees;

public class SalesPerson extends WageEmployee {
	private float percent;
	private long sales;

	public SalesPerson(long id, int basicSalary, String department, long sales, float percent) {
		super(id, basicSalary, department, 0, 0);
		this.percent = percent;
		this.sales = sales;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public long getSales() {
		return sales;
	}

	public void setSales(long sales) {
		this.sales = sales;
	}
	
	@Override
	public int computeSalary() {
		return (int)(super.computeSalary() + sales * percent/100);
	}

}
