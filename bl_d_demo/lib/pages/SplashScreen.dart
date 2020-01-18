import 'package:bl_d_demo/utils/ScreenUtil.dart';
import 'package:drawing_animation/drawing_animation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_svg/svg.dart';

import '../utils/extensions.dart';

class SplashScreen extends StatefulWidget {
  final Widget launchAfterSplash;

  SplashScreen({this.launchAfterSplash});

  @override
  _SplashScreenState createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  bool run = true;

  @override
  void initState() {
    super.initState();
    Future.delayed(
        const Duration(seconds: 3),
        () => Navigator.push(context,
            MaterialPageRoute(builder: (_) => widget.launchAfterSplash)));
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setEnabledSystemUIOverlays([SystemUiOverlay.bottom]);

//    ScreenUtil.init(context,allowFontScaling: true);
    ScreenUtil.init(context);

    return Scaffold(
      backgroundColor: HexColor.fromHex("#0D0D15"),
      body: Stack(
        children: <Widget>[
          Align(
            alignment: Alignment.topLeft,
            child: SvgPicture.asset(
              'assets/images/tl_bg.svg',
              color: Colors.purple,
            ),
          ),
          Align(
            alignment: Alignment.bottomRight,
            child: SvgPicture.asset(
              'assets/images/br_bg.svg',
              color: Colors.purple,
            ),
          ),
          Align(
            alignment: Alignment.center,
            child: Container(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Container(
                    width: ScreenUtil().setWidth(300) ,
                    height: ScreenUtil().setHeight(300) ,
                    decoration: BoxDecoration(
                        color: Colors.yellow,
                        backgroundBlendMode: BlendMode.colorDodge),
                    child: AnimatedDrawing.svg(
                      'assets/images/bl_logo.svg',
                      run: this.run,
                      duration: Duration(seconds: 2),
                      onFinish: () => setState(() {
                        this.run = false;
                      }),
                    ),
                  ),
                  Container(
                    margin: EdgeInsets.only(top: 20),
                    child: SvgPicture.asset(
                      'assets/images/bookinglane_txt_svg.svg',
                      width: ScreenUtil().setWidth(300) ,

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
