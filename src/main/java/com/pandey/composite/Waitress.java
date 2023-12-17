package com.pandey.composite;

import lombok.AllArgsConstructor;

import java.util.Iterator;

@AllArgsConstructor
public class Waitress {
    MenuComponent allMenus;
    public void printMenu() {
        allMenus.print();
    }
    public void printVegetarianMenu() {
        Iterator<MenuComponent> iterator = allMenus.createIterator();

        System.out.println("\nVEGETARIAN MENU\n----");
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            try {
                if (menuComponent.isVegetarian()) {
                    menuComponent.print();
                }
            } catch (UnsupportedOperationException e) {}
        }
    }
}
