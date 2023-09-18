package com.hope;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.hope.model.Person;
import com.hope.model.Product;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Person p1 = new Person(1, "Quijote", LocalDate.of(1991, 1, 21));
        Person p2 = new Person(2, "Leroy", LocalDate.of(1999, 12, 3));
        Person p3 = new Person(3, "Tabita", LocalDate.of(2008, 4, 17));
        Person p4 = new Person(4, "Kummi", LocalDate.of(2012, 10, 13));
        Person p5 = new Person(5, "Axios", LocalDate.of(1980, 12, 6));

        Product prod1 = new Product(1, "Cevada", 17.81);
        Product prod2 = new Product(2, "Trigo", 27.35);
        Product prod3 = new Product(3, "Maiz", 77.22);
        Product prod4 = new Product(4, "Soya", 45.00);

        List < Person > persons = Arrays.asList(p1, p2, p3, p4, p5);
        List < Product > products = Arrays.asList(prod1, prod2, prod3, prod4);

        // Lambda method reference
        // list.forEach(System.out::println)p
        // Programacion imperativa;
        //for (int i=0; i < persons.size(); i++ ) {
        //    System.out.println(persons.get(i));
        //}   


        //for (Person p: persons){
        //    System.out.println(p);
        //}

        // programacion funcional
        //persons.forEach(p -> System.out.println(p));

        // metodos a referencia
        // persons.forEach(System.out::println);

        // 1- Filter (param: Predicate)
        // List<Person> filteredList1 = persons.stream()
        //         .filter(p -> App.getAge(p.getBirthDate()) >= 18 )
        //         .collect(Collectors.toList());
        
        //System.out.println("\nPersonas mayores de 18 años");
        //App.printList(filteredList1);
        // SELECT * FROM PERSONA p WHERE p.edad > 18;

        // 2 - Map (param: Function) - Obtengo una Lista de edades, ya no una Lista de Personas
        // List<Integer> filteredList2 =  persons.stream()
        //                                 .filter(p -> App.getAge(p.getBirthDate()) >= 18 )
        //                                 .map(p -> App.getAge(p.getBirthDate()))
        //                                 .collect(Collectors.toList());

        // 2 - Map (param: Function) - Transformar los nombres de las Personas y agregarle la palabra 'coder'
        // List<String> filteredList2 =  persons.stream()
        //                                 .map(p -> "Coder " + p.getName())
        //                                 .collect(Collectors.toList());

        // 2 - Map (param: Function) - Transformar los nombres de las Personas y agregarle la palabra 'coder'
        // Function<String, String> coderFunction = name -> "Coder " + name;
        // List<String> filteredList2 =  persons.stream()
        //                                 .map(p -> p.getName())
        //                                 .map(coderFunction)
        //                                 .collect(Collectors.toList());

        // 2 - Map (param: Function) - Transformar los nombres de las Personas y agregarle la palabra 'coder'. Metodos a Referencia
        Function<String, String> coderFunction = name -> "Coder " + name;
        List<String> filteredList2 =  persons.stream()
                                        .map(Person::getName)
                                        .map(coderFunction)
                                        .collect(Collectors.toList());
        
        //App.printList(filteredList2);

        // 3- Sorted (param: Comparator) Ordenando de manera ascendente por el campo nombre de la lista de Personas
        Comparator<Person> byNameAsc = (Person o1, Person o2) -> o1.getName().compareTo(o2.getName());
        List<Person> filteredList3 = persons.stream()
                                                .sorted(byNameAsc)
                                                .collect(Collectors.toList());
        App.printList(filteredList3);
    }

    public static int getAge(LocalDate birthDate){
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public static void printList(List<?> list){
        list.forEach(System.out::println);
    }
}

