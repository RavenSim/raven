package raven;

import raven.game.GameLoopThread;
import raven.game.RavenGame;
import raven.ui.RavenUI;
import raven.utils.Log;
import raven.utils.Log.Level;

public class Main {
	private static RavenUI ui;
	private static RavenGame game;

	public static void main(String args[]) {
		Log.setLevel(Level.INFO);

		game = new RavenGame();
		ui = new RavenUI(game);

		GameLoopThread t1 = new GameLoopThread(game, ui);
		GameLoopThread t2 = new GameLoopThread(game, ui);

		t1.start();
		t2.start();
	}
}
