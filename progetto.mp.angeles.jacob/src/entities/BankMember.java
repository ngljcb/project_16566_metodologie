package entities;

import java.util.Objects;
import java.util.Optional;

import utilities.BankMemberVisitor;

public abstract class BankMember {

	private String name;
	private Optional<BankMember> next;

	protected BankMember(String name, BankMember next) {
		this.name = name;
		this.next = Optional.ofNullable(next);
	}

	public String getName() {
		return name;
	}

	public void setNext(BankMember next) {
		this.next = Optional.ofNullable(next);
	}

	public boolean authorizeLoan(String loanCategory, int price) {
		return next
				.map(authorizer -> authorizer.authorizeLoan(loanCategory, price))
				.orElse(false);
	}

	public abstract void accept(BankMemberVisitor visitor);

	@Override
	public String toString() {
		return "BankMember [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankMember other = (BankMember) obj;
		return Objects.equals(name, other.name);
	}
}