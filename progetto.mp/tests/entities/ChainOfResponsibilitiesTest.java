package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChainOfResponsibilitiesTest {

	private BankMember agent;
	private BankMember manager;

	@Before
	public void setup() {
		agent = LoanAgent.builder("Sarah", "Personal").build();
		manager = Manager.builder("Kristine").build();
	}
	
	@Test(expected = StackOverflowError.class)
	public void testLoop() {
		agent.setNext(manager);
		manager.setNext(agent);
		// StackOverflowError!
		assertFalse(agent.authorizeLoan("Housing", 9000));
	}

	@Test(expected = StackOverflowError.class)
	public void testSelfLoop() {
		agent.setNext(agent);
		// StackOverflowError!
		assertFalse(agent.authorizeLoan("Housing", 9000));
	}

	@Test
	public void testAgentLoanWithExplicitNextNull() {
		agent.setNext(null);
		assertTrue(agent.authorizeLoan("Personal", 9000));
		assertFalse(agent.authorizeLoan("Personal", 12000));
		assertFalse(agent.authorizeLoan("Housing", 9000));
		assertFalse(agent.authorizeLoan("Business", 12000));
	}

	@Test
	public void testAgentLoanWithoutExplicitNextNull() {
		assertTrue(agent.authorizeLoan("Personal", 9000));
		assertFalse(agent.authorizeLoan("Personal", 12000));
		assertFalse(agent.authorizeLoan("Housing", 9000));
		assertFalse(agent.authorizeLoan("Business", 12000));
	}

	@Test
	public void testManagerLoanWithExplicitNextNull() {
		manager.setNext(null);
		assertTrue(manager.authorizeLoan("Business", 90000));
		assertFalse(manager.authorizeLoan("Housing", 10000));
		assertFalse(manager.authorizeLoan("Personal", 200001));
	}

	@Test
	public void testManagerLoanWithoutExplicitNextNull() {
		assertTrue(manager.authorizeLoan("Business", 90000));
		assertFalse(manager.authorizeLoan("Housing", 10000));
		assertFalse(manager.authorizeLoan("Personal", 200001));
	}

	@Test
	public void testNegativePriceLoan() {
		agent.setNext(manager);
		manager.setNext(null);
		assertFalse(agent.authorizeLoan("Personal", -732));
	}
}
