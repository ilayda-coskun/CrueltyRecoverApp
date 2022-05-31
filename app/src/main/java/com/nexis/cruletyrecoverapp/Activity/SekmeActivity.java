package com.nexis.cruletyrecoverapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nexis.cruletyrecoverapp.Fragments.AddItemFragment;
import com.nexis.cruletyrecoverapp.Fragments.AramaFragment;
import com.nexis.cruletyrecoverapp.Fragments.HakkindaFragment;
import com.nexis.cruletyrecoverapp.Fragments.UrunlerFragment;
import com.nexis.cruletyrecoverapp.R;

public class SekmeActivity extends AppCompatActivity {

    private BottomNavigationView mBottomView;
    private AramaFragment aramaFragment;
    private UrunlerFragment urunlerFragment;
    private HakkindaFragment hakkindaFragment;
    private AddItemFragment addItemFragment;
    private FragmentTransaction transaction;

    private void init(){
       mBottomView=(BottomNavigationView) findViewById(R.id.sekme_bottomView);

       aramaFragment= new AramaFragment();
       urunlerFragment= new UrunlerFragment();
       hakkindaFragment= new HakkindaFragment();
       addItemFragment = new AddItemFragment();

       fragmentAyarla(aramaFragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sekme);

        init();

        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bottom_nav_ic_search:
                        fragmentAyarla(aramaFragment);
                        return true;

                    case R.id.bottom_nav_ic_products:
                        fragmentAyarla(urunlerFragment);
                            return true;

                    case R.id.bottom_nav_ic_info:
                        fragmentAyarla(hakkindaFragment);
                                return true;


                    case R.id.addNewItem:
                        fragmentAyarla(addItemFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void fragmentAyarla(Fragment fragment ){

          transaction=getSupportFragmentManager().beginTransaction();
          transaction.replace(R.id.sekme_activity_frameLayout, fragment);
          transaction.commit();
    }
}