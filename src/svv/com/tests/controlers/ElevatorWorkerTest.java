package svv.com.tests.controlers;

import org.junit.Before;
import org.junit.Test;
import svv.com.controlers.Elevator;
import svv.com.controlers.ElevatorWorker;
import svv.com.controlers.Orientation;
import svv.com.controlers.WaiterAtFloor;
import svv.com.views.MainPanel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class ElevatorWorkerTest {
    static final int SECOND_FLOOR = 2;
    static final int  THIRD_FLOOR = 3;
    static final int FOURTH_FLOOR = 4;
    static final int  FIFTH_FLOOR = 5;

    Elevator elevator;
    ElevatorWorker elevatorWorker;

    @Before
    public void runBeforeEveryTest() {
        elevator = new Elevator();
        new MainPanel(elevator);

        elevatorWorker = new ElevatorWorker(elevator);
    }

    @Test
    public void Moving_SomeDifferentWaiters_2ndFloorIsCurrentFloor() throws Exception {
        elevator.getCurrentFloor().set(5);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(SECOND_FLOOR, Orientation.DOWN));
        elevator.getQueueInElevator().add(FIFTH_FLOOR);
        elevator.getQueueInElevator().add(FOURTH_FLOOR);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(FOURTH_FLOOR, Orientation.UP));
        elevator.getQueueInElevator().add(THIRD_FLOOR);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(elevatorWorker);

        waitingUntilTheElevatorWorkerCompletes();

        assertEquals(elevator.getCurrentFloor().get(), SECOND_FLOOR);
    }

    @Test
    public void Moving_CurrentFloorIsFourAndFourWaitersOnDifferentFloors_5thFloorIsCurrentFloor() throws Exception {
        elevator.getCurrentFloor().set(4);
        elevator.getQueueInElevator().add(SECOND_FLOOR);
        elevator.getQueueInElevator().add(FIFTH_FLOOR);
        elevator.getQueueInElevator().add(THIRD_FLOOR);
        elevator.getQueueInElevator().add(FOURTH_FLOOR);
        elevator.getMovingUpState().moving();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(elevatorWorker);

        waitingUntilTheElevatorWorkerCompletes();

        assertEquals(elevator.getCurrentFloor().get(), FIFTH_FLOOR);
    }

    private void waitingUntilTheElevatorWorkerCompletes() {
        while (true) {
            if (elevator.getQueueInElevator().isEmpty()
                    && elevator.getQueueOnFloors().isEmpty()) {
                break;
            }
        }
    }
}