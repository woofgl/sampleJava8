package com.britesnow.j8.test;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.britesnow.j8.User;

public class StreamTestLiang {
    @Test
    public void streamTest(){
        
        String[][] userArray = {{"1", "John", "12","male"}, {"2", "Juli", "21","female"},{"3", "Jason", "32","male"}, {"4","Lily","21","female"}};
        
        //users
        List<User> users = Stream.of(userArray).map(data -> new User(data[0], data[1], data[2], data[3])).collect(Collectors.toList());
        System.out.println("users:" + users + "\n");
        
        //forEach and forEachOrdered
        System.out.println("list of users:");
        users.stream().forEach(e -> System.out.print(e +" "));
        System.out.println("\n");
        
        System.out.println("Parallel stream:");
        users.parallelStream().forEach(e -> System.out.print(e +" "));
        System.out.println("\n");

        System.out.println("Another parallel stream:");
        users.parallelStream().forEach(e -> System.out.print(e +" "));
        System.out.println("\n");
        
        System.out.println("With forEachOrdered:");
        users.parallelStream().forEachOrdered(e -> System.out.print(e +" "));
        System.out.println("\n");
        
        //usersByMale
        Map<String, List<User>> usersByMale = users.stream().filter(u -> u.getSex().equals("male")).collect(groupingBy(User::getSex));
        System.out.println("usersByMale:\n"+usersByMale+"\n");
        
        //userById
        Map<String, User> userById = users.stream().collect(toMap(User::getId, Function.identity()));
        System.out.println("userById:\n"+userById+"\n");
        
        //contact stream
        List<User> contactStream = Stream.concat(users.stream(), users.stream()).collect(Collectors.toList());
        System.out.println("contact Stream:\n" + contactStream + "\n");
        
        //generate and iterate
        System.out.println("generate :");
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
        System.out.println("\n"+"iterate :");
        Stream.iterate(1,x -> x + 1).skip(2).limit(5).forEach(System.out::println);
        
        //map
        System.out.println("\n"+"map :");
        users.stream().filter(u -> u.getName().startsWith("J")).map(u -> u.getName().toUpperCase()).forEach(System.out::println);
        System.out.println("\n"+"mapToLong :");
        
        users.stream().filter(u -> u.getName().startsWith("J")).mapToLong(u -> u.getAge()).forEach(System.out::println);
        System.out.println("\n");
        
        //flatMap
        System.out.println("flatMap :");
        Stream<String> flatMapStream = Stream.of(userArray).flatMap(x -> Stream.of(x));
        System.out.println("flatMap:[");
        flatMapStream.forEach(System.out::println);
        System.out.println("]\n");
        
        //peek
        System.out.println("peek :");
        List<String> peekStream = users.stream()
                .filter(u ->u.getName().startsWith("J"))
                .peek(e -> {System.out.println("Filtered value:" + e);})
                .map(u -> u.getName())
                .peek(e -> {System.out.println("Mapped value: " + e + "\n"); })
                .collect(Collectors.toList());
        System.out.println("peekStream :\n" + peekStream + "\n");
        
        //distinct
        List<User> distinctStream = users.stream().filter(u -> u.getAge().equals("21")).distinct().collect(Collectors.toList());
        System.out.println("distinct :\n" + distinctStream + "\n");
        
        //sorted
        List<User> sortedStream = users.stream().sorted((x,y) -> y.getAge().compareTo(x.getAge())).collect(Collectors.toList());
        System.out.println("sorted :\n" + sortedStream + "\n");
        
        //match
        boolean anyStartsWithJ = users.stream().anyMatch((u) -> u.getName().startsWith("J"));
        System.out.println("anyMatch :\n" + anyStartsWithJ + "\n");
        
        boolean allStartsWithJ = users.stream().allMatch((u) -> u.getName().startsWith("J"));
        System.out.println("allMatch :\n" + allStartsWithJ + "\n");
        
        boolean noneStartsWithJ = users.stream().noneMatch((u) -> u.getName().startsWith("J"));
        System.out.println("noneMatch :\n" + noneStartsWithJ + "\n");
        
        //count
        long startsWithJ = users.stream().filter((u) -> u.getName().startsWith("J")).count();
        System.out.println(startsWithJ);
        
        //Reduce
        Optional<String> reduced = users.stream().map(u -> u.getName()).sorted().reduce((u1,u2) -> u1 + "#" + u2);
        reduced.ifPresent(System.out::println);
        
        Optional<User> findAny = users.stream().findAny();
        System.out.println(findAny);
        
        Optional<User> findFrist = users.stream().findFirst();
        System.out.println(findFrist+"\n");
        
        //toArray
        User[] toArrayStream = users.stream().filter(p -> p.getSex() == "male").toArray(User[]::new);
        System.out.println("toArray :\n" + toArrayStream + "\n");
    }
}
