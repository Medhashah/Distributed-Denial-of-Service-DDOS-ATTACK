import java.rmi.*;
// DDOSService Interface
// Interface for a RMI service that will actually attack on a target machine
public interface DDOSService extends java.rmi.Remote
{
public String attack ( )
throws RemoteException;
}