package gui;

import generation.CardinalDirection;
import gui.Constants.UserInput;

public class BasicRobot implements Robot {
	
	private final int MAX_ENERGY = 2500;
	private final int FULL_ROTATION_ENERGY_COST = 12;
	private final int STEP_ENERGY_COST = 5;
	private final int SENSOR_ENERGY_COST = 1;
	
	private Controller Maze;
	private boolean IsStopped;
	private float Energy = MAX_ENERGY;
	private int Odometer = 0;
	
	@Override
	public void rotate(Turn turn) {
		if(Energy>=FULL_ROTATION_ENERGY_COST/4) {
			switch(turn) {
				case LEFT:
					Maze.keyDown(UserInput.Left, 0);
					Energy -= FULL_ROTATION_ENERGY_COST/4;
					break;
				case RIGHT:
					Maze.keyDown(UserInput.Right, 0);
					Energy -= FULL_ROTATION_ENERGY_COST/4;
					break;
				case AROUND:
					if(Energy >= FULL_ROTATION_ENERGY_COST/2) {
						Maze.keyDown(UserInput.Right, 0);
						Maze.keyDown(UserInput.Right, 0);
						Energy -= FULL_ROTATION_ENERGY_COST/2;
					}
					else
						IsStopped = true;
					break;
			}
		}
		else {
			System.out.println("stopped");
			IsStopped = true;
		}
	}

	@Override
	public void move(int distance, boolean manual) {
		System.out.println("forward");
		for(int i=0; i<distance; i++) {
			int[] temp = Maze.getCurrentPosition();
			if(Energy >= STEP_ENERGY_COST)
				Maze.keyDown(UserInput.Up, 0);
			else {
				IsStopped = true;
				System.out.println("stpped");
				break;
			}
			if(temp[0] != Maze.getCurrentPosition()[0] || temp[1] != Maze.getCurrentPosition()[1])
				Energy -= STEP_ENERGY_COST;
			else break;
		}
	}

	@Override
	public int[] getCurrentPosition() throws Exception {
		return Maze.getCurrentPosition();
	}

	@Override
	public void setMaze(Controller controller) {
		Maze = controller; 
	}

	@Override
	public boolean isAtExit() {
		return (Maze.getMazeConfiguration().getDistanceToExit(Maze.getCurrentPosition()[0], Maze.getCurrentPosition()[1]) == 1);
	}

	@Override
	public boolean canSeeExit(Direction direction) throws UnsupportedOperationException {
		try {
			this.distanceToObstacle(direction);
			return true;
		} catch(UnsupportedOperationException e) {
			return false;
		}
	}

	@Override
	public boolean isInsideRoom() throws UnsupportedOperationException {
		return Maze.getMazeConfiguration().getMazecells().isInRoom(Maze.getCurrentPosition()[0], Maze.getCurrentPosition()[1]);
	}

	@Override
	public boolean hasRoomSensor() {
		return true;
	}

	@Override
	public CardinalDirection getCurrentDirection() {
		return Maze.getCurrentDirection();
	}

	@Override
	public float getBatteryLevel() {
		return Energy;
	}

	@Override
	public void setBatteryLevel(float level) {
		Energy = level;
	}

	@Override
	public int getOdometerReading() {
		return Odometer;
	}

	@Override
	public void resetOdometer() {
		Odometer = 0;
	}

	@Override
	public float getEnergyForFullRotation() {
		return FULL_ROTATION_ENERGY_COST;
	}

	@Override
	public float getEnergyForStepForward() {
		return STEP_ENERGY_COST;
	}

	@Override
	public boolean hasStopped() {
		return IsStopped;
	}

	@Override
	public int distanceToObstacle(Direction direction) throws UnsupportedOperationException {
		if(Energy >= SENSOR_ENERGY_COST) {
			Energy -= SENSOR_ENERGY_COST;
			int add[] = {0, 0};
			switch(convertRelativeDirection(direction)) {
			case North:
				add[1] = -1;
				break;
			case East:
				add[0] = 1;
				break;
			case South:
				add[1] = 1;
				break;
			case West:
				add[0] = -1;
				break;
			}
			
			int[] CurPos = {Maze.getCurrentPosition()[0], Maze.getCurrentPosition()[1]};
			try {
				if(Maze.getMazeConfiguration().getMazecells().hasWall(CurPos[0], CurPos[1], convertRelativeDirection(direction))){
					return 0;
				}
				int counter = 1;
				while(!Maze.getMazeConfiguration().getMazecells().hasWall(CurPos[0], CurPos[1], convertRelativeDirection(direction))) {
					counter++;
					CurPos[0] += add[0];
					CurPos[1] += add[1];
					System.out.println("thing");
				}
				return counter;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new UnsupportedOperationException();
			}
		}
		IsStopped = true;
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasDistanceSensor(Direction direction) {
		return true;
	}
	
	private CardinalDirection convertRelativeDirection(Direction cd) {
		switch(cd) {
			case FORWARD:
				return Maze.getCurrentDirection();
			case BACKWARD:
				switch(Maze.getCurrentDirection()) {
					case North:
						return CardinalDirection.South;
					case East:
						return CardinalDirection.West;
					case South:
						return CardinalDirection.North;
					case West:
						return CardinalDirection.East;
				}
			case RIGHT:
				switch(Maze.getCurrentDirection()) {
					case North:
						return CardinalDirection.West;
					case East:
						return CardinalDirection.North;
					case South:
						return CardinalDirection.East;
					case West:
						return CardinalDirection.South;
			}
			case LEFT:
				switch(Maze.getCurrentDirection()) {
					case North:
						return CardinalDirection.East;
					case East:
						return CardinalDirection.South;
					case South:
						return CardinalDirection.West;
					case West:
						return CardinalDirection.North;
			}
		}
		return null;
	}
}
