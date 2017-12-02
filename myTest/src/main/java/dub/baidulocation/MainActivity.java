package dub.baidulocation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView locations = (TextView) findViewById(R.id.locations);
        locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLocation();
            }
        });

    }

    private void isLocation() {
        MyApplicaton.mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                if(null != bdLocation){
                    Toast.makeText(MainActivity.this,"定位地址是："+bdLocation.getCity()+
                            ",时间是:"+bdLocation.getTime()+","+
                            ",详情地址是:"+bdLocation.getAddrStr()+",",
                            Toast.LENGTH_SHORT).show();
                    if(MyApplicaton.mLocationClient.isStarted()){
                        MyApplicaton.mLocationClient.stop();
                        MyApplicaton.mLocationClient.unRegisterLocationListener(this);
                    }
                    double curLongitude = bdLocation.getLongitude();
                    double curLatitude = bdLocation.getLatitude();
                }else{
                    Toast.makeText(MainActivity.this,"定位失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {

            }
        });
        MyApplicaton.mLocationClient.start();
    }
}
