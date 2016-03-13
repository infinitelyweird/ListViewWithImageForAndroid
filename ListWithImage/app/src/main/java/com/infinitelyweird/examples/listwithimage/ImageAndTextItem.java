package com.infinitelyweird.examples.listwithimage;

/**
 * This method will store the image resource id and the text to display in the
 * list view.
 */
public class ImageAndTextItem {
    public int imageResourceId;
    public String listItemText;

    public ImageAndTextItem(int imageResourceId, String listItemText)
    {
        this.imageResourceId = imageResourceId;
        this.listItemText = listItemText;
    }

}
