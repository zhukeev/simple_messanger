import 'package:flutter/material.dart';
import 'package:video_game/common/app_background_dart.dart';
import 'package:video_game/common/horizontal_tab_layout.dart';
import 'package:video_game/styleguide/colors.dart';
import 'package:video_game/styleguide/text_styles.dart';

class LandingPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: <Widget>[
          AppBackground(firstColor: firstCircleColor,secondColor: secondCircleColor,thirdColor: thirdCircleColor,),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              SizedBox(
                height: 50,
              ),
              Padding(
                padding: const EdgeInsets.only(right: 32.0),
                child: Align(
                  alignment: Alignment.topRight,
                  child: Material(
                    elevation: 10,
                    child: Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Icon(Icons.apps, color: primaryColor, size: 30),
                    ),
                    color: Colors.white,
                    shape: CircleBorder(),
                  ),
                ),
              ),
              HeadingSunHeadingWidget(),
              SizedBox(
                height: 20,
              ),
              HorizontalTabLayout(),
              Spacer(),
              Align(
                alignment: Alignment.bottomRight,
                child: Container(
                  padding:
                      const EdgeInsets.symmetric(horizontal: 60, vertical: 30),
                  child: Text('New Topic', style: buttonStyle),
                  decoration: BoxDecoration(
                      color: primaryColor,
                      borderRadius:
                          BorderRadius.only(topLeft: Radius.circular(40))),
                ),
              )
            ],
          ),
        ],
      ),
    );
  }
}

class HeadingSunHeadingWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 42.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(
            'Forum',
            style: headingStyle,
          ),
          Text(
            'Kick of the conversation',
            style: subHeadingStyle,
          ),
        ],
      ),
    );
  }
}
