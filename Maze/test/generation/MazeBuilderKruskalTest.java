package generation;

import static org.junit.Assert.*;
import generation.CardinalDirection;
import generation.Cells;
import generation.Wall;
import generation.Order.Builder;

import org.junit.Before;
import org.junit.Test;


/**
 * Tests individual methods of Cells class. 
 * 
 * 
 * @author Aaron Klein
 *
 */
public class MazeBuilderKruskalTest{
	StubOrder MyOrder = new StubOrder(5, Builder.Kruskal, true);
	MazeConfiguration MazeConfig;
	
	/**
	 * We make a maze.
	 */
	@Before
	public void setUp() {
		MazeFactory maze = new MazeFactory(true);
		maze.order(MyOrder);
		maze.waitTillDelivered();
		MazeConfig = MyOrder.getMazeConfiguration();
	}
	
	/**
	 * Test case: Check if the program has successfully set the maze configuration equal to something.
	 * <p>
	 * Correct behavior: the maze configuration is set to something.
	 */
	@Test
	public void testMazeCreated() {
		assertNotNull(MazeConfig);
	}
	
	/**
 * Test case: Test the size of the maze.
	 * <p>
	 * Correct behavior: Width and height are 25
	 */
	@Test
	public void testMazeSize() {
		assertEquals(25,MazeConfig.getHeight());
		assertEquals(25,MazeConfig.getWidth());
	}
	
	/**
	 * Test case: There is a non-infinite path to the exit
	 * <p>
	 * Correct behavior: Each spot has a numbered path to the exit that is not infinite.
	 */
	@Test
	public void testExitable() {
		for(int i=0; i<25; i++) {
			for(int j=0; j<25; j++) {
				assertTrue(2000 > MazeConfig.getDistanceToExit(i,j));
			}
		}
	}
	
	/**
	 * Test case: Make sure determinism works right
	 * <p>
	 * Correct behavior: A new deterministic maze configuration object is equal to the previous one.
	 */
	@Test
	public void testDeterminism() {
		StubOrder NewOrder = new StubOrder(5, Builder.Kruskal, true);
		MazeFactory NewMaze = new MazeFactory(true);
		NewMaze.order(NewOrder);
		NewMaze.waitTillDelivered();
		MazeConfiguration NewMazeConfig = NewOrder.getMazeConfiguration();
		assertEquals(MazeConfig.getDistanceToExit(2, 3), NewMazeConfig.getDistanceToExit(2, 3));
	}
	
	
}
