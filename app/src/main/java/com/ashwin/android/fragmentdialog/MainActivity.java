package com.ashwin.android.fragmentdialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyDialogFragment.DialogListener {
    public static final String TAG = "dialog-fragment";
    private static final String COMPONENT_TAG = "main-activity";

    Button embedDialogFragmentButton, dialogFragmentButton, fullscreenDialogFragmentButton, alertDialogFragmentButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.email_textview);
        embedDialogFragmentButton = findViewById(R.id.embed_dialogfragment_button);
        dialogFragmentButton = findViewById(R.id.dialogfragment_button);
        fullscreenDialogFragmentButton = findViewById(R.id.fullscreen_dialogfragment_button);
        alertDialogFragmentButton = findViewById(R.id.alert_dialogfragment_button);

        embedDialogFragmentButton.setOnClickListener(this);
        dialogFragmentButton.setOnClickListener(this);
        fullscreenDialogFragmentButton.setOnClickListener(this);
        alertDialogFragmentButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, COMPONENT_TAG + ": on-start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, COMPONENT_TAG + ": on-resume");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, COMPONENT_TAG + ": on-attached-to-window");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.embed_dialogfragment_button:
                MyDialogFragment dialogFragment = new MyDialogFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.embed_framelayout, dialogFragment);
                ft.commit();
                break;

            case R.id.dialogfragment_button:
                dialogFragment = new MyDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);
                dialogFragment.setArguments(bundle);
                ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                dialogFragment.show(ft, "dialog");
                break;

            case R.id.fullscreen_dialogfragment_button:
                dialogFragment = new MyDialogFragment();
                bundle = new Bundle();
                bundle.putString("email", "xyz@gmail.com");
                bundle.putBoolean("fullScreen", true);
                bundle.putBoolean("notAlertDialog", true);
                dialogFragment.setArguments(bundle);

                ft = getSupportFragmentManager().beginTransaction();
                prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                dialogFragment.show(ft, "dialog");
                break;

            case R.id.alert_dialogfragment_button:
                dialogFragment = new MyDialogFragment();
                ft = getSupportFragmentManager().beginTransaction();
                prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                dialogFragment.show(ft, "dialog");
                break;
        }
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        if (TextUtils.isEmpty(inputText)) {
            textView.setText("Email was not entered");
        } else {
            textView.setText("Email entered: " + inputText);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, COMPONENT_TAG + ": on-pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, COMPONENT_TAG + ": on-stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, COMPONENT_TAG + ": on-destroy");
    }
}
