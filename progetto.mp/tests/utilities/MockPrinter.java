package utilities;

public class MockPrinter implements Printer {

	private StringBuilder builder = new StringBuilder();
	
	@Override
	public void print(String text) {
		builder.append(text);
	}

	@Override
	public String toString() {
		return builder.toString();
	}
}
