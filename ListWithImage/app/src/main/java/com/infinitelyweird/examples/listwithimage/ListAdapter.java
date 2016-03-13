package com.infinitelyweird.examples.listwithimage;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by dustinflegel on 3/12/16.
 */
public class ListAdapter extends BaseAdapter {

    // We have an array list that stores multiple
    // instances of the ImageAndText class that
    // is defined near the bottom of this class.
    ArrayList<ImageAndTextItem> list;

    // Create a reference to the calling
    // activity.
    Activity activity;

    public ListAdapter(Activity activity, ArrayList<ImageAndTextItem> list)
    {
        super();                    // Call Super to finish out the initialization.
        this.activity = activity;   // Assign the passed activity to the local activity object.
        this.list = list;           // Assign the passed list object to the local list object.
    }

    /*
    Note: this part of the class is VERY important to get correct, because this is the part that
    will actually inflate the text and image view we're using as a list item,
    and it will also get the text and image path that should go into this list item.
 */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // It's time to inflate our view.  Let's do that with the following steps:

        // 1. Specify an instance of the ViewHolder class (from below...)
        ViewHolder holder;

        // 2. Specify our layout inflater by getting it from the activity
        //    we passed in earlier.
        LayoutInflater inflater = activity.getLayoutInflater();

        // 3. Check if convertView was specified in this method call.  Initially
        //    this value is going to be null, so we'll have to inflate our objects
        if (convertView == null)
        {
            // 4. inflate an instance of our single_column_list, which is specified
            //    in the list_item_with_image.xml file found in the res > layout group
            //    in our project.
            convertView = inflater.inflate(R.layout.list_item_with_image, null);

            // 5. Create a new instance of the ViewHolder class specified below.
            //    because we're going to need to reference our TextView for the
            //    row being created.
            holder = new ViewHolder();

            // 6. Now, we're going to assign the listItemText object found in
            //    list_item_with_image.xml to the local class variable for reference
            //    later.
            holder.listItemText = (TextView)convertView.findViewById(R.id.listItemText);

            // 7. Let's go ahead and find the image view in our list_item_with_image.xml
            //    and assign the listItemImage object for reference later.
            holder.listItemImage = (ImageView)convertView.findViewById(R.id.listItemImage);

            // 8. We will now go ahead and set the "tag" of our "holder" class object
            //    in case we need to recall it later.
            convertView.setTag(holder);
        } else {
            // 9. Since convertView wasn't null this time around
            //    we'll assign the object we stored in the "tag" of
            //    convertView to the object 'holder', since we don't
            //    have to re-inflate it or reinstantiate it.
            holder = (ViewHolder) convertView.getTag();
        }

        // 10.   This is where we assign the HashMap to the list object.
        //      let's get the string value stored in our list
        //      and set the text property of the textField
        //      in the object "holder"
        ImageAndTextItem listObjectValue = list.get(position);

        // 11.  Assign the String value to the textField.
        holder.listItemText.setText(listObjectValue.listItemText);

        // 12.  Assign the image resource id to the ImageView with the following steps:
        //  12a. Get the Resources for the android app from the context of the 'convertView' object.
        Resources res = convertView.getContext().getResources();
        //  12b. Create a bitmap object from the resource id found in the listObjectValue item
        //       that references the ImageAndTextItem class.
        Bitmap imageToDisplay = BitmapFactory.decodeResource(res, listObjectValue.imageResourceId);
        //  12c. Set the image bitmap that we just made.
        holder.listItemImage.setImageBitmap(imageToDisplay);
        // 13. Finally, we'll return the either newly instantiated convertView object
        //     or our reused convertView
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;  // Not required for this example, but I'll address this in a later code sample.
    }


    // Create a view holder subclass.
    private class ViewHolder {
        TextView listItemText;     // Specify the list item text view

        ImageView listItemImage;   // Specify the list item image view
    }
}
