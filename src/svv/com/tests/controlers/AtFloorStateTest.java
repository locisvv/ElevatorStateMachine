package svv.com.tests.controlers;

import org.junit.Before;
import org.junit.Test;
import svv.com.controlers.Elevator;
import svv.com.controlers.Orientation;
import svv.com.controlers.WaiterAtFloor;
import svv.com.views.MainFrame;

import static org.junit.Assert.*;

public class AtFloorStateTest {
    Elevator elevator;

    @Before
    public void runBeforeEveryTest() {
        elevator = new Elevator();
        MainFrame mainFrame = new MainFrame(elevator);
    }

    @Test
    public void AtFloor_2dnFloorWaiter_EmptyQueue() throws Exception {
        elevator.getQueueOnFloors().add(new WaiterAtFloor(2, Orientation.UP));
        elevator.getAtFloorState().atFloor();

        assertTrue(elevator.getQueueOnFloors().isEmpty());
    }

    @Test
    public void AtFloor_SomeWaiter_EmptyQueue() throws Exception {
        elevator.getQueueOnFloors().add(new WaiterAtFloor(2, Orientation.UP));
        elevator.getQueueOnFloors().add(new WaiterAtFloor(4, Orientation.DOWN));
        elevator.getQueueOnFloors().add(new WaiterAtFloor(1, Orientation.UP));
        elevator.getQueueOnFloors().add(new WaiterAtFloor(5, Orientation.DOWN));

        elevator.getAtFloorState().atFloor();

        assertTrue(elevator.getQueueOnFloors().isEmpty());
    }

    @Test
    public void AtFloor_2dnFloorWaiterInElevator_EmptyElevatorQueue() throws Exception {
        elevator.getQueueInElevator().add(2);

        elevator.getAtFloorState().atFloor();

        assertTrue(elevator.getQueueInElevator().isEmpty());
    }

    @Test
    public void AtFloor_SomeWaitersInElevator_EmptyElevatorQueue() throws Exception {
        elevator.getQueueInElevator().add(2);
        elevator.getQueueInElevator().add(5);
        elevator.getQueueInElevator().add(1);
        elevator.getQueueInElevator().add(4);

        elevator.getAtFloorState().atFloor();

        assertTrue(elevator.getQueueInElevator().isEmpty());
    }

    @Test
    public void AtFloor_SomeDiferentWaiters_BouthQueueAreEmpty() throws Exception {
        elevator.getQueueInElevator().add(2);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(2, Orientation.UP));
        elevator.getQueueInElevator().add(5);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(4, Orientation.DOWN));
        elevator.getQueueInElevator().add(1);
        elevator.getQueueInElevator().add(4);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(1, Orientation.UP));

        elevator.getAtFloorState().atFloor();

        assertTrue(elevator.getQueueInElevator().isEmpty());
    }

}