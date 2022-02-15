import java.util.Random;
public class Board{
    int score;
    int count;
    int numMoves = 0;
    int maxVal = 4;
    int[] numPool = {2,2,2,2,4};
    Random rand = new Random();
    private int[][] board = new int[4][4];
    boolean found = true;
    boolean beenMoved = false;
    boolean beenMerged = false;
    public int getMoves(){ //getter for the variable "numMoves"
		return numMoves;
	  }
    public void setMoves(int numMoves){ //getter for the variable "numMoves"
		this.numMoves = numMoves;
	}
  public int getMax (){ //getter for the variable "maxVal"
    return maxVal;
  }

    public Board(){
        count = 2;
        numMoves = 0;
    }
    public void setBoard(int[][] newBoard){
        for(int i = 0; i <4; i++){
          for(int j = 0; j < 4; j++){
            newBoard[i][j] = board[i][j];
          }
        }
    }
    public int[][] getBoard(){
      return board;
    }
    public void move(int direction){
      beenMoved = false;
      switch(direction){
        case 1: //Left  
        for(int i = 0; i < 4; i++){
          int localCount = 0; //initialize a local variable "counter"
          for(int j=0;j<4;j++){
            if(board[i][j]==0){ //if the space is 0, counter adds up 1
              localCount++;
            }
            else if(board[i][j]!=0 && j==0){
              //if the current element is not 0 but the position is 0, continue since it is at the first place
              continue;
            }
            else if(j==0 && board[i][j]!=0 && board[i][j+1]==board[i][j]){
              //if at position 0, and position 1 is the same element as position 0, continue
              continue;
            }
  
            else if(localCount>0){
              //if more than one space found, counter will be greater than 0
              //move everything counter position before its current position
              board[i][j-localCount]=board[i][j];
              board[i][j]=0;
              beenMoved=true; //set n to be true to indicate that the board has been "moved"
            }
          }
        }
        break;
        case 2: //Right
        for(int i = 0; i<4; i++){
          int localCount = 0; //see above localCount
          for(int j=0;j<4;j++){
            if(board[3-i][3-j]==0){
              localCount++;
            }
            else if(board[3-i][3-j]!=0 && (j)==0){
              continue;
            }
            else if(j==0 && board[3-i][3-j]!=0 && board[3-i][2-j]==board[3-i][3-j]){
              continue;
            }
  
            else if(localCount>0){
              board[3-i][3-j+localCount]=board[3-i][3-j];
              board[3-i][3-j]=0;
              beenMoved=true;
            }
          }
        }
        break;
        case 3: //up
          for(int i = 0; i < 4; i++){
            int localCount = 0;
            for(int j=0;j<4;j++){
              if(board[j][i]==0){
                localCount++;
              }
              else if(board[j][i]!=0 && j==0){
                continue;
              }
              else if(j==0 && board[j][i]!=0 && board[j+1][i]==board[j][i]){
                continue;
              }
    
              else if(localCount>0){
                board[j-localCount][i]=board[j][i];
                board[j][i]=0;
                beenMoved=true;
              }
            }
          }
          break;
        case 4: //down
          for(int i = 0; i < 4; i++){
            int localCount = 0;
            for(int j=0;j<4;j++){
              if(board[3-j][3-i]==0){
                localCount++;
              }
              else if(board[3-j][3-i]!=0 && j==0){
                continue;
              }
              else if(j==0 && board[2-j][3-i]!=0 && board[2-j][3-i]==board[3-j][3-i]){
                continue;
              }
              else if(j==1 && board[3-j][3-i]!=0 && board[3-j][3-i]==board[4-j][3-i]){
                continue;
              }
              else if(localCount>0){
                board[3-j+localCount][3-i]=board[3-j][3-i];
                board[3-j][3-i]=0;
                beenMoved=true;
              }
            }
          }
          break;
      }
    }
  public void merge(int direction){
      beenMerged = false;
      switch(direction){
        case 1: //left merge
        for(int i = 0; i < 4; i++){
          for(int j = 1; j < 4; j++){
            if(board[i][j]==board[i][j-1] && board[i][j]!=0){      
              board[i][j-1] += board[i][j];
              if(board[i][j-1] > maxVal){
                maxVal = board[i][j-1];
              }
              board[i][j] = 0;
              beenMerged = true;
              count--;
              System.out.println("LEFT");
            }
          }
        }
        break;
        case 2: //right merge
        for(int i = 0; i < 4; i++){
          for(int j = 1; j < 4; j++){
            if(board[3-i][3-j]==board[3-i][3-j+1] && board[3-i][3-j]!=0){
              board[3-i][3-j+1] += board[3 - i][3 - j];
              if( board[3-i][3-j+1] > maxVal){
                maxVal =  board[3-i][3-j+1];
              }
              board[3 - i][3 - j] = 0;
              beenMerged = true;
              count--;
              System.out.println("RIGHT");
            } 
          }
        }
        break;
        case 3: //up merge
        for(int i = 0; i < 4; i++){
          for(int j = 1; j < 4; j++){
            if(board[j][i]==board[j-1][i] && board[j][i]!=0){
              board[j- 1][i] += board[j][i];
              if(board[j- 1
              ][i] > maxVal){
                maxVal = board[j- 1][i];
              }
              board[j][i] = 0;
              beenMerged = true;
              count--;
              System.out.println("UP");
            }
          }
        }
        break;
        case 4: //down merge
        for(int i = 0; i < 4; i++){
          for(int j = 1; j < 4; j++){
            if(board[3-j][3-i]==board[4-j][3-i] && board[3-j][3-i]!=0){
              board[3-j][3-i] += board[4-j][3-i];
              if(board[3-j][3-i] > maxVal){
                maxVal = board[3-j][3-i];
              }
              board[4-j][3-i] = 0;
              beenMerged = true;
              count--;
              System.out.println("DOWN");
            }
          }
        }
        break;
      }
  }
  //Used to initialize board and also to get future tiles
    public void genTile(){
      while(found){
          int x = rand.nextInt(4);
          int y = rand.nextInt(4);
          if(board[x][y] == 0){
            board[x][y] = numPool[rand.nextInt(5)];
            found = false;
            count++;
          }
      }
      found = true;
    }
    //To determine if the board is finished and no more moves can be made
    public boolean checked(){
      if(count == 16){
        Board fullBoard = new Board();
        fullBoard.setBoard(board);
        for(int i =0; i < 4; i++){
          fullBoard.move(i);
          fullBoard.merge(i);
        }
        if(fullBoard.beenMoved == false && fullBoard.beenMerged == false){
          return false;
        }
        else{
          return true;
        }
      }
      else{
        return true;
      }
    }
    //Resets the board
    public void reset(){
        board = new int[4][4];
        for(int i = 0; i < 4; i++){
          for(int j = 0; j < 4; j++){
            board[i][j] = 0;
          }
        }
        genTile();
        genTile();
        count = 2;
        numMoves = 0;
    }

}