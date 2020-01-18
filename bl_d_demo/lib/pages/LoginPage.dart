import 'package:bl_d_demo/pages/MainPage.dart';
import 'package:bl_d_demo/utils/ScreenUtil.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_svg/flutter_svg.dart';

import '../utils/extensions.dart';

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController _passwordController;

  final _formKey = GlobalKey<FormState>();

  @override
  void initState() {
    super.initState();
    _passwordController = TextEditingController();
  }

  @override
  void dispose() {
    super.dispose();
    _passwordController.dispose();
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setEnabledSystemUIOverlays([SystemUiOverlay.bottom]);
    double insets = MediaQuery.of(context).viewInsets.bottom;
    return Stack(
      children: <Widget>[
        Scaffold(
          backgroundColor: HexColor.fromHex("#0D0D15"),
          body: Form(
              key: _formKey,
              child: Container(
                  padding: const EdgeInsets.all(50),
                  alignment: Alignment.center,
                  child: _components())),
        ),
        Positioned.fill(
          child: Align(
            alignment: Alignment.topLeft,
            child: SvgPicture.asset(
              'assets/images/tl_bg.svg',
              color: Colors.purple,
            ),
          ),
        ),
        Align(
          alignment: Alignment.bottomRight,
          child: SvgPicture.asset(
            'assets/images/br_bg.svg',
            color: Colors.purple,
          ),
        ),
      ],
    );
  }

  InputDecoration _buildInputDecoration(String hint, String iconPath) {
    return InputDecoration(
        focusedBorder: UnderlineInputBorder(
            borderSide: BorderSide(color: Color.fromRGBO(252, 252, 252, 1))),
        hintText: hint,
        enabledBorder: UnderlineInputBorder(
            borderSide: BorderSide(color: Color.fromRGBO(151, 151, 151, 1))),
        hintStyle: TextStyle(color: Color.fromRGBO(252, 252, 252, 1)),
//        icon: iconPath != '' ? Image.asset(iconPath) : null,
        errorStyle: TextStyle(color: Color.fromRGBO(248, 218, 87, 1)),
        errorBorder: UnderlineInputBorder(
            borderSide: BorderSide(color: Color.fromRGBO(248, 218, 87, 1))),
        focusedErrorBorder: UnderlineInputBorder(
            borderSide: BorderSide(color: Color.fromRGBO(248, 218, 87, 1))));
  }

  Widget _buildLogin() {
    return TextFormField(
      validator: (val) => val.isEmpty ? 'Login can not be empty' : null,
      style: TextStyle(color: Colors.white),
      decoration: _buildInputDecoration('Login', ''),
    );
  }

  Widget _buildPassword() {
    return TextFormField(
      obscureText: true,
      controller: _passwordController,
      validator: (value) => value.length <= 6
          ? "Password must be 6 or more characters in length"
          : null,
      style: TextStyle(color: Color.fromRGBO(252, 252, 252, 1)),
      decoration: _buildInputDecoration("Password", ''),
    );
  }

  Widget _components() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        _buildLogin(),
        SizedBox(
          height: ScreenUtil().setHeight(10),
        ),
        _buildPassword(),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            Row(
              children: <Widget>[
                Switch.adaptive(
                  value: true,
                  onChanged: (val) {},
                  inactiveTrackColor: Colors.grey,
                ),
                SizedBox(
                  width: ScreenUtil().setWidth(10),
                ),
                Text(
                  'Touch ID',
                  style: TextStyle(color: Colors.white),
                )
              ],
            ),
            Row(
              children: <Widget>[
                Text(
                  'Remember',
                  style: TextStyle(color: Colors.white),
                ),
                SizedBox(
                  width: ScreenUtil().setWidth(10),
                ),
                Switch.adaptive(
                  value: true,
                  onChanged: (val) {},
                  inactiveTrackColor: Colors.grey,
                ),
              ],
            ),
          ],
        ),
        SizedBox(
          height: ScreenUtil().setHeight(10),
        ),
        MaterialButton(
          hoverColor: Colors.transparent,
          highlightColor: Colors.transparent,
          splashColor: Colors.transparent,
          onPressed: () {
            Navigator.push(
                context, MaterialPageRoute(builder: (_) => MainPage()));
          },
          child: Container(
            width: double.infinity,
            height: ScreenUtil().setHeight(88),
            decoration: BoxDecoration(
                border: Border.all(color: Colors.black54),
                color: Colors.purple,
                boxShadow: [
                  BoxShadow(
                      color: HexColor.fromHex("#568AFD"),
                      offset: Offset(0.0, 5.0),
                      blurRadius: 10,
                      spreadRadius: 3),
                  BoxShadow(
                      color: HexColor.fromHex("#851EDF"),
                      offset: Offset(0.0, -4.0),
                      blurRadius: 10,
                      spreadRadius: 3),
                ],
                gradient: LinearGradient(colors: [
                  HexColor.fromHex("#568AFD"),
                  HexColor.fromHex("#670DF2"),
                  HexColor.fromHex("#851EDF"),
                ]),
                borderRadius: BorderRadius.circular(10)),
            child: Center(
                child: Text(
              "LOGIN",
              style: TextStyle(
                  color: Colors.white,
                  fontSize: ScreenUtil().setSp(22),
                  fontWeight: FontWeight.bold),
              textAlign: TextAlign.center,
            )),
          ),
        ),
      ],
    );
  }
}
