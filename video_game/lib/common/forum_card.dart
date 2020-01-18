import 'package:flutter/material.dart';
import 'package:video_game/common/forum_details.dart';
import 'package:video_game/model/forum.dart';
import 'package:video_game/pages/detials_page.dart';

import 'forum_name.dart';

class ForumCard extends StatelessWidget {
  final Forum forum;

  ForumCard({this.forum});

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: (){
        Navigator.push(context,MaterialPageRoute(builder: (context)=>DetailsPage(forum: forum,)));
      },
      child: SizedBox(
        width: 280,
        child: Card(
          margin: const EdgeInsets.symmetric(vertical: 60, horizontal: 25),
          elevation: 20,
          shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.all(Radius.circular(20))),
          child: ClipRRect(
            borderRadius: BorderRadius.all(Radius.circular(20)),
            child: Stack(
              children: <Widget>[
                Container(
                  margin: const EdgeInsets.only(bottom: 70),
                  child: Hero(
                    tag: 'image-'+forum.title,
                    child: Image.asset(forum.imgPath,
                        width: 280, fit: BoxFit.fitWidth),
                  ),
                ),
                Positioned(
                    left: 0, bottom: 0, right: 0, child: ForumDetailsWidget(forum: forum)),
                Positioned(
                    left: 0, bottom: 70, child: ForumNameWidget(forum: forum)),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
