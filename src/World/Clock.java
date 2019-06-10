package World;

public class Clock {
	private static long elapsedTime;
	private static long lastTimeCheck;
	private static double fps;
	private static long lastFpsCheck;
	private static final int TARGET_FPS = 60;
	private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	private static double delta;

	public Clock() {
		lastTimeCheck = System.nanoTime();
		elapsedTime = 0;
	}

	public static void update() {
		long currentTime = System.nanoTime();  //if the computer is fast you need more precision
		elapsedTime = currentTime - lastTimeCheck;
		lastTimeCheck = currentTime;

		delta = elapsedTime / ((double) OPTIMAL_TIME);
		lastFpsCheck += elapsedTime;
		fps++;

		if (lastFpsCheck > 1000000000) {
			lastFpsCheck = 0;
			fps = 0;
		}

		try {
			Thread.sleep((lastTimeCheck - System.nanoTime() + OPTIMAL_TIME) / 1000000);
		} catch (InterruptedException e) {
			//lol
		}
	}

	public static int getFps() {
		return (int)Math.round(fps);
	}

	public static int getDelta() {
		return (int)Math.round(delta);
	}
}
