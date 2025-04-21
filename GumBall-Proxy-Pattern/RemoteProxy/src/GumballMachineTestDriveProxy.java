import java.rmi.*;

public class GumballMachineTestDriveProxy {
 
	public static void main(String[] args) {
		GumballMachineRemoteProxy gumballMachine = null;
		int count;

		if (args.length < 2) {
			System.out.println("GumballMachineProxyProxy <name> <inventory>");
 			System.exit(1);
		}

		try {
			count = Integer.parseInt(args[1]);

			gumballMachine = 
				new GumballMachineProxyProxy(args[0], count);
			Naming.rebind("//" + args[0] + "/gumballmachine", gumballMachine);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
