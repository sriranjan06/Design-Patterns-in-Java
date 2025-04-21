public class MenuCompositeTestDrive {
    public static void main(String args[]) {
        MenuComponent pancakeHouseMenu =
                new MenuComposite("PANCAKE HOUSE MENU", "Breakfast");
        MenuComponent dinerMenu =
                new MenuComposite("DINER MENU", "Lunch");
        MenuComponent cafeMenu =
                new MenuComposite("CAFE MENU", "Dinner");
        MenuComponent dessertMenu =
                new MenuComposite("DESSERT MENU", "Dessert of course!");
        MenuComponent coffeeMenu = new MenuComposite("COFFEE MENU", "Stuff to go with your afternoon coffee");

        MenuComponent allMenus = new MenuComposite("ALL MENUS", "All menus combined");

        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);

        pancakeHouseMenu.add(new MenuCompositeItem(
                "K&B's Pancake Breakfast",
                "Pancakes with scrambled eggs and toast",
                true,
                2.99));
        pancakeHouseMenu.add(new MenuCompositeItem(
                "Regular Pancake Breakfast",
                "Pancakes with fried eggs, sausage",
                false,
                2.99));
        pancakeHouseMenu.add(new MenuCompositeItem(
                "Blueberry Pancakes",
                "Pancakes made with fresh blueberries, and blueberry syrup",
                true,
                3.49));
        pancakeHouseMenu.add(new MenuCompositeItem(
                "Waffles",
                "Waffles with your choice of blueberries or strawberries",
                true,
                3.59));

        dinerMenu.add(new MenuCompositeItem(
                "Vegetarian BLT",
                "(Fakin') Bacon with lettuce & tomato on whole wheat",
                true,
                2.99));
        dinerMenu.add(new MenuCompositeItem(
                "BLT",
                "Bacon with lettuce & tomato on whole wheat",
                false,
                2.99));
        dinerMenu.add(new MenuCompositeItem(
                "Soup of the day",
                "A bowl of the soup of the day, with a side of potato salad",
                false,
                3.29));
        dinerMenu.add(new MenuCompositeItem(
                "Hot Dog",
                "A hot dog, with saurkraut, relish, onions, topped with cheese",
                false,
                3.05));
        dinerMenu.add(new MenuCompositeItem(
                "Steamed Veggies and Brown Rice",
                "Steamed vegetables over brown rice",
                true,
                3.99));

        dinerMenu.add(new MenuCompositeItem(
                "Pasta",
                "Spaghetti with marinara sauce, and a slice of sourdough bread",
                true,
                3.89));

        dinerMenu.add(dessertMenu);

        dessertMenu.add(new MenuCompositeItem(
                "Apple Pie",
                "Apple pie with a flakey crust, topped with vanilla icecream",
                true,
                1.59));

        dessertMenu.add(new MenuCompositeItem(
                "Cheesecake",
                "Creamy New York cheesecake, with a chocolate graham crust",
                true,
                1.99));
        dessertMenu.add(new MenuCompositeItem(
                "Sorbet",
                "A scoop of raspberry and a scoop of lime",
                true,
                1.89));

        cafeMenu.add(new MenuCompositeItem(
                "Veggie Burger and Air Fries",
                "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
                true,
                3.99));
        cafeMenu.add(new MenuCompositeItem(
                "Soup of the day",
                "A cup of the soup of the day, with a side salad",
                false,
                3.69));
        cafeMenu.add(new MenuCompositeItem(
                "Burrito",
                "A large burrito, with whole pinto beans, salsa, guacamole",
                true,
                4.29));

        cafeMenu.add(coffeeMenu);

        coffeeMenu.add(new MenuCompositeItem(
                "Coffee Cake",
                "Crumbly cake topped with cinnamon and walnuts",
                true,
                1.59));
        coffeeMenu.add(new MenuCompositeItem(
                "Bagel",
                "Flavors include sesame, poppyseed, cinnamon raisin, pumpkin",
                false,
                0.69));
        coffeeMenu.add(new MenuCompositeItem(
                "Biscotti",
                "Three almond or hazelnut biscotti cookies",
                true,
                0.89));

        WaitressComposite waitressComposite = new WaitressComposite(allMenus);

        waitressComposite.printMenu();
    }
}
