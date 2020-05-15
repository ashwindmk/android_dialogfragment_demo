package com.ashwin.android.fragmentdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import static com.ashwin.android.fragmentdialog.MainActivity.TAG;

public class MyDialogFragment extends DialogFragment {
    private static final String COMPONENT_TAG = "dialog-fragment";

    public interface DialogListener {
        void onFinishEditDialog(String inputText);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, COMPONENT_TAG + ": on-attach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean setFullScreen = false;
        if (getArguments() != null) {
            setFullScreen = getArguments().getBoolean("fullScreen");
        }
        if (setFullScreen) {
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            if (getArguments().getBoolean("notAlertDialog")) {
                return super.onCreateDialog(savedInstanceState);
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Alert Dialog");
        builder.setMessage("Alert Dialog inside DialogFragment");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, COMPONENT_TAG + ": on-start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, COMPONENT_TAG + ": on-resume");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText editText = view.findViewById(R.id.email_edittext);

        if (getArguments() != null && !TextUtils.isEmpty(getArguments().getString("email"))) {
            editText.setText(getArguments().getString("email"));
        }

        Button btnDone = view.findViewById(R.id.done_button);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogListener dialogListener = (DialogListener) getActivity();
                dialogListener.onFinishEditDialog(editText.getText().toString());
                dismiss();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, COMPONENT_TAG + ": on-pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, COMPONENT_TAG + ": on-stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, COMPONENT_TAG + ": on-destroy-view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, COMPONENT_TAG + ": on-destroy");
    }
}
