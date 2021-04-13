package com.gabriel.recycler_view_app_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    private int editContact;

    private EditText nameEditText, numberEditText, addressEditText;
    private Button cancelButton, submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editContact = getIntent().getIntExtra("contactToEdit", -1);

        nameEditText = findViewById(R.id.nameEditText);
        numberEditText = findViewById(R.id.numberEditText);
        addressEditText = findViewById(R.id.addressEditText);

        cancelButton = findViewById(R.id.cancelButton);
        submitButton = findViewById(R.id.submitButton);

        if (editContact != -1) {
            nameEditText.setText(ContactList.GetInstance().getContactsData().get(editContact)[0]);
            numberEditText.setText(ContactList.GetInstance().getContactsData().get(editContact)[1]);
            addressEditText.setText(ContactList.GetInstance().getContactsData().get(editContact)[2]);
        }

        submitButton.setOnClickListener((View v) -> {
            if (nameEditText.getText().toString().equals("") || numberEditText.getText().toString().equals("") || addressEditText.getText().toString().equals("")) {
                emptyFieldsAlert();
            } else {
                submitContact();
            }
        });

        cancelButton.setOnClickListener((View v) -> {
            cancelAction();
        });
    }

    private void emptyFieldsAlert(){
        Toast.makeText(AddContact.this, "Empty field(s)", Toast.LENGTH_SHORT).show();
    }

    private void cancelAction(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void submitContact(){
        Toast.makeText(AddContact.this, "Contact submitted", Toast.LENGTH_SHORT).show();


        String[] newContact = {nameEditText.getText().toString(), numberEditText.getText().toString(), addressEditText.getText().toString()};
        if (editContact != -1) {
            ContactList.GetInstance().getContactsData().set(editContact, newContact);
        } else {
            ContactList.GetInstance().getContactsData().add(newContact);
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}