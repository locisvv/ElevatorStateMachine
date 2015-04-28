package svv.com.tests.controlers;

import org.junit.Before;
import org.junit.Test;
import svv.com.controlers.Elevator;
import svv.com.controlers.Orientation;
import svv.com.controlers.WaiterAtFloor;
import svv.com.views.MainPanel;

import static org.junit.Assert.*;

public class MovingDownStateTest {
    Elevator elevator;
    static final int SECOND_FLOOR = 2;
    static final int  THIRD_FLOOR = 3;
    static final int FOURTH_FLOOR = 4;
    static final int  FIFTH_FLOOR = 5;

    @Before
    public void runBeforeEveryTest() {
        elevator = new Elevator();
        MainPanel mainPanel = new MainPanel(elevator);
    }

    @Test
    public void Moving_2ndFloor_2ndFloorIsCurrentFloor() throws Exception {
        elevator.getQueueInElevator().add(SECOND_FLOOR);
        elevator.getMovingUpState().moving();

        assertEquals(elevator.getCurrentFloor().get(), SECOND_FLOOR);
    }

    @Test
    public void Moving_2ndAnd4sFloor_2ndFloorIsCurrentFloor() throws Exception {
        elevator.getCurrentFloor().set(FOURTH_FLOOR);
        elevator.getQueueInElevator().add(SECOND_FLOOR);

        elevator.getMovingDownState().moving();

        assertEquals(elevator.getCurrentFloor().get(), SECOND_FLOOR);
    }

    @Test
    public void Moving_UpWaiters_2ndFloorIsCurrentFloor() throws Exception {
        elevator.getCurrentFloor().set(FOURTH_FLOOR);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(SECOND_FLOOR, Orientation.DOWN));

        elevator.getMovingDownState().moving();

        assertEquals(elevator.getCurrentFloor().get(), SECOND_FLOOR);
    }

    @Test
    public void Moving_SomeWaiters_2ndFloorIsCurrentFloor() throws Exception {
        elevator.getCurrentFloor().set(5);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(SECOND_FLOOR, Orientation.DOWN));
        elevator.getQueueInElevator().add(FIFTH_FLOOR);
        elevator.getQueueInElevator().add(FOURTH_FLOOR);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(FOURTH_FLOOR, Orientation.UP));
        elevator.getQueueInElevator().add(THIRD_FLOOR);

        elevator.getMovingDownState().moving();

        assertEquals(elevator.getCurrentFloor().get(), SECOND_FLOOR);
    }
}