package gui;

import generation.Distance;

/**
 * This class designates a RobotDriver that knows the fastest way of getting out of the maze
 * 
 * @author Aaron
 *
 */
public class Wizard implements RobotDriver{
	Robot Robot;
	private int MaxEnergy;
	Distance dist;
	
	@Override
	public void setRobot(Robot r) {
		this.Robot = r;
		MaxEnergy = (int)Robot.getBatteryLevel();
	}

	@Override
	public void setDimensions(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDistance(Distance distance) {
		dist = distance;
		
	}

	@Override
	public boolean drive2Exit() throws Exception {
		
		throw new Exception();
	}

	@Override
	public float getEnergyConsumption() {
		return MaxEnergy-Robot.getBatteryLevel();
	}

	@Override
	public int getPathLength() {
		return Robot.getOdometerReading();
	}

}
