import 'package:despicable_me/model/character.dart';
import 'package:despicable_me/pages/character_details_screen.dart';
import 'package:despicable_me/styleguide.dart';
import 'package:flutter/material.dart';

class CharacterWidget extends StatelessWidget {
  final Character character;
  final PageController pageController;
  final int currentPage;

  const CharacterWidget(
      {Key key, this.character, this.pageController, this.currentPage})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    final screenHeight = MediaQuery.of(context).size.height;
    final screenWidth = MediaQuery.of(context).size.width;

    return InkWell(
      onTap: () {
        Navigator.push(
            context,
            PageRouteBuilder(
                pageBuilder: (context, _, __) => CharacterDetailsScreen(
                      character: character,
                    )));
      },
      child: AnimatedBuilder(
        builder: (context, child) {
          double value = 1;

          if (pageController.position.haveDimensions) {
            value = pageController.page - currentPage;
            value = (1 - (value.abs() * 0.6)).clamp(0.0, 1.0);
          }

          return Stack(children: [
            Align(
              alignment: Alignment.bottomCenter,
              child: ClipPath(
                clipper: CharacterCarBackgroundClipper(),
                child: Hero(
                  tag: "background-${character.name}",
                  child: Container(
                    height: screenHeight * 0.55,
                    width: screenWidth * 0.9,
                    decoration: BoxDecoration(
                        gradient: LinearGradient(
                            colors: character.colors,
                            begin: Alignment.topRight,
                            end: Alignment.bottomLeft)),
                  ),
                ),
              ),
            ),
            Hero(
              tag: "image-${character.name}",
              child: Align(
                  alignment: Alignment(0, -0.5),
                  child: Image.asset(
                    character.imagePath,
                    height: screenHeight * 0.55*value,
                  )),
            ),
            Padding(
              padding: const EdgeInsets.only(left: 46, right: 16, bottom: 16),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.end,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Hero(
                    tag: "name-${character.name}",
                    child: Material(
                      color: Colors.transparent,
                      child: Text(
                        character.name,
                        style: AppTheme.heading,
                      ),
                    ),
                  ),
                  Hero(
                    tag: "description-${character.name}",
                    child: Material(
                      color: Colors.transparent,
                      child: Text(
                        "Tap to read more",
                        style: AppTheme.subHeading,
                      ),
                    ),
                  ),
                ],
              ),
            )
          ]);
        },
        animation: pageController,
      ),
    );
  }
}

class CharacterCarBackgroundClipper extends CustomClipper<Path> {
  @override
  Path getClip(Size size) {
    Path clipperPath = Path();
    double curveDistance = 40;

    clipperPath.moveTo(0, size.height * 0.4);
    clipperPath.lineTo(0, size.height - curveDistance);
    clipperPath.quadraticBezierTo(
        1, size.height - 1, 0 + curveDistance, size.height);
    clipperPath.lineTo(size.width - curveDistance, size.height);

    clipperPath.quadraticBezierTo(size.width + 1, size.height - 1, size.width,
        size.height - curveDistance);
    clipperPath.lineTo(size.width, 0 + curveDistance);
    clipperPath.quadraticBezierTo(size.width - 1, 0,
        size.width - curveDistance - 5, 0 + curveDistance / 3);

    clipperPath.lineTo(curveDistance, size.height * 0.29);
    clipperPath.quadraticBezierTo(
        1, (size.height * 0.30) + 10, 0, size.height * 0.4);

    return clipperPath;
  }

  @override
  bool shouldReclip(CustomClipper<Path> oldClipper) {
    return true;
  }
}
