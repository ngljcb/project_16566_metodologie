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

public class LoanReportTest {

	private Printer printer;
	private BankMemberVisitor visitor;

	@Before
	public void setup() {
		printer = new MockPrinter();
		visitor = new LoanReportVisitor(printer);
	}

	@Test
	public void testManagerLoanReport() {
		BankMember manager = Manager.builder("Eve")
				.withClosedDeals(5)
				.build();
		manager.accept(visitor);
		String expectedString = manager.getName() + " - closed loans: 5";
		String actualResult = printer.toString();
		assertThat(actualResult).isEqualTo(expectedString);
	}

	@Test
	public void testLoanAgentLoanReport() {
		BankMember agent = LoanAgent.builder("Chris", "Housing")
				.withClosedDeals(14)
				.build();
		agent.accept(visitor);
		String expectedString = agent.getName() + " - closed loans: 14";
		String actualResult = printer.toString();
		assertThat(actualResult).isEqualTo(expectedString);
	}
}
