package entities.reports;

import entities.LoanAgent;
import entities.Manager;
import utilities.BankMemberVisitor;
import utilities.Printer;

public class LoanReportVisitor implements BankMemberVisitor{

	private Printer printer;

	public LoanReportVisitor(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void visitManager(Manager manager) {
		String loanInfo = manager.getName() + " - closed loans: " + manager.getClosedDealsByManager();
		printer.print(loanInfo);
	}

	@Override
	public void visitLoanAgent(LoanAgent agent) {
		String loanInfo = agent.getName() + " - closed loans: " + agent.getClosedDealsByAgent();
		printer.print(loanInfo);
	}
}