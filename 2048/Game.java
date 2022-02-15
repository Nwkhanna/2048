import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Game extends JComponent implements KeyListener{
  private Board board = new Board();
  private int direction;   
  private Game(){
    addKeyListener(this);
    board = new Board();
    board.genTile();
    board.genTile();
  }
  public void paintComponent(Graphics graphics){
	  //BELOW IS THE GUI SETUP
    graphics.setFont(new Font("BOLD", 1, 40)); 
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        if(board.getBoard()[i][j] == 0){
          graphics.setColor(Color.WHITE);
        }
        else if(board.getBoard()[i][j] == 2){
          graphics.setColor(Color.LIGHT_GRAY);
        }
        else if(board.getBoard()[i][j] == 8){
          graphics.setColor(Color.GRAY);
        }
        else if(board.getBoard()[i][j] ==16){
					graphics.setColor(Color.DARK_GRAY);
				}
				else if(board.getBoard()[i][j]==32){
					graphics.setColor(Color.BLUE);
				}
				else if(board.getBoard()[i][j] ==64){
					graphics.setColor(Color.GREEN);
				}
				else if(board.getBoard()[i][j]==128){
					graphics.setColor(Color.RED);
				}
				else if(board.getBoard()[i][j] ==256){
					graphics.setColor(Color.YELLOW);
				}
				else if(board.getBoard()[i][j] == 512){
					graphics.setColor(Color.CYAN);
				}
				else{
					graphics.setFont(new Font("BOLD",1,30));
					graphics.setColor(Color.BLACK);
				}
        graphics.fillRoundRect(i*100, j*100, 90, 90,10,10);
        graphics.setColor(Color.MAGENTA);
        if(board.getBoard()[i][j] != 0){
          graphics.drawString(Integer.toString(board.getBoard()[i][j]), 15+100*i, 50+100*j);
        }
      }
    }
      graphics.setFont(new Font("BOLD", 1,20));
      graphics.setColor(Color.BLACK);
      graphics.drawString("MOVES: " + Integer.toString(board.getMoves()), 400, 50);
   
  }
  public static void main(String[] args) {
    JFrame frame = new JFrame("2048");
		Game g = new Game(); //initilize the Game
		frame.setSize(550, 500); //set the preferred size for the Game
		frame.add(g); //add the Game into the frame
		frame.addKeyListener(g); //add the key listener
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true); //displays game
  }
  @Override
  //BELOW IS THE KEY EVENT LISTENER
  public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT){ //if left pressed
			direction = 1;
			System.out.println("LEFT: ");
			System.out.println(board.getMax());
			System.out.println(board.getMoves());
		}
		if(e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT){//if right pressed
			direction = 2;
			System.out.println("RIGHT: ");
			System.out.println(board.getMoves());
		}
		if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP){//if up pressed
			direction = 3;
			System.out.println("UP: ");
			System.out.println(board.getMoves());

		}
		if(e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN){//if down pressed
			direction = 4;
			System.out.println("DOWN: ");
			System.out.println(board.getMoves());
		}
    if(e.getKeyCode()==KeyEvent.VK_Q){//if Q pressed, prompt the user if wanting to quit the game
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to quit this game?", "quit", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_R){//if R pressed, prompt the user if wanting to restart the game
			int reply = JOptionPane.showConfirmDialog(null, "Do you want to restart this game?", "restart", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				board.reset();
			}
		}
		board.move(direction);  
		board.merge(direction);  
		
		if(board.beenMoved){ //if successfully moved
			board.move(direction); // move again
			board.genTile(); //add in a new number
			board.setMoves(board.getMoves()+1); //moves +1
			repaint(); //repaint the frame
		}
		else if(board.beenMerged){ //if merger successful
			board.genTile(); 
			board.setMoves(board.numMoves+1); 
			repaint(); //repaint the frame
		}

		if(!board.checked()){ //if the board is not checked, that means game is over
			//prompt the user game is over, the final score, and if the user want to restart the game
			int reply = JOptionPane.showConfirmDialog(null, "Game Over! You made:" +board.getMoves()+ " moves. Largest number:"+board.getMax()+". Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				board.reset();
				repaint();
			}
			else{
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}