package entities.reports;

import entities.LoanAgent;
import entities.Manager;
import utilities.BankMemberVisitor;
import utilities.Printer;

public class SalaryReportVisitor implements BankMemberVisitor{

	private Printer printer;

	public SalaryReportVisitor(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void visitManager(Manager manager) {
		int baseSalary = 2200;
		int salary = (manager.getClosedDealsByManager()*10) + baseSalary;
		String salaryInfo = manager.getName() + " - income: " + salary;
		printer.print(salaryInfo);
	}

	@Override
	public void visitLoanAgent(LoanAgent agent) {
		int baseSalary = 1200;
		int salary = (agent.getClosedDealsByAgent()*5) + baseSalary;
		String salaryInfo = agent.getName() + " - income: " + salary;
		printer.print(salaryInfo);
	}
}