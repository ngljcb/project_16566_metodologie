package utilities;

import entities.LoanAgent;
import entities.Manager;

public interface BankMemberVisitor {
	void visitManager(Manager manager);
	void visitLoanAgent(LoanAgent agent);
}
