package ua.ck.android.thegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
     * Created by cheb on 3/23/15.
 */
public class MenuActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViewById(R.id.txt_play).setOnClickListener(this);
        findViewById(R.id.txt_continue).setOnClickListener(this);
        findViewById(R.id.txt_exit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_continue:
            case R.id.txt_play:
                    startActivity(new Intent(MenuActivity.this, LevelsActivity.class));
                break;
            case R.id.txt_exit:
                    finish();
                break;
        }
    }
}
