package io.martinluo.groceryhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class HomeScreenActivity extends AppCompatActivity {

    private static boolean isItemRecurring = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        setupButtonListeners();


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

        final EditText editText = (EditText) findViewById(R.id.newItem);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {

                    addNewItem(isItemRecurring);

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
                return;
            }
        });

    }

    public void addNewItem(Boolean isRecurring){

    }



}
