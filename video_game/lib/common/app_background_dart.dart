import 'package:flutter/material.dart';
import 'package:video_game/styleguide/colors.dart';

class AppBackground extends StatelessWidget {
  final Color firstColor;
  final Color secondColor;
  final Color thirdColor;


  AppBackground({this.firstColor, this.secondColor, this.thirdColor});

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        final height = constraints.maxHeight;
        final width = constraints.maxWidth;
        print("height $height width $width");
        return Stack(
          children: <Widget>[
            Container(
              color: backgroundColor,
            ),
            Positioned(
              left: -(height / 2 - width / 2),
              bottom: height / 4,
              child: Container(
                height: height,
                width: height,
                decoration: BoxDecoration(
                    color: firstColor, shape: BoxShape.circle),
              ),
            ),
            Positioned(
              left: width*0.2 ,
              top: -width *0.7,
              child: Container(
                height: width * 1.5,
                width: width * 1.5,
                decoration: BoxDecoration(
                    color: secondColor,
                    shape: BoxShape.circle),
              ),
            ),
            Positioned(
              right:-width*0.5 ,
              top:-width*0.5,
              child: Container(
                height: width ,
                width: width,
                decoration: BoxDecoration(
                    color: thirdColor,
                    shape: BoxShape.circle),
              ),
            ),
          ],
        );
      },
    );
  }
}
