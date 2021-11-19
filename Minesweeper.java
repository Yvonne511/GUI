
public class Minesweeper {
	private int numOfSquare;
	private int numOfMine;
	private int numOfMineDiscovered;
	private Boolean[][] mineField;
	private Boolean[][] emptyField;
	private String[][] displayField;
	
	Minesweeper(){
		numOfSquare = 0;
		numOfMine = 0;
		mineField = new Boolean[0][0];
		displayField = new String[0][0];
		emptyField = new Boolean[0][0];
	}
	
	Minesweeper(float x, float y, int z){
		numOfSquare = z;
		numOfMine = 0;
		mineField = new Boolean[z][z];
		displayField = new String[z][z];
		emptyField = new Boolean[z][z];
		for (int i2 = 0; i2<numOfSquare; i2++) {
			for (int i3 = 0; i3<numOfSquare; i3++) {
				emptyField[i2][i3] = false;
			}
		}
	}
	
	public int getNumOfSquare() {
		return numOfSquare;
	}
	public void setNumOfSquare(int numOfSquare) {
		this.numOfSquare = numOfSquare;
	}

	public void buildMineField() {
		for (int i = 0; i < mineField.length; i++) {
	        for (int j = 0; j < mineField[i].length; j++) {
	        	mineField[i][j] = Math.random() < 0.2;
	        }
	    }
	}
	
	public void display() {
		for (int i = 0; i < mineField.length; i++) 
		{
			for (int j = 0; j < mineField[i].length; j++) 
				{
				if (mineField[i][j]) displayField[i][j] = String.valueOf('\u25A0');
				//"\uD83D\uDCA3";
				else displayField[i][j] = printHint(i, j);
				}
		}
	}
	
	public Boolean isMine(int i, int j) {
		return mineField[i][j];
	}
	
	public String displayMine(int i, int j) {
		return displayField[i][j];
	}
	
	public void print() {
		for (int i = 0; i < mineField.length; i++) 
		{
			for (int j = 0; j < mineField[i].length; j++) 
				{
				if (mineField[i][j]) System.out.print("\uD83D\uDCA3");
				else System.out.print(" "+printHint(i, j)+" ");
				}
			System.out.println("");
		}
	}
	
	public int[][] displayCoordinates(int i, int j){
		int[][] coordinates = {{i-1,j+1}, {i-1,j}, {i-1,j-1}, {i,j+1}, {i,j-1}, {i+1,j+1}, {i+1,j}, {i+1,j-1}, {i,j}};
		return coordinates;
	}
	
	public String printHint(int i, int j) {
		int surrondings = 0;
		for(int x = 0; x < 8; x++)
		{
			int a = displayCoordinates(i, j)[x][0];
			int b = displayCoordinates(i, j)[x][1];
			if (a >= 0 && a<numOfSquare && b>= 0 && b<numOfSquare){
				if(mineField[a][b])
					surrondings++;
			}
		}
		return Integer.toString(surrondings);
	}
	
	public int countMines() {
		numOfMine = 0;
		for (int i = 0; i < mineField.length; i++) 
		{
			for (int j = 0; j < mineField[i].length; j++) {if (mineField[i][j]) numOfMine++;}
		}
		return numOfMine;
	}
	
	public int countDiscoveredMine(int i, int j) {
		numOfMineDiscovered = 0;
		for (int i1 = 0; i1<9; i1++) {
			int a = displayCoordinates(i, j)[i1][0];
			int b = displayCoordinates(i, j)[i1][1];
			if (a >= 0 && a<numOfSquare && b>= 0 && b<numOfSquare){
				emptyField[a][b]= isMine(a,b);
			}
		}
		for (int i21 = 0; i21<numOfSquare; i21++) {
			for (int i31 = 0; i31<numOfSquare; i31++) {
				if (emptyField[i21][i31])
					numOfMineDiscovered++;
			}
		}
		return numOfMineDiscovered;
	}
		
	public static void main(String[] args) {
		Minesweeper a = new Minesweeper(1, 1, 10);
		a.buildMineField();
		a.print();
		System.out.println(a.countMines());
		System.out.println(a.isMine(2,3));
		System.out.println(a.countDiscoveredMine(2, 3));
		
			
	}
	
}
