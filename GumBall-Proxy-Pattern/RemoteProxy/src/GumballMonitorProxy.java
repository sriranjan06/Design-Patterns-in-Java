import java.rmi.*;
 
public class GumballMonitorProxy {
	GumballMachineRemoteProxy machine;
 
	public GumballMonitorProxy(GumballMachineRemoteProxy machine) {
		this.machine = machine;
	}
 
	public void report() {
		try {
			System.out.println("Gumball Machine: " + machine.getLocation());
			System.out.println("Current inventory: " + machine.getCount() + " gumballs");
			System.out.println("Current stateProxy: " + machine.getState());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
