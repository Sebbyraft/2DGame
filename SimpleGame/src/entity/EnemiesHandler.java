package entity;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import toolkit.Maths;
import toolkit.Vec2;

public class EnemiesHandler {
	
	private ArrayList<Enemy> enemies;
	private Player player;
	private int newEnemy = 0;
	private int level = 300;
	private Shield shield;
	
	public EnemiesHandler(Player player) {
		this.player = player;
		this.shield = player.getShield();
		this.enemies = new ArrayList<Enemy>();
	}
	
	public void render(Graphics2D g2d, ImageObserver observer) {
		for(Enemy enemy: enemies) {
			enemy.render(g2d, observer);
		}
	}
	
	public void update() {
		for(Enemy enemy: enemies) {
			enemy.update();
		}
		spawner();
		collider();
	}

	private void spawner() {
		if(newEnemy > level) {
			enemies.add(new Enemy(new Vec2(0, 0)));
			newEnemy = 0;
			return;
		} else {
			newEnemy ++;
		}
	}

	private void collider() {
		for(int i = 0; i < enemies.size(); i++) {
		
			// It detects any collision with the Shield
			float d = Maths.dist(enemies.get(i).getPosition(), shield.getUpdatedPosition());
			if(d < (shield.getSize().getX()/2+shield.getSize().getY()/2)/2) {
				enemies.remove(i);
				return;
			}
			// It detects any collision with the player
			Vec2 playerPos = new Vec2(player.getPosition().getX()*1.09f, player.getPosition().getY()*1.09f);
			d = Maths.dist(enemies.get(i).getPosition(), playerPos);
			if(d <= (player.getSize().getX()/2+player.getSize().getY()/2)) {
				enemies.remove(i);
				player.decreaseLife();
				System.out.println(player.getLife());
				return;
			}
		}	
	}
	
	public void cleanUp() {
		for(int i = 0; i < enemies.size(); i++) {
			enemies.remove(i);
		}
	}
}
