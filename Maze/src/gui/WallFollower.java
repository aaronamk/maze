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
	public void setDistance(Distance distance) { }

	@Override
	public boolean drive2Exit() throws Exception {
		if(Robot.isInsideRoom()) {
			if(Robot.distanceToObstacle(Direction.RIGHT)==0)
				Robot.rotate(Turn.AROUND);
			else
				Robot.move(Robot.distanceToObstacle(Direction.FORWARD), false);
		}
		while(!Robot.isAtExit()) {
			System.out.println("not at exit");
			if(Robot.getBatteryLevel() == 0) {
				System.out.println("no battery");
				return false;
			}
			if(Robot.distanceToObstacle(Direction.LEFT)!=0) {
				Robot.rotate(Turn.LEFT);
				Robot.move(1, false);
				System.out.println("left");
			}
			else if(Robot.distanceToObstacle(Direction.FORWARD)==0) {
				Robot.rotate(Turn.RIGHT);
				System.out.println("right");
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