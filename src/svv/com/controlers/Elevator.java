package svv.com.controlers;

import svv.com.views.ElevatorView;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator {
    public static final int FLOORS = 5;
    public static final int FIRST_FLOOR = 1;

    private Queue<Integer> queueInElevator;
    private Queue<WaiterAtFloor> queueOnFloors;
    private AtomicInteger currentFloor;

    private State state;
    private MovingUpState movingUpState;
    private MovingDownState movingDownState;
    private AtFloorState atFloorState;

    private ElevatorView elevatorView;

    public Elevator() {
        currentFloor = new AtomicInteger(FIRST_FLOOR);

        queueInElevator = new ConcurrentLinkedQueue<Integer>();
        queueOnFloors = new ConcurrentLinkedQueue<WaiterAtFloor>();

        movingUpState = new MovingUpState(this);
        movingDownState = new MovingDownState(this);
        atFloorState = new AtFloorState(this);

        state = atFloorState;
    }

    public Queue<WaiterAtFloor> getQueueOnFloors() {
        return queueOnFloors;
    }

    public Queue<Integer> getQueueInElevator() {
        return queueInElevator;
    }

    public AtomicInteger getCurrentFloor() {
        return currentFloor;
    }

    public void setState(State state) {
        if (state instanceof MovingDownState) {
            System.out.println("MovingDownState");
        } else if (state instanceof MovingUpState) {
            System.out.println("MovingUpState");
        } else {
            System.out.println("AtFloorState");
        }
        this.state = state;
    }

    public MovingUpState getMovingUpState() {
        return movingUpState;
    }

    public MovingDownState getMovingDownState() {
        return movingDownState;
    }

    public AtFloorState getAtFloorState() {
        return atFloorState;
    }

    public void addUpWaiter(int currentFloor) {
        queueOnFloors.add(new WaiterAtFloor(currentFloor, Orientation.UP));
    }

    public void addDownWaiter(int currentFloor) {
        queueOnFloors.add(new WaiterAtFloor(currentFloor, Orientation.DOWN));
    }
    
    public void moving() {
        if (state instanceof AtFloorState) {
            Integer nextFloor = queueInElevator.peek();
            if (nextFloor == null) {
                nextFloor = queueOnFloors.peek().getFloor();
            }
            if (nextFloor > getCurrentFloor().get()) {
                setState(movingUpState);
            } else {
                setState(movingDownState);
            }
        }
        state.moving();
    }

    public void setElevatorView(ElevatorView elevatorView) {
        this.elevatorView = elevatorView;
    }

    public ElevatorView getElevatorView() {
        return elevatorView;
    }

    public void stopped() {
        state.atFloor();
    }
}