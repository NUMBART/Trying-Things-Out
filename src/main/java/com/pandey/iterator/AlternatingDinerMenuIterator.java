package com.pandey.iterator;

import java.util.Calendar;
import java.util.Iterator;

public class AlternatingDinerMenuIterator implements Iterator {
    MenuItem[] menuItems;
    int position = 0;
    public AlternatingDinerMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
        position = Calendar.DAY_OF_WEEK % 2;
    }
    @Override
    public Object next() {
        MenuItem menuItem = menuItems[position];
        position += 2;
        return menuItem;
    }
    public boolean hasNext() {
        if(position >= menuItems.length || menuItems[position] == null)
            return false;
        else
            return true;
    }
}
