package chapter11.myremote;

import java.rmi.Remote;
import java.rmi.RemoteException;

//원격 인터페이스
public interface MyRemote extends Remote {
    public String sayHello() throws RemoteException;
}
