import javafx.scene.image.*;

public class SteelBlock extends BasicBlock {
	public SteelBlock(Location l, Image i){
		super(l,i,BasicBlock.BlockType.STEEL);
	}

	public void impact() {
		System.out.println("Hit");
	}
}