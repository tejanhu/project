package hussein.com.swype.lockscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 *
 */
public class LockscreenIntentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        //If the screen was just turned on or it just booted up, start your Lock Activity
        if(action.equals(Intent.ACTION_SCREEN_OFF) || action.equals(Intent.ACTION_BOOT_COMPLETED))
        {
            //launch lockscreen if either of the two actions above happen
            Intent i = new Intent(context, LockScreenActivity.class);
            //cycle through another activity (new task) in future
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //commence activity
            context.startActivity(i);
        }
    }
}