package chapter11.myremote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//원격 인터페이스 구현
//https://stackoverflow.com/questions/12336224/rmic-error-class-not-found
//https://finerss.tistory.com/entry/RMI%EC%9B%90%EA%B2%A9-%EB%A9%94%EC%86%8C%EB%93%9C-%ED%98%B8%EC%B6%9C-Remote-Method-Invocation
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    public MyRemoteImpl() throws RemoteException { }

    @Override
    public String sayHello() {
        return "Hello Black Jin";
    }

    public static void main(String[] args) {

        try {
            MyRemote service = new MyRemoteImpl();
            Naming.rebind("RemoteHello", service);
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
