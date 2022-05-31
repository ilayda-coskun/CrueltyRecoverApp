package com.nexis.cruletyrecoverapp.helper;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nexis.cruletyrecoverapp.Model.MarkaModel;

import java.util.Map;

public class FirebaseMarkaHelper {

        public DatabaseReference databaseReference;


        public FirebaseMarkaHelper(){
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            databaseReference = db.getReference(MarkaModel.class.getSimpleName());
        }

        public Task<Void> addUser(String key, MarkaModel markaModel){
            return databaseReference.child(key).setValue(markaModel);
        }

        public Task<Void> updateUser(String key, Map<String,Object> taskMap){
            return databaseReference.child(key).updateChildren(taskMap);
        }

        public Task<Void> removeUser(String key){
            return databaseReference.child(key).removeValue();
        }



}
