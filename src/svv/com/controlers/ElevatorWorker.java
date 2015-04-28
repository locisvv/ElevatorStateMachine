package svv.com.controlers;

import javax.swing.*;

public class ElevatorWorker implements Runnable {
    private Elevator elevator;

    public ElevatorWorker(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (!elevator.getQueueInElevator().isEmpty() || !elevator.getQueueOnFloors().isEmpty()) {
                elevator.moving();
            }
        }
    }
}
