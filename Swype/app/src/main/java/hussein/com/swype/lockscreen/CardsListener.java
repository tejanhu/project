package hussein.com.swype.lockscreen;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;
import android.media.MediaPlayer.OnCompletionListener;
import com.wenchao.cardstack.CardStack;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import hussein.com.swype.R;
import hussein.com.swype.pojo.SwypeImage;



public class CardsListener implements CardStack.CardEventListener {



    MediaPlayer someErrorSound;
    MediaPlayer someHappySound;
    MediaPlayer someSwipingSound;


    private boolean mSound;



    public static final String TAG = "CardEventListener";

    List<SwypeImage> imageList;
    Map<Integer, Boolean> userPattern = new HashMap<>();
    Map<Integer, Boolean> originalPattern = new HashMap<>();
    CardStack cardStack;
    Context mContext;

    public CardsListener(){
        super();
    }

    public CardsListener(Context mContext, List<SwypeImage> imageList, CardStack cardStack) {
        super();
        //error sound if swipe pattern is incorrect
        someErrorSound = MediaPlayer.create(mContext, R.raw.error);
        //happy sound if swipe pattern is correct
        someHappySound = MediaPlayer.create(mContext,R.raw.tada);
        //swipe sound while swiping
        someSwipingSound = MediaPlayer.create(mContext,R.raw.swipe_001);

        this.imageList = imageList;
        //iterate through images in configures activity and store in hashmap at the specified position with like status
        for (int i = 1; i <= imageList.size() ; i++) {
            originalPattern.put(i, imageList.get(i-1).getStatus());
        }
        this.cardStack = cardStack;
        this.mContext = mContext;
    }

    //implement card event interface
    @Override
    public boolean swipeEnd(int direction, float distance) {
        //if "return true" the dismiss animation will be triggered
        //if false, the card will move back to stack
        //distance is finger swipe distance in dp

        //the direction indicate swipe direction
        //there are four directions
        //  0  |  1
        // ----------
        //  2  |  3


        return (distance>300)? true : false;
    }
//returns boolean relates to whether swipe has started or not
    @Override
    public boolean swipeStart(int direction, float distance) {

        return true;
    }
//returns boolean relates to whether it is continuing to swipe or not
    @Override
    public boolean swipeContinue(int direction, float distanceX, float distanceY) {
        //play some swipe sound whilst swiping cards
        someSwipingSound.start();
        return true;
    }



    @Override
    public void discarded(int id, int direction) {
        //add position and return a boolean evaluating if the direction equals the right side
        userPattern.put(id, direction == 1 || direction == 3);
        //check if we have reached the last card
        if(id == imageList.size()) {
            //then check if the pattern matched, if yes, then unlock the screen, else reset all cards back to normailty
            if(userPattern.equals(originalPattern)) {
                //someHappySound.start();
                //Unlock here
                Log.d(TAG, "Everything is okay, unlock the screen now");
                //((LockScreenActivity)mContext).unlockScreen(null);
                //isSoundPlaying(someHappySound);

                playHappySound();
                processHappySoundToast(mContext, mSound);
                //allowtoUnlock();
//                play happy sound in shorter length
                //((LockScreenActivity)mContext).unlockScreen(null);
            } else {

                Log.d(TAG, "Things are bad, reset thing");
//                play error sound if wrong pattern
                //Toast someToast=Toast.makeText(mContext,"Wrong pattern, try again",Toast.LENGTH_SHORT);
                //Toast someotherToast=someToast;
//                if(someToast==someotherToast){
//                    someErrorSound.start();
//                    someToast.show();
//
//                }
                playErrorSound();
                processErrorToast(mContext, mSound);
                cardStack.reset(true);
                userPattern = new HashMap<>();
            }

        }
        //this callback invoked when dismiss animation is finished.
    }

    public boolean isSoundPlaying(MediaPlayer someHappySound){
        boolean happySoundPlayed=false;
        if(someHappySound.isPlaying()){
            happySoundPlayed=true;
            //((LockScreenActivity)mContext).unlockScreen(null);
        }
       return happySoundPlayed;
    }
//
//    public void allowtoUnlock(){
//        ((LockScreenActivity)mContext).unlockScreen(null);
//    }

    public void playHappySound() {
        mSound =true;
        if(mSound) {
            someHappySound.start();
            Toast someHappyToast=Toast.makeText(mContext, "Unlocked Successully.", Toast.LENGTH_SHORT);
            //((LockScreenActivity)mContext).unlockScreen(null);
            someHappyToast.show();

        }
    }

    public void processHappySoundToast(Context context, boolean isSound) {
        mSound = isSound;
        someHappySound.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer someHappySound) {
                //no longer need  to stop sound since lockscreen breaks out activity
                //someHappySound.release();
                playHappySound();
                isSoundPlaying(someHappySound);
                someHappySound.release();
                ((LockScreenActivity)mContext).unlockScreen(null);
//                allowtoUnlock();
                //((LockScreenActivity) mContext).unlockScreen(null);

//                int count=1;
//                if(count==1) {
                    //((LockScreenActivity) mContext).unlockScreen(null);
//                }
            }
        });
    }


    public void playErrorSound() {
        mSound =true;
        if(mSound) {
            someErrorSound.start();
//            Toast someErrorToast=Toast.makeText(mContext, "Wrong pattern, try again", Toast.LENGTH_SHORT);
//            someErrorToast.show();
        }
    }





    public void processErrorToast(Context context, boolean isSound) {

        mSound = isSound;
        someErrorSound.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer someErrorSound) {
                //no longer need  to stop sound since lockscreen breaks out activity
                //someErrorSound.release();
                Toast.makeText(mContext,"Wrong pattern, try again",Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void topCardTapped() {
        //this callback invoked when a top card is tapped by user.
    }
}
