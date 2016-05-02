package hussein.com.swype.app;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import hussein.com.swype.R;

public class SettingsActivity extends AppCompatActivity {
    protected TextView txt_gallery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sett);


        TextView t = (TextView) findViewById(R.id.advancedsettings_txt);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/LANENAR_.ttf");
        t.setTypeface(customFont);

        TextView textLane = (TextView) findViewById(R.id.security_txt);
        textLane.setTypeface(customFont);
        TextView tLane = (TextView) findViewById(R.id.notifications_txt);
        tLane.setTypeface(customFont);
        TextView textL = (TextView) findViewById(R.id.themes_txt);
        textL.setTypeface(customFont);
        TextView tL = (TextView) findViewById(R.id.intruders_txt);
        tL.setTypeface(customFont);
        txt_gallery = (TextView) findViewById(R.id.galley_txt);
        txt_gallery.setTypeface(customFont);

        /*
        fetch id for button configure images and launch this activity when clicked on
         */

        ImageButton button = (ImageButton) findViewById(R.id.gallerybtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SettingsActivity.this, Gallery.class);
                startActivity(i);
            }
        });








        ImageButton securitybtn=(ImageButton)findViewById(R.id.security_btn);
        securitybtn.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                Intent i=new Intent(SettingsActivity.this, SecurityActivity.class);
                startActivity(i);
            }
        });

    }




}







