import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Project2 extends PApplet {
	private static final int cellSize = 40;
	private Minesweeper mine;
	private int status; //status 1: loss ; 2: win; 3: still playing
	int i;
	int j;
	int countMines;
	
	public static void main(String[] args) {
		PApplet.main("Project2");
	}
	
	public Project2() {
		status = 0;
		mine = new  Minesweeper(1, 1, 10);
		mine.buildMineField();
		mine.display();
	}
	
	public void settings() {
		size(cellSize*mine.getNumOfSquare(), cellSize*mine.getNumOfSquare()+cellSize);
	}

	public void draw(){
		for (int h = 0; h <mine.getNumOfSquare(); h++) {
			for (int g = 0; g <mine.getNumOfSquare(); g++) {
				noFill();
				stroke(0);
				rect(h*cellSize, g*cellSize, cellSize, cellSize);
			}
		}
		/*for (int i = 0; i <a.getNumOfSquare(); i++) {
			for (int j = 0; j <a.getNumOfSquare(); j++) {
				String content = a.displayMine(i, j);			
				//a.isMine(i,j):
				PFont font = createFont("NewPeninimMT", cellSize);
				textFont(font);
				text(content,(i)*cellSize+cellSize/4,(j+1)*cellSize-cellSize/4);
			}
		}*/
		
		if (mine.countMines() == countMines) {
			status = 2;
		}
		
		if (mousePressed == true) {
			i = mouseX/cellSize;
			j = mouseY/cellSize;
			if (mine.isMine(i, j)) {
				status = 1;
				//fill(155);
				//rect(0,cellSize*a.getNumOfSquare()/4,cellSize*a.getNumOfSquare(),cellSize*a.getNumOfSquare()/2);
				//clear();
			}
			else {
				status = 3;
				
			}
		}
		
		if (status == 3) {
			for(int z = 0; z < 9; z++)
			{
				int c = mine.displayCoordinates(i, j)[z][0];
				int d = mine.displayCoordinates(i, j)[z][1];
				if (c >= 0 && c<mine.getNumOfSquare() && d>= 0 && d<mine.getNumOfSquare()){
					String content = mine.displayMine(c, d);			
					//a.isMine(i,j):
					PFont font = createFont("Arial-Black", cellSize-10);
					PFont fontmine = createFont("Wingdings-Regular", cellSize-10);
					if (mine.isMine(c, d))
						{
						textFont(fontmine);
						text("M",(c)*cellSize+cellSize/4,(d+1)*cellSize-cellSize/4);
						countMines = mine.countDiscoveredMine(i, j);
						}
					else
						{
						textFont(font);
						text(content,(c)*cellSize+cellSize/4,(d+1)*cellSize-cellSize/4);
						countMines = mine.countDiscoveredMine(i, j);
						}
				}
			}
			//mine.countDiscoveredMine(i, j)
			fill(200);
			rect(0, cellSize*mine.getNumOfSquare(),width,cellSize);
			fill(255);
			int leftmine = mine.countMines()-countMines;
			text("Num of Mine Left = "+leftmine,cellSize/5, cellSize*mine.getNumOfSquare()+3*cellSize/4);
		}
		
		if (status == 2) {
			clear();
			PImage img = loadImage("win.jpg");
			image(img,0,cellSize*mine.getNumOfSquare()/4,cellSize*mine.getNumOfSquare(),cellSize*mine.getNumOfSquare()/2);
		}
		
		if (status == 1) {
			clear();
			PImage img = loadImage("explode.jpg");
			image(img,0,cellSize*mine.getNumOfSquare()/4,cellSize*mine.getNumOfSquare(),cellSize*mine.getNumOfSquare()/2);
		
		}
	}
}
