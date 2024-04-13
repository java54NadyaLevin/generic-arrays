package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employees.*;
import telran.util.Arrays;

class CompanyTest {
private static final long ID1 = 123;
private static final int SALARY1 = 1000;
private static final String DEPARTMENT1 = "Development";
private static final long ID2 = 120;
private static final int SALARY2 = 2000;
private static final int HOURS = 120;
private static final int WAGE = 45;
private static final long ID3 = 125;
private static final int SALARY3 = 3000;
private static final float FACTOR = 15;
private static final String DEPARTMENT2 = "QA";
private static final long ID4 = 200;
private static final int SALARY4= 4000;
private static final String DEPARTMENT3 = "Sales";
private static final long SALES = 1200000;
private static final float PERCENT = 12;

Employee emple = new Employee(ID1, SALARY1, DEPARTMENT1);
Employee emplw = new WageEmployee(ID2, SALARY2, DEPARTMENT2, HOURS, WAGE);
Employee emplm = new Manager(ID3, SALARY3, DEPARTMENT1, FACTOR);
Employee empls = new SalesPerson(ID4, SALARY4, DEPARTMENT3, SALES, PERCENT);
Company company;

@BeforeEach
void setCompany() {
	//before each test there will be created new object company 
	// with array of the given employee objects
	company = new Company(new Employee[] {emple, emplw, emplm});
}
	@Test
	void testAddEmployee()
	{
		Employee empl = new SalesPerson(215, SALARY2, DEPARTMENT2, SALES, PERCENT);
		company.addEmployee(empl);
		assertThrowsExactly(IllegalStateException.class,
				() -> company.addEmployee(empl));
		company.addEmployee(empls);
		assertEquals(empls, company.getEmployee(ID4)) ;
	}

	@Test
	void testGetEmployee() {
		assertEquals(emplw, company.getEmployee(ID2));
		assertNull(company.getEmployee(ID4));
	}

	@Test
	void testRemoveEmployee() {
		assertEquals(emplm, company.removeEmployee(ID3));
		assertThrowsExactly(NoSuchElementException.class,
				() -> company.removeEmployee(ID3));
	}

	@Test
	void testGetDepartmentBudget() {

		assertEquals(SALARY1 + SALARY3, company.getDepartmentBudget(DEPARTMENT1));
		assertEquals(0, company.getDepartmentBudget(DEPARTMENT3));
	}

	@Test
	void testIterator() {
		Employee[] expected = {emplw, emple, emplm};
		Iterator<Employee> it = company.iterator();
		int index = 0;
		while(it.hasNext()) {
			assertEquals(expected[index++], it.next());
		}
		assertEquals(expected.length, index);
		assertThrowsExactly(NoSuchElementException.class, it::next);
	}
	@Test
	void testGetDepartments() {
		String[] expected = {"Development", "QA"};
		String[] actual = company.getDepartments();
		Arrays.bubbleSort(actual);
		assertArrayEquals(expected, actual);
		
	}

}
