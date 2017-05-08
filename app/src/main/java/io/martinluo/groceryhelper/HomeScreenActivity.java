package io.martinluo.groceryhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {

    private static boolean isItemRecurring = false;

    private List<GroceryItem> groceryItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        setupButtonListeners();

        if (groceryItemList == null){
            groceryItemList = new LinkedList<>();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    public void onGroupItemClick(MenuItem item) {
        // One of the group items (using the onClick attribute) was clicked
        // The item parameter passed here indicates which item it is
        // All other menu item clicks are handled by onOptionsItemSelected()
    }

    public void setupButtonListeners(){

        final EditText editText = (EditText) findViewById(R.id.item_editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addNewItem(isItemRecurring);
                    editText.clearFocus();
                    handled = true;
                }
                return handled;
            }
        });

        final ImageButton enterButton = (ImageButton) findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNewItem(isItemRecurring);
            }
        });

        final Button recurringButton = (Button) findViewById(R.id.recurring_button);
        recurringButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                isItemRecurring = !isItemRecurring;

                /*
                for(int i=0;i<groceryItemList.size();i++){
                    Log.d("list item" ,groceryItemList.get(i).getItem().toString());
                }
                */

                return;
            }
        });

    }

    public void addNewItem(Boolean isRecurring){

        EditText editText = (EditText)findViewById(R.id.item_editText);
        String tmp = editText.getText().toString();

        if (tmp.matches("")){
            editText.setError("Empty Item");
        }

        GroceryItem groceryItem = new GroceryItem(tmp, isRecurring);
        groceryItemList.add(groceryItem);



        editText.setText("");
    }




}
