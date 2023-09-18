package com.hope;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
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
        Person p4 = new Person(4, "Lakis", LocalDate.of(2012, 10, 13));
        Person p5 = new Person(5, "Axios", LocalDate.of(1980, 12, 6));

        Product prod1 = new Product(1, "Cevada", 17.81);
        Product prod2 = new Product(2, "Trigo", 25.35);
        Product prod3 = new Product(3, "Maiz", 77.22);
        Product prod4 = new Product(4, "Trigo", 24.65);

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

        // 3- Sorted (param: Comparator) Ordenando de manera asc desc por el campo nombre de la lista de Personas, por fecha de nacimiento
        Comparator<Person> byNameAsc = (Person o1, Person o2) -> o1.getName().compareTo(o2.getName());
        Comparator<Person> byNameDesc = (Person o1, Person o2) -> o2.getName().compareTo(o1.getName());
        Comparator<Person> byBirthDate = (Person o1, Person o2) -> o1.getBirthDate().compareTo(o2.getBirthDate());
        List<Person> filteredList3 = persons.stream()
                                                .sorted(byBirthDate)
                                                .collect(Collectors.toList());
        //App.printList(filteredList3);

        //4- Match (param: Preducate)
        Predicate<Person> startsWithPredicate = person -> person.getName().startsWith("L");

        // anyMatch: No evalua todo el stream, termina en la coincidencia
        boolean rpta1 = persons.stream()
                                    .anyMatch(startsWithPredicate);
        
        // allMatch: Evalua todo el stream bajo la condición; todos los nombres que comiencen con L
        boolean rpta2 = persons.stream()
                                    .allMatch(startsWithPredicate);

        // noneMatch: Evalua todo el stream bajo la condicion
        boolean rpta3 = persons.stream()
                                    .noneMatch(startsWithPredicate);

        // 5- Limit / Skip
        int pageNumber = 0;
        int pageSize = 2;
        List<Person> filteredList4 = persons.stream()
                                                .skip(pageNumber * pageSize)
                                                .limit(pageSize)
                                                .collect(Collectors.toList());
        
        // 6- Collectors
        // GroupBy
        Map<Double, List<Product>> collect1 = products.stream()
                                                        .filter(p -> p.getPrice() > 20 )
                                                        .collect(Collectors.groupingBy(Product::getPrice));
        // System.out.println(collect1);

        // Counting - Contar cantidad de elementos de acuerdo a un criterio que estoy agrupando
        Map<String, Long> collect2 = products.stream()
                                                .collect(Collectors.groupingBy(
                                                                    Product::getName, Collectors.counting()
                                                                    )
                                                );

        // Agrupando por nombre producto y sumando
        Map<String, Double> collect3 = products.stream()
                                                .collect(Collectors.groupingBy(
                                                                    Product::getName, Collectors.summingDouble(Product::getPrice)
                                                                    )
                                                );

        // Obteniendo suma y resumen
        DoubleSummaryStatistics statistics = products.stream()
                                                        .collect(Collectors.summarizingDouble(Product::getPrice));
        
        // 7- Reduce
        Optional<Double> sum = products.stream()
                    .map(Product::getPrice)
                    .reduce(Double::sum);
                    //.reduce((a,b) -> a + b)
        System.out.println(sum.get());
    }

    public static int getAge(LocalDate birthDate){
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public static void printList(List<?> list){
        list.forEach(System.out::println);
    }
}

