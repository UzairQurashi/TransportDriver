package com.driver.travel.driverapp;
/**
 * Created by Uzair Qureshi on 4/2/2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.driver.travel.driverapp.delay.job.LocationSyncJob;
import com.driver.travel.driverapp.extras.PrefsManager;
import com.driver.travel.driverapp.extras.ProgressLoader;
import com.google.firebase.auth.FirebaseAuth;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    protected ViewDataBinding parentBinding;
    protected boolean animationNeeded;
    protected boolean forwardTransition;
    public PrefsManager sharedpreference;
    private ProgressLoader progressLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreference =new PrefsManager(this);
        animationNeeded=true;
        forwardTransition=true;

    }
    //========================================Helper Methods =========================================//
    /**
     * @usage it opens the activity receives in parameter
     * @param activity
     */
    public void openActivity(Class activity)
    {
        startActivity(new Intent(this,activity));
    }

    /**
     * @usage it opens the activity receives in parameter and finish  the current activity running
     * @param activity
     */
    public void openActivityWithFinish(Class activity)
    {
        startActivity(new Intent(this,activity));
        this.finish();
    }

    /**
     * @usage it opens the activity with provide intent and finish  the current activity running
     * @param intent
     */
    public void openActivityWithFinish(Intent intent)
    {
        startActivity(intent);
        finish();
    }

    /**
     * @usage It opens the activity with the provided bundle as a parameter
     * @param activity
     * @param bundle
     */
    public void openActivity(Class activity, Bundle bundle)
    {
        startActivity(new Intent(this,activity).putExtras(bundle));
    }

    /**
     * @usage It opens the activity for result with the provided bundle as a parameter
     * @param activity
     * @param bundle
     */
    public void openActivityForResults(Class activity, Bundle bundle, int requestCode)
    {
        startActivityForResult(new Intent(this,activity).putExtras(bundle), requestCode);
    }


    /**
     * @usage It opens the activity for result
     * @param activity
     */
    public void openActivityForResults(Class activity, int requestCode)
    {
        startActivityForResult(new Intent(this,activity), requestCode);
    }

    protected void finishActivitywithResults(String key,Bundle bundle){
        Intent returnIntent = new Intent();
//                        returnIntent.putExtra("result",result);
        returnIntent.putExtra(key,bundle);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();


    }
    public View getView()
    {
        if (parentBinding!=null)
            return parentBinding.getRoot();
        else return null;

    }

    /**
     * @usage It use to show any message provided by the caller
     * @param view
     * @param message
     */
    public void showMessage(View view, String message)
    {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * the below two methods will show and hide progress loader
     */
    public void showProgress()
    {
        try {
            if (progressLoader == null)
            {
                progressLoader = new ProgressLoader();
            }

            progressLoader.show(getSupportFragmentManager(),TAG);
        }
        catch (IllegalStateException e)
        {
            Log.e(TAG, "showProgress:" + e.getMessage());
        }

    }

    public void hideProgress() {
        if (progressLoader != null) {
            try {
                progressLoader.dismissAllowingStateLoss();
            } catch (Exception e) {
                Log.e(TAG, "hideProgress:" + e.getMessage());
            }
        }
    }



    /**
     * this method will  hide visiblity or unhide  visiblity and  of given view
     * @param isVisible
     * @param view
     */
    protected void visibleView(boolean isVisible, View view) {

        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);

    }

    /**
     * this method will enable or disbale of given view
     * @param isEnable
     * @param view
     */
    protected void enableView(boolean isEnable,View view){
        view.setEnabled(isEnable);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.signout:
                forwardTransition=false;
                FirebaseAuth.getInstance().signOut();
                LocationSyncJob.cancleJob();
                Intent intent = new Intent(this,LoginScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                openActivityWithFinish(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }





    @Override
    protected void onPause() {
        if (animationNeeded) {
            if (forwardTransition)
                overridePendingTransition(R.anim.slide_in_right_activity, R.anim.slide_out_left_activity);

            else  overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);



        }
        super.onPause();
    }



    @Override
    public void onBackPressed() {
        forwardTransition=false;
        super.onBackPressed();
    }



}
