package com.dhaasu.shoptimizer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static ArrayList<String> items;
    private ItemAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        items = getItems();
        RecyclerView recyclerViewItems = (RecyclerView) findViewById(R.id.recyclerViewItems);
        RecyclerView.LayoutManager recyclerViewLManager = new LinearLayoutManager(this);
        recyclerViewAdapter = new ItemAdapter(items);
        recyclerViewItems.setHasFixedSize(true);
        recyclerViewItems.setLayoutManager(recyclerViewLManager);
        recyclerViewItems.setAdapter(recyclerViewAdapter);

        FloatingActionButton fabAddItem = (FloatingActionButton) findViewById(R.id.fabAddItem);
        fabAddItem.setOnClickListener(this);
        /*Object ob = new Object();
        ob.shoppingList = MainActivity.items;
        ob.storeID = "b97153fc14";
        Gson gson = new Gson();
        String json = gson.toJson(ob);
        Intent serviceIntent = new Intent(getApplicationContext(), MyIntentService.class);
        serviceIntent.putExtra(ServerIntentService.MODE, 1);
        serviceIntent.putExtra(ServerIntentService.OBJECT, json);
        startService(serviceIntent);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<String> getItems() {
        ArrayList<String> items1 = new ArrayList<>();
        items1.add("apple");
        items1.add("beer");
        items1.add("bread");
        items1.add("toothpaste");
        /*for(int i=0;i<10;i++){
            Item item = new Item();
            item.setItemName("Apple" + i);
            item.setItemQuantity(i);
            items1.add(item);
        }*/
        return items1;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fabAddItem){
            showAddItemDialog();
        }
    }

    private void showAddItemDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogStyle);
        alertDialog.setTitle(R.string.dialog_title);

        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);


        alertDialog.setPositiveButton("Add",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        items.add(input.getText().toString());
                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
    public class Object{
        public ArrayList<String> shoppingList;
        public String storeID;
    }

}
