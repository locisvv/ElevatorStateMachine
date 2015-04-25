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
            if (elevator.getQueueInElevator().contains(floor) ||
                    elevator.getQueueOnFloors().contains(new WaiterAtFloor(floor, Orientation.UP)) || (
                    !elevator.getQueueOnFloors().isEmpty() && elevator.getQueueOnFloors().peek().getFloor() == floor)) {

                elevator.getProgressBar().setValue((floor * 20));
                elevator.getCurrentFloor().set(floor);
                System.out.println("floor :" + floor);
                elevator.setState(elevator.getStoppedState());
                elevator.stopped();
                return;
            }
        }
    }

    @Override
    public void stopped() {
        throw new IllegalStateException("Elevator is moving. You cant stop now");
    }
}
