package com.nexis.cruletyrecoverapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.nexis.cruletyrecoverapp.R;

public class MarkaAraActivity extends AppCompatActivity {

    //private Button buttonMarkaAra;
    private EditText editMarkaAra;

    private void init() {
        editMarkaAra = (EditText) findViewById(R.id.markaAra_edit_ara);
        //buttonMarkaAra = (Button) findViewById(R.id.btnMarkaAra);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marka_ara);

        init();
    }

    public void btnMarkaAra(View view) {

    }
}