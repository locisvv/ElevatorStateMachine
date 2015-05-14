package svv.com.tests.controlers;

import org.junit.Before;
import org.junit.Test;
import svv.com.controlers.Elevator;
import svv.com.controlers.Orientation;
import svv.com.controlers.WaiterAtFloor;
import svv.com.views.MainPanel;

import static org.junit.Assert.*;

public class AtFloorStateTest {
    Elevator elevator;

    @Before
    public void runBeforeEveryTest() {
        elevator = new Elevator();
        new MainPanel(elevator);
    }

    @Test
    public void AtFloor_2dnFloorWaiter_EmptyQueue() throws Exception {
        elevator.getCurrentFloor().set(2);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(2, Orientation.UP));
        elevator.getAtFloorState().atFloor();

        assertTrue(elevator.getQueueOnFloors().isEmpty());
    }

    @Test
    public void AtFloor_SomeWaiter_EmptyQueue() throws Exception {
        elevator.getCurrentFloor().set(2);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(2, Orientation.UP));
        elevator.getAtFloorState().atFloor();

        elevator.getCurrentFloor().set(4);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(4, Orientation.DOWN));
        elevator.getAtFloorState().atFloor();

        assertTrue(elevator.getQueueOnFloors().isEmpty());
    }

    @Test
    public void AtFloor_2dnFloorWaiterInElevator_EmptyElevatorQueue() throws Exception {
        elevator.getCurrentFloor().set(2);
        elevator.getQueueInElevator().add(2);
        elevator.getAtFloorState().atFloor();

        assertTrue(elevator.getQueueInElevator().isEmpty());
    }

    @Test
    public void AtFloor_AddTwoWaitersInElevator_EmptyElevatorQueue() throws Exception {
        elevator.getCurrentFloor().set(2);
        elevator.getQueueInElevator().add(2);
        elevator.getAtFloorState().atFloor();

        elevator.getCurrentFloor().set(5);
        elevator.getQueueInElevator().add(5);
        elevator.getAtFloorState().atFloor();

        assertTrue(elevator.getQueueInElevator().isEmpty());
    }

    @Test
    public void AtFloor_AddTwoDifferentWaiters_BoutsQueueAreEmpty() throws Exception {
        elevator.getCurrentFloor().set(2);

        elevator.getQueueInElevator().add(2);
        elevator.getQueueOnFloors().add(new WaiterAtFloor(2, Orientation.UP));
        elevator.getQueueOnFloors().add(new WaiterAtFloor(2, Orientation.DOWN));

        elevator.getAtFloorState().atFloor();

        assertTrue(elevator.getQueueOnFloors().isEmpty());
        assertTrue(elevator.getQueueInElevator().isEmpty());
    }

}