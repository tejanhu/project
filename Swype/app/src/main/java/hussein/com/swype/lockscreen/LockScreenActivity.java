package hussein.com.swype.lockscreen;


import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.wenchao.cardstack.CardStack;
import java.util.List;
import hussein.com.swype.R;
import hussein.com.swype.db.DBHandler;
import hussein.com.swype.pojo.SwypeImage;


 /*
  The swipe feature was availabe from https://github.com/wenchaojiang/AndroidSwipeableCardStack
 */

/*
  The lock feature was availabe from https://github.com/thomasvidas/Simple-Lockscreen
 */



public class LockScreenActivity extends Activity {

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this,LockscreenService.class));
        //Set up our Lockscreen
        makeFullScreen();
        setupCardUI();
        //onAttachedToWindow();
    }

    private void setupCardUI() {
        dbHandler = new DBHandler(this);
        CardStack mCardStack = (CardStack)findViewById(R.id.container);
        CardsDataAdapter mCardAdapter = new CardsDataAdapter(getApplicationContext(),0);
        mCardStack.setAdapter(mCardAdapter);
        mCardStack.setContentResource(R.layout.card_layout);
        mCardStack.setStackMargin(20);

        //Adding images
        List<SwypeImage> images = dbHandler.getAllswypeImages();
        for(SwypeImage swypeImage : images) {
            mCardAdapter.add(swypeImage.getImageUri().toString());
        }
        mCardStack.setListener(new CardsListener(this, images, mCardStack));
    }

    /**
     * A simple method that sets the screen to fullscreen.  It removes the Notifications bar,
     *   the Actionbar and the virtual keys (if they are on the phone)
     */
    public void makeFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } else {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }

    public void unlockScreen(View view) {
        //Instead of using finish(), this totally destroys the process
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_HOME)) {
            System.out.println("KEYCODE_HOME");
            return true;
        }
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("KEYCODE_BACK");
            return true;
        }
        if ((keyCode == KeyEvent.KEYCODE_MENU)) {
            System.out.println("KEYCODE_MENU");
            return true;
        }
        return false;
    }
//
//    //Now we can’t disable Home Button by handling Key Event . So we should also use some extra code  using onAttachedWindow() method.
//
//    public void onAttachedToWindow() {
//        // TODO Auto-generated method stub
//        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG|WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        super.onAttachedToWindow();
//    }


}