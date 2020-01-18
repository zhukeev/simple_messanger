import 'package:flutter/material.dart';
import 'package:meal_app/model/category.dart';
import 'package:meal_app/model/meal.dart';
import 'package:meal_app/widget/meal_item.dart';

class CategoryMeals extends StatefulWidget {
  static const String routeName = '/category-meals';

  @override
  _CategoryMealsState createState() => _CategoryMealsState();
}

class _CategoryMealsState extends State<CategoryMeals> {
  Category cat;
  List<Meal> displayedMeals;
  var _loadedInitData = false;

  @override
  void didChangeDependencies() {
    if(!_loadedInitData){
      final routeArgs =
      ModalRoute.of(context).settings.arguments as Map<String, Category>;
      cat = routeArgs['category'];
      displayedMeals = meals.where((meal) {
        return meal.categories.contains(cat.id);
      }).toList();
      _loadedInitData = true;
    }
    super.didChangeDependencies();
  }

  void _removeMeal(String id) {
    print('cat_meal ' + id);
    setState(() {
      print(displayedMeals.length);
      displayedMeals.removeWhere((meal) => meal.id == id);
      print(displayedMeals.length);
      print('setState ' + id);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('The ${cat.title} Recipes'),
        ),
        body: ListView.builder(
          itemBuilder: (ctx, index) {
            return MealItem(
              id: displayedMeals[index].id,
              title: displayedMeals[index].title,
              imgUrl: displayedMeals[index].imageUrl,
              duration: displayedMeals[index].duration,
              complexity: displayedMeals[index].complexity,
              affordability: displayedMeals[index].affordability,
              removeItem: _removeMeal,
            );
          },
          itemCount: displayedMeals.length,
        ));
  }
}
