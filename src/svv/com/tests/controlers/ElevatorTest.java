package svv.com.tests.controlers;

import org.junit.Before;
import org.junit.Test;
import svv.com.controlers.Elevator;
import svv.com.controlers.Orientation;
import svv.com.controlers.WaiterAtFloor;
import svv.com.views.MainFrame;

import static org.junit.Assert.*;

public class ElevatorTest {
    Elevator elevator;
    @Before
    public void runBeforeEveryTest() {
        elevator = new Elevator();
        MainFrame mainFrame = new MainFrame(elevator);
    }

    @Test
    public void WillAnybodyGoOutAtThisFloor_2ndFloor_True() throws Exception {
        elevator.getQueueInElevator().add(2);
        assertTrue(elevator.willAnybodyGoOutAtThisFloor(2));
    }

    @Test
    public void WillAnybodyGoOutAtThisFloor_2ndFloor_False() throws Exception {
        elevator.getQueueInElevator().add(2);
        assertFalse(elevator.willAnybodyGoOutAtThisFloor(1));
    }

    @Test
    public void WillAnybodyGoIntoElevator_SomeWaiters_True() throws Exception {
        elevator.getQueueOnFloors().add(new WaiterAtFloor(2, Orientation.UP));
        elevator.getQueueOnFloors().add(new WaiterAtFloor(4, Orientation.DOWN));
        elevator.getQueueOnFloors().add(new WaiterAtFloor(5, Orientation.DOWN));

        assertTrue(elevator.willAnybodyGoIntoElevator(2, Orientation.UP));
        assertTrue(elevator.willAnybodyGoIntoElevator(4, Orientation.DOWN));
        assertTrue(elevator.willAnybodyGoIntoElevator(5, Orientation.DOWN));
    }

    @Test
    public void WillAnybodyGoIntoElevator_2ndFloorAndDiferentOrientation_True() throws Exception {
        elevator.getQueueOnFloors().add(new WaiterAtFloor(2, Orientation.UP));
        assertTrue(elevator.willAnybodyGoIntoElevator(2, Orientation.DOWN));
    }

    @Test
    public void WillAnybodyGoIntoElevator_SomeWaiters_False() throws Exception {
        assertFalse(elevator.willAnybodyGoIntoElevator(1, Orientation.UP));

        elevator.getQueueOnFloors().add(new WaiterAtFloor(2, Orientation.UP));
        assertFalse(elevator.willAnybodyGoIntoElevator(3, Orientation.DOWN));
    }
}