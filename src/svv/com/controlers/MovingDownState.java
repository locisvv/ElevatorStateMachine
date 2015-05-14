package svv.com.controlers;

public class MovingDownState implements State {

    private Elevator elevator;

    public MovingDownState(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void moving() {
        int currentFloor = elevator.getCurrentFloor().get();

        for (int floor = currentFloor; floor >= Elevator.FIRST_FLOOR; --floor) {
            elevator.getMainView().showCurrentFloor(floor);

            if (elevator.willAnybodyGoOutAtThisFloor(floor) ||
                    elevator.willAnybodyGoIntoElevator(floor, Orientation.DOWN)) {

                elevator.stoppingAtFloor(floor);
                return;
            }
            elevator.getMainView().getElevatorView().oneFloorDown();
        }
    }

    @Override
    public void atFloor() {
        throw new IllegalStateException("Elevator is moving down. You cant go out");
    }
}