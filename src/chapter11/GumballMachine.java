package chapter11;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote {

    private String mLocation;
    private int mCount;

    private String mState = "동전 투입 대기 중";

    public GumballMachine(String location, int count) throws RemoteException {
        mLocation = location;
        mCount = count;
    }

    public int getCount() {
        return mCount;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getState() {
        return mState;
    }
}
