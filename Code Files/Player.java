import javafx.scene.image.*;
import javafx.event.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
 
public class Player extends Tank implements EventHandler{
 
    Image imageHelmetUp;
    Image imageHelmetDown;
    Image imageHelmetRight;
    Image imageHelmetLeft;
    public Player(Location l,Image im){
        super(l,im);
        imageUp = new Image("Player1_Up.png");
        imageDown = new Image("Player1_Down.png");
        imageRight = new Image("Player1_Right.png");
        imageLeft = new Image("Player1_Left.png");
        imageHelmetUp = new Image("PlayerHelmetUp.png");
        imageHelmetDown = new Image("PlayerHelmetDown.png");
        imageHelmetRight = new Image("PlayerHelmetRight.png");
        imageHelmetLeft = new Image("PlayerHelmetLeft.png");
        vel = 7;
        missile = new Missile(new Location(420, 400), im);
        missile.caller = this;
        orientation = 3;
        BasicBlock.tanks.add(this.missile);
        isEnemy = false;
        isPlayer = true;
    }
     
    /*
     * You need to register the appropriate event handling at the root node level.
     * eg, if Group is your root node, then addEventHandler(KeyEvent.KEY_TYPED, player)
     */
    // void handle(Event e){
    //  //logic for player. Remember that the player is not allowed to make more
    //  //than one move per 'tick' or frame.
         
    // }
 
    public void doAction() {
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
        if (helmet) {
            helmetCount++;
            // this.rect.setFill(imageHelmet);
            if (orientation == 1) {
                image = imageHelmetUp;
            } else if (orientation == 2) {
                image = imageHelmetDown;
            } else if (orientation == 3) {
                image = imageHelmetLeft;
            } else {
                image = imageHelmetRight;
            }
            if (helmetCount > 400) {
                helmetCount = 0;
                helmet = false;
            }
        }
        rect.setX(location.getRow());
        rect.setY(location.getCol());
        rect.setFill(new ImagePattern(image));
    }
    // use makeMove? (if necessary)
    public void handle(Event _event) {
        if (deleter > 0) {
            return;
        }
        KeyEvent event = (KeyEvent)_event;
        if (event.getCode() == KeyCode.UP) {
            tryUp();
        } else if (event.getCode() == KeyCode.DOWN) {
            tryDown();
        } else if (event.getCode() == KeyCode.LEFT) {
            tryLeft();
        } else if (event.getCode() == KeyCode.RIGHT) {
            tryRight();
        } else if (event.getCode() == KeyCode.SPACE) {
            if (this.location.getRow() <= 0 || this.location.getRow() >= 530 || this.location.getCol() <= 0 || this.location.getCol() >= 510) {
                return;
            }
            missile.location = this.location;
            missile.isPressed = true;
            missile.orientation = orientation;
            // System.out.println("Space");
        } else if (event.getCode() == KeyCode.SHIFT) {
            // System.out.println("Shift");
        } else {
            // wrong key
        }
        _event.consume();
    }
 
}