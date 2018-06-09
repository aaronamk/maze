package generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import generation.Order.Builder;

class MazeFactoryTest {
	StubOrder MyOrder = new StubOrder(5, Builder.Prim, true);
	MazeConfiguration MazeConfig;
	
	@Before
	public void setUp() {
		MazeFactory maze = new MazeFactory(true);
		
		maze.order(MyOrder);
		maze.waitTillDelivered();
		MazeConfig = MyOrder.getMazeConfiguration();
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
