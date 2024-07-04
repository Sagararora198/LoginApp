package com.example.loginapp;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;


public class RegisterFragment extends Fragment {
//    Declare buttons in view
    Button signUp,pickerBtn;
//    declare editText in view
    EditText usernameText,emailText,passwordText,confirmPasswordText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Initialize views
        initializeView(view);


    }

    /** Initializes view
     *
     * @param view view
     */
    private void initializeView(View view) {
        usernameText = view.findViewById(R.id.et_name);
        emailText = view.findViewById(R.id.et_email);
        passwordText = view.findViewById(R.id.et_password);
        confirmPasswordText = view.findViewById(R.id.et_repassword);
        signUp = view.findViewById(R.id.btn_register);

        // set up signup button clickEvent
        signUp.setOnClickListener(v->
        {

                String username = usernameText.getText().toString();
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                String rePassword = confirmPasswordText.getText().toString();
                register(username,email,password,rePassword);
        });

        // set up swipe listener
        view.setOnTouchListener(new SwipeListener(){
            @Override
            public void onLeftToRightSwipe() {
                super.onLeftToRightSwipe();
                navigateToLoginFragment();
            }
        });
    }


    /** register the user and save info in shared preference
     *
     * @param username name of user
     * @param email email of user
     * @param password password of user
     * @param rePassword confirm password of user
     */
    private void register(String username, String email, String password, String rePassword) {
        // check for valid fields
        if(!validateField(username,email,password,rePassword)){
            Toast.makeText(getContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
            return;
        }
        // save the data in sharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(email + "_password",password);
        editor.putString(email+"_fullName",username);
        editor.apply();
        //navigate to login fragment
        navigateToLoginFragment();
    }

    /**navigate to login fragment
     *
     */
    private void navigateToLoginFragment() {
        if(getActivity() != null){
            FragmentActivity fragmentActivity = (FragmentActivity) getActivity();
            fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LoginFragment()).setReorderingAllowed(true).addToBackStack(null).commit();
        }
    }

    /** validate fields
     *
     * @param username name
     * @param email email
     * @param password password
     * @param rePassword confirm password
     * @return true if validations approved
     */
    private Boolean validateField(String username, String email, String password, String rePassword) {
        if(username.isEmpty()){
                usernameText.setError("This field is required");
                return false;
        }
        else if(email.isEmpty()){
            emailText.setError("This field is required");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("Enter a valid email address");
            return false;
        }
        else if(password.isEmpty() || password.length()<8){
            passwordText.setError("Enter more then 8 characters");
            return false;
        }
        else if(!rePassword.equals(password)) {
            confirmPasswordText.setError("Password does not match");

            return false;
        }
        else{

            return true;
        }
    }


}