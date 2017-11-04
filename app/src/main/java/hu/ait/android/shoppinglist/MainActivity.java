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

public class MainActivity extends AppCompatActivity {

    private ListRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewList = findViewById(R.id.recyclerItem);
        adapter = new ListRecyclerAdapter();
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewList.setHasFixedSize(true);

        ItemTouchHelper.Callback callback = new ShoppingItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewList);

        recyclerViewList.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_item){
            Intent intent = new Intent(this, AddItemActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.delete_list){

        }
        return super.onOptionsItemSelected(item);
    }



}
