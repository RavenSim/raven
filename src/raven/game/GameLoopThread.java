package raven.game;

import raven.ui.GameCanvas;
import raven.ui.RavenUI;
import raven.utils.Log;
import raven.utils.MapLoadedException;

public class GameLoopThread extends Thread {

	private RavenUI ui;
	private RavenGame game;
	private static int threadID;

	public GameLoopThread(RavenGame game, RavenUI ui) {
		threadID++;
		this.game = game;
		this.ui = ui;
	}

	public void run() {
		Log.info("raven", "Starting game thread " + threadID + "...");

		long lastTime = System.nanoTime();

		while (true) {
			// TODO Resize UI if the map changes!
			boolean loadedMap = false;

			long currentTime = System.nanoTime();

			try {
				game.update((currentTime - lastTime) * 1.0e-9);
			} catch (MapLoadedException e) {
				loadedMap = true;
				ui.dispose();
				ui = new RavenUI(game);

			}
			// Always dispose the canvas
			try {
				GameCanvas.startDrawing(game.getMap().getSizeX(), game.getMap()
						.getSizeY(), loadedMap);
				loadedMap = false;
				game.render();
			} finally {
				GameCanvas.stopDrawing();
			}

			long millisToNextUpdate = Math
					.max(0, (1000 / 60)
							- ((System.nanoTime() - currentTime) / 1000000));
			lastTime = currentTime;
			try {
				Thread.sleep(millisToNextUpdate);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}