import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
 
public class BrickBlock extends BasicBlock {
    public BrickBlock(Location l, Image im){
        // super(l,im,BasicBlock.BlockType.BRICK);
        super(l,im,BasicBlock.BlockType.BRICK);
        isEnemy = false;
    }
 
    public void impact(BasicBlock b) {
        int count = 0;
        int toDelete = 0;
        for (Rectangle x : BasicBlock.mapRectangles) {
            if (x == this.rect) {
                toDelete = count;
                x.setVisible(false);
                int rectX = (int)x.getX()/40;
                int rectY = (int)x.getY()/40;
                map[rectX][rectY] = null;
                // break;
            }
            count++;
        }
        BasicBlock.mapRectangles.remove(toDelete);
    }
}