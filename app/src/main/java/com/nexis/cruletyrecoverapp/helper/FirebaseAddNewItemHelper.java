package com.nexis.cruletyrecoverapp.helper;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nexis.cruletyrecoverapp.Model.MarkaModel;
import com.nexis.cruletyrecoverapp.Model.ProductModel;

import java.util.Map;

public class FirebaseAddNewItemHelper {

        public DatabaseReference databaseReference;


        public FirebaseAddNewItemHelper(){
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            databaseReference = db.getReference(ProductModel.class.getSimpleName());
        }

        public Task<Void> addUser(String key, ProductModel markaModel){
            return databaseReference.child(key).setValue(markaModel);
        }

        public Task<Void> updateUser(String key, Map<String,Object> taskMap){
            return databaseReference.child(key).updateChildren(taskMap);
        }

        public Task<Void> removeUser(String key){
            return databaseReference.child(key).removeValue();
        }


}
