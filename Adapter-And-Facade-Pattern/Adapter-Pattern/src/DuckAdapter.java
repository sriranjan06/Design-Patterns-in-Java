import java.util.Random;

public class DuckAdapter implements Turkey {
	DuckAda duckAda;
	Random rand;
 
	public DuckAdapter(DuckAda duckAda) {
		this.duckAda = duckAda;
		rand = new Random();
	}
    
	public void gobble() {
		duckAda.quack();
	}
  
	public void fly() {
		if (rand.nextInt(5)  == 0) {
		     duckAda.fly();
		}
	}
}
