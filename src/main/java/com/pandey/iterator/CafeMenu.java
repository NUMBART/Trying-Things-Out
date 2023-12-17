package com.pandey.iterator;

import java.util.HashMap;
import java.util.Iterator;

public class CafeMenu implements Menu {
    HashMap<String, MenuItem> menuItems = new HashMap<>();
    public CafeMenu() {
        addItem("Veggie Burger and Air Fries",
                "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
                true, 3.99);
        addItem("Soup of the day",
                "A cup of the soup of the day, with a side salad",
                false, 3.69);
        addItem("Burrito",
                "A large burrito, with whole pinto beans, salsa, guacamole",
                true, 4.29);
    }

    private void addItem(String name, String s, boolean b, double v) {
        MenuItem menuItem = new MenuItem(name, s, b, v);
        menuItems.put(name, menuItem);
    }
    @Override
    public Iterator<MenuItem> createIterator() {
        return menuItems.values().iterator();
    }
    public HashMap<String, MenuItem> getMenuItems() {
        return menuItems;
    }
}
