package hussein.com.swype.lockscreen;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import hussein.com.swype.R;


/*
 The swipe feature was availabe from https://github.com/wenchaojiang/AndroidSwipeableCardStack
*/

public class CardsDataAdapter extends ArrayAdapter<String> {

    public CardsDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){
        //supply the layout for your card
        ImageView v = (ImageView) (contentView.findViewById(R.id.content));
        v.setImageURI(Uri.parse(getItem(position)));
        return contentView;
    }

}