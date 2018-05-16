package com.sdjy.book.view.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.sdjy.book.R;
import com.sdjy.book.app.BaseFragment;
import com.sdjy.book.app.BookApplication;
import com.sdjy.book.app.Constant;


public class MapFragment extends BaseFragment implements AMapLocationListener {

    @SuppressLint("StaticFieldLeak")
    private static MapFragment INSTANCE;
    private TextureMapView mapView;
    private AMap aMap;
    private Bundle savedInstanceState;
    private MyLocationStyle myLocationStyle;
    private AMapLocationClient mapLocationClient;
    private AMapLocationClientOption aMapLocationClientOption;

    private MarkerOptions markerOption;
    private LatLng latlng;
    private Marker marker;

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
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
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

        latlng = new LatLng(36.541264, 116.802539);
        addMarkersToMap();
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

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {

        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .position(latlng)
                .draggable(true);
        marker = aMap.addMarker(markerOption);
    }

}
