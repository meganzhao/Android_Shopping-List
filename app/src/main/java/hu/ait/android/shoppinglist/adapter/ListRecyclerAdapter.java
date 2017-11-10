package hu.ait.android.shoppinglist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import hu.ait.android.shoppinglist.MainActivity;
import hu.ait.android.shoppinglist.R;
import hu.ait.android.shoppinglist.data.Item;
import hu.ait.android.shoppinglist.touch.ItemTouchHelperAdapter;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by zhaozhaoxia on 11/4/17.
 */

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {

    private List<Item> itemList;
    private Realm realm;
    private Context context;

    public ListRecyclerAdapter(Context context, Realm realm) {
        this.context = context;
        this.realm = realm;

        itemList = new ArrayList<Item>();
        RealmResults<Item> itemResult = realm.where(Item.class).findAll();

        for (Item item: itemResult){
            itemList.add(item);
        }
    }




        @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(listRow);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Item itemData = itemList.get(position);
        holder.tvName.setText(itemData.getName());
        holder.tvPrice.setText(Double.toString(itemData.getPrice()));
        holder.tvNote.setText(itemData.getNote());
        holder.cbPurchased.setChecked(itemData.isPurchased());
        holder.cbPurchased.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                realm.beginTransaction();
                itemData.setIsPurchased(isChecked);
                realm.commitTransaction();
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).openEditActivity(
                        holder.getAdapterPosition(),
                        itemList.get(holder.getAdapterPosition()).getItemId()
                );
            }
        });


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

    public void addItem(String itemName, double itemPrice, String itemNote, boolean isPurchased) {
        realm.beginTransaction();

        Item newItem = realm.createObject(Item.class, UUID.randomUUID().toString());

        newItem.setName(itemName);
        newItem.setPrice(itemPrice);
        newItem.setNote(itemNote);
        newItem.setIsPurchased(isPurchased);

        realm.commitTransaction();

        itemList.add(newItem);
        notifyDataSetChanged();
    }

    public void updateItem(String itemIdThatWasEdited, int positionToEdit) {
        Item item = realm.where(Item.class).
                equalTo("itemId", itemIdThatWasEdited).findFirst();
        itemList.set(positionToEdit, item);
        notifyItemChanged(positionToEdit);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvPrice;
        private TextView tvNote;
        private CheckBox cbPurchased;
        private Button btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvNote = itemView.findViewById(R.id.tvNote);
            cbPurchased = itemView.findViewById(R.id.cbPurchased);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
