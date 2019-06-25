package chapter11;

import java.rmi.Naming;

/**
 *  실행 법
 *
 *  1. src 폴더에서 rmiregistry
 *  2. java chapter11/MyRemoteImpl
 *  3. java chapter11/MyRemoteClient
 */
public class MyRemoteClient{
    public static void main (String[] args) {
        new MyRemoteClient().go();
    }

    public void go(){

        try{
            MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");

            String s = service.sayHello();

            System.out.println(s);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}