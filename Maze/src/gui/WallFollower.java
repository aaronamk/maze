package gui;

import generation.Distance;

/**
 * This class designates a RobotDriver that follows walls
 * 
 * @author Aaron
 *
 */
public class WallFollower implements RobotDriver{
	Robot Robot = new BasicRobot();
	int[] dimensions = new int[2];
	
	@Override
	public void setRobot(Robot r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDimensions(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDistance(Distance distance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean drive2Exit() throws Exception {
		while(!Robot.isAtExit()) {
			if(Robot.getBatteryLevel() == 0) {
				return false;
			}
			
		}
		return false;
	}

	@Override
	public float getEnergyConsumption() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPathLength() {
		// TODO Auto-generated method stub
		return 0;
	}
}