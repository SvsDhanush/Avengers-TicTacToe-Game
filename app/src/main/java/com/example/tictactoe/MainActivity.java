package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.SystemClock.sleep;

public class MainActivity<string> extends AppCompatActivity {

    //to track the boxes (if value is 2 it is taken as empty box)
    int[] position = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //used to decide who's turn to play
    int active;
    //helps to stop the after a player is the winner
    boolean activeGame = true;
    //to display the winner text
    String winner = "";


    public void click(View view) {


        ImageView iv = (ImageView) view;
        TextView turn = (TextView) findViewById(R.id.turn);

        int tagPosition = Integer.parseInt(iv.getTag().toString());

      //checks valid move or not and whether game is active or not
      if(position[tagPosition] == 2 && activeGame) {

          //animation
          iv.animate().alpha(1).setDuration(300);

          // 0 : captain america, 1 : ironman , 2 : empty
          if (active == 0) {
              iv.setImageResource(R.drawable.captainamerica);
              position[tagPosition] = active;
          } else {
              iv.setImageResource(R.drawable.ironman);
              position[tagPosition] = active;
          }

          //flips the active value (change the current player)
          if(active ==0){
              active =1;
              turn.setText("Ironman's turn.");
          }else{
              active =0;
              turn.setText("Captain america's turn.");
          }

          //----------------------------------------------------------------------------------------
          //checking whether game is over or not
          //winning positions {0, 1, 2}, {3, 4 ,5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}
          //win condition for Captain america
          if((position[0]==0 && position[1]==0 && position[2]==0) || (position[3]==0 && position[4]==0 && position[5]==0) || (position[6]==0 && position[7]==0 && position[8]==0) ||
                  (position[0]==0 && position[3]==0 && position[6]==0) || (position[1]==0 && position[4]==0 && position[7]==0) || (position[2]==0 && position[5]==0 && position[8]==0) ||
                  (position[0]==0 && position[4]==0 && position[8]==0) || (position[2]==0 && position[4]==0 && position[6]==0)){

              turn.setVisibility(View.INVISIBLE);
              activeGame = false;
              winner = "Captain america is the winner!!";
              gameFinish(winner);
              Toast.makeText(this, "I can do this all day...", Toast.LENGTH_SHORT).show();

          }
          //win condition for Ironman
          else if((position[0]==1 && position[1]==1 && position[2]==1) || (position[3]==1 && position[4]==1 && position[5]==1) || (position[6]==1 && position[7]==1 && position[8]==1) ||
                  (position[0]==1 && position[3]==1 && position[6]==1) || (position[1]==1 && position[4]==1 && position[7]==1) || (position[2]==1 && position[5]==1 && position[8]==1) ||
                  (position[0]==1 && position[4]==1 && position[8]==1) || (position[2]==1 && position[4]==1 && position[6]==1)){

              turn.setVisibility(View.INVISIBLE);
              activeGame = false;
              winner = "Ironman is the winner!!";
              gameFinish(winner);
              Toast.makeText(this, "I am ironman...", Toast.LENGTH_SHORT).show();

          }
          //condition for draw match
          else if(isTied() ){

              turn.setVisibility(View.INVISIBLE);
              winner = "Game is Draw, let's try one more time!!";
              gameFinish(winner);

          }

      }

    }

   //----------------------------------------------------------------------------------------------
   //logic for playAgain
   public void playAgain (View view) {
       TextView tvResult = (TextView) findViewById(R.id.tvResult);

       Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);

       TextView name = (TextView) findViewById(R.id.name);

       TextView turn = (TextView) findViewById(R.id.turn);

       tvResult.setVisibility(View.INVISIBLE);
       btnPlayAgain.setVisibility(View.INVISIBLE);
       name.setVisibility(View.INVISIBLE);
       turn.setVisibility(View.VISIBLE);

       turn.setText("Let's begin the battle again!!\nSelect a grid to start the game.\nIt's Captain america's turn.");

       GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

       for (int i = 0; i < gridLayout.getChildCount(); i++) {

           ImageView imageView = (ImageView) gridLayout.getChildAt(i);
           imageView.animate().alpha(0).setDuration(300);
           imageView.setImageDrawable(null);
       }

       for (int i = 0; i < position.length; i++) {
           position[i] = 2;
       }
       active = 0;
       activeGame = true;


   }

   //----------------------------------------------------------------------------------------------
   //to print the winner and for displaying the text and playAgain btn
   public void gameFinish(String winner){

       TextView tvResult = (TextView) findViewById(R.id.tvResult);

       Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);

       TextView name = (TextView) findViewById(R.id.name);

       tvResult.setVisibility(View.VISIBLE);
       btnPlayAgain.setVisibility(View.VISIBLE);
       name.setVisibility(View.VISIBLE);

       tvResult.setText(winner);
   }

  //------------------------------------------------------------------------------------------------
  //checks every every value in position if there is 2 it indicates there are empty boxes
   public boolean isTied(){
        
        for(int i=0; i<position.length; i++){
            if(position[i]==2){
                return false;
            }
        }
        return true;
   }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}