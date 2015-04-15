package ua.ck.android.thegame;

import android.app.FragmentTransaction;
import android.media.Image;
import android.os.Handler;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private String buffer = "";
    private TextView txtDebug;
    private ProgressBar progressBar;
    private int progress = 0;
    private ImageView btnEro, btnTrombo, btnLeiko;
    private ImageView imgBadGuy;
    private LinearLayout drumsContainer;
    private TextView txtHP, txtHPe, txtO2;
    private ImageView imgCombo;
    private int hp, hpe, o2;
    private boolean hurt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hp =100;
        hpe = 100;
        o2 = 100;
        hurt = false;
        txtDebug = (TextView)findViewById(R.id.txt_debug);
        drumsContainer = (LinearLayout)findViewById(R.id.drums_container);
        imgBadGuy = (ImageView)findViewById(R.id.img_bad_guy_1);
        btnEro = (ImageView)findViewById(R.id.img_ero);
        btnTrombo = (ImageView)findViewById(R.id.img_trombo);
        btnLeiko = (ImageView)findViewById(R.id.img_leiko);

        txtHP = (TextView)findViewById(R.id.txt_hp);
        txtHPe = (TextView)findViewById(R.id.txt_hpe);
        txtO2 = (TextView)findViewById(R.id.txt_o2);

        imgCombo = (ImageView)findViewById(R.id.img_combo);

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
                        Random random = new Random();
                        boolean b= random.nextBoolean();
                        if(buffer.startsWith("LRRL")){
                            Toast.makeText(MainActivity.this, "Atack", Toast.LENGTH_SHORT).show();
                            if (b) {
                                hpe = hpe - 5;
                            }else{
                                hpe = hpe - 10;
                            }
                            txtHPe.setText(String.valueOf(hpe));
                            showCombo(R.drawable.combo_lei);
                        }else if (buffer.startsWith("RLLR")){
                            Toast.makeText(MainActivity.this, "Defence", Toast.LENGTH_SHORT).show();
                            showCombo(R.drawable.combo_eri);
                        }else if (buffer.startsWith("RRRL")){
                            Toast.makeText(MainActivity.this, "Heal", Toast.LENGTH_SHORT).show();
                            showCombo(R.drawable.combo_tro);
                        }else{
                            Toast.makeText(MainActivity.this, "No action", Toast.LENGTH_SHORT).show();
                        }
                        buffer = "";
                        txtDebug.setText(buffer);
                        drumsContainer.removeAllViews();
                        progress++;
                        progressBar.setProgress(progress);
                        if (progress >= 10){
                            timer.cancel();
                            boolean win;
                            if (hp >= hpe){
                                win = true;
                            }else{
                                win = false;
                            }
                            ResultDialog dialog = ResultDialog.newInstance(win);

                            FragmentTransaction ft = getFragmentManager().beginTransaction();

                            dialog.show(ft, "dialog");
                            Toast.makeText(MainActivity.this, "End of level ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }, 3000, 3000);

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

    private void showCombo(int res){
        imgCombo.setImageResource(res);
        imgCombo.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgCombo.setVisibility(View.GONE);
            }
        }, 350);
    }

    private void addR(){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.button_blue_statusbar);
        drumsContainer.addView(imageView);
    }

    private void addL(){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.button_green_statusbar);
        drumsContainer.addView(imageView);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left:
                Log.i(TAG, "Left");
                buffer = buffer + "L";
                txtDebug.setText(buffer);
                addR();
                break;
            case R.id.right:
                Log.i(TAG, "Right");
                buffer = buffer + "R";
                txtDebug.setText(buffer);
                addL();
                break;
        }
    }


}
