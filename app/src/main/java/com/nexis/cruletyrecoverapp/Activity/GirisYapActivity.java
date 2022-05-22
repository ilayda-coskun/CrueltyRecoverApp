package com.nexis.cruletyrecoverapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nexis.cruletyrecoverapp.R;

public class GirisYapActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private LinearLayout mLinear;

    private TextInputLayout inputEmail;
    private TextInputLayout inputSifre;

    private EditText editEmail;
    private EditText editSifre;

    private String txtEmail;
    private String txtSifre;

    private void init() {
        mLinear = (LinearLayout) findViewById(R.id.girisYap_linear);
        mAuth = FirebaseAuth.getInstance();

        inputEmail = (TextInputLayout) findViewById(R.id.girisYap_input_email);
        inputSifre = (TextInputLayout) findViewById(R.id.girisYap_input_sifre);

        editEmail = (EditText) findViewById(R.id.girisYap_edit_email);
        editSifre = (EditText) findViewById(R.id.girisYap_edit_sifre);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);

        init();

        getSupportActionBar().hide();
    }

    public void btnGirisYap(View view) {
        txtEmail = editEmail.getText().toString();
        txtSifre = editSifre.getText().toString();

        if (!(TextUtils.isEmpty(txtEmail))) {
            if (!(TextUtils.isEmpty(txtSifre))) {
                mAuth.signInWithEmailAndPassword(txtEmail, txtSifre).addOnCompleteListener(GirisYapActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(GirisYapActivity.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(GirisYapActivity.this, MarkaAraActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        } else {
                            Snackbar.make(mLinear, task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                inputSifre.setError("Şifre Geçerli Değil");
            }
        } else {
            inputEmail.setError("Email Geçerli Değil");
        }
    }

    public void btnGitKayitOl(View view){
        startActivity(new Intent(GirisYapActivity.this, KayitOlActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}