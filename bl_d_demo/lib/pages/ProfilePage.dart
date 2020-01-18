import 'package:bl_d_demo/utils/ScreenUtil.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_svg/svg.dart';

import '../utils/extensions.dart';

class ProfilePage extends StatefulWidget {
  @override
  _ProfilePageState createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  @override
  Widget build(BuildContext context) {
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
            child: ShaderMask(
              shaderCallback: (bounds) {
                return LinearGradient(stops: [
                  0.0,
                  0.4,
                ], colors: [
                  Colors.red,
                  Colors.black,
                ]).createShader(bounds);
              },
              blendMode: BlendMode.srcATop,
              child: SvgPicture.asset(
                'assets/images/br_bg.svg',
                color: Colors.purple,
              ),
            ),
          ),
          Scaffold(
            backgroundColor: Colors.transparent,
            appBar: AppBar(
              backgroundColor: Colors.transparent,
              elevation: 0,
              leading: IconButton(
                  onPressed: () {
                    Navigator.pop(context);
                    print('object');
                  },
                  icon: Icon(Icons.arrow_back_ios)),
              title: SvgPicture.asset(
                'assets/images/bookingLane_txt.svg',
                width: 100,
                height: 30,
              ),
              actions: <Widget>[
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Icon(Icons.exit_to_app),
                )
              ],
            ),
            body: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(
                    'Driver profile',
                    style: TextStyle(
                        fontSize: ScreenUtil().setSp(40), color: Colors.white),
                  ),
                  Container(
                    width: double.infinity,
                    margin: EdgeInsets.only(top: 8),
                    height: ScreenUtil().setHeight(500),
                    decoration: BoxDecoration(
                        color: HexColor.fromHex('#424242'),
                        borderRadius: BorderRadius.circular(10)),
                    child: Padding(
                      padding: const EdgeInsets.all(16),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          Text(
                            'Autdrey Steward',
                            style: TextStyle(
                                color: Colors.white,
                                fontWeight: FontWeight.bold,
                                fontSize: ScreenUtil().setSp(50)),
                          ),
                          Text(
                            'Company: SIXT',
                            style: TextStyle(
                                color: Colors.white,
                                fontSize: ScreenUtil().setSp(40)),
                          ),
                          Text(
                            'Phone: (217) 555-0113',
                            style: TextStyle(
                                color: Colors.white,
                                fontSize: ScreenUtil().setSp(40)),
                          ),
                          Text(
                            'PID: 501055890558',
                            style: TextStyle(
                                color: Colors.white,
                                fontSize: ScreenUtil().setSp(40)),
                          ),
                          Text(
                            'Address: 7510 Pecan Acres Ln Boulder, Texas 19897 United States',
                            style: TextStyle(
                                color: Colors.white,
                                fontSize: ScreenUtil().setSp(40)),
                          ),
                        ],
                      ),
                    ),
                  ),
                  SizedBox(height: ScreenUtil().setHeight(8)),
                  Text(
                    'Driver\'s license',
                    style: TextStyle(
                        fontSize: ScreenUtil().setSp(40), color: Colors.white),
                  ),
                  SizedBox(height: ScreenUtil().setHeight(8)),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Image.asset(
                        'assets/images/license.png',
                        width: MediaQuery.of(context).size.width / 2 - 20,
                      ),
                      Image.asset(
                        'assets/images/license.png',
                        width: MediaQuery.of(context).size.width / 2 - 20,
                      ),
                    ],
                  ),
                  SizedBox(height: ScreenUtil().setHeight(8)),
                  Center(
                    child: Text(
                      'End of date: 15/09/2020',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: ScreenUtil().setSp(40)),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
