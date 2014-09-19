package com.britesnow.j8.test;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.britesnow.j8.User;

public class CollectorsTestLiang {
    
    static String[][] userArray = {
                            
                            {"1", "John", "12","male"}, 
                            {"2", "Juli", "21","female"},
                            {"3", "Jason", "32","male"}, 
                            {"4","Lily","21","female"}
                        };
    @Test
    public void collectorTest(){
        List<User> users = Stream.of(userArray)
                                .map(data -> new User(data[0],data[1],data[2],data[3]))
                                .collect(Collectors.toList());
        
        // Accumulate names into a List
        List<String> list = users.stream()
                                .map(User::getName)
                                .collect(Collectors.toList());
        System.out.println("nameList:"+list+"\n");
        
        // Accumulate names into a TreeSet
        Set<String> set = users.stream()
                                .map(User::getName)
                                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("TreeSet Name:"+set+"\n");
        
        
        //joining,Convert elements to strings and concatenate them, separated by commas
        String joined = users.stream().map(Object::toString).collect(Collectors.joining());
        System.out.println("Joining:"+joined+"\n");
        
        String joined1 = users.stream().map(Object::toString).collect(Collectors.joining(","));
        System.out.println("Joining:"+joined1+"\n");
        
        String joined2 = users.stream().map(Object::toString).collect(Collectors.joining(",","#","*"));
        System.out.println("Joining2:"+joined2+"\n");
        
        
        //mapping
        Map<Long, Set<String>> mapping = users.stream()
                                .collect(Collectors.groupingBy(User::getAge,Collectors.mapping(User::getName,Collectors.toSet())));
        System.out.println("mapping:"+mapping+"\n");
        
        
        //summing,average,maxBy,minBy,counting
        long total = users.stream().collect(Collectors.summingLong(User::getAge));
        System.out.println("summingLong age:"+total+"\n");
        
        double averagingAge= users.stream().collect(Collectors.averagingLong(User::getAge));
        System.out.println("averagingLong Age:"+averagingAge+"\n");
        
        Optional maxBy =users.stream().collect(Collectors.maxBy((s1,s2) -> s1.getName().length() > s2.getName().length() ? 1 : -1));
        System.out.println("maxBy:"+maxBy+"\n");
        
        Optional minBy2 =users.stream().collect(Collectors.minBy((s1,s2) -> s1.getAge() > s2.getAge() ? 1 : -1));
        System.out.println("minBy:"+minBy2+"\n");
        
        Long counting = users.stream().filter((u) -> u.getName().startsWith("J")).collect(Collectors.counting());
        System.out.println("counting:"+counting+"\n");
        
        
        //groupingBy,groupingByConcurrent
        Map<String,List<User>> byDept = users.stream().collect(Collectors.groupingBy(User::getSex));
        System.out.println("groupingBy sex:"+byDept+"\n");
        
        Map<String,Long> totalByDept =users.stream().collect(Collectors.groupingBy(User::getSex, Collectors.summingLong(User::getAge)));
        System.out.println("summingLong age by sex:"+totalByDept+"\n");
        
        ConcurrentMap<String, List<User>> groupingByConcurrent = users.stream().collect(Collectors.groupingByConcurrent(User::getSex));
        System.out.println("groupingByConcurrent"+groupingByConcurrent+"\n");
        
        Map<String,Long> groupingByC = users.stream().collect(Collectors.groupingByConcurrent(User::getSex,Collectors.summingLong(User::getAge)));
        System.out.println("groupingByConcurrent"+groupingByC+"\n");
        
        //Partition
        Map<Boolean,List<User>> partitioningBy = users.stream().collect(Collectors.partitioningBy(u -> u.getAge() > 20));
        System.out.println("partitioningBy:"+partitioningBy+"\n");
        
        
        //Mapto
        Map<String, User> userById = users.stream().collect(toMap(User::getId, Function.identity()));
        System.out.println("userById:"+userById+"\n");
        
        Map<String,String> toMap = users.stream().collect(toMap(User::getName,User::getSex,(s,a) -> s +","+a));
        System.out.println("toMap:"+toMap+"\n");
        
    }
    
    //create custom collector
    @Test
    public void customCollectorTest(){
        List<User> users = Stream.of(userArray)
                                .map(data -> new User(data[0],data[1],data[2],data[3]))
                                .collect(Collectors.toList());
        System.out.println("Init users:\n" + users + "\n");
        
        ArrayList<User> userArrayList = users.stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println("ArrayList users:\n" + userArrayList + "\n");
        
        ArrayList<User> arrayList = users.parallelStream().collect(new Collector<User,ArrayList,ArrayList<User>>(){

            @Override
            public Supplier supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<ArrayList, User> accumulator() {
                return ArrayList::add;
            }

            @Override
            // use when it is parallelStream
            public BinaryOperator<ArrayList> combiner() {
                return (left, right) -> {
                    right.addAll(left);
                    return right;
                };
            }

            @Override
            public Function finisher() {
                return a -> a;
            }

            @Override
            public Set characteristics() {
                return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
            }
            
        });
        
        System.out.println("Custom ArrayList projects:\n" + arrayList + "\n");
    }
    
