package hussein.com.swype.lockscreen;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import hussein.com.swype.R;

/**
 *
 */
public class LockscreenUtils {

    // Member variables
    private OverlayDialog mOverlayDialog;
    private OnLockStatusChangedListener mLockStatusChangedListener;

    // Interface to communicate with owner activity
    public interface OnLockStatusChangedListener
    {
        public void onLockStatusChanged(boolean isLocked);
    }

    // Reset the variables
    public LockscreenUtils() {
        reset();
    }

    // Display overlay dialog with a view to prevent home button click
    public void lock(Activity activity) {
        if (mOverlayDialog == null) {
            mOverlayDialog = new OverlayDialog(activity);
            mOverlayDialog.show();
            mLockStatusChangedListener = (OnLockStatusChangedListener) activity;
        }
    }

    // Reset variables
    public void reset() {
        if (mOverlayDialog != null) {
            mOverlayDialog.dismiss();
            mOverlayDialog = null;
        }
    }

    // Unlock the home button and give callback to unlock the screen
    public void unlock() {
        if (mOverlayDialog != null) {
            mOverlayDialog.dismiss();
            mOverlayDialog = null;
            if(mLockStatusChangedListener!=null)
            {
                mLockStatusChangedListener.onLockStatusChanged(false);
            }
        }
    }

    // Create overlay dialog for lockedscreen to disable hardware buttons
    private static class OverlayDialog extends AlertDialog {

        public OverlayDialog(Activity activity) {
            super(activity, R.style.OverlayDialog);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setOwnerActivity(activity);
            setCancelable(false);
        }

        // consume touch events
        public final boolean dispatchTouchEvent(MotionEvent motionevent) {
            return true;
        }

    }
}
