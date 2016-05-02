package hussein.com.swype.utils;

import android.graphics.Color;
import android.widget.ImageView;

/**
 *
 */
public class SwypeImageUtils {

    public static final int RED_SCREEN = Color.argb(100, 255, 0, 0);
    public static final int GREEN_SCREEN = Color.argb(100, 0, 255, 0);

    public static void setTone(ImageView imageView, Boolean liked) {
        imageView.setColorFilter(liked ? GREEN_SCREEN : RED_SCREEN);
    }
}
