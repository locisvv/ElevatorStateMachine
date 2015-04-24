package svv.com.controlers;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;
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
    private StoppedState stoppedState;

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    private JProgressBar progressBar;

    public Elevator() {
        currentFloor = new AtomicInteger(FIRST_FLOOR);

        queueInElevator = new LinkedList<Integer>();
        queueOnFloors = new LinkedList<WaiterAtFloor>();

        movingUpState = new MovingUpState(this);
        movingDownState = new MovingDownState(this);
        stoppedState = new StoppedState(this);

        state = stoppedState;
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
            System.out.println("StoppedState");
        }
        this.state = state;
    }

    public MovingUpState getMovingUpState() {
        return movingUpState;
    }

    public MovingDownState getMovingDownState() {
        return movingDownState;
    }

    public StoppedState getStoppedState() {
        return stoppedState;
    }

    public void addUpWaiter(int currentFloor) {
        queueOnFloors.add(new WaiterAtFloor(currentFloor, Orientation.UP));
        moving();
    }

    public void addDownWaiter(int currentFloor) {
        queueOnFloors.add(new WaiterAtFloor(currentFloor, Orientation.DOWN));
        moving();
    }
    
    public void moving() {
        if (state instanceof StoppedState) {
            Integer nextFloor = queueInElevator.peek();
            if (nextFloor == null) {
                nextFloor = queueOnFloors.peek().getFloor();
            }

            if (nextFloor > getCurrentFloor().get()) {
                setState(movingUpState);
            } else {
                setState(movingDownState);
            }

            state.moving();
        }
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void stopped() {
        state.stopped();
    }
}
