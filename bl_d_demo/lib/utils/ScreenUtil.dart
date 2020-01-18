import 'package:flutter/material.dart';

class ScreenUtil {
  static ScreenUtil _instance;
  static const int defaultWidth = 1080;
  static const int defaultHeight = 1920;

  num uiWidthPx;
  num uiHeightPx;

  bool allowFontScaling;

  static MediaQueryData _mediaQueryData;
  static double _screenWidth;
  static double _screenHeight;
  static double _pixelRatio;
  static double _statusBarHeight;
  static double _bottomBarHeight;
  static double _textScaleFactor;

  ScreenUtil._();

  factory ScreenUtil() {
    return _instance;
  }

  static void init(BuildContext context,
      {num width = defaultWidth,
      num height = defaultHeight,
      bool allowFontScaling = false}) {
    if (_instance == null) {
      _instance = ScreenUtil._();
    }
    _instance.uiWidthPx = width;
    _instance.uiHeightPx = height;
    _instance.allowFontScaling = allowFontScaling;

    var mediaQuery = MediaQuery.of(context);
    _mediaQueryData = mediaQuery;
    _pixelRatio = mediaQuery.devicePixelRatio;
    _screenHeight = mediaQuery.size.height;
    _screenWidth = mediaQuery.size.width;
    _statusBarHeight = mediaQuery.padding.top;
    _bottomBarHeight = mediaQuery.padding.bottom;
    _textScaleFactor = mediaQuery.textScaleFactor;
  }

  static MediaQueryData get mediaQueryData => _mediaQueryData;

  static double get textScaleFactor => _textScaleFactor;

  static double get pixelRatio => _pixelRatio;

  static double get screenWidthDp => _screenWidth;

  static double get screenHeightDp => _screenHeight;

  static double get screenHeight => _screenHeight * _pixelRatio;

  static double get screenWidth => _screenWidth * _pixelRatio;

  static double get statusBarHeight => _statusBarHeight;

  static double get bottomBarHeight => _bottomBarHeight;

  double get scaleWidth => _screenWidth / uiWidthPx;

  double get scaleHeight => _screenHeight / uiHeightPx;

  double get scaleText => scaleWidth;

  num setWidth(num width) => width * scaleWidth;

  num setHeight(num height) => height * scaleHeight;

  num setSp(num fontSize, {bool allowFontScalingSelf}) =>
      allowFontScalingSelf == null
          ? (allowFontScaling
              ? (fontSize * scaleText)
              : ((fontSize * scaleText) / _textScaleFactor))
          : (allowFontScalingSelf
              ? (fontSize * scaleText)
              : ((fontSize * scaleText) / _textScaleFactor));
}
