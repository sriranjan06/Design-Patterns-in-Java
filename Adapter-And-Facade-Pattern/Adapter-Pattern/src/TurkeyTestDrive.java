public class TurkeyTestDrive {
	public static void main(String[] args) {
		MallardDuckAda duckAda = new MallardDuckAda();
		Turkey duckAdapter = new DuckAdapter(duckAda);
 
		for(int i=0;i<10;i++) {
			System.out.println("The DuckAdapter says...");
			duckAdapter.gobble();
			duckAdapter.fly();
		}
	}
}
