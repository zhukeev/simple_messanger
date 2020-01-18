import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_navigation_bloc/bloc/bloc_provider.dart';

class NavigationDrawerBloc {
  final _navigationController = StreamController();
  NavigationProvider navigationProvider = NavigationProvider();

  Stream get getNavigation => _navigationController.stream;

  void updateNavigation(Widget nav) {
    navigationProvider.updateNavigation(nav);
    
    _navigationController.sink.add(navigationProvider.currentNavigation);
  }

  void dispose() {
    _navigationController.close();
  }
}

final bloc = NavigationDrawerBloc();
