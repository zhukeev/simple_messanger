import 'topics.dart';

class Forum {
  final String title;
  final String imgPath;
  final String rank;
  final String threads;
  final String subs;
  final List<Topic> topics;

  Forum(
      {this.title,
      this.imgPath,
      this.rank,
      this.threads,
      this.subs,
      this.topics});
}

final fortniteForum = Forum(
    title: 'Fortnite',
    imgPath: 'assets/images/fortnite.jpg',
    rank: '31',
    threads: '88',
    subs: '500+',
    topics: fortniteTopics);
final pubgForum = Forum(
    title: 'PUBG',
    imgPath: 'assets/images/pubg.png',
    rank: '61',
    threads: '28',
    subs: '200+',
    topics: pubgTopics);

final forums = [
  fortniteForum,
  pubgForum
];
