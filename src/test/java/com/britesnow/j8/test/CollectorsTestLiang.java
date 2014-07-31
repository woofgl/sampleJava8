package com.britesnow.j8.test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.britesnow.j8.User;

public class CollectorsTestLiang {
    @Test
    public void streamTest(){
        
        String[][] userArray = {
                                    {"1", "John", "12","male"}, 
                                    {"2", "Juli", "21","female"},
                                    {"3", "Jason", "32","male"}, 
                                    {"4","Lily","21","female"}
                                };
        
        List<User> users = Stream.of(userArray)
                                .map(data -> new User(data[0],data[1],data[2],data[3]))
                                .collect(Collectors.toList());
        
        // Accumulate names into a List
        List<String> list = users.stream()
                                .map(User::getName)
                                .collect(Collectors.toList());
        System.out.println(list);
        
        // Accumulate names into a TreeSet
        Set<String> set = users.stream()
                                .map(User::getName)
                                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set);
        
        
        // Convert elements to strings and concatenate them, separated by commas
        String joined = users.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(","));
        System.out.println(joined);
     
        // Compute sum of age of users
        long total = users.stream().collect(Collectors.summingLong(User::getAge));
        System.out.println(total);
        
        // Compute average of age of users
        double averagingAge= users.stream().collect(Collectors.averagingLong(User::getAge));
        System.out.println(averagingAge);
        
        // Group user by sex
        Map<String,List<User>> byDept = users.stream().collect(Collectors.groupingBy(User::getSex));
        System.out.println(byDept);
        
        // Compute sum of age by sex
        Map<String,Long> totalByDept =users.stream().
                                collect(Collectors.groupingBy(User::getSex, Collectors.summingLong(User::getAge)));
        System.out.println(totalByDept);
        
        //Partition user age for 20 
        Map<Boolean,List<User>> passingFailing = users.stream().collect(Collectors.partitioningBy(u -> u.getAge() > 20));
        System.out.println(passingFailing);
        
    }
}
