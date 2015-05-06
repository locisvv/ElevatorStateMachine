package svv.com.controlers;

/**
 * This is a background task which controls the elevator
 * */
public class ElevatorWorker implements Runnable {
    private Elevator elevator;

    public ElevatorWorker(Elevator elevator) {
        this.elevator = elevator;
    }

    /**
     * When someone call lift or select some floor
     * this task is going to move elevator
     * */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (!elevator.getQueueInElevator().isEmpty() || !elevator.getQueueOnFloors().isEmpty()) {
                elevator.moving();
            }
        }
    }
}
