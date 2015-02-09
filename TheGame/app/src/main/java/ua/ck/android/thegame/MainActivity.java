package ua.ck.android.thegame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private String buffer = "";
    private TextView txtDebug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDebug = (TextView)findViewById(R.id.txt_debug);
        findViewById(R.id.left).setOnClickListener(this);
        findViewById(R.id.right).setOnClickListener(this);
        Timer timer = new Timer();
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
                    }
                });
            }
        }, 3000, 3000);
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
