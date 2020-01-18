import 'package:flutter/material.dart';
import 'package:meal_app/model/category.dart';
import 'package:meal_app/widget/category_item.dart';

class CategoriesScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return GridView(
        padding: const EdgeInsets.all(25),
        gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
            maxCrossAxisExtent: 200,
            childAspectRatio: 3 / 2,
            crossAxisSpacing: 20,
            mainAxisSpacing: 20),
        children: categories
            .map((category) => CategoryItem(
                  category: category,
                ))
            .toList());
  }
}
