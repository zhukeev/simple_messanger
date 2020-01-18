
import 'package:flutter/material.dart';
import 'package:flutter_navigation_bloc/ui/FirendsPage.dart';
import 'package:flutter_navigation_bloc/ui/HomePage.dart';

class NavigationProvider{
  Widget currentNavigation = FriendsPage();
  void updateNavigation(Widget nav){
    currentNavigation = nav;
  }
}

