package com.hope;

import java.time.LocalDate;

import com.hope.model.Person;
import com.hope.model.Product;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Person p1 = new Person(1, "Quijote", LocalDate.of(1991, 1, 21));
        Person p2 = new Person(2, "Leroy", LocalDate.of(1999, 12, 3));
        Person p3 = new Person(3, "Tabita", LocalDate.of(2008, 4, 17));
        Person p4 = new Person(4, "Kummi", LocalDate.of(2022, 10, 13));
        Person p5 = new Person(5, "Axios", LocalDate.of(1980, 12, 6));

        Product prod1 = new Product(1, "Cevada", 17.81);
        Product prod2 = new Product(2, "Trigo", 27.35);
        Product prod3 = new Product(3, "Maiz", 77.22);
        Product prod4 = new Product(4, "Soya", 45.00);

    }
}
