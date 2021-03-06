package svv.com.tests.controlers;

import org.junit.Before;
import org.junit.Test;
import svv.com.controlers.Elevator;
import svv.com.views.MainPanel;

import static org.junit.Assert.*;

public class MovingUpStateTest {

    static final int FIRST_FLOOR = 1;
    static final int SECOND_FLOOR = 2;
    static final int  THIRD_FLOOR = 3;
    static final int FOURTH_FLOOR = 4;
    static final int  FIFTH_FLOOR = 5;

    private Elevator elevator;

    @Before
    public void runBeforeEveryTest() {
        elevator = new Elevator();
        MainPanel mainPanel = new MainPanel(elevator);
    }

    @Test
    public void Moving_4thFloorAnd1stFloorWaiters_ElevatorDontGoDown() throws Exception {
        elevator.getQueueInElevator().add(FOURTH_FLOOR);
        elevator.getMovingUpState().moving();

        elevator.getQueueInElevator().add(FIRST_FLOOR);
        elevator.getMovingUpState().moving();

        assertNotEquals(elevator.getCurrentFloor().get(), FIRST_FLOOR);
    }
}