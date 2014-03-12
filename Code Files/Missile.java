import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Missile extends BasicBlock {

	int vel;
	boolean isPressed;
	int orientation;
	BasicBlock caller;
	boolean animate;
	int animationTimer;
	ImagePattern animationSprite1;

	public Missile(Location l, Image im){
		super(l,im,BasicBlock.BlockType.MISSILE);
		vel = 13;
		isPressed = false;
		orientation = 0;
		caller = null;
		animate = false;
		animationTimer = 0;
		animationSprite1 = new ImagePattern(new Image("Eagle.png"));
	}

	protected boolean tryUp() {
		
		Location newLoc = new Location(location.getRow(), location.getCol() - vel);
		if (movePossible(location, newLoc)) {
			location = newLoc;
			return true;
		}
		return false;
	}

	protected boolean tryDown() {
		
		Location newLoc = new Location(location.getRow(), location.getCol() + vel);
		if (movePossible(location, newLoc)) {
			location = newLoc;
			return true;
		}		
		return false;
	}

	protected boolean tryLeft() {
		
		Location newLoc = new Location(location.getRow() - vel, location.getCol());
		if (movePossible(location, newLoc)) {
			location = newLoc;
			return true;
		}
		return false;
	}

	protected boolean tryRight() {
		
		Location newLoc = new Location(location.getRow() + vel, location.getCol());
		if (movePossible(location, newLoc)) {
			location = newLoc;
			return true;
		}
		return false;
	}

	protected boolean movePossible(Location oldL, Location newL) {
		// System.out.println("In");
        //evaluate move
        Rectangle newLocRect = new Rectangle(newL.getRow(), newL.getCol(), 5, 5);

        // for (Rectangle x : mapRectangles) {
        for (int i = 0; i < 13; i++) {
        	for (int j = 0; j < 13; j++) {
        		BasicBlock x = map[i][j];
        		if (x == null) {
        			continue;
        		}
				if (x.rect != this.rect && x.type != BasicBlock.BlockType.TANK) {
					if (newLocRect.getBoundsInParent().intersects(x.rect.getBoundsInParent())) {
						isPressed = false;
						this.location.setLocation(-100, -100);
						x.impact(this);
						return false;
					}
				}
			}
		}

		for (BasicBlock x : BasicBlock.tanks) {
			if (x.rect != this.rect && x.rect != caller.rect) {
				if (newLocRect.getBoundsInParent().intersects(x.rect.getBoundsInParent())) {
					isPressed = false;
					this.location.setLocation(-100, -100);
					x.impact(this);
					System.out.println("Killing enemy");
					animate = true;
					return false;
				}
			}
		}

		if (newLocRect.getX() < 0 || newLocRect.getX() > 530 || newLocRect.getY() < 0 || newLocRect.getY() > 510) {
			this.location.setLocation(-100, -100);
			isPressed = false;
		}

		if (this.location.getRow() < 0 || this.location.getRow() > 530 || this.location.getCol() < 0 || this.location.getCol() > 510) {
			this.location.setLocation(-100, -100);
			isPressed = false;
		}
        return true;
    }

	public void doAction(){

		// if (animate) {
		// 	animationTimer++;
		// 	// System.out.println(animationTimer);
		// 	if (animationTimer > 100) {
		// 		animationTimer = 0;
		// 		animate = false;
		// 	}
		// 	return;
		// }
		
		if (!isPressed) {
			rect.setVisible(false);
			return;
		} else {
			rect.setVisible(true);
		}
		if (orientation == 1) { // up
			tryUp();
		} else if (orientation == 2) { // down
			tryDown();
		} else if (orientation == 3) { // left
			tryLeft();
		} else if (orientation == 4) { // right
			tryRight();
		} else {
			System.out.println("invalid orientation");
			return;	
		}
		// System.out.println("missile");
		
		rect.setX(location.getRow());
		rect.setY(location.getCol());
	}
}