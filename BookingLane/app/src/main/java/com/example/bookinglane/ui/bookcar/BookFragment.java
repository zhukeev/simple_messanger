package com.example.bookinglane.ui.bookcar;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.bookinglane.R;
import com.example.bookinglane.adapter.AutoCompleteAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BookFragment extends Fragment implements OnMapReadyCallback, AdapterView.OnItemClickListener {

    private static final String TAG = "BookFragment";

    private BookViewModel toolsViewModel;
    private GoogleMap mMap;
    private TextView peopleCount_tv;
    private Button buttonBook;
    private ImageView imageViewMinus, imageViewPlus;
    private EditText date_time_et;
    private LinearLayout companies_ll;
    private FrameLayout map_layout;
    private ImageView selectedCar;
    //    private Location mLocation;
//    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 100;

    //    EditText searchLocation;
//    TextView placeDetails;
    String apiKey;
    private AutoCompleteTextView autoCompleteFrom, autoCompleteTo;
    private AutoCompleteAdapter adapter;
    private PlacesClient placesClient;
    private double[] point_a_LatLng = new double[2];
    private double[] point_b_LatLng = new double[2];

    private static final String[] LOCATION_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(BookViewModel.class);
        View root = inflater.inflate(R.layout.fragment_book, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        apiKey = getString(R.string.google_maps_key);


        // Setup Places Client
        if (!Places.isInitialized()) {
            Places.initialize(getContext(), apiKey);
        }

        placesClient = Places.createClient(getContext());

        initAutoCompleteTextView(root);
        init(root);
        return root;
    }

    private void listenLocation() {
       /* Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                mLocation = location;
                Log.e(TAG, "listenLocation: "+location.toString() );
                LatLng myCurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myCurrentLocation, 19));
                ac();
            }
        });*/
    }


    private void askLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(), LOCATION_PERMS, 100);
        }

    }

    private void init(View root) {
//        LinearLayout linearLayout = root.findViewById(R.id.bottom_sheet);
//        linearLayout.getLayoutParams().height=1;

//      new BottomSheetSearch().show(getChildFragmentManager(),"fragment");

        //region Finding by id
        buttonBook = root.findViewById(R.id.book_car_btn_book);
        peopleCount_tv = root.findViewById(R.id.people_count_tv_book);
        imageViewMinus = root.findViewById(R.id.minus_iv_book);
        imageViewPlus = root.findViewById(R.id.plus_iv_book);
        map_layout = (FrameLayout) root.findViewById(R.id.map_holder_layout_book);
        date_time_et = root.findViewById(R.id.date_time_et_book);
        selectedCar = root.findViewById(R.id.car_icon_book);
        //endregion

//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        //region Set Listeners
        date_time_et.setOnClickListener(view -> {
            showDateTimePicker();
        });

        buttonBook.setOnClickListener(view -> {
//            takeScreenshot();
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_home);

        });

        imageViewPlus.setOnClickListener(view -> {
            int count = Integer.parseInt(peopleCount_tv.getText().toString());
            peopleCount_tv.setText(String.valueOf(count + 1));
        });
        imageViewMinus.setOnClickListener(view -> {
            int count = Integer.parseInt(peopleCount_tv.getText().toString());
            if (count > 1) {
                peopleCount_tv.setText(String.valueOf(count - 1));
            }
        });
        //endregion


    }

    private void takeScreenshot() {
        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(Bitmap bitmap) {

                selectedCar.setImageBitmap(bitmap);
                try {
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "map.png");
                    FileOutputStream fout = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, fout);
                    Toast.makeText(getContext(), "Capture", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Not Capture", Toast.LENGTH_SHORT).show();
                }
            }
        };

        mMap.snapshot(callback);
    }

    public Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

 /*   private void ac() {
        Intent intent = new Intent(getActivity(), SearchPlaceActivity.class);

        SearchPlaceActivity.Config.Builder config = new SearchPlaceActivity.Config.Builder(apiKey);
//                .setSearchBarTitle("Title")
//                .setMyLocation(mLocation.getLatitude() + "," + mLocation.getLongitude());
//                .setEnclosingRadius("500");


        intent.putExtra("configuration", config.build());


    }*/

    private void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        Calendar date;
        date = Calendar.getInstance();
        new DatePickerDialog(getContext(), (view, year, monthOfYear, dayOfMonth) -> {

            date.set(year, monthOfYear, dayOfMonth);
            new TimePickerDialog(getContext(), (view1, hourOfDay, minute) -> {
                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date.set(Calendar.MINUTE, minute);

                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
                df.format(date.getTime());

                date_time_et.setText(df.format(date.getTime()));


            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();

    }

    private void initAutoCompleteTextView(View root) {

        adapter = new AutoCompleteAdapter(getContext(), placesClient);

        autoCompleteFrom = root.findViewById(R.id.location_a_et_book);
        autoCompleteFrom.setThreshold(1);
        autoCompleteFrom.setOnItemClickListener(this);
        autoCompleteFrom.setAdapter(adapter);
        autoCompleteFrom.invalidate();


        autoCompleteTo = root.findViewById(R.id.location_b_et_book);
        autoCompleteTo.setThreshold(1);
        autoCompleteTo.setOnItemClickListener((adapterView, view, i, l) -> {
            try {
                final AutocompletePrediction item = adapter.getItem(i);
                String placeID = null;
                if (item != null) {
                    placeID = item.getPlaceId();
                }
                List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS
                        , Place.Field.LAT_LNG);

                FetchPlaceRequest request = null;
                if (placeID != null) {
                    request = FetchPlaceRequest.builder(placeID, placeFields)
                            .build();
                }

                if (request != null) {
                    placesClient.fetchPlace(request).addOnSuccessListener(task -> {
                        autoCompleteTo.setText("");
//                        destination = task.getPlace().getLatLng();
                        point_b_LatLng[0] = task.getPlace().getLatLng().latitude;
                        point_b_LatLng[1] = task.getPlace().getLatLng().longitude;
                        autoCompleteTo.setText(task.getPlace().getAddress(), false);

                        drawRoute();

                    }).addOnFailureListener(e -> {
                        e.printStackTrace();
//                    responseView.setText(e.getMessage());
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        autoCompleteTo.setAdapter(adapter);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.dark_mode_google_map));

        // Add a marker in Sydney and move the camera
        LatLng sanjose = new LatLng(37.3300396, -121.8979276);

        mMap.addMarker(new MarkerOptions().position(sanjose).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanjose, 19));
//        listenLocation();
        askLocationPermission();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        try {
            final AutocompletePrediction item = adapter.getItem(i);
            String placeID = null;
            if (item != null) {
                placeID = item.getPlaceId();
            }
            List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS
                    , Place.Field.LAT_LNG);

            FetchPlaceRequest request = null;
            if (placeID != null) {
                request = FetchPlaceRequest.builder(placeID, placeFields)
                        .build();
            }

            if (request != null) {
                placesClient.fetchPlace(request).addOnSuccessListener(task -> {
                    autoCompleteFrom.setText("");
                    autoCompleteFrom.setText(task.getPlace().getAddress(), false);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(task.getPlace().getLatLng(), 16));

                    point_a_LatLng[0] = task.getPlace().getLatLng().latitude;
                    point_a_LatLng[1] = task.getPlace().getLatLng().longitude;


                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), 0);
                }).addOnFailureListener(e -> {
                    e.printStackTrace();
//                    responseView.setText(e.getMessage());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMarkersToMap(DirectionsResult results) {

        BitmapDescriptor origin = getMarkerIconFromDrawable(getActivity().getDrawable(R.drawable.ic_location_point));
        BitmapDescriptor destination = getMarkerIconFromDrawable(getActivity().getDrawable(R.drawable.ic_location_point_2));


        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(results.routes[0].legs[0].startLocation.lat, results.routes[0].legs[0].startLocation.lng))
                .title(results.routes[0].legs[0].startAddress))
                .setIcon(origin);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(results.routes[0].legs[0].endLocation.lat, results.routes[0].legs[0].endLocation.lng))
                .title(results.routes[0].legs[0].startAddress)
                .snippet(getEndLocationTitle(results))
                .icon(destination));
    }

    private String getEndLocationTitle(DirectionsResult results) {
        return "Time :" + results.routes[0].legs[0].duration.humanReadable + " Distance :" + results.routes[0].legs[0].distance.humanReadable;
    }

    private void addPolyline(DirectionsResult results) {
        List<LatLng> decodedPath = PolyUtil.decode(results.routes[0].overviewPolyline.getEncodedPath());
        mMap.addPolyline(new PolylineOptions().addAll(decodedPath).color(Color.WHITE));
    }

    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void zoomRoute() {


        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        boundsBuilder.include(new LatLng(point_a_LatLng[0], point_a_LatLng[1]));
        boundsBuilder.include(new LatLng(point_b_LatLng[0], point_b_LatLng[1]));

        int routePadding = 100;
        LatLngBounds latLngBounds = boundsBuilder.build();

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding));
    }

    private void drawRoute() {

        com.google.maps.model.LatLng origin = new com.google.maps.model.LatLng(
                point_a_LatLng[0],
                point_a_LatLng[1]
        );
        com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
                point_b_LatLng[0],
                point_b_LatLng[1]
        );
        DateTime now = new DateTime();
        try {

            DirectionsResult result = DirectionsApi.newRequest(getGeoContext())
                    .mode(TravelMode.DRIVING)
                    .origin(origin)
                    .alternatives(true)
                    .destination(destination)
                    .departureTime(now).await();

            Log.e(TAG, "drawRoutes: " + result.routes.toString());

            if (result != null) {
                addPolyline(result);
//                positionCamera(result.routes[0]);
                zoomRoute();
                addMarkersToMap(result);
            }

        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(3)
                .setApiKey(getString(R.string.google_maps_key))
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 700 && requestCode == Activity.RESULT_OK) {

            ArrayList<PlaceDetails> detailsArrayList;
            detailsArrayList = data.getParcelableExtra("PLACE_DETAILS");

            Log.e(TAG, "onActivityResult: " + Arrays.toString(detailsArrayList.toArray()));

//            placeDetails.setText(detailsArrayList);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}