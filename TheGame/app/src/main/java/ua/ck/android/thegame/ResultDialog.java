package ua.ck.android.thegame;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by cheb on 4/15/15.
 */
public class ResultDialog extends DialogFragment {
    private static final String TAG = "tag";

    static ResultDialog newInstance(boolean win) {
        ResultDialog f = new ResultDialog();
        Bundle args = new Bundle();
        args.putBoolean(TAG, win);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style=DialogFragment.STYLE_NO_TITLE;
        int theme=android.R.style.Theme_Translucent;
        setStyle(style, theme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_win_lose, container, false);
        boolean b = getArguments().getBoolean(TAG, false);
        ImageView imageView = (ImageView)v.findViewById(R.id.img_img);
        if (b){
            imageView.setImageResource(R.drawable.win);
        }else{
            imageView.setImageResource(R.drawable.lose);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return v;
    }
}
