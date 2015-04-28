package svv.com.controlers;

import java.util.Queue;

public class AtFloorState implements State {

    private Elevator elevator;
    private Queue<WaiterAtFloor> queueOnFloors;
    private Queue<Integer> queueInElevator;

    public AtFloorState(Elevator elevator) {
        this.elevator = elevator;
        queueInElevator = elevator.getQueueInElevator();
        queueOnFloors = elevator.getQueueOnFloors();
    }

    @Override
    public void moving() {
        throw new IllegalStateException("Elevator is at floor. You cant moving anywhere");
    }

    @Override
    public void atFloor() {
        goOut();
        comeIn();
        if (queueInElevator.isEmpty() && queueOnFloors.isEmpty()) {
            return;
        }

        elevator.defineElevatorOrientation();
        elevator.moving();
    }

    private void goOut() {
        while (queueInElevator.remove(elevator.getCurrentFloor().get()));
    }

    private void comeIn() {
        int currentFloor = elevator.getCurrentFloor().get();
        WaiterAtFloor downWaiter = new WaiterAtFloor(currentFloor, Orientation.DOWN);
        WaiterAtFloor upWaiter = new WaiterAtFloor(currentFloor, Orientation.UP);

        while (queueOnFloors.remove(downWaiter) || queueOnFloors.remove(upWaiter));
    }
}
