import java.rmi.*;
import java.rmi.server.*;
 
public class GumballMachineProxyProxy
		extends UnicastRemoteObject implements GumballMachineRemoteProxy
{
	private static final long serialVersionUID = 2L;
	StateProxy soldOutStateProxy;
	StateProxy noQuarterStateProxy;
	StateProxy hasQuarterStateProxy;
	StateProxy soldStateProxy;
	StateProxy winnerStateProxy;
 
	StateProxy stateProxy = soldOutStateProxy;
	int count = 0;
 	String location;

	public GumballMachineProxyProxy(String location, int numberGumballs) throws RemoteException {
		soldOutStateProxy = new SoldOutStateProxyProxy(this);
		noQuarterStateProxy = new NoQuarterStateProxyProxy(this);
		hasQuarterStateProxy = new HasQuarterStateProxyProxy(this);
		soldStateProxy = new SoldStateProxyProxy(this);
		winnerStateProxy = new WinnerStateProxy(this);

		this.count = numberGumballs;
 		if (numberGumballs > 0) {
			stateProxy = noQuarterStateProxy;
		} 
		this.location = location;
	}
 
 
	public void insertQuarter() {
		stateProxy.insertQuarter();
	}
 
	public void ejectQuarter() {
		stateProxy.ejectQuarter();
	}
 
	public void turnCrank() {
		stateProxy.turnCrank();
		stateProxy.dispense();
	}

	void setState(StateProxy stateProxy) {
		this.stateProxy = stateProxy;
	}
 
	void releaseBall() {
		System.out.println("A gumball comes rolling out the slot...");
		if (count != 0) {
			count = count - 1;
		}
	}

	public void refill(int count) {
		this.count = count;
		stateProxy = noQuarterStateProxy;
	}
 
	public int getCount() {
		return count;
	}
 
    public StateProxy getState() {
        return stateProxy;
    }
 
    public String getLocation() {
        return location;
    }
  
    public StateProxy getSoldOutState() {
        return soldOutStateProxy;
    }

    public StateProxy getNoQuarterState() {
        return noQuarterStateProxy;
    }

    public StateProxy getHasQuarterState() {
        return hasQuarterStateProxy;
    }

    public StateProxy getSoldState() {
        return soldStateProxy;
    }

    public StateProxy getWinnerState() {
        return winnerStateProxy;
    }
 
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("\nMighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2014");
		result.append("\nInventory: " + count + " gumball");
		if (count != 1) {
			result.append("s");
		}
		result.append("\n");
		result.append("Machine is " + stateProxy + "\n");
		return result.toString();
	}
}
