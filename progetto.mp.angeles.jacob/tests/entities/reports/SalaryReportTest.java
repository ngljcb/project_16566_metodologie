package entities.reports;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

import entities.BankMember;
import entities.LoanAgent;
import entities.Manager;
import utilities.BankMemberVisitor;
import utilities.MockPrinter;
import utilities.Printer;

public class SalaryReportTest {

	private Printer printer;
	private BankMemberVisitor visitor;

	@Before
	public void setup() {
		printer = new MockPrinter();
		visitor = new SalaryReportVisitor(printer);
	}

	@Test
	public void testManagerSalaryReport() {
		BankMember manager = Manager.builder("Joshua")
				.withClosedDeals(5)
				.build();
		manager.accept(visitor);
		String expectedString = manager.getName() + " - income: 2250";
		String actualResult = printer.toString();
		assertThat(actualResult).isEqualTo(expectedString);
	}

	@Test
	public void testLoanAgentSalaryReport() {
		BankMember agent = LoanAgent.builder("Sara", "Housing")
				.withClosedDeals(14)
				.build();
		agent.accept(visitor);
		String expectedString = agent.getName() + " - income: 1270";
		String actualResult = printer.toString();
		assertThat(actualResult).isEqualTo(expectedString);
	}
}
