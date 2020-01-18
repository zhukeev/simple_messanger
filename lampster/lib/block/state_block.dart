import 'dart:async';

import 'state_provider.dart';

class StateBlock {
  StreamController animationController = StreamController();
  final StateProvider stateProvider = StateProvider();

  Stream get animationStatus => animationController.stream;

  void toggleAnimation(){
    stateProvider.toggleAnimationValue();
    animationController.sink.add(stateProvider.isAnimating);
  }

  void dispose(){
    animationController.close();
  }
}

final stateBlock = StateBlock();
