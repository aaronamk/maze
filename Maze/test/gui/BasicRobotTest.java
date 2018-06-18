package gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import generation.Order;
import gui.Robot.Direction;
import gui.Robot.Turn;

class BasicRobotTest {
	Robot robot = new BasicRobot();
	Controller controller = new Controller();
	
	/**
	 * Creates a robot and connects it to a controller.
	 */
	@Before
	public void setUp() {
		controller.setBuilder(Order.Builder.Prim);
		controller.start();
		robot.setMaze(controller);
	}
	
	/**
	 * Test case: Tests for existence of the robot
	 * <p>
	 * Correct Outcome: the robot exists
	 */
	@Test
	void testRobotExistence() {
		assertTrue(robot!=null);
	}
	
	/**
	 * Test case: Tests for existence of the controller
	 * <p>
	 * Correct Outcome: the controller exists
	 */
	@Test
	void testControllerExistence() {
		assertTrue(controller!=null);
	}
	
	/**
	 * Test case: Tests for sensing, then rotating being consistant.
	 * <p>
	 * Correct Outcome: gives the same result no matter which side it is sensing from.
	 */
	@Test
	void testSensingConsistency() {
		int sense = robot.distanceToObstacle(Direction.LEFT);
		robot.rotate(Turn.RIGHT);
		assertEquals(sense, robot.distanceToObstacle(Direction.BACKWARD));
	}
	
	/**
	 * Test case: Tests the odometer
	 * <p>
	 * Correct Outcome: the odometer has increased by two
	 */
	@Test
	void testOdometer() {
		robot.move(2, false);
		assertEquals(2, robot.getOdometerReading());
	}
	
	/**
	 * Test case: Tests the battery level
	 * <p>
	 * Correct Outcome: The battery properly reduces
	 */
	@Test
	void testBattery() {
		int energy = (int)robot.getBatteryLevel();
		robot.rotate(Turn.AROUND);
		robot.rotate(Turn.AROUND);
		robot.move(1, false);
		assertEquals(energy, robot.getBatteryLevel()-robot.getEnergyForFullRotation()-robot.getEnergyForStepForward());
	}
}
