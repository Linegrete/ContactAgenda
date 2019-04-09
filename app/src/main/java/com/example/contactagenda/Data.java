package com.example.contactagenda;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Data {

    private static String db = "Contactos";

    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    private static ArrayList<Contact> Contacts = new ArrayList<>();
    public static void Save(Contact c){ Contacts.add(c);}
    public static ArrayList<Contact> Get(){return Contacts;}


    public static void SaveFirebase(Contact c){
        //child -> acceder al objeto
        databaseReference.child(db).child(c.getID()).setValue(c);
    }

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void setContacts(ArrayList<Contact> contacts){
        Contacts = contacts;
    }

    //Obtener datos desde la firebase
    public static ArrayList<Contact> Obtener(){
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Contacts.clear();
                //Si hay dato en la base de datos
                if(dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Contact c = snapshot.getValue(Contact.class);
                        Contacts.add(c);
                    }
                    setContacts(Contacts);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return Contacts;
    }



}

