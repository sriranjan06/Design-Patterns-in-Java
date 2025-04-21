import java.util.Random;

public class HasQuarterStateProxyProxy implements StateProxy {
	private static final long serialVersionUID = 2L;
	Random randomWinner = new Random(System.currentTimeMillis());
	transient GumballMachineProxyProxy gumballMachineProxy;
 
	public HasQuarterStateProxyProxy(GumballMachineProxyProxy gumballMachineProxy) {
		this.gumballMachineProxy = gumballMachineProxy;
	}
  
	public void insertQuarter() {
		System.out.println("You can't insert another quarter");
	}
 
	public void ejectQuarter() {
		System.out.println("Quarter returned");
		gumballMachineProxy.setState(gumballMachineProxy.getNoQuarterState());
	}
 
	public void turnCrank() {
		System.out.println("You turned...");
		int winner = randomWinner.nextInt(10);
		if (winner == 0) {
			gumballMachineProxy.setState(gumballMachineProxy.getWinnerState());
		} else {
			gumballMachineProxy.setState(gumballMachineProxy.getSoldState());
		}
	}

    public void dispense() {
        System.out.println("No gumball dispensed");
    }
 
	public String toString() {
		return "waiting for turn of crank";
	}
}
