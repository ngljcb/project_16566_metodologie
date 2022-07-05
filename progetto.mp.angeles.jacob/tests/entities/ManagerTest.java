package entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;

public class ManagerTest {

	private Manager manager;

	@Test
	public void testAddManagerAsSubordinate() {
		BankMember subordinate = Manager.builder("Jeff")
				.build();
		manager = Manager.builder("Kim")
				.build();
		manager.add(subordinate);
		
		assertThat(manager.getSubordinates())
				.size()
				.isEqualTo(1);
		assertThat(manager.getSubordinates())
				.containsExactlyInAnyOrder(subordinate);
	}

	@Test
	public void testAddAgentAsSubordinate() {
		BankMember subordinate = LoanAgent.builder("Shirley", "Personal")
				.build();
		manager = Manager.builder("Jim")
				.build();
		manager.add(subordinate);
		
		assertThat(manager.getSubordinates())
				.size()
				.isEqualTo(1);
		assertThat(manager.getSubordinates())
				.containsExactlyInAnyOrder(subordinate);
	}

	@Test
	public void testAddSubordinates() {
		BankMember subordinate1 = LoanAgent.builder("Igmedio", "Housing")
				.build();
		BankMember subordinate2 = LoanAgent.builder("Juanita", "Personal")
				.build();
		BankMember subordinate3 = Manager.builder("Joseph")
				.build();
		manager = Manager.builder("Jake").build();
		
		manager.add(subordinate1);
		manager.add(subordinate2);
		manager.add(subordinate3);
		
		Collection<BankMember> members = new ArrayList<>();
		members.add(subordinate1);
		members.add(subordinate2);
		members.add(subordinate3);
		
		assertThat(manager.getSubordinates())
				.size()
				.isEqualTo(3);
		assertThat(manager.getSubordinates())
				.containsExactlyElementsOf(members);
	}

	@Test
	public void testRemoveSubordinate() {
		BankMember subordinate1 = LoanAgent.builder("Claire", "Housing")
				.build();
		BankMember subordinate2 = LoanAgent.builder("Druig", "Personal")
				.build();
		BankMember subordinate3 = Manager.builder("Stefani")
				.build();
		manager = Manager.builder("Jim").build();
		
		manager.add(subordinate1);
		manager.add(subordinate2);
		manager.add(subordinate3);
		
		manager.remove(subordinate2);
		
		Collection<BankMember> members = new ArrayList<>();
		members.add(subordinate1);
		members.add(subordinate3);
		
		assertThat(manager.getSubordinates())
				.size()
				.isEqualTo(2);
		assertThat(manager.getSubordinates())
				.containsExactlyElementsOf(members);
	}

	@Test
	public void testManagerBuilderWithAllValidParams() {
		manager = Manager.builder("Daniel")
				.withClosedDeals(29)
				.build();
		assertThat(manager.getName()).isEqualTo("Daniel");
		assertThat(manager.getClosedDealsByManager()).isEqualTo(29);
	}

	@Test
	public void testManagerBuilderWithoutOptionalParams() {
		manager = Manager.builder("Zabria")
				.build();
		assertThat(manager.getName()).isEqualTo("Zabria");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testManagerBuilderWithoutName() {
		manager = Manager.builder("")
				.build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testManagerBuilderWithInvalidClosedDealsByManager() {
		manager = Manager.builder("Jonna")
				.withClosedDeals(-9)
				.build();
	}
}