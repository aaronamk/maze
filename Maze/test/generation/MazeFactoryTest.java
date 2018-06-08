package generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import generation.Order.Builder;

class MazeFactoryTest {
	MazeConfiguration MazeConfig;
	
	@Before
	public void setUp() {
		MazeConfig = (new StubOrder(5, Builder.Kruskal, true)).getMazeConfiguration();
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
