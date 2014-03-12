import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.scene.paint.ImagePattern;

public class PowerUp extends BasicBlock {
	
	Location location;
	int powerType;

	public PowerUp(Location l, Image im, int _powerType) {
		super(l,im,BasicBlock.BlockType.POWERUP);
		image = im;
		int type = 7;
		location = l;
		// image = im;
		powerType = _powerType;
        // rect = new Rectangle(location.getRow(), location.getCol(), 30, 30);
        rect.setFill(new ImagePattern(im));
	}

	public void impact (BasicBlock b) {
		if (!b.isPlayer) {
			return;
		}
		if (powerType == 1) { // HELMET
			if (b.type == BasicBlock.BlockType.TANK) {
				if (b.isPlayer) {
					b.helmet = true;
					System.out.println("Wearing helmet");
					this.location = new Location(-50, -50);
					this.rect.setX(-50);
					this.rect.setY(-50);
					this.rect.setVisible(false);
				}
			}
		} else if (powerType == 2) { // GRENADE
			if (b.type == BasicBlock.BlockType.TANK) {
				if (!b.isPlayer) {
					return;
				}
				System.out.println("Grenade!");
				int counter = 0;
				int toDelete = -1;
				for (BasicBlock x : BasicBlock.tanks) {
					if (x.isEnemy) {
						for (int i = 0; i < 6; i++) {
							x.doAction();
						}
						toDelete = counter;
						x.rect.setVisible(false);
						x.rect.setX(-100);
						x.rect.setY(-100);
						this.location = new Location(-50, -50);
						this.rect.setX(-50);
						this.rect.setY(-50);
						this.rect.setVisible(false);
					}
					System.out.println(counter);
					counter++;
				}
				if (toDelete != -1) {
					BasicBlock.tanks.remove(toDelete);
				}
				toDelete = -1;
				counter = 0;
				for (BasicBlock x : BasicBlock.tanks) {
					if (x.isEnemy) {
						BasicBlock.tanks.remove(counter);
						for (int i = 0; i < 6; i++) {
							x.doAction();
						}
						x.rect.setVisible(false);
						x.rect.setX(-100);
						x.rect.setY(-100);
						this.location = new Location(-50, -50);
						this.rect.setX(-50);
						this.rect.setY(-50);
						this.rect.setVisible(false);
					}
					System.out.println(counter);
					counter++;
				}
				if (toDelete != -1) {
					BasicBlock.tanks.remove(toDelete);
				}
				toDelete = -1;
				counter = 0;
				for (BasicBlock x : BasicBlock.tanks) {
					if (x.isEnemy) {
						for (int i = 0; i < 6; i++) {
							x.doAction();
						}
						BasicBlock.tanks.remove(counter);
						x.rect.setVisible(false);
						x.rect.setX(-100);
						x.rect.setY(-100);
						this.location = new Location(-50, -50);
						this.rect.setX(-50);
						this.rect.setY(-50);
						this.rect.setVisible(false);
					}
					System.out.println(counter);
					counter++;
				}
				if (toDelete != -1) {
					BasicBlock.tanks.remove(toDelete);
				}
				toDelete = -1;
				counter = 0;	
			}
		} else {
			for (BasicBlock x : BasicBlock.tanks) {
				if (x.isEnemy) {
					x.freeze();
				}
			}
			this.location = new Location(-50, -50);
			this.rect.setX(-50);
			this.rect.setY(-50);
			this.rect.setVisible(false);
		}
	}

	public void setPower(int _t) {
		powerType = _t;
		System.out.println(powerType);
	}

}