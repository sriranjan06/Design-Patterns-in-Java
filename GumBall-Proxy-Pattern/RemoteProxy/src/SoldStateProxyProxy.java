public class SoldStateProxyProxy implements StateProxy {
	private static final long serialVersionUID = 2L;
    transient GumballMachineProxyProxy gumballMachineProxy;
 
    public SoldStateProxyProxy(GumballMachineProxyProxy gumballMachineProxy) {
        this.gumballMachineProxy = gumballMachineProxy;
    }
       
	public void insertQuarter() {
		System.out.println("Please wait, we're already giving you a gumball");
	}
 
	public void ejectQuarter() {
		System.out.println("Sorry, you already turned the crank");
	}
 
	public void turnCrank() {
		System.out.println("Turning twice doesn't get you another gumball!");
	}
 
	public void dispense() {
		gumballMachineProxy.releaseBall();
		if (gumballMachineProxy.getCount() > 0) {
			gumballMachineProxy.setState(gumballMachineProxy.getNoQuarterState());
		} else {
			System.out.println("Oops, out of gumballs!");
			gumballMachineProxy.setState(gumballMachineProxy.getSoldOutState());
		}
	}
 
	public String toString() {
		return "dispensing a gumball";
	}
}
