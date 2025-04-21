public class SoldOutStateProxyProxy implements StateProxy {
	private static final long serialVersionUID = 2L;
    transient GumballMachineProxyProxy gumballMachineProxy;
 
    public SoldOutStateProxyProxy(GumballMachineProxyProxy gumballMachineProxy) {
        this.gumballMachineProxy = gumballMachineProxy;
    }
 
	public void insertQuarter() {
		System.out.println("You can't insert a quarter, the machine is sold out");
	}
 
	public void ejectQuarter() {
		System.out.println("You can't eject, you haven't inserted a quarter yet");
	}
 
	public void turnCrank() {
		System.out.println("You turned, but there are no gumballs");
	}
 
	public void dispense() {
		System.out.println("No gumball dispensed");
	}
 
	public String toString() {
		return "sold out";
	}
}
