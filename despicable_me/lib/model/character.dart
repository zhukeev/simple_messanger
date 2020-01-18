import 'package:flutter/material.dart';

class Character {
  final String name;
  final String imagePath;
  final String description;
  final List<Color> colors;

  Character({this.name, this.imagePath, this.description, this.colors});
}

List characters = [
  Character(
      name: "Kevin",
      imagePath: "assets/images/Kevin_minions.png",
      description:
          "Sir Kevin KBE (formerly known as Kevin) is one of the Minions and the protagonist in the film Minions. Kevin is a tall, two-eyed minion"
          " with sprout cut hair and is usually seen wearing his golf apparel. Kevin loves to make fun of and tease people or Minions, shown when "
          "he made fun of Jerry and teases him for being a coward. He loves playing golf and cricket. In the film Minions he is the leader of the trio"
          " in search of a new master. He truly cares about the well-being of the Minion tribe (which is dependent on them having a proper master).",
      colors: [Colors.orange.shade200, Colors.deepOrange.shade400]),
  Character(
      name: "Agnes",
      imagePath: "assets/images/Agnes_gru.png",
      description:
          "Agnes Gru it is one of Gru and Lucy's three adopted daughters, alongside her sisters Margo and Edith. She is the youngest child of the"
          " three sisters. She greatly adores unicorns, as shown on various occasions. Agnes is a little girl with dark brown eyes. Her long black"
          " hair is tied in an upwards ponytail with a red scrunchie. Most of the time, Agnes wears blue overalls over a yellow and brown striped"
          " t-shirt, and white sneakers with yellow socks. She also wears a white ballet outfit like Edith and Margo (at the ballet recital). "
          "For pajamas, Agnes wears a long blue nightshirt covered with teddy bears and polar bear slippers; her hair stays the same. On her birthday,"
          " Agnes is wearing a dress that resembles a princess riding a unicorn. The colors are similar to her regular outfit. She also wears a blue princess"
          " hat on her head.",
      colors: [Colors.pink.shade200, Colors.redAccent.shade400]),
  Character(
      name: "Vector ",
      imagePath: "assets/images/vector.png",
      description:
          "Vector (formerly Victor Perkins) is the main antagonist of Despicable Me. He is a supervillain and the son of Mr. Perkins, the owner of the Bank of Evil."
          "  The upstart villain is famous for stealing the Great Pyramid of Giza, and becomes Gru\'s archenemy when he attempts to hijack the latter\'s plan"
          " of stealing the Moon. ",
      colors: [Colors.deepOrangeAccent.shade100, Colors.deepOrange.shade900]),
  Character(
      name: "Dr. Nefario ",
      imagePath: "assets/images/nefario.png",
      description: "Dr. Joseph Albert Nefario, most commonly referred to as Dr. Nefario, is a major character in Despicable Me and "
          "a former major antagonist in Despicable Me 2, with cameos in Minions and Despicable Me 3. He is Gru\'s gadgeteer, residing in "
          "his underground lair and laboratories. He builds and designs most of Gru\'s machines. Gru and Dr. Nefario seem to have been "
          "trying to shock the world with \"The True Crime of the Century\" for several years. Although Nefario has doubts about Gru\'s plans "
          "due to lack of funds, and his new found love for his adopted daughters, he still considers him to be \"one of the greats\" in the "
          "supervillain world. Dr. Nefario also has terrible hearing, mistaking Gru\'s orders for \"dart gun\" and \"cookie robots\" as \"fart"
          " gun\" and \"boogie robots\", respectively. He is briefly El Macho\'s right-hand man and main henchman. " ,
      colors: [Colors.yellowAccent.shade100, Colors.black]),
];
