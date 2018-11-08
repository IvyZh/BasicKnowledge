package com.ivyzh.basicknowledge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ivyzh.basicknowledge.annotation.ViewById;

public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.tv_result)
    TextView mTextView;
    TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.bind(this);
        mTextView.setText("mTextView ViewById");
    }
}
