package svv.com.controlers;

public class MovingDownState implements State {

    private Elevator elevator;

    public MovingDownState(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void moving() {
        final int currentFloor = elevator.getCurrentFloor().get();

        for (int floor = currentFloor; floor >= Elevator.FIRST_FLOOR; --floor) {
            if (elevator.getQueueInElevator().contains(floor) ||
                    elevator.getQueueOnFloors().contains(new WaiterAtFloor(floor, Orientation.DOWN)) || (
                    !elevator.getQueueOnFloors().isEmpty() && elevator.getQueueOnFloors().peek().getFloor() == floor)) {

                elevator.getCurrentFloor().set(floor);
                elevator.setState(elevator.getAtFloorState());
                elevator.stopped();
                return;
            }
            elevator.getElevatorView().oneFloorDown();
        }
    }

    @Override
    public void atFloor() {
        throw new IllegalStateException("Elevator is moving. You cant go out");
    }
}