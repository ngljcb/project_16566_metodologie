package entities;

import java.util.Objects;

import utilities.BankMemberVisitor;

public class LoanAgent extends BankMember {

	private int closedDealsByAgent;
	private String loanCategory;
	
	private LoanAgent(String name, BankMember next, int closedDealsByAgent, String loanCategory) {
		super(name, next);
		this.closedDealsByAgent = closedDealsByAgent;
		this.loanCategory = loanCategory;
	}

	public static class LoanAgentBuilder {
		private String name;
		private BankMember next;
		private int closedDealsByAgent;
		private String loanCategory;
	
		private LoanAgentBuilder(String name, String loanCategory) {
			if(name.isEmpty())
				throw new IllegalArgumentException("Name must not be null or empty.");
			if(loanCategory.isEmpty())
				throw new IllegalArgumentException("Loan category must not be null or empty.");
			this.name = name;
			this.next = null;
			this.closedDealsByAgent = 0;
			this.loanCategory = loanCategory;
		}
	
		public LoanAgentBuilder withNext(BankMember next) {
			this.next = next;
			return this;
		}
	
		public LoanAgentBuilder withClosedDeals(int closedDealsByAgent) {
			if(closedDealsByAgent < 0)
				throw new IllegalArgumentException("closedDealsByAgent must be positive integer.");
			this.closedDealsByAgent = closedDealsByAgent;
			return this;
		}
	
		public LoanAgent build() {
			return new LoanAgent(this.name, this.next, this.closedDealsByAgent, this.loanCategory);
		}
	}

	public static LoanAgentBuilder builder(String name, String loanCategory) {
		return new LoanAgentBuilder(name, loanCategory);
	}

	public int getClosedDealsByAgent() {
		return closedDealsByAgent;
	}

	public String getLoanCategory() {
		return loanCategory;
	}

	@Override
	public boolean authorizeLoan(String loanCategory, int price) {
		if(loanCategory.equalsIgnoreCase(this.loanCategory) && price>0 && price<=10000) {
			this.closedDealsByAgent++;
			return true;
		}
		return super.authorizeLoan(loanCategory, price);
	}

	@Override
	public void accept(BankMemberVisitor visitor) {
		visitor.visitLoanAgent(this);
	}

	@Override
	public String toString() {
		return "LoanAgent [name=" + super.getName() + ", closedDealsByAgent=" + closedDealsByAgent + ", loanCategory=" + loanCategory + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(closedDealsByAgent, loanCategory);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanAgent other = (LoanAgent) obj;
		return closedDealsByAgent == other.closedDealsByAgent && Objects.equals(loanCategory, other.loanCategory);
	}
}