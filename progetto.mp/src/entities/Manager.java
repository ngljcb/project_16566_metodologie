package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import utilities.BankMemberVisitor;

public class Manager extends BankMember {

	private int closedDealsByManager;
	private Collection<BankMember> subordinates;

	private Manager(String name, BankMember next, int closedDealsByManager) {
		super(name, next);
		this.closedDealsByManager = closedDealsByManager;
		this.subordinates = new ArrayList<>();
	}

	public static class ManagerBuilder{
		private String name;
		private BankMember next;
		private int closedDealsByManager;
		private Collection<BankMember> subordinates;
	
		private ManagerBuilder(String name) {
			if(name.isEmpty())
				throw new IllegalArgumentException("Name must not be null or empty.");
			this.name = name;
			this.next = null;
			this.subordinates = new ArrayList<>();
		}
	
		public ManagerBuilder withNext(BankMember next) {
			this.next = next;
			return this;
		}
	
		public ManagerBuilder withClosedDeals(int closedDealsByManager) {
			if(closedDealsByManager < 0)
				throw new IllegalArgumentException("closedDealsByManager must be positive integer.");
			this.closedDealsByManager = closedDealsByManager;
			return this;
		}
	
		public ManagerBuilder withSubordinate(BankMember subordinate) {
			this.subordinates.add(subordinate);
			return this;
		}
	
		public Manager build() {
			Manager manager = new Manager(this.name, this.next, this.closedDealsByManager);
			manager.setSubordinates(this.subordinates);
			return manager;
		}
	}

	public static ManagerBuilder builder(String name) {
		return new ManagerBuilder(name);
	}

	private void setSubordinates(Collection<BankMember> subordinates) {
		this.subordinates = subordinates;
	}

	public Collection<BankMember> getSubordinates() {
		return subordinates;
	}

	void add(BankMember member) {
		this.subordinates.add(member);
	}

	void remove(BankMember member) {
		this.subordinates.remove(member);
	}

	public int getClosedDealsByManager() {
		return closedDealsByManager;
	}

	@Override
	public boolean authorizeLoan(String loanCategory, int price) {
		if(price>10000 && price<=200000) {
			this.closedDealsByManager++;
			return true;
		}
		return super.authorizeLoan(loanCategory, price);
	}

	@Override
	public void accept(BankMemberVisitor visitor) {
		visitor.visitManager(this);
	}

	@Override
	public String toString() {
		return "Manager [name=" + super.getName() + ", subordinates=" + subordinates + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(subordinates);
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
		Manager other = (Manager) obj;
		return Objects.equals(subordinates, other.subordinates);
	}
}