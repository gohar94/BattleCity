public class Location {
	private int row, col;

	public Location(int r, int c){
		row = r;
		col = c;
	}

	public boolean equals(Location loc){
		return (row == loc.getRow() && col == loc.getCol());
	}

	public void setLocation(int r,int c){
		row = r;
		col = c;
	}

	public void setLocation(Location l){
		row = l.getRow();
		col = l.getCol();
	}

	public int findDistance(int r,int c){
		// implement here
		int dist = 0;
		return dist;
	}

	public int getRow(){
		return row;
	}

	public int getCol(){
		return col;
	}



}
