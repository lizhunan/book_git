package com.sdjy.book.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.Projection;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlay;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.sdjy.book.R;
import com.sdjy.book.app.BaseFragment;
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.app.Constant;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends BaseFragment implements AMapLocationListener {

    @SuppressLint("StaticFieldLeak")
    private static MapFragment INSTANCE;
    private TextureMapView mapView;
    private AMap aMap;
    private Bundle savedInstanceState;
    private MyLocationStyle myLocationStyle;
    private AMapLocationClient mapLocationClient;
    private AMapLocationClientOption aMapLocationClientOption;

    Marker marker = null;

    public MapFragment() {

    }

    public static MapFragment newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MapFragment();
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        myLocationStyle = new MyLocationStyle();
        mapLocationClient = new AMapLocationClient(BookApplication.getContext());
        aMapLocationClientOption = new AMapLocationClientOption();
        MapsInitializer.sdcardDir = Constant.MAP_CACHE;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mapLocationClient.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /*
        * 在这里进行更新数据
        * fragment显示时刷新数据
        * */
        if (hidden) {
            //fragment隐藏时调用
        } else {
            //fragment显示时调用
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_map;
    }

    @Override
    protected void initView(View view) {
        mapView = $(view, R.id.map);
        mapView.onCreate(savedInstanceState);
        /*
        * 用于解决切换fragment无法自动定位问题
        * */
        if (aMap == null) {
            aMap = mapView.getMap();
        } else {
            aMap.clear();
            aMap = mapView.getMap();
        }
        /*
        * 高德地图API，方法详情参见高德地图手册
        * */
        myLocationStyle.interval(1000 * 60 * 60);//使用高延迟取消自动定位，使用LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER时出现定位到北京的问题
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));//默认缩放级别
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        myLocationStyle.showMyLocation(true);
        myLocationStyle.strokeColor(getResources().getColor(R.color.no_color));
        myLocationStyle.radiusFillColor(getResources().getColor(R.color.no_color));
        aMap.setMyLocationStyle(myLocationStyle);
        mapLocationClient.setLocationListener(this);
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setInterval(1000);
        aMapLocationClientOption.setHttpTimeOut(20000);
        aMapLocationClientOption.setLocationCacheEnable(true);
        mapLocationClient.setLocationOption(aMapLocationClientOption);
        mapLocationClient.startLocation();

        List<MultiPointItem> list = new ArrayList<MultiPointItem>();
        //36.5375234379,116.7959761620
        //36.5369329474,116.7964696884
        //36.5353511001,116.7962175608
        //36.5359459130,116.7974513769
        //36.5378337669,116.7974781990
        LatLng latLng;
        MultiPointItem multiPointItem;
        latLng = new LatLng(36.5375234379, 116.7959761620, false);//保证经纬度没有问题的时候可以填false
        multiPointItem = new MultiPointItem(latLng);
        list.add(multiPointItem);
        latLng = new LatLng(36.5369329474, 116.7964696884, false);
        multiPointItem = new MultiPointItem(latLng);
        list.add(multiPointItem);
        latLng = new LatLng(36.5353511001, 116.7962175608, false);
        multiPointItem = new MultiPointItem(latLng);
        list.add(multiPointItem);
        latLng = new LatLng(36.5359459130, 116.7974513769, false);
        multiPointItem = new MultiPointItem(latLng);
        list.add(multiPointItem);
        latLng = new LatLng(36.5378337669, 116.7974781990, false);
        multiPointItem = new MultiPointItem(latLng);
        list.add(multiPointItem);

        //添加一个Marker用来展示海量点点击效果
        marker = aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.marker);
        MultiPointOverlayOptions overlayOptions = new MultiPointOverlayOptions();
        overlayOptions.icon(bitmapDescriptor);
        overlayOptions.anchor(0.5f, 0.5f);
        MultiPointOverlay multiPointOverlay = aMap.addMultiPointOverlay(overlayOptions);
        aMap.setOnMultiPointClickListener(new AMap.OnMultiPointClickListener() {
            @Override
            public boolean onPointClick(MultiPointItem pointItem) {
                android.util.Log.i("amap ", "onPointClick");

                if (marker.isRemoved()) {
                    //调用amap clear之后会移除marker，重新添加一个
                    marker = aMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                }
                //添加一个Marker用来展示海量点点击效果
                marker.setPosition(pointItem.getLatLng());
                marker.setToTop();
                Toast.makeText(getActivity(), "点击了：" + marker.getPosition(), Toast.LENGTH_LONG).show();
                return false;
            }
        });

        multiPointOverlay.setItems(list);
        multiPointOverlay.setEnable(true);

    }

    @Override
    protected void doBusiness(Context mContext, Activity activity) {

    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }

}
