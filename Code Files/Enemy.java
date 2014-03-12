import javafx.scene.image.*;
import java.lang.Math;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;

public class Enemy extends Tank {
 
    double lastMove = 0;
    double fireNum = 0;
 
    public Enemy(Location l, Image im){
        super(l,im);
        imageUp = new Image("Enemy1_Up.png");
        imageDown = new Image("Enemy1_Down.png");
        imageRight = new Image("Enemy1_Right.png");
        imageLeft = new Image("Enemy1_Left.png");

        vel = 3;
        orientation = 3;
        missile = new Missile(new Location(420, 370), im);
        missile.caller = this;
        BasicBlock.tanks.add(this.missile);
        missile.location = this.location;
        missile.orientation = orientation;
        // missile.isPressed = true;
        isEnemy = true;
        isPlayer = false;
    }
 
    public void fire () {
        if (this.location.getRow() <= 0 || this.location.getRow() >= 530 || this.location.getCol() <= 0 || this.location.getCol() >= 510) {
            return;
        }
        missile.location = this.location;
        missile.isPressed = true;
        missile.orientation = orientation;
        return;
    }
 
    public void doAction(){
        if (shouldDel) {
            if (deleter == 1) {
                image = del1;
            } else if (deleter == 2) {
                image = del2;
            } else if (deleter == 3) {
                image = del3;
            } else if (deleter == 4) {
                image = del4;
            } else if (deleter == 5) {
                image = del5;
            } else {
                impact(baa);
            }
            rect.setFill(new ImagePattern(image));
            deleter++;
            return;
        }
        if (frozen) {
            frozenTimer++;
            if (frozenTimer > 300) {
                frozenTimer = 0;
                frozen = false;
            }
            return;
        }
 
        fireNum = Math.random()*20;
 
        if (lastMove >= 0 && lastMove < 5) {
            if (!tryUp()) {
                lastMove = Math.random()*20;
            }
        } else if (lastMove >= 5 && lastMove < 10) {
            if (!tryDown()) {
                lastMove = Math.random()*20;
            }
        } else if (lastMove >= 10 && lastMove < 15) {
            if (!tryLeft()) {
                lastMove = Math.random()*20;
            }
        } else {
            if (!tryRight()) {
                lastMove = Math.random()*20;
            }
        }
 
        if (fireNum <= 0.5 && missile.isPressed == false) {
            fire();
        }
 
        rect.setX(location.getRow());
        rect.setY(location.getCol());
        rect.setFill(new ImagePattern(image));
 
    }
}