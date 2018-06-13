package gui;

import generation.CardinalDirection;

public class BasicRobot implements Robot {
	
	final int MAX_ENERGY = 2500;
	final int FULL_ROTATION_ENERGY_COST = 12;
	final int STEP_ENERGY_COST = 5;
	final int SENSOR_ENERGY_COST = 1;
	
	private Controller Maze;
	private boolean IsStopped;
	private float Energy = MAX_ENERGY;
	private int Odometer = 0;
	
	@Override
	public void rotate(Turn turn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(int distance, boolean manual) {
		for(int i=0; i<distance; i++) {
			
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
		}
		catch(UnsupportedOperationException e) {
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasDistanceSensor(Direction direction) {
		return true;
	}
	
	private int[] convertCardinalDirection(CardinalDirection cd) {
		int[] ret = new int[2];
		switch(cd) {
			case North:
				ret[0] = 0;
				ret[1] = -1;
				break;
			case East:
				ret[0] = 1;
				ret[1] = 0;
				break;
			case South:
				ret[0] = 0;
				ret[1] = 1;
				break;
			case West:
				ret[0] = -1;
				ret[1] = 0;
				break;
		}
		return(ret);
	}
}
