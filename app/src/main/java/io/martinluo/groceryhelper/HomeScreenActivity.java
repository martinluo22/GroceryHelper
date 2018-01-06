package io.martinluo.groceryhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HomeScreenActivity extends AppCompatActivity {

    private Context context;

    private boolean isRecurring = false;

    private EditText mItemInput;
    private Button recurringButton;

    private List<GroceryItem> mGroceryItemList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this.getApplicationContext();
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<GroceryItem>>(){}.getType();
        String json = prefs.getString(getString(R.string.saved_grocery_list), "");
        List<GroceryItem> tmpList = gson.fromJson(json, type);

        mGroceryItemList = new ArrayList<>();

        if (tmpList != null){
            mGroceryItemList.addAll(tmpList);
        }

        setUpListView();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_clear_list:
                mGroceryItemList.clear();
                mAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause(){
        super.onPause();

        SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(mGroceryItemList);

        editor.putString(getString(R.string.saved_grocery_list), json);
        editor.commit();
    }

    public void setupButtonListeners(){

        mItemInput = findViewById(R.id.item_editText);
        mItemInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addNewItem();
                    handled = true;
                }
                return handled;
            }
        });

        final ImageButton enterButton = (ImageButton) findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNewItem();
            }
        });

        recurringButton = (Button) findViewById(R.id.recurring_button);
        recurringButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                isRecurring = !isRecurring;
                if (isRecurring) {
                    recurringButton.setTextColor(ContextCompat.getColor(context, R.color.groceryGreenDark));
                }
                else{
                    recurringButton.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDarker));
                }
            }
        });


    }

    public void addNewItem(){
        String tmp = mItemInput.getText().toString();

        if (tmp.matches("")){
            mItemInput.setError("Empty Item");
        }

        GroceryItem groceryItem = new GroceryItem(tmp, isRecurring);
        mGroceryItemList.add(groceryItem);
        mAdapter.notifyItemInserted(mGroceryItemList.size() - 1);

        mItemInput.setText("");
    }

    public void setUpListView(){
        mRecyclerView = findViewById(R.id.main_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new GroceryListAdapter(context, mGroceryItemList);
        mRecyclerView.setAdapter(mAdapter);
    }

    //Remove focus from textbox when user touches outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                Rect recRect = new Rect();
                recurringButton.getHitRect(recRect);
                outRect.union(recRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }



}
