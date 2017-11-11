package hu.ait.android.shoppinglist;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import hu.ait.android.shoppinglist.data.Item;

public class EditItemActivity extends AppCompatActivity {

    private EditText etName;
    private String categorySelected;
    private EditText etPrice;
    private EditText etNote;
    private CheckBox cbPurchased;
    private boolean isEdit = false;
    private Item itemToEdit = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etNote = findViewById(R.id.etNote);
        cbPurchased = findViewById(R.id.cbPurchased);
        Button btnSave = findViewById(R.id.btnSave);
        Spinner spinner = findViewById(R.id.spinner);
        itemToEdit = new Item();

        final List<String> categories = new ArrayList<>();
        categories.add("Accessories");
        categories.add("Art");
        categories.add("Beauty");
        categories.add("Clothing");
        categories.add("Drugs");
        categories.add("Electronics");
        categories.add("Gift");
        categories.add("Grocery");
        categories.add("Pet");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(categoryAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                  categorySelected = adapterView.getItemAtPosition(i).toString();
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> adapterView) {
                                                  categorySelected = "N/A";
                                              }
                                          });





        if (getIntent().hasExtra(MainActivity.KEY_ITEM_ID)) {
            String itemId = getIntent().getStringExtra(MainActivity.KEY_ITEM_ID);

            itemToEdit = ((ListApplication)getApplication()).getRealm().where(Item.class).
                    equalTo("itemId", itemId).findFirst();
            isEdit = true;
        }
        if (itemToEdit != null) {
            etName.setText(itemToEdit.getName());
            etPrice.setText(String.valueOf(itemToEdit.getPrice()));
            etNote.setText(itemToEdit.getNote());
            spinner.setSelection(categoryAdapter.getPosition(itemToEdit.getCategory()));
            cbPurchased.setChecked(itemToEdit.isPurchased());
        } else {
            ((ListApplication)getApplication()).getRealm().beginTransaction();
            itemToEdit = ((ListApplication)getApplication()).getRealm().createObject(Item.class, UUID.randomUUID().toString());
            ((ListApplication)getApplication()).getRealm().commitTransaction();
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListApplication)getApplication()).getRealm().beginTransaction();

                itemToEdit.setName(etName.getText().toString());
                itemToEdit.setCategory(categorySelected);
                itemToEdit.setPrice(Double.parseDouble(etPrice.getText().toString()));
                itemToEdit.setNote(etNote.getText().toString());
                itemToEdit.setIsPurchased(cbPurchased.isChecked());
                ((ListApplication)getApplication()).getRealm().commitTransaction();

                Intent intentResult = new Intent();
                if (isEdit){
                    intentResult.putExtra(MainActivity.KEY_ITEM_ID, itemToEdit.getItemId());
                } else {
                    intentResult.putExtra(MainActivity.ITEM_NAME, etName.getText().toString());
                    intentResult.putExtra(MainActivity.ITEM_CATEGORY, categorySelected);
                    intentResult.putExtra(MainActivity.ITEM_PRICE, Double.parseDouble(etPrice.getText().toString()));
                    intentResult.putExtra(MainActivity.ITEM_NOTE, etNote.getText().toString());
                    intentResult.putExtra(MainActivity.ITEM_ISPURCHASED, cbPurchased.isChecked());

                }

                setResult(RESULT_OK, intentResult);
                finish();
            }
        }
        );
    }

}
