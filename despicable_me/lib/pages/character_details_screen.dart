import 'package:after_layout/after_layout.dart';
import 'package:despicable_me/model/character.dart';
import 'package:despicable_me/styleguide.dart';
import 'package:flutter/material.dart';

class CharacterDetailsScreen extends StatefulWidget {
  final double _expandedBottomSheetBottomPosition = 0;
  final double _collapsedBottomSheetBottomPosition = -250;
  final double _completeCollapsedBottomSheetBottomPosition = -330;

  final Character character;

  const CharacterDetailsScreen({Key key, this.character}) : super(key: key);

  @override
  _CharacterDetailsScreenState createState() => _CharacterDetailsScreenState();
}

class _CharacterDetailsScreenState extends State<CharacterDetailsScreen>
    with AfterLayoutMixin<CharacterDetailsScreen> {
  double bottomSheetBottomPosition = -330;
  bool isCollapsed = false;

  @override
  Widget build(BuildContext context) {
    final screenHeight = MediaQuery.of(context).size.height;
    return Scaffold(
      body: Stack(fit: StackFit.expand, children: [
        Hero(
          tag: "background-${widget.character.name}",
          child: DecoratedBox(
            decoration: BoxDecoration(
                gradient: LinearGradient(
                    colors: widget.character.colors,
                    begin: Alignment.topRight,
                    end: Alignment.bottomLeft)),
          ),
        ),
        SingleChildScrollView(
          scrollDirection: Axis.vertical,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              SizedBox(height: 40),
              Padding(
                padding: const EdgeInsets.only(top: 8.0, left: 16),
                child: IconButton(
                  onPressed: () async {

                   await Future.delayed(Duration(milliseconds: 250),(){
                      setState(() {
                        bottomSheetBottomPosition =
                            widget._completeCollapsedBottomSheetBottomPosition;
                      });
                    });


                    Navigator.pop(context);
                  },
                  icon: Icon(
                    Icons.close,
                    color: Colors.white.withOpacity(0.9),
                    size: 40,
                  ),
                ),
              ),
              Align(
                  alignment: Alignment.topRight,
                  child: Hero(
                    tag: "image-${widget.character.name}",
                    child: Image.asset(widget.character.imagePath,
                        height: screenHeight * .45),
                  )),
              Padding(
                padding:
                    const EdgeInsets.symmetric(horizontal: 32, vertical: 8),
                child: Hero(
                  tag: "name-${widget.character.name}",
                  child: Material(
                    color: Colors.transparent,
                    child: Text(
                      widget.character.name,
                      style: AppTheme.heading,
                    ),
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.fromLTRB(32, 0, 8, 32),
                child: Hero(
                  child: Material(
                    color: Colors.transparent,
                    child: Text(
                      widget.character.description,
                      style: AppTheme.subHeading,
                    ),
                  ),
                  tag: "description-${widget.character.name}",
                ),
              )
            ],
          ),
        ),
        AnimatedPositioned(
            duration: Duration(milliseconds: 350),
            curve: Curves.decelerate,
            bottom: bottomSheetBottomPosition,
            right: 0,
            left: 0,
            child: Container(
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(40),
                    topRight: Radius.circular(40)),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  InkWell(
                    onTap: () {
                      _clipsTap();
                    },
                    child: Container(
                      alignment: Alignment.centerLeft,
                      padding: const EdgeInsets.symmetric(horizontal: 32),
                      height: 80,
                      child: Text(
                        "Clips",
                        style:
                            AppTheme.subHeading.copyWith(color: Colors.black),
                      ),
                    ),
                  ),
                  SingleChildScrollView(
                    scrollDirection: Axis.horizontal,
                    child: _childWidget(),
                  )
                ],
              ),
            ))
      ]),
    );
  }

  void _clipsTap() {
    setState(() {
      bottomSheetBottomPosition = isCollapsed
          ? widget._expandedBottomSheetBottomPosition
          : widget._collapsedBottomSheetBottomPosition;
      isCollapsed = !isCollapsed;
    });
  }

  @override
  void afterFirstLayout(BuildContext context) {
    Future.delayed(const Duration(milliseconds: 500), () {
      setState(() {
        isCollapsed = true;
        bottomSheetBottomPosition = widget._collapsedBottomSheetBottomPosition;
      });
    });
  }
}

Widget _childWidget() {
  return Container(
    height: 250,
    margin: const EdgeInsets.symmetric(horizontal: 16),
    child: Row(
      children: <Widget>[
        Column(
          children: <Widget>[
            roundedContainer(Colors.redAccent),
            SizedBox(height: 20),
            roundedContainer(Colors.greenAccent),
          ],
        ),
        SizedBox(
          width: 16,
        ),
        Column(
          children: <Widget>[
            roundedContainer(Colors.orangeAccent),
            SizedBox(
              height: 20,
            ),
            roundedContainer(Colors.purple),
          ],
        ),
        SizedBox(
          width: 16,
        ),
        Column(
          children: <Widget>[
            roundedContainer(Colors.grey),
            SizedBox(
              height: 20,
            ),
            roundedContainer(Colors.blueGrey),
          ],
        ),
        SizedBox(
          width: 16,
        ),
        Column(
          children: <Widget>[
            roundedContainer(Colors.lightGreenAccent),
            SizedBox(
              height: 20,
            ),
            roundedContainer(Colors.pinkAccent),
          ],
        ),
      ],
    ),
  );
}

Widget roundedContainer(Color color) {
  return Container(
    width: 100,
    height: 100,
    decoration: BoxDecoration(
        color: color, borderRadius: BorderRadius.all(Radius.circular(20))),
  );
}
