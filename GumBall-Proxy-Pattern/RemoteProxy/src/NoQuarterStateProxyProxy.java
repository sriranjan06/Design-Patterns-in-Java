public class NoQuarterStateProxyProxy implements StateProxy {
	private static final long serialVersionUID = 2L;
    transient GumballMachineProxyProxy gumballMachineProxy;
 
    public NoQuarterStateProxyProxy(GumballMachineProxyProxy gumballMachineProxy) {
        this.gumballMachineProxy = gumballMachineProxy;
    }
 
	public void insertQuarter() {
		System.out.println("You inserted a quarter");
		gumballMachineProxy.setState(gumballMachineProxy.getHasQuarterState());
	}
 
	public void ejectQuarter() {
		System.out.println("You haven't inserted a quarter");
	}
 
	public void turnCrank() {
		System.out.println("You turned, but there's no quarter");
	 }
 
	public void dispense() {
		System.out.println("You need to pay first");
	} 
 
	public String toString() {
		return "waiting for quarter";
	}
}
