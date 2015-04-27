package svv.com.controlers;

public class MovingUpState implements State {

    private Elevator elevator;

    public MovingUpState(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void moving() {
        int currentFloor = elevator.getCurrentFloor().get();

        for (int floor = currentFloor; floor <= Elevator.FLOORS; ++floor) {
            if (elevator.willAnybodyGoOutAtThisFloor(floor) ||
                    elevator.willAnybodyGoIntoElevator(floor, Orientation.UP)) {
                elevator.getCurrentFloor().set(floor);
                elevator.setState(elevator.getAtFloorState());
                elevator.stopped();
                return;
            }
            elevator.getElevatorView().oneFloorUp();
        }
    }

    @Override
    public void atFloor() {
        throw new IllegalStateException("Elevator is moving up. You cant stop now");
    }
}
