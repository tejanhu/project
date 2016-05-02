package hussein.com.swype.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.List;
import hussein.com.swype.pojo.SwypeImage;
import hussein.com.swype.utils.SwypeImageUtils;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<SwypeImage> mThumbList;

    public ImageAdapter(Context c, List<SwypeImage> imageList) {
        mContext = c;
        mThumbList = imageList;
    }
//return size of swipe images selected
    public int getCount() {
        return mThumbList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //modifies the colour of the image hinting if liked or disliked based on colour
            SwypeImageUtils.setTone(imageView, mThumbList.get(position).getStatus());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 500);
            imageView.setLayoutParams(layoutParams);

            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageURI(mThumbList.get(position).getImageUri());
        return imageView;
    }


}