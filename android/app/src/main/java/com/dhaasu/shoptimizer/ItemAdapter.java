package com.dhaasu.shoptimizer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ramitsuri on 9/24/16.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CustomViewHolder> {
    private ArrayList<String> items;

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView itemName;
        //protected EditText itemQuatity;
        protected TextView itemQuatity;
        protected Button buttonAdd;
        protected Button buttonRemove;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.itemName = (TextView)itemView.findViewById(R.id.itemName);
            //this.itemQuatity = (EditText)itemView.findViewById(R.id.itemQuantity);
            this.itemQuatity = (TextView)itemView.findViewById(R.id.itemQuantity);
            this.buttonAdd = (Button) itemView.findViewById(R.id.buttonAdd);
            this.buttonRemove = (Button)itemView.findViewById(R.id.buttonRemove);
            buttonRemove.setOnClickListener(this);
            buttonAdd.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int quantity = Integer.parseInt(itemQuatity.getText().toString());
            if(view.getId() == buttonAdd.getId()){
                quantity = quantity + 1;
            }

            else if(view.getId() == buttonRemove.getId()) {
                quantity = quantity - 1;
            }
            itemQuatity.setText(String.valueOf(quantity));
        }
    }

    private static ItemAdapter ourInstance = new ItemAdapter();

    public static ItemAdapter getInstance() {
        return ourInstance;
    }

    private ItemAdapter() {
    }

    public ItemAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.itemName.setText(items.get(position));
        holder.itemQuatity.setText(String.valueOf(1));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateDataSet(ArrayList<String> items){
        this.items = items;
        this.notifyDataSetChanged();
    }
}
