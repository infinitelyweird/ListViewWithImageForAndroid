package com.infinitelyweird.examples.listwithimage;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*

        BEFORE YOU ADD THIS CODE, MAKE SURE YOU HAVE A ListAdapter created
        AND A LAYOUT FOR YOUR ROW.

        (see ListAdapter class and list_item_with_image.xml)
     */


    // The first step to populating a list item
    // is to create an object to store data that
    // will be use for each row in the list.
    private ArrayList<ImageAndTextItem> listViewItems;

    // Next, we'll want to create a reference to
    // our List Adapter.  We'll pass our current
    // activity as 'this' and the object we just
    // created to store our items called 'listViewItems'
    private ListAdapter listAdapter = new ListAdapter(this, listViewItems);

    // We want this available throughout this class
    // so we'll declare our myBasicListView object
    // here.
    private ListView myImageListView;

    private void setupListViewItems()
    {
        listViewItems.add(new ImageAndTextItem(R.drawable.image1, "Item 1"));
        listViewItems.add(new ImageAndTextItem(R.drawable.image2, "Item 2"));
        listViewItems.add(new ImageAndTextItem(R.drawable.image3, "Item 3"));
        listViewItems.add(new ImageAndTextItem(R.drawable.image4, "Item 4"));
        listViewItems.add(new ImageAndTextItem(R.drawable.image5, "Item 5"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewItems = new ArrayList<ImageAndTextItem>();

        setupListViewItems();

        // Now that we've got 5 records to display, let's take our ArrayList
        // and assign it to the adapter class that we prepared an ArrayList for.
        listAdapter.list = listViewItems;

        // Let's find our myBasicListView item and set the variable we declared above
        // so we can populate the data later.
        myImageListView = (ListView)findViewById(R.id.listView);

        // Next, we'll assign our ListAdapter class ('listAdapter') to
        // our myBasicListView adapter property.
        myImageListView.setAdapter(listAdapter);

        // Now, how do we handle 'touch' events, you say?  Well, surprisingly
        // it's not a touch event, but it's an OnItemClick event.
        // You don't have to memorize this code, but you should note a quick
        // shortcut where you can type new OnClickLi and then pressing Tab on your keyboard
        // should generate the @Override event.
        myImageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // So, how do we know what the data is on the item we just "clicked"?  We'll
                // take the "position" variable, and find the item in our ArrayList.

                // To keep things simple, I'm just going to print the value out to the
                // debug window.
                System.out.println(listViewItems.get(position));

                // THE BELOW LINE IS OPTIONAL AND JUST FOR FUN!
                // For fun, we'll use Snackbar to display a message at the bottom
                // of the screen with the item you selected.
                ImageAndTextItem imageAndTextItem = listViewItems.get(position);
                Snackbar.make(view, imageAndTextItem.listItemText, Snackbar.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
