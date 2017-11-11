package hu.ait.android.shoppinglist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
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

        itemList = new ArrayList<>();
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
        //lots of if statement
        //holder.ivCategory.setText(itemData.getCategory());
        holder.tvPrice.setText(Double.toString(itemData.getPrice()));
        holder.tvNote.setText(itemData.getNote());
        holder.cbPurchased.setChecked(itemData.isPurchased());

        holder.cbPurchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                itemData.setIsPurchased(!itemData.isPurchased());
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
        String itemDismissId = itemList.get(position).getItemId();
        realm.beginTransaction();

        Item itemToBeDeleted = realm.where(Item.class).
                equalTo("itemId", itemDismissId).findFirst();
        itemToBeDeleted.deleteFromRealm();

        realm.commitTransaction();

        itemList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //itemMoveInRealm(fromPosition, toPosition);
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
//
//    private void itemMoveInRealm(int fromPosition, int toPosition) {
//        realm.beginTransaction();
//        Item fromItem = realm.where(Item.class).equalTo("itemId", itemList.get(fromPosition).getItemId()).findFirst();
//        if (fromPosition < toPosition) {
//            RealmResults<Item> results = realm.where(Item.class)
//                                                .greaterThan("itemId", fromPosition)
//                                                .lessThanOrEqualTo("itemId", toPosition)
//                                                .findAll();
//            for (int i = 0; i < results.size(); i++) {
//                //results.get(i).
//            }
//        }
//        itemResult = realm.where(Item.class).findAll();
//
//        Item toItem = realm.where(Item.class).equalTo("itemId", itemList.get(toPosition).getItemId()).findFirst();
//        Item tempItem = new Item(fromItem.getName(),fromItem.getPrice(),
//                fromItem.getNote(), fromItem.isPurchased());
//
//
//        realm.commitTransaction();
//    }

    public void addItem(String itemName, String itemCategory, double itemPrice, String itemNote, boolean isPurchased) {
        realm.beginTransaction();

        Item newItem = realm.createObject(Item.class, UUID.randomUUID().toString());

        newItem.setName(itemName);
        newItem.setCategory(itemCategory);
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

    public void deleteList() {
        realm.beginTransaction();
        itemList.clear();
        notifyDataSetChanged();
        realm.deleteAll();
        realm.commitTransaction();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private ImageView ivCategory;
        private TextView tvPrice;
        private TextView tvNote;
        private CheckBox cbPurchased;
        private Button btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            ivCategory = itemView.findViewById(R.id.ivCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvNote = itemView.findViewById(R.id.tvNote);
            cbPurchased = itemView.findViewById(R.id.cbPurchased);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
