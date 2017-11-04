package hu.ait.android.shoppinglist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.ait.android.shoppinglist.R;
import hu.ait.android.shoppinglist.data.Item;
import hu.ait.android.shoppinglist.touch.ItemTouchHelperAdapter;

/**
 * Created by zhaozhaoxia on 11/4/17.
 */

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {

    private List<Item> itemList;

//    public ListRecyclerAdapter(){
//        this.itemList = new ArrayList<>();
//    }

    public ListRecyclerAdapter() {
        itemList = new ArrayList<Item>();
        for (int i = 0; i < 20; i++) {
            itemList.add(new Item("Todo", 3, "f", false));
        }
    }




        @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(listRow);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item itemData = itemList.get(position);
        holder.tvName.setText(itemData.getName());
        holder.tvPrice.setText(Double.toString(itemData.getPrice()));
        holder.tvNote.setText(itemData.getNote());
        holder.cbPurchased.setChecked(itemData.isDone());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onItemDismiss(int position) {
        //Item itemToDelete = itemList.get(position);

        itemList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(itemList,i,i+1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(itemList,i,i-1);
            }
        }
        notifyItemMoved(fromPosition,toPosition);
    }

    public void addTodo(Item item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

//    public void addItem(String name, double price, String note, boolean isDone) {
//        Item newItem = new Item(name, price, note, isDone);
//        itemList.add(0, newItem);
//        notifyItemInserted(0);
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvPrice;
        private TextView tvNote;
        private CheckBox cbPurchased;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvNote = itemView.findViewById(R.id.tvNote);
            cbPurchased = itemView.findViewById(R.id.cbPurchased);

        }
    }
}
