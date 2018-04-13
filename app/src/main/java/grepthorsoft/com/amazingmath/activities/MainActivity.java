package grepthorsoft.com.amazingmath.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import grepthorsoft.com.amazingmath.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout relative;

    TextView first_number_textview,second_number_textview,sum_textview,txt;
    int firstnumber,secondnumber,sum;
    ImageButton correct_image,wrong_image;
    Random rand,rnd;
    int score,best_score;
    String result;
    SharedPreferences sp;
    SharedPreferences.Editor sp_editor;
    int color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();
        relative=(RelativeLayout)findViewById(R.id.relative);

        txt=(TextView)findViewById(R.id.txt);
        first_number_textview=findViewById(R.id.first_number_textview);
        second_number_textview=findViewById(R.id.second_number_textview);
        sum_textview=findViewById(R.id.sum_textview);
        correct_image=findViewById(R.id.correct_image);
        wrong_image=findViewById(R.id.wrong_image);

         rnd = new Random();



        //  relative.setBackgroundColor(color);


        score=0;
        best_score=0;

        sp=getSharedPreferences("score_department",MODE_PRIVATE);
        sp_editor=sp.edit();

        sp_editor.putInt("bestscore",0);
        sp_editor.commit();


        rand=new Random();

        getNumbers();
        System.out.println("firstnumber:"+firstnumber);
        System.out.println("secondnumber:"+secondnumber);
        System.out.println("sum:"+sum);


        correct_image.setOnClickListener(this);
        wrong_image.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.correct_image: if(firstnumber+secondnumber==sum) {
                backGround();
                score++;
                txt.setText(String.valueOf(score));
                getNumbers();
            }
            else
            {
                gameOver();
            }
                break;
            //hello
            case R.id.wrong_image:if(firstnumber+secondnumber!=sum)
            {
                backGround();
                score++;
                txt.setText(String.valueOf(score));
                getNumbers();
            }
            else
            {
                gameOver();
            }
                break;
        }
    }

    public void getNumbers()
    {

        firstnumber = rand.nextInt(9) + 1;
        secondnumber = rand.nextInt(9) + 1;
        sum= rand.nextInt(18) + 1;

        first_number_textview.setText(String.valueOf(firstnumber));
        second_number_textview.setText(String.valueOf(secondnumber));
        sum_textview.setText(String.valueOf(sum));
    }


    public void gameOver()
    {

        best_score=sp.getInt("bestscore",0);

        if(score>best_score);
        {
            best_score=score;
        }
                StringBuilder sb=new StringBuilder();

        sb.append("Game Over\n"+"Score:"+score+"\n"+"Best Score:"+best_score);
        result=sb.toString();
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
/*

public void backGround()
{
    color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    relative.setBackgroundColor(color);
}
*/

    final Random mRandom = new Random(System.currentTimeMillis());

    public void backGround() {
        // This is the base color which will be mixed with the generated one
        final int baseColor = Color.WHITE;

        final int baseRed = Color.red(baseColor);
        final int baseGreen = Color.green(baseColor);
        final int baseBlue = Color.blue(baseColor);

        final int red = (baseRed + mRandom.nextInt(256)) / 2;
        final int green = (baseGreen + mRandom.nextInt(256)) / 2;
        final int blue = (baseBlue + mRandom.nextInt(256)) / 2;

        relative.setBackgroundColor(Color.rgb(red,green,blue));
       // return Color.rgb(red, green, blue);
    }



    @Override
    protected void onPause() {
super.onPause();
        sp_editor.putInt("bestscore",best_score);
    }
}

