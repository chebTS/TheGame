package ua.ck.android.thegame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private String buffer = "";
    private TextView txtDebug;
    private ProgressBar progressBar;
    private int progress = 0;
    private Button btnEro, btnTrombo, btnLeiko;
    private ImageView imgBadGuy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDebug = (TextView)findViewById(R.id.txt_debug);
        imgBadGuy = (ImageView)findViewById(R.id.img_bad_guy_1);
        btnEro = (Button)findViewById(R.id.btn_ero);
        btnTrombo = (Button)findViewById(R.id.btn_trombo);
        btnLeiko = (Button)findViewById(R.id.btn_leiko);

        progressBar = (ProgressBar)findViewById(R.id.progress_level);
        findViewById(R.id.left).setOnClickListener(this);
        findViewById(R.id.right).setOnClickListener(this);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                txtDebug.post(new Runnable() {
                    @Override
                    public void run() {
                        if(buffer.startsWith("LRRL")){
                            Toast.makeText(MainActivity.this, "Atack", Toast.LENGTH_SHORT).show();
                        }else if (buffer.startsWith("RLLR")){
                            Toast.makeText(MainActivity.this, "Defence", Toast.LENGTH_SHORT).show();
                        }else if (buffer.startsWith("RRRL")){
                            Toast.makeText(MainActivity.this, "Heal", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "No action", Toast.LENGTH_SHORT).show();
                            
                        }
                        buffer = "";
                        txtDebug.setText(buffer);
                        progress++;
                        progressBar.setProgress(progress);
                        if (progress >= 10){
                            timer.cancel();
                            Toast.makeText(MainActivity.this, "End of level ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }, 3000, 3000);

        load_animations();
    }
    void load_animations(){
        new AnimationUtils();
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        imgBadGuy.startAnimation(rotation);

        Animation move = AnimationUtils.loadAnimation(this, R.anim.move_anim);
        btnEro.startAnimation(move);
        btnLeiko.startAnimation(move);
        btnTrombo.startAnimation(move);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left:
                Log.i(TAG, "Left");
                buffer = buffer + "L";
                txtDebug.setText(buffer);
                break;
            case R.id.right:
                Log.i(TAG, "Right");
                buffer = buffer + "R";
                txtDebug.setText(buffer);
                break;
        }
    }


}
