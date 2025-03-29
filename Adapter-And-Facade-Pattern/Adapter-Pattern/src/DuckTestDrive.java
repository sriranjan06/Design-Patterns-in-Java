public class DuckTestDrive {
	public static void main(String[] args) {
		DuckAda duckAda = new MallardDuckAda();

		Turkey turkey = new WildTurkey();
		DuckAda turkeyAdapter = new TurkeyAdapter(turkey);

		System.out.println("The Turkey says...");
		turkey.gobble();
		turkey.fly();

		System.out.println("\nThe Duck says...");
		testDuck(duckAda);

		System.out.println("\nThe TurkeyAdapter says...");
		testDuck(turkeyAdapter);


		System.out.println("\nThe Drone says...");
		Drone drone = new SuperDrone();
		DuckAda droneAdapter = new DroneAdapter(drone);
		testDuck(droneAdapter);
	}

	static void testDuck(DuckAda duckAda) {
		duckAda.quack();
		duckAda.fly();
	}
}
