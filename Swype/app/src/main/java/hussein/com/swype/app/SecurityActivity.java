package hussein.com.swype.app;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import hussein.com.swype.R;
import hussein.com.swype.lockscreen.LockScreenActivity;
import hussein.com.swype.lockscreen.LockScreenActivity2;


public class SecurityActivity extends AppCompatActivity {

    boolean pressed=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String displayInfo="Please, select one of lockscreen methods.";
        final String posBtntxt="OK";
        initiateMoreInfoAlert(displayInfo, posBtntxt);




        final Button disable_enable_SwipemethodBtn=(Button)findViewById(R.id.disable_enable_Swipebtn);
        Typeface buttoncustomFont=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Thin.ttf");
        disable_enable_SwipemethodBtn.setTypeface(buttoncustomFont);


        TextView password_text=(TextView)findViewById(R.id.text_Password);
        password_text.setTypeface(buttoncustomFont);


        ImageButton backtoSettings=(ImageButton)findViewById(R.id.back_button);
        backtoSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecurityActivity.this, SettingsActivity.class));
            }
        });




        ImageButton swipe_method_one=(ImageButton)findViewById(R.id.select_swipe_method1);
        swipe_method_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pressed = true;
                        if (pressed) {

                             Toast.makeText(SecurityActivity.this, "Swipe Up Down successfully selected",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(SecurityActivity.this, LockScreenActivity2.class);
                            startActivity(i);

                        }

                    }
                });

        ImageButton swipe_method_two=(ImageButton)findViewById(R.id.select_swipe_method2);
        swipe_method_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed = true;
                if (pressed) {

                    Toast.makeText(SecurityActivity.this, "Swipe Left Right successfully selected", Toast.LENGTH_SHORT).show();
                    Intent j=new Intent(SecurityActivity.this, LockScreenActivity.class);
                    startActivity(j);
                    //disable_enableBtn.setVisibility(View.GONE);
                    //disable_enableBtn.setVisibility(View.INVISIBLE);
                    //enableSwipeBtn.setVisibility(View.VISIBLE);

                }
            }
        });


    }


    private void initiateMoreInfoAlert(String displayInfo,
                                       String posBtntxt) {
        AlertDialog popupWin = new AlertDialog.Builder(this)
                .setTitle("Information")
                .setMessage(displayInfo)
                .setPositiveButton(posBtntxt, null)
                .show();


        WindowManager.LayoutParams lParams = popupWin.getWindow().getAttributes();
        lParams.dimAmount = 0.2f;
        popupWin.getWindow().setAttributes(lParams);
        popupWin.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    }







}


