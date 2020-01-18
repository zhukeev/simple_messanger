import 'package:flutter/material.dart';

double iconSize = 30;
CarList carList = CarList(cars: [
  Car(companyName: "Chevrolet", carName: "Corvette", price: 2100, imgList: [
    'corvette_front.png',
    'corvette_front2.png',
    'corvette_back.png',
    'interior1.PNG',
    'interior2.PNG'
  ], offerDetails: [
    {Icon(Icons.bluetooth, size: iconSize): "Automatic"},
    {Icon(Icons.airline_seat_individual_suite, size: iconSize): "4 seats"},
    {Icon(Icons.pin_drop, size: iconSize): "6.4L"},
    {Icon(Icons.shutter_speed, size: iconSize): "5HP"},
    {Icon(Icons.invert_colors, size: iconSize): "Variant color"},
  ], specifications: [
    {
      Icon(
        Icons.av_timer,
        size: iconSize,
      ): {"Milegp(upto)": "14.2 kmpl"}
    },
    {
      Icon(
        Icons.power,
        size: iconSize,
      ): {"Engine(upto)": "3996 cc"}
    },
    {
      Icon(
        Icons.assignment_late,
        size: iconSize,
      ): {"BHP": "700"}
    },
    {
      Icon(
        Icons.account_balance_wallet,
        size: iconSize,
      ): {"More Specs": "14.2  kmpl"}
    },
    {
      Icon(
        Icons.cached,
        size: iconSize,
      ): {"More Specs": "14.2  kmpl"}
    },
  ], features: [
    {Icon(Icons.bluetooth, size: iconSize): "Bluetooth"},
    {Icon(Icons.usb, size: iconSize): "USB port"},
    {Icon(Icons.power_settings_new, size: iconSize): "Keyless"},
    {Icon(Icons.android, size: iconSize): "Android Auto"},
    {Icon(Icons.ac_unit, size: iconSize): "AC"},
  ]),
  Car(companyName: "Lamborghini", carName: "Aventador", price: 3000, imgList: [
    'lambo_front.PNG',
    'lambo_back.PNG',
    'interior_lambo.PNG'
  ], offerDetails: [
    {Icon(Icons.bluetooth, size: iconSize): "Automatic"},
    {Icon(Icons.airline_seat_individual_suite, size: iconSize): "4 seats"},
    {Icon(Icons.pin_drop, size: iconSize): "6.4L"},
    {Icon(Icons.shutter_speed, size: iconSize): "5HP"},
    {Icon(Icons.invert_colors, size: iconSize): "Variant color"},
  ], specifications: [
    {
      Icon(
        Icons.av_timer,
        size: iconSize,
      ): {"Milegp(upto)": "14.2 kmpl"}
    },
    {
      Icon(
        Icons.power,
        size: iconSize,
      ): {"Engine(upto)": "3996 cc"}
    },
    {
      Icon(
        Icons.assignment_late,
        size: iconSize,
      ): {"BHP": "700"}
    },
    {
      Icon(
        Icons.account_balance_wallet,
        size: iconSize,
      ): {"More Specs": "14.2  kmpl"}
    },
    {
      Icon(
        Icons.cached,
        size: iconSize,
      ): {"More Specs": "14.2  kmpl"}
    },
  ], features: [
    {Icon(Icons.bluetooth, size: iconSize): "Bluetooth"},
    {Icon(Icons.usb, size: iconSize): "USB port"},
    {Icon(Icons.power_settings_new, size: iconSize): "Keyless"},
    {Icon(Icons.android, size: iconSize): "Android Auto"},
    {Icon(Icons.ac_unit, size: iconSize): "AC"},
  ]),
]);

class CarList {
  List<Car> cars;

  CarList({@required this.cars});
}

class Car {
  String companyName;
  String carName;
  int price;
  List<String> imgList;
  List<Map<Icon, String>> offerDetails;
  List<Map<Icon, String>> features;
  List<Map<Icon, Map<String, String>>> specifications;

  Car(
      {this.companyName,
      this.carName,
      this.price,
      this.imgList,
      this.offerDetails,
      this.features,
      this.specifications});
}
