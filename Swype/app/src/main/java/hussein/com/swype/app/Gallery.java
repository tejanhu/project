package hussein.com.swype.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hussein.com.swype.R;
import hussein.com.swype.db.DBHandler;
import hussein.com.swype.pojo.SwypeImage;
import hussein.com.swype.utils.SwypeImageUtils;
import nl.changer.polypicker.Config;
import nl.changer.polypicker.ImagePickerActivity;

public class Gallery extends AppCompatActivity {

    /*
        The gallery image picking feature was availabe from https://github.com/jaydeepw/poly-picker
    */


    public static final int GET_IMAGES = 1;

    private static final String TAG = "Gallery";

    private DBHandler dbHandler;

    private List<SwypeImage> imageList = new ArrayList<>();

    private ImageAdapter imageAdapter;

    private Context mContext = this;

    Button moreInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_images);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHandler = new DBHandler(this);
        final String displayMIText="How to dislike: "+"\n"+"HOLD image until it appears red."+"\n"+"\n"+"How to like: "+"\n"+"PRESS image until it appears green.";
        final String posBtntxt="OK";


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupSelectButton();
        //Try and get images from database
        imageList = dbHandler.getAllswypeImages();
        setupGridView();
        setupDoneButton();
        setupCancelButton();

        moreInformation = (Button) findViewById(R.id.moreinfo);


        moreInformation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                initiateMoreInfoAlert(displayMIText,posBtntxt);

            }
        });



    }




    private void setupCancelButton() {
//        Button cancelButton = (Button) findViewById(R.id.cancelSave);

    }

    private void setupDoneButton() {
        Button doneButton = (Button) findViewById(R.id.doneSave);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Added images to Database, closing this window now");
                dbHandler.cleanAllSwypeImages();
                dbHandler.addSwypeImages(imageList);
                Toast.makeText(Gallery.this, "Saved to database",Toast.LENGTH_SHORT).show();
                ((Activity)mContext).finish();
            }
        });
    }

    private void setupSelectButton() {
        Button button = (Button) findViewById(R.id.imageSelect);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ImagePickerActivity.class);
                Config config = new Config.Builder()
                        .setTabBackgroundColor(R.color.white)    // set tab background color. Default white.
                        .setTabSelectionIndicatorColor(R.color.blue)
                        .setCameraButtonColor(R.color.green)
                        .setSelectionLimit(30)    // set photo selection limit. Default unlimited selection.
                        .build();
                ImagePickerActivity.setConfig(config);
                startActivityForResult(intent, GET_IMAGES);
            }
        });
    }

    private void setupGridView() {
        GridView gridview = (GridView) findViewById(R.id.imageGrid);
        imageAdapter = new ImageAdapter(this, imageList);
        gridview.setAdapter(imageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d(TAG, "Upvote this image " + position);
                SwypeImageUtils.setTone((ImageView)v,true);
                imageList.get(position).setStatus(true);
            }
        });

        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d(TAG, "Downvote this image " + position);
                SwypeImageUtils.setTone((ImageView) v, false);
                imageList.get(position).setStatus(false);
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent returnIntent) {
        // If the selection didn't work
        if (resultCode != RESULT_OK) {
            // Exit without doing anything else
            Log.e(TAG, "There was an error while getting images, ErrorCode: " + resultCode);
        } else {
            // Get the file's content URI from the incoming Intent
            switch(requestCode){
                case GET_IMAGES:
                    resolveAndSaveImages(returnIntent);
                    break;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void resolveAndSaveImages(Intent returnIntent) {
        imageList = new ArrayList<>();
        Parcelable[] parcelableUris = returnIntent.getParcelableArrayExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
        if (parcelableUris == null) {
            return;
        }

        // Java doesn't allow array casting, this is a little hack
        Uri[] uris = new Uri[parcelableUris.length];
        System.arraycopy(parcelableUris, 0, uris, 0, parcelableUris.length);
        for (Uri uri : uris) {
            imageList.add(new SwypeImage(uri));
        }
        //Invalidating the adapter data to refresh
        setupGridView();

    }


    /*
    display pop up window that is lanuched to inform user more information on how to like or dislike images in configure images activity

    */

    private void initiateMoreInfoAlert(String displayMIText,
                                   String posBtntxt) {
        AlertDialog popupWin = new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage(displayMIText)
                .setPositiveButton(posBtntxt, null)
                .show();


        WindowManager.LayoutParams lParams = popupWin.getWindow().getAttributes();
        lParams.dimAmount = 0.2f;
        popupWin.getWindow().setAttributes(lParams);
        popupWin.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    }





}
