package entities;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class LoanAgentTest {

	private LoanAgent agent;

	@Test
	public void testLoanAgentBuilderWithAllValidParams() {
		agent = LoanAgent.builder("April", "Housing")
				.withClosedDeals(23)
				.build();
		assertThat(agent.getName()).isEqualTo("April");
		assertThat(agent.getLoanCategory()).isEqualTo("Housing");
		assertThat(agent.getClosedDealsByAgent()).isEqualTo(23);
	}

	@Test
	public void testLoanAgentBuilderWithoutOptionalParams() {
		agent = LoanAgent.builder("Beatrice", "Business")
				.build();
		assertThat(agent.getName()).isEqualTo("Beatrice");
		assertThat(agent.getLoanCategory()).isEqualTo("Business");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLoanAgentBuilderWithoutName() {
		agent = LoanAgent.builder("", "Personal")
				.build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLoanAgentBuilderWithoutLoanCategory() {
		agent = LoanAgent.builder("Steve", "")
				.build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLoanAgentBuilderWithoutNameAndLoanCategory() {
		agent = LoanAgent.builder("", "")
				.build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLoanAgentBuilderWithInvalidClosedDealsByAgent() {
		agent = LoanAgent.builder("Axel", "Health")
				.withClosedDeals(-1)
				.build();
	}
}
