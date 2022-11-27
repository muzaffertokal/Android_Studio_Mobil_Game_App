package com.kotele.catchthekukuli;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    int score;
    ImageView ImageView;
    ImageView ImageView2;
    ImageView ImageView3;
    ImageView ImageView4;
    ImageView ImageView5;
    ImageView ImageView6;
    ImageView ImageView7;
    ImageView ImageView8;
    ImageView ImageView9;

    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize (başlatma)
        timeText =(TextView) findViewById(R.id.timeText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        ImageView = findViewById(R.id.imageView);
        ImageView2 = findViewById(R.id.imageView2);
        ImageView3 = findViewById(R.id.imageView3);
        ImageView4 = findViewById(R.id.imageView4);
        ImageView5 = findViewById(R.id.imageView5);
        ImageView6 = findViewById(R.id.imageView6);
        ImageView7 = findViewById(R.id.imageView7);
        ImageView8 = findViewById(R.id.imageView8);
        ImageView9 = findViewById(R.id.imageView9);


        imageArray = new ImageView[] {ImageView, ImageView2, ImageView3, ImageView3, ImageView4, ImageView5, ImageView6, ImageView7, ImageView8, ImageView9};


        hideImages();


        score = 0;


       new CountDownTimer(30000,1000){ //sayaç kullanımı

           @Override
           public void onTick(long l) {
               timeText.setText("Time: " + l/1000); // sayacın ne kadar sürede (periyotta) bir artacağı veya azalacağı işlemini belirleme.


           }

           @Override
           public void onFinish() { // sayaç süresi bittiğinde olacak işlemler.

               timeText.setText("Time Off");
               handler.removeCallbacks(runnable);
               for (ImageView image: imageArray) { // sayaç süresi bittiğinde görünümler gözükmez hale geliyor.
                   image.setVisibility(View.INVISIBLE); //INVISIBLE: GÖRÜNMEZ
               }
               AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

               alert.setTitle("Restart?");
                       alert.setMessage("Are you sure to restart game?");
               alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       // restart işlemi

                       Intent intent = getIntent();
                       finish();
                       startActivity(intent);
                   }
               });
               alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                   }
               });
               alert.show();

           }
       }.start();

    }
    public void  increaseScore (View view){ //  İmageView'e atadığımız onClick metodu increaseScore


        score++;
       // "score = score + 1;" aynı şey "score++;" "skoru birer, birer artır"


        scoreText.setText("Score:" + score);



    }
    public void hideImages(){ // Görünümü gizleme



            handler = new Handler();


            runnable = new Runnable() {
                @Override
                public void run() {
                    for (ImageView image: imageArray) {
                        image.setVisibility(View.INVISIBLE); //INVISIBLE: GÖRÜNMEZ //loop ile array'de bulunan görünümler gizleniyor.
                    }

                    Random random = new Random(); //rastgele metodu ile array içindeki görünümler gözle görülür hale geliyor.
                    int i = random.nextInt(9);
                    imageArray[i].setVisibility(View.VISIBLE); //VISIBLE: GÖZLE GÖRÜLÜR

                    handler.postDelayed(this,500);
                }
            };
                handler.post(runnable);
        }

    }

