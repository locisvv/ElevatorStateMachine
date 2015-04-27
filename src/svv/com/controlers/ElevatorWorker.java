package svv.com.controlers;

import javax.swing.*;

public class ElevatorWorker extends SwingWorker<Boolean, Integer> {
    private Elevator elevator;

    public ElevatorWorker(Elevator elevator) {
        this.elevator = elevator;
    }
    @Override
    protected Boolean doInBackground() throws Exception {
        while (!Thread.interrupted()) {
            if (!elevator.getQueueInElevator().isEmpty() || !elevator.getQueueOnFloors().isEmpty()) {
                elevator.moving();
            }
        }
        return true;
    }
}
