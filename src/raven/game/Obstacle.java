/**
 * @author Mitchell Barnett
 * 
 **/

package raven.game;

import java.util.ArrayList;
import java.util.List;
import raven.game.messaging.Telegram;
import raven.math.Vector2D;
import raven.game.BaseGameEntity;
import raven.ui.GameCanvas;

	public class Obstacle extends BaseGameEntity{
		//double r = 1.0;
		List<Vector2D> points;
		public double x;
		public double y;
		
		protected Obstacle (List<Vector2D> p){
			//r = rad;
			points = p;
		}
		
		public void render(){
			GameCanvas.bluePen();
			//GameCanvas.circle(pos(), r);
			GameCanvas.closedShape(points);
		}
		
		public void normalize(){
			double vector_length = length();
			
			if (vector_length > Double.MIN_VALUE) {
				x /= vector_length;
				y /= vector_length;
			}
		}
			
		public double length() {
			return Math.sqrt(x * x + y * y);
		}
		//public Vector2D getIndex(int index){
			
		//}
	}
	
	