package com.example.contactagenda;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateContact extends AppCompatActivity {
    private EditText Name, LastName, Phone, Cellphone;
    private Resources Resources;
    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate (Bundle SavedInstaceState){
        super.onCreate(SavedInstaceState);
        setContentView(R.layout.create_contact);

        Name = (EditText)findViewById(R.id.TxtName);
        LastName = (EditText)findViewById(R.id.TxtLasName);
        Phone = (EditText)findViewById(R.id.TxtPhone);
        Cellphone = (EditText)findViewById(R.id.TxtCellphone);

        Resources = this.getResources();
        //contacts = Data.Get();
    }

    public void Save(View View){
        String  ID, NameV, LastNameV, PhoneV, CellphoneV;
        ///ID = (contacts.size()+1) + "";
        ID = Data.getId();
        NameV = Name.getText().toString();
        LastNameV = LastName.getText().toString();
        PhoneV = Phone.getText().toString();
        CellphoneV = Cellphone.getText().toString();

        //validar que los campos no esten vacios
        if (Name.getText().toString().equals("") || LastName.getText().toString().equals("") ||
                LastName.getText().toString().equals("") || Phone.getText().toString().equals("") ||
                Cellphone.getText().toString().equals("")){
            Toast.makeText(this, "There can not be empty fields",Toast.LENGTH_LONG).show();
        }else{
            Contact C = new Contact(ID, NameV, LastNameV, PhoneV, CellphoneV);
            //C.SaveContact();
            C.SaveFirebase();
            Toast.makeText(this, R.string.done, Toast.LENGTH_LONG).show();
        }


    }
}


