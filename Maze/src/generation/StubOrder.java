package generation;

public class StubOrder implements Order {
	private int SkillLevel;
	Builder Builder;
	boolean Perfect;
	int Progress = 0;
	MazeConfiguration MazeConfig;
	
	public StubOrder() {}
	
	public StubOrder(int SkillLevel, Builder Builder, boolean Perfect) {
		this.SkillLevel = SkillLevel;
		this.Builder = Builder;
		this.Perfect = Perfect;
	}
	
	@Override
	public int getSkillLevel() {
		return SkillLevel;
	}

	@Override
	public Builder getBuilder() {
		return Builder;
	}

	@Override
	public boolean isPerfect() {
		return Perfect;
	}

	@Override
	public void deliver(MazeConfiguration mazeConfig) {
		this.MazeConfig = mazeConfig;
	}

	@Override
	public void updateProgress(int percentage) {
		Progress = percentage;
	}
	
	public MazeConfiguration getMazeConfiguration() {
		return MazeConfig;
	}
}
