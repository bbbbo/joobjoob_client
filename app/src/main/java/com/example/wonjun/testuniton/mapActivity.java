package com.example.wonjun.testuniton;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

/**
 * Created by 김관현 on 2018-01-28.
 */

public class mapActivity extends AppCompatActivity implements MapView.MapViewEventListener, MapView.POIItemEventListener,
        MapView.OpenAPIKeyAuthenticationResultListener{


    public static final String TAG = MainActivity.class.getSimpleName();

    private MapPOIItem customMarker = new MapPOIItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapView mapView = new MapView(this);
        mapView.setDaumMapApiKey("f26ccbf313b6ea3841b9122918b30ad5");

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        // 여기 중심부분 관련된 부분
        mapView.setMapViewEventListener(this); // this에 MapView.MapViewEventListener 구현.
        mapView.setPOIItemEventListener(this);

        // 맵 이벤트 처리 하는부분
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("CU");
        marker.setTag(0);
        marker.setMapPoint((MapPoint.mapPointWithGeoCoord(37.543445, 126.951512)));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);


        MapPOIItem marker1 = new MapPOIItem();
        marker1.setItemName("Seven");
        marker1.setTag(0);
        marker1.setMapPoint((MapPoint.mapPointWithGeoCoord(37.543045, 126.951012)));
        marker1.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker1.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker1);

        MapPOIItem marker2 = new MapPOIItem();
        marker2.setItemName("GS");
        marker2.setTag(0);
        marker2.setMapPoint((MapPoint.mapPointWithGeoCoord(36.543245, 125.951312)));
        marker2.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker2);
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.541245, 126.951512), true);

        // 마커 감지 시키
        // 마커 네임
        customMarker.setItemName("GS");
        customMarker.setTag(1);
        customMarker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.543445, 126.951512));
//        Log.e("customMarker->", customMarker.getMapPoint().toString());
//        customMarker.setMapPoint(customMarker.getMapPoint());
        //customMarker.setMapPoint(MapPoint.)
        customMarker.setMapPoint(MapPoint.mapPointWithCONGCoord(100, 200));
        //customMarker.setMapPoint(customMarker.getMapPoint());
        customMarker.setMapPoint(mapView.getMapCenterPoint());
        customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
        customMarker.setCustomImageResourceId(R.drawable.map_mark); // 마커 이미지.
        customMarker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
        customMarker.setCustomImageAnchor(0.5f, 1.0f); // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
        mapView.addPOIItem(customMarker);
    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_yes:
                Intent intent = new Intent(mapActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_no:
                Intent be_intent = new Intent(mapActivity.this, CommentActivity.class);
                startActivity(be_intent);
                break;
        }
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int i, String s) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}
