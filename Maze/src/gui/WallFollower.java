package gui;

import generation.Distance;
import gui.Robot.Direction;
import gui.Robot.Turn;

/**
 * This class designates a RobotDriver that follows walls
 * 
 * @author Aaron
 *
 */
public class WallFollower implements RobotDriver{
	Robot Robot;
	private int MaxEnergy;
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean drive2Exit() throws Exception {
		if(Robot.isInsideRoom()) {
			if(Robot.distanceToObstacle(Direction.RIGHT)==0)
				Robot.rotate(Turn.AROUND);
			else
				Robot.move(Robot.distanceToObstacle(Direction.FORWARD), false);
		}
		while(!Robot.isAtExit()) {
			if(Robot.getBatteryLevel() == 0) {
				return false;
			}
			if(Robot.distanceToObstacle(Direction.LEFT)!=0) {
				Robot.rotate(Turn.LEFT);
				Robot.move(1, false);
			}
			else if(Robot.distanceToObstacle(Direction.FORWARD)==0) {
				Robot.rotate(Turn.RIGHT);
				Robot.move(1, false);
			}
			else
				Robot.move(1, false);
			return true;
		}
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