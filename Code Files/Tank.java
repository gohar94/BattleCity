import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

public class Tank extends BasicBlock{

	Image imageUp;
	Image imageDown;
	Image imageLeft;
	Image imageRight;
	int vel;
	// Missile missile;
	int orientation;
	int deleter;
	boolean shouldDel;
	
	Image del1;
	Image del2;
	Image del3;
	Image del4;
	Image del5;

	BasicBlock baa;
	// boolean isEnemy;

	Tank(Location l,Image im){
		super(l,im,BasicBlock.BlockType.TANK);
		BasicBlock.tanks.add(this);
		// BasicBlock.tanks.add(this.missile);
		helmet = false;
		helmetCount = 0;
		deleter = 0;
		shouldDel = false;
		del1 = new Image("1.png");
		del2 = new Image("2.png");
		del3 = new Image("3.png");
		del4 = new Image("4.png");
		del5 = new Image("5.png");
	}

	protected boolean tryUp() {
		
		Location newLoc = new Location(location.getRow(), location.getCol() - vel);
		image = imageUp;
		orientation = 1;
		if (movePossible(location, newLoc)) {
			location = newLoc;
			image = imageUp;
			orientation = 1;
			return true;
		}
		return false;
	}

	protected boolean tryDown() {
		
		Location newLoc = new Location(location.getRow(), location.getCol() + vel);
		image = imageDown;
		orientation = 2;
		if (movePossible(location, newLoc)) {
			location = newLoc;
			image = imageDown;
			orientation = 2;
			return true;
		}		
		return false;
	}

	protected boolean tryLeft() {
		
		Location newLoc = new Location(location.getRow() - vel, location.getCol());
		image = imageLeft;
		orientation = 3;
		if (movePossible(location, newLoc)) {
			location = newLoc;
			image = imageLeft;
			orientation = 3;
			return true;
		}
		return false;
	}

	protected boolean tryRight() {
		
		Location newLoc = new Location(location.getRow() + vel, location.getCol());
		image = imageRight;
		orientation = 4;
		if (movePossible(location, newLoc)) {
			location = newLoc;
			image = imageRight;
			orientation = 4;
			return true;
		}
		return false;
	}

	public void impact(BasicBlock b){
		if (helmet) {
			return;
		}

		if (b == this.missile) {
			System.out.println("Might kill myself");
			return;
		}

		if (this.isEnemy == false && b.isEnemy == false) {

		shouldDel = true;
		baa = b;

		if (deleter < 5) {
			return;
		}

		}

		if (isEnemy) {
			if (b.type == BasicBlock.BlockType.MISSILE) {
				Missile x = (Missile)b;
				if (x.caller.isEnemy) {
					System.out.println("Friendly fire");
					return;
				}
			}
		}

		int count = 0;
		int count2 = 0;
		int toDelete = 0;
		int toDelete2 = 0;
		int toDelete3 = 0;

		// ---

		for (Rectangle x : BasicBlock.mapRectangles) {
			if (x == this.rect) {
				toDelete = count;
				x.setVisible(false);
				break;
			}
			count++;
		}
		for (BasicBlock x : BasicBlock.tanks) {
			if (x == this) {
				if (this.isPlayer) {
					System.out.println("Killing player");
					BasicBlock.end = true;
				}
				toDelete2 = count2;
				x.rect.setVisible(false);
				x.rect.setX(-100);
				x.rect.setY(-100);
			}
			if (x == this.missile) {
				toDelete3 = count2;
				x.rect.setVisible(false);
				x.rect.setX(-100);
				x.rect.setY(-100);
			}
			count2++;
		}
		BasicBlock.mapRectangles.remove(toDelete);
		BasicBlock.tanks.remove(toDelete2);
		BasicBlock.tanks.remove(toDelete3);
	}
}