package gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class WallFollowerTest {
	RobotDriver driver = new WallFollower();
	
	/**
	 * Properly sets up the WallFollower driver.
	 */
	@Before
	public void setUp() {
		driver.setRobot(new BasicRobot());
	}
	
	/**
	 * Test case: Tests for existence of the driver
	 * <p>
	 * Correct Outcome: the driver exists
	 */
	@Test
	void testRobotExistence() {
		assertTrue(driver!=null);
	}
	
	/**
	 * Test case: Tests for the solution of the maze
	 * <p>
	 * Correct Outcome: the robot gets to the end
	 */
	@Test
	void testSolve() {
		try {
			driver.drive2Exit();
		} catch (Exception e) {
			fail("It ran out of energy");
		}
		
	}

}
