public class WinnerStateProxy implements StateProxy {
	private static final long serialVersionUID = 2L;
    transient GumballMachineProxyProxy gumballMachineProxy;
 
    public WinnerStateProxy(GumballMachineProxyProxy gumballMachineProxy) {
        this.gumballMachineProxy = gumballMachineProxy;
    }
 
	public void insertQuarter() {
		System.out.println("Please wait, we're already giving you a Gumball");
	}
 
	public void ejectQuarter() {
		System.out.println("Please wait, we're already giving you a Gumball");
	}
 
	public void turnCrank() {
		System.out.println("Turning again doesn't get you another gumball!");
	}
 
	public void dispense() {
		System.out.println("YOU'RE A WINNER! You get two gumballs for your quarter");
		gumballMachineProxy.releaseBall();
		if (gumballMachineProxy.getCount() == 0) {
			gumballMachineProxy.setState(gumballMachineProxy.getSoldOutState());
		} else {
			gumballMachineProxy.releaseBall();
			if (gumballMachineProxy.getCount() > 0) {
				gumballMachineProxy.setState(gumballMachineProxy.getNoQuarterState());
			} else {
          		 	System.out.println("Oops, out of gumballs!");
				gumballMachineProxy.setState(gumballMachineProxy.getSoldOutState());
			}
		}
	}
 
	public String toString() {
		return "despensing two gumballs for your quarter, because YOU'RE A WINNER!";
	}
}
