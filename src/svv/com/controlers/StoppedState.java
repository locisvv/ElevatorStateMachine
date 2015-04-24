package svv.com.controlers;

import java.util.Queue;

public class StoppedState implements State {

    private Elevator elevator;
    private Queue<WaiterAtFloor> queueOnFloors;
    private Queue<Integer> queueInElevator;

    public StoppedState(Elevator elevator) {
        this.elevator = elevator;
        queueInElevator = elevator.getQueueInElevator();
        queueOnFloors = elevator.getQueueOnFloors();
    }

    @Override
    public void moving() {
    }

    @Override
    public void stopped() {
        goOut();
        if (queueInElevator.isEmpty() && queueOnFloors.isEmpty()) {
            return;
        }
        comeIn();
        if (queueInElevator.isEmpty() || queueOnFloors.isEmpty()) {
            return;
        }
        if (queueInElevator.peek() > elevator.getCurrentFloor().get()) {
            elevator.setState(elevator.getMovingUpState());
        } else {
            elevator.setState(elevator.getMovingDownState());
        }
    }

    private void goOut() {
        while (queueInElevator.remove(elevator.getCurrentFloor().get()));
    }

    private void comeIn() {
        while (queueOnFloors.remove(new WaiterAtFloor(elevator.getCurrentFloor().get(), Orientation.DOWN)) ||
                queueOnFloors.remove(new WaiterAtFloor(elevator.getCurrentFloor().get(), Orientation.UP)));
    }
}
