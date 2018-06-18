package gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import generation.Order;
import gui.Robot.Direction;
import gui.Robot.Turn;

class BasicRobotTest {
	Robot robot;
	Controller controller;
	
	/**
	 * Creates a robot and connects it to a controller.
	 */
	@Before
	public void setUp() {
		controller = new Controller();
		controller.setBuilder(Order.Builder.Prim);
		controller.start();
		robot = new BasicRobot();
		robot.setMaze(controller);
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
	
	@Test
	void testOdometer() {
		int sense = robot.distanceToObstacle(Direction.LEFT);
		robot.rotate(Turn.RIGHT);
		assertEquals(sense, robot.distanceToObstacle(Direction.BACKWARD));
	}
}
