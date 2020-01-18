import 'package:flutter/material.dart';
import 'package:flutter_navigation_bloc/bloc/bloc_navigation.dart';
import 'package:flutter_navigation_bloc/ui/FirendsPage.dart';
import 'package:flutter_navigation_bloc/ui/gallery_page.dart';
import 'package:flutter_navigation_bloc/ui/settings_page.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      drawer: Drawer(
        child: Column(
          children: <Widget>[
            UserAccountsDrawerHeader(
                accountName: ListTile(
                  title: Text('Zhukeev'),
                  leading: Icon(Icons.person),
                  subtitle: Text('Mobile Developer'),
                ),
                accountEmail: Text('email@example.com')),
            ListTile(
              leading: Icon(Icons.home),
              title: Text('Friends'),
              onTap: () {
                Navigator.pop(context);
                bloc.updateNavigation(FriendsPage());
              },
            ),
            ListTile(
                leading: Icon(Icons.settings),
                title: Text('Settings'),
                onTap: () {
                  Navigator.pop(context);
                  bloc.updateNavigation(SettingsPage());
                }),
            ListTile(
                leading: Icon(Icons.category),
                title: Text('Categories'),
                onTap: () {
                  Navigator.pop(context);
                  bloc.updateNavigation(GalleryPage());
                }),
          ],
        ),
      ),
      body: StreamBuilder(
          initialData: bloc.navigationProvider.currentNavigation,
          stream: bloc.getNavigation,
          builder: (context, snapshot) {
            return bloc.navigationProvider.currentNavigation;
          }),
    );
  }
}
