package com.example.onthimusic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;

public class MainActivity extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;
    Button login;
    private CheckBox chkAdmin;
    private int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        resolveSignInGoogleAccount();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
        login=findViewById( R.id.btnlogin );
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginfun();
            }
        } );
    }
    private void loginfun() {

        Toast.makeText( this,"Chức năng đang được xây dựng",Toast.LENGTH_SHORT ).show();

    }
    private void initView() {
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        //btnLocation = findViewById(R.id.btnLocation);
        chkAdmin = findViewById(R.id.chkAdmin);
    }
    private void resolveSignInGoogleAccount() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {

        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
//            GoogleSignInAccount account = result.getSignInAccount();
            if(chkAdmin.isChecked()){
                startActivity(new Intent(this, ListMusicAdmin.class));
                overridePendingTransition( R.anim.enterx,R.anim.exit_x );
                Toast.makeText(this, "Sang màn hình Admin", Toast.LENGTH_SHORT).show();
            }else {
                startActivity(new Intent(this, ListMusic.class));
                Toast.makeText(this, "Sang màn hình User", Toast.LENGTH_SHORT).show();
            }
        }else {
        }
    }
}