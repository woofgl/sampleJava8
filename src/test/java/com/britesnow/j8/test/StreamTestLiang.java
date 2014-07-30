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
        
        //usersByMale
        Map<String, List<User>> usersByMale = users.stream().filter(u -> u.getSex().equals("male")).collect(groupingBy(User::getSex));
        System.out.println("usersByMale:"+usersByMale+"\n");
        
        //userById
        Map<String, User> userById = users.stream().collect(toMap(User::getId, Function.identity()));
        System.out.println("userById:"+userById+"\n");
        
        //contact stream
        List<User> contactStream = Stream.concat(users.stream(), users.stream()).collect(Collectors.toList());
        System.out.println("contactStream:" + contactStream + "\n");
        
        //generate and iterate
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
        Stream.iterate(1,x -> x + 1).skip(2).limit(5).forEach(System.out::println);
        
        //map
        users.stream().filter(u -> u.getName().startsWith("J")).map(u -> u.getName().toUpperCase()).forEach(System.out::println);
        
        //match
        boolean anyStartsWithJ = users.stream().anyMatch((u) -> u.getName().startsWith("J"));
        System.out.println(anyStartsWithJ);
        
        boolean allStartsWithJ = users.stream().allMatch((u) -> u.getName().startsWith("J"));
        System.out.println(allStartsWithJ);
        
        boolean noneStartsWithJ = users.stream().noneMatch((u) -> u.getName().startsWith("J"));
        System.out.println(noneStartsWithJ);
        
        //count
        long startsWithJ = users.stream().filter((u) -> u.getName().startsWith("J")).count();
        System.out.println(startsWithJ);
        
        //Reduce
        Optional<String> reduced = users.stream().map(u -> u.getName()).sorted().reduce((u1,u2) -> u1 + "#" + u2);
        reduced.ifPresent(System.out::println);
    }
}
