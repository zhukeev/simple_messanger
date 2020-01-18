import 'package:flutter/material.dart';
import 'package:video_game/common/forum_card.dart';
import 'package:video_game/common/tab_text.dart';
import 'package:video_game/model/forum.dart';

class HorizontalTabLayout extends StatefulWidget {
  @override
  _HorizontalTabLayoutState createState() => _HorizontalTabLayoutState();
}

class _HorizontalTabLayoutState extends State<HorizontalTabLayout>
    with SingleTickerProviderStateMixin {
  var selectedTabIndex = 0;
  AnimationController _controller;
  Animation<double> _animation;
  Animation<Offset> _slideAnimation;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
        vsync: this, duration: const Duration(milliseconds: 1000));
    _animation = Tween<double>(begin: 0.0, end: 1.0).animate(_controller);
    _slideAnimation = Tween<Offset>(begin: Offset(0.3, 0), end: Offset(-0.02, 0))
        .animate(_controller);
  }

  playingAnimation() {
    _controller.reset();
    _controller.forward();
  }

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Container(
      height: size.height * 0.6,
      child: Stack(
        children: <Widget>[
          Positioned(
            left: 0.0,
            bottom: 0,
            top: 0,
            width: size.width * .25,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                TabText(
                  text: "Media",
                  isSelected: selectedTabIndex == 0,
                  onTapTab: () {
                    onTabTap(0);
                  },
                ),
                TabText(
                  text: "Streamers",
                  isSelected: selectedTabIndex == 1,
                  onTapTab: () {
                    onTabTap(1);
                  },
                ),
                TabText(
                  text: "Forum",
                  isSelected: selectedTabIndex == 2,
                  onTapTab: () {
                    onTabTap(2);
                  },
                ),
              ],
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(left: 72),
            child: FutureBuilder(
                future: playingAnimation(),
                builder: (context, snapshot) {
                  return SlideTransition(
                    child: FadeTransition(
                      opacity: _animation,
                      child: ListView(
                        controller: PageController(),
                        scrollDirection: Axis.horizontal,
                        children: getList(selectedTabIndex),
                      ),
                    ),
                    position: _slideAnimation,
                  );
                }),
          )
        ],
      ),
    );
  }

  List<Widget> getList(index) {
    return [
      [
        ForumCard(forum: fortniteForum),
        ForumCard(forum: pubgForum),
      ],
      [
        ForumCard(forum: fortniteForum),
        ForumCard(forum: pubgForum),
      ],
      [
        ForumCard(forum: fortniteForum),
        ForumCard(forum: pubgForum),
      ]
    ][index];
  }

  onTabTap(int index) {
    setState(() {
      selectedTabIndex = index;
    });
  }
}
