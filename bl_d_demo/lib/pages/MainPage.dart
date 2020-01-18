import 'dart:async';

import 'package:bl_d_demo/pages/ProfilePage.dart';
import 'package:bl_d_demo/utils/ScreenUtil.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart' show rootBundle;
import 'package:flutter_polyline_points/flutter_polyline_points.dart';
import 'package:flutter_svg/svg.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

import '../utils/extensions.dart';

class MainPage extends StatefulWidget {
  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  Completer<GoogleMapController> _controller = Completer();
  GoogleMapController mapController;
  String _mapStyle;

  static const LatLng _center = const LatLng(45.521563, -122.677433);
  static final CameraPosition _kGooglePlex = CameraPosition(
    target: SOURCE_LOCATION /* LatLng(37.42796133580664, -122.085749655962)*/,
    zoom: 14.4746,
  );

  static const double CAMERA_ZOOM = 13;
  static const double CAMERA_TILT = 0;
  static const double CAMERA_BEARING = 30;
  static const LatLng SOURCE_LOCATION = LatLng(42.7477863, -71.1699932);
  static const LatLng DEST_LOCATION = LatLng(42.6871386, -71.2143403);

  Set<Marker> _markers = {};
  Set<Polyline> _polylines = {};

  List<LatLng> polylineCoordinates = [];

  PolylinePoints polylinePoints = PolylinePoints();

  String googleAPIKey = "AIzaSyAC7wMqvGOFN7sXDN-6DoX7cjy6CS6L1vs";

  BitmapDescriptor sourceIcon = BitmapDescriptor.defaultMarker;
  BitmapDescriptor destinationIcon = BitmapDescriptor.defaultMarker;

