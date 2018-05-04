import java.rmi.*;
import java.rmi.Naming;
import java.io.*;
//
// DDOSServiceClient
//
public class DDOSServiceClient
{
String attack =""; public static void main(String args[]) throws Exception
{
DDOSServiceClient obj = new DDOSServiceClient() ;
while(true){
obj.attack ="";
try{
obj.go();
}
catch(java.rmi.ConnectException rc){
System.out.println("Connection Failure");
}
catch(java.net.ConnectException rc){
System.out.println("Net Failure");
} catch(java.rmi.NotBoundException je){
System.out.println("java.rmi.NotBoundException");
} System.out.println(obj.attack);
}
}
private static void go() throws Exception{
// Call registry for DDOSService
DDOSServiceClient obj = new DDOSServiceClient() ;
Thread.sleep(5000);
//A server IP that need to be replaced with this IP
DDOSService service = (DDOSService) Naming.lookup
("rmi://192.168.55.44/DDOSService");
DataInputStream din = new
DataInputStream (System.in);
//Calling remote Method
obj.attack = service.attack();
}
}