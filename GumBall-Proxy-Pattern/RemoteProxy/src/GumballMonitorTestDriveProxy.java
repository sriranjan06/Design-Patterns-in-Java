import java.rmi.*;
 
public class GumballMonitorTestDriveProxy {
 
	public static void main(String[] args) {
		String[] location = {"rmi://santafe.mightygumball.com/gumballmachine",
		                     "rmi://boulder.mightygumball.com/gumballmachine",
		                     "rmi://austin.mightygumball.com/gumballmachine"}; 
		
		if (args.length >= 0)
        {
            location = new String[1];
            location[0] = "rmi://" + args[0] + "/gumballmachine";
        }
		
		GumballMonitorProxy[] monitor = new GumballMonitorProxy[location.length];
		
		
		for (int i=0;i < location.length; i++) {
			try {
           		GumballMachineRemoteProxy machine =
						(GumballMachineRemoteProxy) Naming.lookup(location[i]);
           		monitor[i] = new GumballMonitorProxy(machine);
				System.out.println(monitor[i]);
        	} catch (Exception e) {
            	e.printStackTrace();
        	}
		}
 
		for (int i=0; i < monitor.length; i++) {
			monitor[i].report();
		}
	}
}
