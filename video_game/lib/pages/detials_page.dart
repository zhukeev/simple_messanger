import 'package:flutter/material.dart';
import 'package:video_game/common/app_background_dart.dart';
import 'package:video_game/common/label_value_widget.dart';
import 'package:video_game/model/forum.dart';
import 'package:video_game/model/topics.dart';
import 'package:video_game/styleguide/colors.dart';
import 'package:video_game/styleguide/text_styles.dart';

class DetailsPage extends StatefulWidget {
  final Forum forum;

  DetailsPage({this.forum});

  @override
  _DetailsPageState createState() => _DetailsPageState();
}

class _DetailsPageState extends State<DetailsPage>
    with SingleTickerProviderStateMixin {
  AnimationController _controller;
  Animation<double> _fadeAnimation;
  Animation<double> _scaleAnimation;
  Animation<Offset> _offsetAnimation;

  @override
  void initState() {
    super.initState();
    _controller =
        AnimationController(vsync: this, duration: Duration(milliseconds: 500));
    _fadeAnimation = Tween<double>(begin: 0.0, end: 1.0).animate(_controller);
    _scaleAnimation = Tween<double>(begin: 0.0, end: 1.0).animate(_controller);
    _offsetAnimation = Tween<Offset>(begin: Offset(0, 1), end: Offset(0, 0))
        .animate(_controller);
  }

  @override
  void dispose() {
    super.dispose();
    _controller.dispose();
  }

  _playAnimation() {
    _controller.reset();
    _controller.forward();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: <Widget>[
          AppBackground(
            firstColor: firstOrangeCircleColor,
            secondColor: secondOrangeCircleColor,
            thirdColor: thirdOrangeCircleColor,
          ),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              SizedBox(height: 50),
              InkWell(
                onTap: () {
                  Navigator.pop(context);
                },
                child: Padding(
                  padding: const EdgeInsets.only(left: 20.0),
                  child: Icon(Icons.arrow_back, color: Colors.white),
                ),
              ),
              SizedBox(
                height: 40,
              ),
              FutureBuilder(
                  future: _playAnimation(),
                  builder: (context, snapshot) {
                    return FadeTransition(
                      opacity: _fadeAnimation,
                      child: Padding(
                        padding: const EdgeInsets.only(left: 20, right: 100),
                        child: Row(
                          mainAxisSize: MainAxisSize.max,
                          mainAxisAlignment: MainAxisAlignment.spaceAround,
                          children: <Widget>[
                            LabelValueWidget(
                              value: widget.forum.topics.length.toString(),
                              label: "topics",
                              labelStyle: whiteLabelTextStyle,
                              valueStyle: whiteValueTextStyle,
                            ),
                            LabelValueWidget(
                              value: widget.forum.threads,
                              label: "threads",
                              labelStyle: whiteLabelTextStyle,
                              valueStyle: whiteValueTextStyle,
                            ),
                            LabelValueWidget(
                              value: widget.forum.subs,
                              label: "subs",
                              labelStyle: whiteLabelTextStyle,
                              valueStyle: whiteValueTextStyle,
                            ),
                          ],
                        ),
                      ),
                    );
                  }),
              SizedBox(
                height: 20,
              ),
              SizedBox(
                width: double.infinity,
                height: 500,
                child: ClipRRect(
                    borderRadius:
                        BorderRadius.only(topLeft: Radius.circular(60)),
                    child: Hero(
                      tag: 'image-' + widget.forum.title,
                      child: Image.asset(
                        widget.forum.imgPath,
                        fit: BoxFit.fill,
                      ),
                    )),
              ),
            ],
          ),
          Align(
            alignment: Alignment.bottomLeft,
            child: ClipRRect(
                borderRadius: BorderRadius.only(topLeft: Radius.circular(60)),
                child: Container(
                  height: 200,
                  color: Colors.white,
                  child: Padding(
                    padding:
                        const EdgeInsets.only(left: 16, right: 16, top: 24),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Text(
                          'Topic',
                          style: subHeadingStyle.copyWith(fontSize: 26),
                        ),
                        Expanded(
                          child: SlideTransition(
                            position: _offsetAnimation,
                            child: ListView(
                              shrinkWrap: true,
                              children: <Widget>[
                                TopicsTile(topic: widget.forum.topics[0]),
                                TopicsTile(topic: widget.forum.topics[1]),
                              ],
                            ),
                          ),
                        )
                      ],
                    ),
                  ),
                )),
          ),
          Positioned(
            bottom: 165,
            right: 20,
            child: ScaleTransition(
              scale: _scaleAnimation,
              child: Material(
                elevation: 10,
                child: Padding(
                  padding: const EdgeInsets.all(20.0),
                  child: Text(
                    widget.forum.rank,
                    style: rankBigStyle,
                  ),
                ),
                color: Colors.white,
                shape: CircleBorder(),
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class TopicsTile extends StatelessWidget {
  final Topic topic;

  TopicsTile({this.topic});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Row(
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            Text(
              topic.question,
              style: topicQuestionStyle,
            ),
            Container(
              padding:
                  const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(10)),
                  color: primaryColor),
              child: Text(
                topic.answerCount,
                style: topicAnswerCountStyle,
              ),
            )
          ],
        ),
        SizedBox(
          height: 6,
        ),
        Text(
          topic.recentAnswer,
          style: topicAnswerStyle,
        ),
        SizedBox(
          height: 8,
        ),
      ],
    );
  }
}
