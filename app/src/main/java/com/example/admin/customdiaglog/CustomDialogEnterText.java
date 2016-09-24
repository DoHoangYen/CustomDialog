package com.example.admin.customdiaglog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This class is used to
 *
 * @author Yen Do
 * @since 6/27/2016
 */
public class CustomDialogEnterText {
    protected String message;
    protected Context context;
    protected int screenWidth;
    private TextView positiveButton;
    private TextView negativeButton;
    private TextView noteTv;
    private EditText enterText;
    View view;
    int colorText;

    protected DialogInterface.OnCancelListener mOnCancelListener;

    protected Dialog dialog;

    public CustomDialogEnterText(String message, int screenWidth,
                                 Context context) {
        this.message = message;
        this.context = context;
        this.screenWidth = screenWidth;
        this.initDialog();
    }

    public CustomDialogEnterText(int message, int screenWidth,
                                 Context context) {
        this.context = context;
        this.screenWidth = screenWidth;
        this.message = this.context.getResources().getString(message);
        this.initDialog();
    }

    private void initDialog() {
        dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout_dialog_enter_text, null, false);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.setContentView(view);
        dialog.setCancelable(false);

        //Grab the window of the dialog, and change the width
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());

        lp.width = (int) (screenWidth * 0.9);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        ((TextView) view.findViewById(R.id.alert_description)).setText(this.message);
        this.view = (View) view.findViewById(R.id.line_view);
        this.enterText = (EditText) view.findViewById(R.id.enter_text_edt);
        this.noteTv = (TextView) view.findViewById(R.id.note_tv);

        this.negativeButton = (TextView) view.findViewById(R.id.alert_negative_button);

        this.positiveButton = (TextView) view.findViewById(R.id.alert_positive_button);
        this.positiveButton.setText("OK");
        this.positiveButton.setEnabled(false);
        this.positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        this.enterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (enterText.length() >= 1) {
                    positiveButton.setEnabled(true);
                    positiveButton.setTextColor(colorText);
                } else {
                    positiveButton.setEnabled(false);
                    positiveButton.setTextColor(context.getResources().getColor(R.color.textnormal));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }

    public CustomDialogEnterText setUpPositiveButton(int positiveButtonTitle,
                                                     int color,
                                                     final OnClickListener onClickListener) {
        this.colorText = color;
        this.positiveButton.setText(positiveButtonTitle);
        this.positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(view, enterText.getText().toString());
                dismiss();
            }
        });
        return this;
    }

    public CustomDialogEnterText setUpNegativeButton(int negativeButtonTitle,
                                                     int color,
                                                     final View.OnClickListener onClickListener) {
        if (color != -1) {
            this.negativeButton.setTextColor(color);
        }
        this.negativeButton.setText(context.getString(negativeButtonTitle));
        this.negativeButton.setVisibility(View.VISIBLE);
        this.view.setVisibility(View.VISIBLE);
        this.negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(view);
                dismiss();
            }
        });
        return this;
    }

    public CustomDialogEnterText setEnterText(String hint) {
        this.enterText.setHint(hint);
        return this;
    }

    public CustomDialogEnterText setEnterHintPassword(boolean isPassword) {
        if (isPassword) {
            this.enterText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            this.noteTv.setVisibility(View.GONE);
        } else {
            this.enterText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            this.enterText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
            this.noteTv.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public void show() {
        this.dialog.show();
    }

    public void dismiss() {
        this.dialog.dismiss();
    }

    public CustomDialogEnterText setOnCancelListener(DialogInterface.OnCancelListener listener) {
        this.mOnCancelListener = listener;
        return this;
    }

    public interface OnClickListener {
        void onClick(View view, String content);
    }
}
