package ua.ck.android.thegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by cheb on 4/5/15.
 */
public class LevelsActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        findViewById(R.id.btn_1).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_1:
                startActivity(new Intent(LevelsActivity.this, MainActivity.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(LevelsActivity.this, MainActivity.class));
                break;
            case R.id.btn_3:
                break;

        }
    }


}
