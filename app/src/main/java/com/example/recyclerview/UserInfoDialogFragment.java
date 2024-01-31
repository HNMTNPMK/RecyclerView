package com.example.recyclerview;

import android.app.AlertDialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class UserInfoDialogFragment extends DialogFragment {

    private static final String ARG_USER_ID = "user_id";
    private static final String ARG_USER_EMAIL = "user_email";
    private static final String ARG_USER_PASSWORD = "user_password";
    private static final String ARG_USER_ROLE = "user_role";

    public static UserInfoDialogFragment newInstance(int userId, String userEmail, int userPassword, String userRole) {
        UserInfoDialogFragment fragment = new UserInfoDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_USER_ID, userId);
        args.putString(ARG_USER_EMAIL, userEmail);
        args.putInt(ARG_USER_PASSWORD, userPassword);
        args.putString(ARG_USER_ROLE, userRole);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int userId = args.getInt(ARG_USER_ID);
        String userEmail = args.getString(ARG_USER_EMAIL);
        int userPassword = args.getInt(ARG_USER_PASSWORD);
        String userRole = args.getString(ARG_USER_ROLE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("User Information")
                .setMessage("ID: " + userId + "\n"
                        + "Email: " + userEmail + "\n"
                        + "Password: " + userPassword + "\n"
                        + "Role: " + userRole)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when OK button is clicked
                    }
                });

        return builder.create();
    }
}