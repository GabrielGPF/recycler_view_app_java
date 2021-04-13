package com.gabriel.recycler_view_app_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements ViewAdapter.OnContactListener {

    private RecyclerView contactsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsView = findViewById(R.id.contactsView);

        ViewAdapter viewAdapter = new ViewAdapter(this, this);
        contactsView.setAdapter(viewAdapter);
        contactsView.setLayoutManager(new LinearLayoutManager(this));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                ContactList.GetInstance().getContactsData().remove(viewHolder.getAdapterPosition());
                viewAdapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(contactsView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contacts_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, AddContact.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void OnContactClick(int position) {
        Intent intent = new Intent(this, AddContact.class);
        intent.putExtra("contactToEdit", position);
        startActivity(intent);
    }
}