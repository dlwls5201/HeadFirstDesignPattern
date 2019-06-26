package chapter11;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

//http://mwultong.blogspot.com/2006/11/java-main-string-args.html
public class GumballMachineTestDrive {

    public static void main(String[] args) {
        int count = 0;

        if (args.length < 2) {
            System.out.println("GumballMachine <name> <inventory>");
            System.exit(1);
        }

        try {

            count = Integer.parseInt(args[1]);

            GumballMachineRemote gumballMachine = new GumballMachine(args[0], count);

            Naming.rebind("//" + args[0] + "/gumballmachine", gumballMachine);

            GumballMonitor monitor = new GumballMonitor(gumballMachine);

            monitor.report();

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
