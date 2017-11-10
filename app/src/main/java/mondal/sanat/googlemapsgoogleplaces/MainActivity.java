package mondal.sanat.googlemapsgoogleplaces;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 901;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServicesOK()){
            init();
        }
    }

    private void init(){
        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
    
    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google play serives version");
        int avaiable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(avaiable == ConnectionResult.SUCCESS){
            // Everything is fine and the user can make map request
            Log.d(TAG, "isServicesOK: Google play service is working");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(avaiable)){
            // An error occure but we can resolved it
            Log.d(TAG, "isServicesOK: an error occure but we can fix this");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, avaiable, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else {
            Toast.makeText(this, "You cant make map request", Toast.LENGTH_LONG).show();
        }
        return false;
    }
}
