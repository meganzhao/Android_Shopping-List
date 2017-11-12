package hu.ait.android.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import hu.ait.android.shoppinglist.adapter.ListRecyclerAdapter;
import hu.ait.android.shoppinglist.touch.ShoppingItemTouchHelperCallback;

import static hu.ait.android.shoppinglist.R.*;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_ITEM_ID = "KEY_ITEM_ID";
    public static final int REQUEST_CODE = 1001;
    public static final String ITEM_NAME = "ITEM_NAME";
    public static final String ITEM_ISPURCHASED = "ITEM_ISPURCHASED";
    public static final String ITEM_PRICE = "ITEM_PRICE";
    public static final String ITEM_NOTE = "ITEM_NOTE";
    public static final String ITEM_CATEGORY = "ITEM_CATEGORY";
    private ListRecyclerAdapter adapter;
    private int positionToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        ((ListApplication)getApplication()).openRealm();

        RecyclerView recyclerViewList = findViewById(id.recyclerItem);
        adapter = new ListRecyclerAdapter(this,
                ((ListApplication)getApplication()).getRealm());
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewList.setHasFixedSize(true);

        ItemTouchHelper.Callback callback = new ShoppingItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewList);

        recyclerViewList.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        ((ListApplication)getApplication()).closeRealm();

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void openEditActivity (int adapterPosition, String itemId) {
        positionToEdit = adapterPosition;
        Intent intentEdit = new Intent(this, EditItemActivity.class);
        intentEdit.putExtra(KEY_ITEM_ID, itemId);
        startActivityForResult(intentEdit, REQUEST_CODE);
    }

    public void openEditActivityToAddItem (){
        Intent intentAdd = new Intent(this, EditItemActivity.class);
        startActivityForResult(intentAdd,  REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data.hasExtra(KEY_ITEM_ID)) {
                String itemIdThatWasEdited = data.getStringExtra(KEY_ITEM_ID);
                adapter.updateItem(itemIdThatWasEdited, positionToEdit);
            } else {
                String itemName = data.getStringExtra(ITEM_NAME);
                String itemCategory = data.getStringExtra(ITEM_CATEGORY);
                Double itemPrice = data.getDoubleExtra(ITEM_PRICE, 8.0);
                String itemNote = data.getStringExtra(ITEM_NOTE);
                boolean isPurchased = data.getBooleanExtra(ITEM_ISPURCHASED, false);
                adapter.addItem(itemName,itemCategory,itemPrice,itemNote,isPurchased);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == id.add_item){
            openEditActivityToAddItem();
        }
        if (item.getItemId() == id.delete_list){
            adapter.deleteList();
        }
        return super.onOptionsItemSelected(item);
    }
}
