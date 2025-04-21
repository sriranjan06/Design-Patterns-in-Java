import java.rmi.*;
 
public interface GumballMachineRemoteProxy extends Remote {
	public int getCount() throws RemoteException;
	public String getLocation() throws RemoteException;
	public StateProxy getState() throws RemoteException;
}
