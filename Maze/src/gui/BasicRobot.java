package gui;

import generation.CardinalDirection;

public class BasicRobot implements Robot {
	
	final int MAX_ENERGY = 2500;
	final int FULL_ROTATION_ENERGY_COST = 12;
	final int STEP_ENERGY_COST = 5;
	final int SENSOR_ENERGY_COST = 1;
	
	private Controller Maze;
	private int[] Position;
	private CardinalDirection Direction;
	private float Energy = MAX_ENERGY;
	private boolean IsStopped;
	private int Odometer = 0;
	
	@Override
	public void rotate(Turn turn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(int distance, boolean manual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getCurrentPosition() throws Exception {
		return Position;
	}

	@Override
	public void setMaze(Controller controller) {
		Maze = controller;
	}

	@Override
	public boolean isAtExit() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRoomSensor() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CardinalDirection getCurrentDirection() {
		return Direction;
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
		// TODO Auto-generated method stub
		return false;
	}
}
