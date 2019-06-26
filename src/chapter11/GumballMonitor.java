package chapter11;

import java.rmi.RemoteException;

class GumballMonitor {

    GumballMachineRemote mMachine;

    public GumballMonitor(GumballMachineRemote machine) {
        mMachine = machine;
    }

    void report() {
        try {
            System.out.println("뽑기 기계 위치: " + mMachine.getLocation());
            System.out.println("현재 재고: " + mMachine.getCount());
            System.out.println("현재 상태: " + mMachine.getState());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