    //create custom collector toMap
    @Test
    public void customMapCollectorTest(){
        List<User> users = Stream.of(userArray)
                                .map(data -> new User(data[0],data[1],data[2],data[3]))
                                .collect(Collectors.toList());
        Map<String,List<User>> mapCollector = users.parallelStream().collect(new Collector<User,Map,Map<String,List<User>>>(){
            
            @Override
            public Supplier supplier(){
                return HashMap::new;
            }
            @Override
            public BiConsumer<Map, User> accumulator() {
                Function<User, String> keyMapper = User::getId;
                Function<User, User> valueMapper = Function.identity();
                BinaryOperator<String> operator = (u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); };
                BiConsumer<Map, User> accumulator = (map, element) -> map.merge(keyMapper.apply(element),valueMapper.apply(element), operator);
                return accumulator;
            }

            @Override
            // use when it is parallelStream
            public BinaryOperator<Map> combiner() {
                return (left, right) -> {
                    right.putAll(left);
                    return right;
                };
            }

            @Override
            public Function finisher() {
                return a -> a;
            }

            @Override
            public Set characteristics() {
                return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
            }
            
        });
        
        System.out.println("Custom map projects:\n" + mapCollector + "\n");
        
    }
    
    //custom set collector
    @Test
    public void customSetCollectorTest(){
        List<User> users = Stream.of(userArray)
                                .map(data -> new User(data[0],data[1],data[2],data[3]))
                                .collect(Collectors.toList());
        
        Set<User> tosetCollector = users.stream().collect(Collectors.toSet());
        System.out.println("to set Collector :\n" + tosetCollector + "\n");
        Set<User> setCollector = users.parallelStream().collect(new Collector<User,Set,Set<User>>(){
            
            @Override
            public Supplier supplier() {
                return HashSet::new;
            }

            @Override
            public BiConsumer<Set, User> accumulator() {
                return Set::add;
            }

            @Override
            // use when it is parallelStream
            public BinaryOperator<Set> combiner() {
                return (left, right) -> {
                    right.addAll(left);
                    return right;
                };
            }

            @Override
            public Function finisher() {
                return a -> a;
            }

            @Override
            public Set characteristics() {
                return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
            }
            
        });
        System.out.println("set Collector:\n" + setCollector + "\n");
    }
    
    @Test
    public void groupCollectorTest(){
        List<User> users = Stream.of(userArray)
                                .map(data -> new User(data[0],data[1],data[2],data[3]))
                                .collect(Collectors.toList());
        
        Map<String,List<User>> groupingByCollector = users.stream().collect(Collectors.groupingBy(User::getSex));
        System.out.println("grouping by collector:\n" + groupingByCollector + "\n");
        
        Map<String,List<User>> groupingCollector = users.parallelStream().collect(new Collector<User,Map<String,List<User>>,Map<String,List<User>>>(){
            @Override
            public Supplier supplier(){
                return HashMap::new;
            }
            @Override
            public BiConsumer<Map<String, List<User>>, User> accumulator() {
                Function<User, String> keyMapper = User::getSex;
                BiConsumer<Map<String, List<User>>, User> accumulator = (map, element) -> {
                    String key = keyMapper.apply(element);
                    List<User> container = map.computeIfAbsent(key, new Function<String, List<User>>() {
                        @Override
                        public List<User> apply(String k) {
                            return new ArrayList<User>();
                        }
                    });
                    container.add(element);
                };
                return accumulator;
            }

            @Override
            // use when it is parallelStream
            public BinaryOperator<Map<String, List<User>>> combiner() {
                return (m1, m2) -> {
                    for (Map.Entry<String,List<User>> e : m2.entrySet())
                        m1.merge(e.getKey(), e.getValue(), new BinaryOperator<List<User>>(){
                            public List<User> apply(List<User> t, List<User> u) {
                                t.addAll(u);
                                return t;
                            }});
                    return m1;
                };
            }

            @Override
            public Function finisher() {
                return a -> a;
            }

            @Override
            public Set characteristics() {
                return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
            }
            
        });
        
        System.out.println("grouping collector:\n" + groupingCollector + "\n");
    }
}
