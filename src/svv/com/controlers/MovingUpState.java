package svv.com.controlers;

public class MovingUpState implements State {

    private Elevator elevator;

    public MovingUpState(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void moving() {
        int currentFloor = elevator.getCurrentFloor().get();

        for (int i = currentFloor; i <= Elevator.FLOORS; ++i) {
            if (elevator.getQueueInElevator().contains(i) ||
                    elevator.getQueueOnFloors().contains(new WaiterAtFloor(i, Orientation.UP))) {

                elevator.getProgressBar().setValue((i * 20));
                elevator.getCurrentFloor().set(i);
                elevator.setState(elevator.getStoppedState());
                elevator.stopped();
            }
        }
    }

    @Override
    public void stopped() {
        throw new IllegalStateException("Elevator is moving. You cant stop now");
    }
}
