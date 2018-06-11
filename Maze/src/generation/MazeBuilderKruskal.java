package generation;

import java.util.ArrayList;


/**
 * This class is an extension of the MazeBuilder class.
 * It uses the Kruskal Algorithm to generate a maze.
 * 
 * @author Aaron
 * 
 */
public class MazeBuilderKruskal extends MazeBuilder implements Runnable {
	private ArrayList<ArrayList<int[]>> Families = new ArrayList<>();
	private ArrayList<Wall> Walls = new ArrayList<>();
	
	/**
	 * Constructor for a randomized maze generation
	 */
	public MazeBuilderKruskal() {
		super();
	}
	
	/**
	 * Constructor with option to make maze generation deterministic or random
	 * @param deterministic
	 */
	public MazeBuilderKruskal(boolean deterministic) {
		super(deterministic);
	}
	
	/**
	 * This method generates pathways into the maze.
	 */
	@Override
	protected void generatePathways() {
		System.out.print(width);
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				int[] coordinates = {i,j};
				ArrayList<int[]> ToAdd = new ArrayList<>();
				ToAdd.add(coordinates);
				Families.add(ToAdd);
				
				if(i != width-1)
					Walls.add(new Wall(i,j,CardinalDirection.East));
				if(j != height-1)
					Walls.add(new Wall(i,j,CardinalDirection.South));
			}
		}
		
		while(Families.size()>1) {
			Wall RandWall = Walls.remove((int)(Math.random()*Walls.size()));
			int ParentPos = -1, ChildPos = -1;
			
			for(int i=0; i<Families.size(); i++) {
				for(int j=0; j<Families.get(i).size(); j++) {
					if(Families.get(i).get(j)[0] == RandWall.getX() && Families.get(i).get(j)[1] == RandWall.getY())
						ParentPos = i;
					if(Families.get(i).get(j)[0] == RandWall.getNeighborX() && Families.get(i).get(j)[1] == RandWall.getNeighborY())
						ChildPos = i;
				}
			}
			if(combineFamilies(ParentPos, ChildPos)) {
				cells.deleteWall(RandWall);
			}
		}
	}
	
	/**
	 * Combines two families
	 * @param ParentFam
	 * @param ChildFam
	 * @return true if the families were different
	 */
	private boolean combineFamilies(int ParentFam, int ChildFam) {
		if(ParentFam == ChildFam)
			return false;
		while(Families.get(ChildFam).size()>0)
			Families.get(ParentFam).add(Families.get(ChildFam).remove(0));
		Families.remove(ChildFam);
		return true;
	}
}