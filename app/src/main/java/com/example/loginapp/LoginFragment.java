package com.example.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;


public class LoginFragment extends Fragment {
    Button loginBtn;
    EditText emailText,passwordText;
    public LoginFragment() {



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView(view);


    }

    private void initializeView(View view) {
        loginBtn = (Button) view.findViewById(R.id.btn_login);
        emailText = view.findViewById(R.id.lg_email);
        passwordText = view.findViewById(R.id.lg_password);

        //swipe listener
        view.setOnTouchListener(new SwipeListener(){
            @Override
            public void onRightToLeftSwipe() {
                super.onRightToLeftSwipe();
                if(getActivity() != null){
                    FragmentActivity fragmentActivity = (FragmentActivity) getActivity();
                    fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegisterFragment()).setReorderingAllowed(true).addToBackStack(null).commit();
                }
            }
        });

        //login button event
        loginBtn.setOnClickListener(v->{
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            String[] userData = validateLogin(email,password);
            if(userData!=null){
                Intent intent = new Intent(getActivity(),HomePage.class);
                intent.putExtra("username",userData[1]);
                intent.putExtra("email",userData[0]);
                startActivity(intent);
            }
            else {
                Toast.makeText(getActivity(), "Not logged in", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String[] validateLogin(String email, String password) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String storedPassword = sharedPreferences.getString(email+"_password",null);
        if( storedPassword != null && storedPassword.equals(password)){
            String username = sharedPreferences.getString(email+"_fullName","");
            return new String[]{email,username};
        }
        return null;
    }

}