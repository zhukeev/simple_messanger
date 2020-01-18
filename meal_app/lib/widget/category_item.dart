import 'package:flutter/material.dart';
import 'package:meal_app/model/category.dart';
import 'package:meal_app/pages/category_meals.dart';

class CategoryItem extends StatelessWidget {
  final Category category;

  const CategoryItem({this.category});

  void selectCategory(ctx, String id, String title) {
    Navigator.of(ctx)
        .pushNamed(CategoryMeals.routeName, arguments: {'category': category});

    /* Navigator.of(ctx).push(MaterialPageRoute(builder: (_) {
      return CategoryMeals(categoryId: id,categoryTitle: title,);
    }));*/
  }

  @override
  Widget build(BuildContext context) {
    return InkWell(
      splashColor: Theme.of(context).primaryColor,
      borderRadius: BorderRadius.circular(15),
      onTap: () => selectCategory(context, category.id, category.title),
      child: Container(
        padding: const EdgeInsets.all(15),
        child: Text(category.title, style: Theme.of(context).textTheme.title),
        decoration: BoxDecoration(
            gradient: LinearGradient(
                colors: [category.color.withOpacity(0.7), category.color],
                begin: Alignment.topLeft,
                end: Alignment.bottomRight),
            borderRadius: BorderRadius.circular(15)),
      ),
    );
  }
}
