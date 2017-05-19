package com.example.testdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.baidu.mobads.AppActivity;
import com.baidu.mobads.BaiduManager;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.appName)
    TextView appName;
    @BindView(R.id.showCount)
    TextView showCount;
    @BindView(R.id.target)
    TextView target;
    @BindView(R.id.setTarget)
    Button setTarget;
    @BindView(R.id.cleanHuanCun)
    Button cleanHuanCun;
    @BindView(R.id.flash)
    Button flash;
    @BindView(R.id.fillBanner)
    Button fillBanner;
    @BindView(R.id.bannerView)
    LinearLayout bannerView;
    @BindView(R.id.ci1)
    EditText ci1;
    @BindView(R.id.ci2)
    EditText ci2;
    @BindView(R.id.ci3)
    EditText ci3;
    @BindView(R.id.suiji)
    Button suiji;
    @BindView(R.id.clickAD)
    Button clickAD;
    private AdView adView1;
    private AdView adView2;

    private String TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // 广告展现前先调用sdk初始化方法，可以有效缩短广告第一次展现所需时间
        BaiduManager.init(this);
        initData();
        initEvent();
    }

    private void initEvent() {
        fillBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        suiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击随机词", Toast.LENGTH_SHORT).show();
            }
        });
        clickAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击广告", Toast.LENGTH_SHORT).show();
            }
        });
        adView1.setListener(new AdViewListener() {
            @Override
            public void onAdReady(AdView adView) {
                Log.i(TAG,"adView1 onAdReady");
            }

            @Override
            public void onAdShow(JSONObject jsonObject) {
                Log.i(TAG,"adView1 onAdShow");
            }

            @Override
            public void onAdClick(JSONObject jsonObject) {
                Log.i(TAG,"adView1 onAdClick");
            }

            @Override
            public void onAdFailed(String s) {
                Log.i(TAG,"adView1 onAdFailed，reason："+s);
            }

            @Override
            public void onAdSwitch() {
                Log.i(TAG,"adView1 onAdSwitch");
            }

            @Override
            public void onAdClose(JSONObject jsonObject) {
                Log.i(TAG,"adView1 onAdClose");
            }
        });
        adView2.setListener(new AdViewListener() {
            @Override
            public void onAdReady(AdView adView) {
                Log.i(TAG,"adView2 onAdReady");
            }

            @Override
            public void onAdShow(JSONObject jsonObject) {
                Log.i(TAG,"adView2 onAdShow");
            }

            @Override
            public void onAdClick(JSONObject jsonObject) {
                Log.i(TAG,"adView2 onAdClick");
            }

            @Override
            public void onAdFailed(String s) {
                Log.i(TAG,"adView2 ondFailed，reason："+s);
            }

            @Override
            public void onAdSwitch() {
                Log.i(TAG,"adView2 onAdSwitch");
            }

            @Override
            public void onAdClose(JSONObject jsonObject) {
                Log.i(TAG,"adView2 onAdClose");
            }
        });

    }

    private void initData() {
        // 设置'广告着陆页'动作栏的颜色主题
        AppActivity.setActionBarColorTheme(AppActivity.ActionBarColorTheme.ACTION_BAR_WHITE_THEME);

        // 创建广告View
        String adPlaceId1 = "2015351"; //  重要：请填上您的广告位ID，代码位错误会导致无法请求到广告
        adView1 = new AdView(this, adPlaceId1);

        String adPlaceId2 = "4108486";
        adView2 = new AdView(this, adPlaceId2);

        bannerView.addView(adView1);
//        bannerView.addView(adView2,1);
    }

    @Override
    protected void onDestroy() {
        adView1.destroy();
        adView2.destroy();
        // 通过BaiduXAdSDKContext.exit()来告知AdSDK，以便AdSDK能够释放资源.
        com.baidu.mobads.production.BaiduXAdSDKContext.exit();
        super.onDestroy();
    }
}
