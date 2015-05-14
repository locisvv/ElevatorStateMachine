package svv.com.controlers;

import svv.com.views.MainPanel;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator {
    public static final int FLOORS = 5;
    public static final int FIRST_FLOOR = 1;

    private Queue<Integer> queueInElevator;
    private Queue<WaiterAtFloor> queueOnFloors;
    private AtomicBoolean isStop = new AtomicBoolean(false);
    private CountDownLatch countDownLatch;

    private AtomicInteger currentFloor;
    private State state;
    private MovingUpState movingUpState;
    private MovingDownState movingDownState;
    private AtFloorState atFloorState;

    private MainPanel mainView;

    public Elevator() {
        currentFloor = new AtomicInteger(FIRST_FLOOR);

        queueInElevator = new ConcurrentLinkedQueue<Integer>();
        queueOnFloors = new ConcurrentLinkedQueue<WaiterAtFloor>();

        movingUpState = new MovingUpState(this);
        movingDownState = new MovingDownState(this);
        atFloorState = new AtFloorState(this);

        state = atFloorState;
    }

    public void addUpWaiter(int floor) {
        queueOnFloors.add(new WaiterAtFloor(floor, Orientation.UP));
    }

    public void addDownWaiter(int floor) {
        queueOnFloors.add(new WaiterAtFloor(floor, Orientation.DOWN));
    }

    public void moving() {
        if (state instanceof AtFloorState) {
            defineElevatorOrientation();
        }
        state.moving();
    }
    /**
     * Define where elevator should go up or down
     * */
    public void defineElevatorOrientation() {
        Integer nextFloor = queueInElevator.peek();
        if (nextFloor == null) {
            nextFloor = queueOnFloors.peek().getFloor();
        }

        if (nextFloor > currentFloor.get()) {
            setState(movingUpState);
        } else {
            setState(movingDownState);
        }
    }
    /**
     * Stops elevator on that floor and change moving state to AtFloorState
     *
     *  @param floor the floor where elevator will stop
     * */
    public void stoppingAtFloor(int floor) {
        currentFloor.set(floor);
        state = atFloorState;
        state.atFloor();
    }

    /**
     * Return true if anyone want to go out
     * @param floor the floor where elevator is now
     *
     * @return true if anyone want to go out.
     */
    public boolean willAnybodyGoOutAtThisFloor(int floor) {
        return !queueInElevator.isEmpty() && queueInElevator.contains(floor);
    }

    /**
     * Return true if anyone want to enter in elevator
     *
     * @param currentFloor the floor where elevator is now
     * @param orientation the elevator orientation
     *
     * @return true if anyone want to enter
     */
    public boolean willAnybodyGoIntoElevator(int currentFloor, Orientation orientation) {
        if (!queueOnFloors.isEmpty()) {
            if (queueOnFloors.contains(new WaiterAtFloor(currentFloor, orientation))){
                return true;
            }

            int floor = queueOnFloors.peek().getFloor();
            if (queueInElevator.isEmpty()) {
                return floor == currentFloor;
            } else {
                if (orientation == Orientation.UP) {
                    if (queueInElevator.peek() < floor) {
                        return floor == currentFloor;
                    }
                } else {
                    if ((queueInElevator.peek() > floor)) {
                        return floor == currentFloor;
                    }
                }
            }
        }
        return false;
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
        this.state = state;
    }

    public State getState() {
        return state;
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

    public void setStop(boolean isStop) {
        this.isStop.set(isStop);
    }

    public boolean getStop() {
        return this.isStop.get();
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void setMainView(MainPanel mainView) {
        this.mainView = mainView;
    }

    public MainPanel getMainView() {
        return mainView;
    }
}