  void _onMapCreated(GoogleMapController controller) {
    mapController = controller;
    mapController.setMapStyle(_mapStyle);
    _controller.complete(controller);
    setMapPins();
    setPolylines();
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  void initState() {
    super.initState();

    rootBundle
        .loadString('assets/google_map_style/dark_mode_google_map.txt')
        .then((style) {
      _mapStyle = style;
    });
  }

  @override
  Widget build(BuildContext context) {
//    ScreenUtil.init(context);

    return Container(
      color: HexColor.fromHex("#0D0D15"),
      child: Stack(
        children: <Widget>[
          Positioned.fill(
            child: Align(
              alignment: Alignment.topLeft,
              child: SvgPicture.asset(
                'assets/images/tl_bg.svg',
                color: Colors.purple,
              ),
            ),
          ),
          Align(
            alignment: Alignment.bottomRight,
            child: SvgPicture.asset(
              'assets/images/br_bg.svg',
              color: Colors.purple,
            ),
          ),
          Scaffold(
            backgroundColor: Colors.transparent,
            appBar: AppBar(
              elevation: 0,
              automaticallyImplyLeading: false,
              backgroundColor: Colors.transparent,
              actions: <Widget>[
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: SvgPicture.asset(
                    'assets/images/bookingLane_txt.svg',
                    width: ScreenUtil().setWidth(300),
                    height: ScreenUtil().setHeight(80),
                  ),
                ),
                Spacer(),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: CircleAvatar(
                    child: IconButton(
                      color: Colors.grey,
                      icon: Icon(Icons.person_outline),
                      onPressed: () {
                        Navigator.push(context,
                            MaterialPageRoute(builder: (_) => ProfilePage()));
                      },
                    ),
                    backgroundColor: Colors.white,
                  ),
                ),
              ],
            ),
            body: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Text(
                    'Car',
                    style: TextStyle(
                        fontSize: ScreenUtil().setSp(30), color: Colors.white),
                  ),
                ),
                SizedBox(
                  height: ScreenUtil().setHeight(10),
                ),
                Container(
                  margin: EdgeInsets.only(left: 8, right: 8),
                  height: ScreenUtil().setHeight(200),
                  decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(10),
                      color: Colors.white),
                  child: Row(
                    mainAxisSize: MainAxisSize.max,
                    children: <Widget>[
                      Image.asset(
                        'assets/images/car.png',
                      ),
                      Padding(
                        padding: const EdgeInsets.all(10.0),
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            Text(
                              'SUV Toyota',
                              style: TextStyle(
                                  fontSize: ScreenUtil().setSp(30),
                                  fontWeight: FontWeight.bold),
                            ),
                            StarRating(rating: 4),
                          ],
                        ),
                      ),
                      Spacer(),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Container(
                          height: ScreenUtil().setHeight(150),
                          width: ScreenUtil().setWidth(150),
                          decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(10),
                              gradient: LinearGradient(colors: [
                                HexColor.fromHex('#568AFD'),
                                HexColor.fromHex('#670DF2'),
                                HexColor.fromHex('#851EDF')
                              ])),
                          child: Center(
                              child: Text(
                            '80\$',
                            style: TextStyle(
                                fontSize: ScreenUtil().setSp(50),
                                color: Colors.white),
                          )),
                        ),
                      )
                    ],
                  ),
                ),
                SizedBox(
                  height: ScreenUtil().setHeight(10),
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Text(
                    'Trip details',
                    style: TextStyle(
                        fontSize: ScreenUtil().setSp(20), color: Colors.white),
                  ),
                ),
                Container(
                  height: ScreenUtil().setHeight(250),
                  decoration: BoxDecoration(
                      gradient: LinearGradient(colors: [
                    HexColor.fromHex('#568AFD'),
                    HexColor.fromHex('#670DF2'),
                    HexColor.fromHex('#851EDF')
                  ])),
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            _buildTextWithIcon(Icons.place, 'From: Asanbai 21'),
                            Text(
                              '15/08/19 15:00',
                              style: TextStyle(
                                  color: Colors.white,
                                  fontSize: ScreenUtil().setSp(30)),
                            )
                          ],
                        ),
                        Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            _buildTextWithIcon(
                                Icons.radio_button_checked, 'To: Osipenko 113'),
                            Text(
                              'Limousine',
                              style: TextStyle(
                                  color: Colors.white,
                                  fontSize: ScreenUtil().setSp(30)),
                            )
                          ],
                        ),
                        Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            _buildTextWithIcon(Icons.person, 'People 5'),
                            Text(
                              'Time of receipt: 24:00',
                              style: TextStyle(
                                  color: Colors.white,
                                  fontSize: ScreenUtil().setSp(30)),
                            )
                          ],
                        ),
                      ],
                    ),
                  ),
                ),
                Expanded(
                  child: GoogleMap(
                      myLocationEnabled: true,
                      compassEnabled: true,
                      tiltGesturesEnabled: false,
                      markers: _markers,
                      polylines: _polylines,
                      mapType: MapType.normal,
                      initialCameraPosition: initialLocation,
                      onMapCreated: _onMapCreated),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }

  CameraPosition initialLocation = CameraPosition(
      zoom: CAMERA_ZOOM,
      bearing: CAMERA_BEARING,
      tilt: CAMERA_TILT,
      target: SOURCE_LOCATION);

  Widget _buildTextWithIcon(IconData icon, String s) {
    return Row(
      children: <Widget>[
        Icon(
          icon,
          size: 20,
        ),
        SizedBox(
          width: ScreenUtil().setWidth(10),
        ),
        Text(
          s,
          style: TextStyle(
              color: Colors.white,
              fontSize: ScreenUtil().setSp(30, allowFontScalingSelf: true)),
        )
      ],
    );
  }

  void setMapPins() {
    setState(() {
      _markers.add(Marker(
          markerId: MarkerId('SourceId'),
          position: SOURCE_LOCATION,
          icon: sourceIcon));

      _markers.add(Marker(
          markerId: MarkerId('destId'),
          position: DEST_LOCATION,
          icon: destinationIcon));
    });
  }

  void setPolylines() async {
    List<PointLatLng> result = await polylinePoints?.getRouteBetweenCoordinates(
        googleAPIKey,
        SOURCE_LOCATION.latitude,
        SOURCE_LOCATION.longitude,
        DEST_LOCATION.latitude,
        DEST_LOCATION.longitude);

    if (result.isNotEmpty) {
      result.forEach((point) {
        polylineCoordinates.add(LatLng(point.latitude, point.longitude));
      });
    }

    setState(() {
      Polyline polyline = Polyline(
          polylineId: PolylineId('polyline'),
          color: Colors.redAccent,
          points: polylineCoordinates);

      _polylines.add(polyline);
    });
  }
}

typedef void RatingChangeCallback(double rating);

class StarRating extends StatelessWidget {
  final int starCount;
  final double rating;
  final RatingChangeCallback onRatingChanged;
  final Color color;

  StarRating(
      {this.starCount = 5, this.rating = .0, this.onRatingChanged, this.color});

  Widget buildStar(BuildContext context, int index) {
    Widget icon;
    if (index >= rating) {
      icon = new Icon(
        Icons.star,
        color: Theme.of(context).buttonColor,
        size: 20,
      );
    } else {
      icon = ShaderMask(
        shaderCallback: (bounds) {
          return RadialGradient(colors: [
            HexColor.fromHex('#568AFD'),
            HexColor.fromHex('#670DF2'),
            HexColor.fromHex('#851EDF')
          ]).createShader(bounds);
        },
        child: Icon(
          Icons.star,
          color: color ?? Theme.of(context).primaryColor,
          size: 20,
        ),
      );
    }
    return new InkResponse(
      onTap:
          onRatingChanged == null ? null : () => onRatingChanged(index + 1.0),
      child: icon,
    );
  }

  @override
  Widget build(BuildContext context) {
    return new Row(
        children:
            new List.generate(starCount, (index) => buildStar(context, index)));
  }
}
