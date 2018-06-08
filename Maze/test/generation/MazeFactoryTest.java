package generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import gui.StateGenerating;

class MazeFactoryTest {

	@Before
	public void setUp() {
		Order OrderStatus = new StateGenerating();
		MazeFactory maze = new MazeFactory(true);
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
