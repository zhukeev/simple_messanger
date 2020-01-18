import 'package:flutter/material.dart';
import 'package:video_game/styleguide/text_styles.dart';

class TabText extends StatelessWidget {
  final String text;
  final bool isSelected;
  final Function onTapTab;

  TabText({this.text, this.isSelected, this.onTapTab});

  @override
  Widget build(BuildContext context) {
    return Transform.rotate(
      angle: -1.58,
      child: InkWell(
        onTap: onTapTab,
        child: AnimatedDefaultTextStyle(
          duration: Duration(milliseconds: 500),
          child: Text(
            text,
          ),
          style: isSelected ? selectedTabStyle : defaultTabStyle,
        ),
      ),
    );
  }
}
