import 'package:flutter/material.dart';
import 'package:food_app/animation/FadeAnimation.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.grey[100],
        appBar: AppBar(
          leading: Icon(null),
          backgroundColor: Colors.grey[100],
          elevation: 0,
          brightness: Brightness.light,
          actions: <Widget>[
            IconButton(
                icon: Icon(
                  Icons.shopping_basket,
                  color: Colors.grey[800],
                ),
                onPressed: () {})
          ],
        ),
        body: SafeArea(
            child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  FadeAnimation(
                    delay: 1,
                    child: Text(
                      'Food Delivery',
                      style: TextStyle(
                          fontSize: 30,
                          color: Colors.grey[80],
                          fontWeight: FontWeight.bold),
                    ),
                  ),
                  SizedBox(
                    height: 20,
                  ),
                  Container(
                    height: 50,
                    child: ListView(
                      scrollDirection: Axis.horizontal,
                      children: <Widget>[
                        FadeAnimation(
                          delay: .5,
                          child: makeCategory(isActive: true, title: 'Pizza'),
                        ),
                        FadeAnimation(
                          delay: .6,
                          child:
                              makeCategory(isActive: false, title: 'Burgers'),
                        ),
                        FadeAnimation(
                          delay: .7,
                          child: makeCategory(isActive: false, title: 'Kebab'),
                        ),
                        FadeAnimation(
                          delay: .8,
                          child: makeCategory(isActive: false, title: 'Desert'),
                        ),
                        FadeAnimation(
                          delay: .9,
                          child: makeCategory(isActive: false, title: 'Soup'),
                        ),
                        FadeAnimation(
                          delay: 1.0,
                          child:
                              makeCategory(isActive: false, title: 'Lasagna'),
                        ),
                        FadeAnimation(
                          delay: 1.1,
                          child: makeCategory(isActive: false, title: 'Borsch'),
                        ),
                      ],
                    ),
                  )
                ],
              ),
            ),
            FadeAnimation(
              delay: .5,
              child: Padding(
                padding: const EdgeInsets.all(20),
                child: Text(
                  'Free Delivery',
                  style: TextStyle(
                      color: Colors.grey[700],
                      fontSize: 20,
                      fontWeight: FontWeight.bold),
                ),
              ),
            ),
            Expanded(
                child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20,vertical: 10),
              child: ListView(
                scrollDirection: Axis.horizontal,
                children: <Widget>[
                  FadeAnimation(
                    delay: 1,
                    child: makeItem(image: 'assets/images/one.jpg'),
                  ),FadeAnimation(
                    delay: 1.1,
                    child: makeItem(image: 'assets/images/two.jpg'),
                  ),FadeAnimation(
                    delay: 1.2,
                    child: makeItem(image: 'assets/images/three.jpg'),
                  )
                ],
              ),
            ))
          ],
        )));
  }

  Widget makeItem({String image}) {
    return AspectRatio(
      aspectRatio: 1 / 1.4,
      child: GestureDetector(
        child: Container(
          margin: const EdgeInsets.only(right: 20) ,
          decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              image:
                  DecorationImage(image: AssetImage(image), fit: BoxFit.cover)),
          child: Container(
            decoration: BoxDecoration(

                borderRadius: BorderRadius.circular(20),
                gradient: LinearGradient(begin: Alignment.bottomCenter, stops: [
              .2,
              .9
            ], colors: [
              Colors.black.withOpacity(.9),
              Colors.black.withOpacity(.3),
            ])),
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget>[
                  Align(
                    alignment: Alignment.topRight,
                    child: Icon(
                      Icons.favorite,
                      color: Colors.white,
                    ),
                  ),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                    Text("\$ 15.00",style: TextStyle(color: Colors.white,fontSize: 40,fontWeight: FontWeight.bold),),
                    SizedBox(height: 10,),
                    Text("Vegeterian Pizza",style: TextStyle(color: Colors.white,fontSize: 20)),
                 ],)
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}

Widget makeCategory({bool isActive, String title}) {
  return AspectRatio(
    aspectRatio: isActive ? 3 : 2.0 / 1,
    child: Container(
      margin: const EdgeInsets.only(right: 10),
      decoration: BoxDecoration(
          color: isActive ? Colors.yellow[700] : Colors.white,
          borderRadius: BorderRadius.circular(50)),
      child: Align(
        child: Text(
          title,
          style: TextStyle(
              color: isActive ? Colors.white : Colors.grey[500],
              fontSize: 18,
              fontWeight: isActive ? FontWeight.bold : FontWeight.w100),
        ),
      ),
    ),
  );
}
