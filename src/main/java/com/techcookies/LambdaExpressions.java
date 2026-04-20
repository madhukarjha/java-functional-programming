package com.techcookies;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Map;

public class LambdaExpressions {
	
	public static void main(String[] args) {
		
		//Assign a lambda to a `BinaryOperator<Integer>` that returns the product of two integers. Test with three pairs.
		System.out.println("Q1 - Assign a lambda to a `BinaryOperator<Integer>` that returns the product of two integers. Test with three pairs.");
		BinaryOperator<Integer> multiply = (a, b)-> a * b;
		System.out.println(multiply.apply(4, 5));
		System.out.println(multiply.apply(3, 7));
		System.out.println(multiply.apply(10, 0));
		
		System.out.println("Q2 — Check String Starts with 'A'.");
		Predicate<String> strStartsWithA = (str)-> str.startsWith("A") ;
		System.out.println(strStartsWithA.test("Alice"));
        System.out.println(strStartsWithA.test("Bob"));
        System.out.println(strStartsWithA.test("Andrew"));
        System.out.println(strStartsWithA.test("anna"));
        
        System.out.println("Q3 —  Sort Integers Descending with Lambda Comparator.");
        List<Integer> sortQ3 = Arrays.asList(3, 1, 7, 2, 9, 4);
        sortQ3.sort((a, b)-> b - a);
        System.out.println(sortQ3);
        
        System.out.println("Q4 — Print Names in Uppercase using forEach + Lambda.");
        List<String> upperCaseQ4 = Arrays.asList("alice", "bob", "charlie", "diana");
        upperCaseQ4.forEach((str)->  System.out.println(str.toUpperCase()));

        System.out.println("---------------------------------------------------------------");
        System.out.println("2. Streams, Filters, and Lambdas");
        System.out.println("Q1 - Extract only even numbers from a list using stream().filter().\n");
        
        List<Integer> evenQ21 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenFiltered = evenQ21
        		.stream()
        		.filter(num -> num % 2 == 0)
        		.collect(Collectors.toList());
        System.out.println(evenFiltered);    
		
        System.out.println("Q2 - Keep only words whose length is greater than 4.");
        List<String> wordsGreaterThan4Q22Source = Arrays.asList("cat", "elephant", "dog", "butterfly", "ox");
        List<String> wordsGreaterThan4Q22 = wordsGreaterThan4Q22Source
        		.stream()
        		.filter(str -> str.length() > 4)
        		.collect(Collectors.toList());
        System.out.println(wordsGreaterThan4Q22);
        
        System.out.println("Q3 - Use two chained filter() calls to get numbers strictly between 3 and 8.");
        List<Integer> numbersBetween3and8Q23Source = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numbersBetween3and8Q23 = numbersBetween3and8Q23Source
			        .stream()
			        .filter(n -> n > 3)
			        .filter(n -> n < 8)
			        .collect(Collectors.toList());
        System.out.println(numbersBetween3and8Q23);
        
        System.out.println("Q4 - Filter a list of Person objects, keeping only those aged 18 or above.");
        record Person(String name, int age) {};
        List<Person> peopleObjectAgeGrt18Q24Source = List.of(
                new Person("Alice", 22),
                new Person("Bob",   16),
                new Person("Carol", 30),
                new Person("Dave",  15)
            );
        
       peopleObjectAgeGrt18Q24Source
        .stream()
        .filter(p -> p.age > 18)
        .collect(Collectors.toList())
        .forEach(p -> System.out.println("Name - " + p.name + " ,Age - "+ p.age));
       
       System.out.println("Q5 - Count how many numbers in a list are greater than 50.");
       List<Integer> numsGreaterThan50Q25 = Arrays.asList(10, 55, 30, 70, 45, 90, 20);
       long count = numsGreaterThan50Q25
    		   .stream()
    		   .filter(n -> n > 50)
    		   .count();
       System.out.println(count);
       
       System.out.println("---------------------------------------------------------------"); 
       System.out.println("3. map() and filter() vs map()");
       System.out.println("Q1 - Use map() to produce a list where each element is the square of the original.");
       List<Integer> squareQ31Source = Arrays.asList(1, 2,3,4,5,6,7,8,9,10);
       List<Integer> squareQ31 = squareQ31Source.stream().map(m-> m * m).collect(Collectors.toList());
       System.out.println(squareQ31);
       
       System.out.println("Q2 - Use map() to extract only the name field from a list of Person objects.");
       List<Person> peopleQ32Source = List.of(
               new Person("Alice", 30),
               new Person("Bob",   25),
               new Person("Carol", 35)
           );
       List<String> peopleQ32 = peopleQ32Source
    		   .stream()
    		   .map(Person::name)
    		   .collect(Collectors.toList());
       System.out.println(peopleQ32);
       
       System.out.println("Q3 - Filter numbers greater than 5, then multiply each survivor by 10.");
       List<Integer> filterMapQ33Source = Arrays.asList(1, 2,3,4,5,6,7,8,9,10);
       List<Integer> filterMapQ33 = filterMapQ33Source
    		   .stream().filter(f-> f > 5)
    		   .map(m-> m * m)
    		   .collect(Collectors.toList());
       System.out.println(filterMapQ33);
        
       System.out.println("Q4 - Use map() to extract the domain part (everything after @) from each email.");
       List<String> emailsQ34Source = Arrays.asList(
               "alice@gmail.com",
               "bob@yahoo.com",
               "carol@company.org"
           );
       
       List<String> emailsQ34 = emailsQ34Source
    		   .stream()
    		   .map(m -> m.substring(m.indexOf("@") + 1))
    		   .collect(Collectors.toList());
       
       System.out.println(emailsQ34);
       
       
       System.out.println("Q5 - From a list of names, keep only names longer than 3 characters and return them uppercased.");
       List<String> namesLongerThan3CharsSource = Arrays.asList("Jo", "Alice", "Li", "Charlie", "Bo", "Sam");
       List<String> namesLongerThan3Chars = namesLongerThan3CharsSource
       .stream()
       .filter(f-> f.length() > 3)
       .map(m-> m.toUpperCase())
       .collect(Collectors.toList());
       System.out.println(namesLongerThan3Chars);
       
       System.out.println("---------------------------------------------------------------"); 
       System.out.println("4. reduce() Stream Operation");
       System.out.println("Q1 - Sum all integers in a list using reduce() with an identity value.");
       List<Integer> numsQ41Source = Arrays.asList(1, 2, 3, 4, 5);
       int total = numsQ41Source.stream().reduce(0, Integer::sum);
       System.out.println(total);
       
       int totalFunction = numsQ41Source.stream().reduce(0, (a, b)-> a + b);
       System.out.println(totalFunction);

       System.out.println("Q2 - Compute the product of all numbers in the list using reduce().");
       List<Integer> numsQ4Source = Arrays.asList(1, 2, 3, 4, 5);
       int numsQ4Total = numsQ4Source.stream().reduce(1, (a, b)-> a * b);
       System.out.println(numsQ4Total);
       
       System.out.println("Q3 - Find the maximum element using reduce() without using max()");
       List<Integer> numsQ43Source = Arrays.asList(3, 7, 1, 9, 4, 6);
       Optional<Integer> maxQ43Total = numsQ43Source.stream().reduce((a, b)-> a >b ? a : b);
       System.out.println(maxQ43Total);
       
       System.out.println("Q4 - Join all words in a list into a single sentence using reduce()");
       List<String> joinWordsSource = Arrays.asList("Java", "is", "fun", "to", "learn");
       Optional<String> joinWordsQ44 = joinWordsSource.stream().reduce((a, b)-> a + " "+ b);
       System.out.println(joinWordsQ44);
             
       System.out.println("Q5 - Compute 6! using IntStream.rangeClosed() and reduce().");
       int n = 6;
       int factorialQ45 = IntStream.rangeClosed(1, n).reduce(1, (a, b)-> a* b);
       System.out.println(factorialQ45);
                  
       System.out.println("---------------------------------------------------------------"); 
       System.out.println("5. distinct(), sorted(), sort()");
       System.out.println("Q1 - Remove duplicate integers from a list, preserving first-seen order."   );
       List<Integer> numsQ51Source = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3);
       List<Integer> numsQ51 = numsQ51Source.stream().distinct().collect(Collectors.toList());
       System.out.println(numsQ51);
       
       System.out.println("Q2 - Sort integers in ascending order using sorted() on a stream.");
       List<Integer> numsQ52Source =Arrays.asList(5, 2, 8, 1, 9, 3);
       List<Integer> numsQ52 = numsQ52Source.stream().sorted().collect(Collectors.toList());
       System.out.println(numsQ52);
       
       System.out.println("Q3 - Sort integers in ascending order using sorted() on a stream.");
       List<Integer> numsQ53Source = Arrays.asList(5, 2, 8, 1, 9, 3);
       List<Integer> numsQ53 = numsQ53Source.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
       System.out.println(numsQ53);
       
       System.out.println("Q4 - Remove duplicates, then sort in ascending order.");
       List<Integer> numsQ54Source = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3);
       List<Integer> numsQ54 = numsQ54Source.stream().distinct().sorted().collect(Collectors.toList());
       System.out.println(numsQ54);
       
       System.out.println("Q5 - Sort words by character length in ascending order.");
       List<String> strQ55Source = Arrays.asList("banana", "apple", "kiwi", "mango", "fig");
       List<String> strQ55 = strQ55Source.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
       System.out.println(strQ55);
       
       
       System.out.println("---------------------------------------------------------------"); 
       System.out.println("6. Collecting Stream Elements to List");
       System.out.println("Q1 - Filter integers greater than 10 and collect to a new List.");
       
       List<Integer> numsQ601Source = Arrays.asList(5, 12, 3, 18, 7, 25, 9);
       List<Integer> numsQ601 = numsQ601Source.stream().filter(f-> f>10).toList();
       System.out.println(numsQ601);
       
       System.out.println("Q2 - Collect distinct characters from a string into a Set, then print sorted.");
       String str = "programming";
       Set<Character> strQ602Source = str.chars().mapToObj(m-> (char)m).sorted(Comparator.reverseOrder()).collect(Collectors.toSet());
       System.out.println(strQ602Source);
       

       System.out.println("Q3 - Collect a list of names into a Map<String, Integer> where key = name, value = length.");
       
       List<String> namesQ603Source = Arrays.asList("Alice", "Bob", "Charlie");
       Map<String, Integer> namesQ603 = namesQ603Source.stream().collect(Collectors.toMap(name->name, String::length));
       System.out.println(namesQ603);
       

       System.out.println("Q4 - Collect odd numbers to an unmodifiable list and verify it throws on mutation.\n");
       List<Integer> namesQ604Source = Arrays.asList(1, 2, 3, 4, 5);
       List<Integer> namesQ604 = namesQ604Source.stream().filter(f-> f%2 != 0).collect(Collectors.toUnmodifiableList());
       System.out.println(namesQ604);
       

       System.out.println("Q5 - Use Collectors.toCollection() to collect sorted names into an ArrayList.");       
       List<String> namesQ605Source = Arrays.asList("Zara", "Alice", "Mia");
       ArrayList<String> namesQ605 =  namesQ605Source.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
       System.out.println(namesQ605);
       
       System.out.println("---------------------------------------------------------------"); 
       System.out.println("7. Functional Interface, Predicate, Consumer");
       System.out.println("Q1 - Predicate to Test Even Number");
       
       Predicate<Integer> isEvenQ71 = q -> q % 2 == 0;
       System.out.println(isEvenQ71.test(4));
       System.out.println(isEvenQ71.test(11));
       System.out.println(isEvenQ71.test(7));
       
       System.out.println("Q2 - Combine two predicates to filter even AND positive numbers.");
       Predicate<Integer> isPositiveQ72 = q->q > 0;
       List<Integer> numsQ72Source = Arrays.asList(-4, 0, 3, 6, -2, 8);
       List<Integer> numsQ72Result= numsQ72Source.stream()
       .filter(isEvenQ71.and(isPositiveQ72))
       .collect(Collectors.toList());       
       System.out.println(numsQ72Result);

       
       System.out.println("Q3 - Chain two Consumers — one prints original, one prints uppercase.");
       Consumer<String> originalQ73 = s -> System.out.println(s);
       Consumer<String> consumerUpperQ73 = s -> System.out.println(s.toUpperCase());
       Consumer<String> ConsumerOriginalUpperQ73 = originalQ73.andThen(consumerUpperQ73);
       ConsumerOriginalUpperQ73.accept("hello");
       List.of("Alice", "Bob").forEach(originalQ73);
       
       System.out.println("Q4 - Define your own functional interface MathOperation and implement add, subtract, multiply.");
       MathsOperation add = (a,b) -> a+b;
       MathsOperation subtract = (a,b) -> a-b;
       MathsOperation multiplyQ74 = (a,b) -> a*b;
       
       System.out.println("5 + 3 = " + apply(5, 3, add));
       System.out.println("5 - 3 = " + apply(5, 3, subtract));
       System.out.println("5 * 3 = " + apply(5, 3, multiplyQ74));
     
       System.out.println("Q5 - Use Predicate.not() (Java 11+) to remove null and blank entries.");
       List<String> dataSourceQ75 = Arrays.asList("Alice", "", "Bob", "  ", "Carol", null);
       List<String> cleanedQ75 =  dataSourceQ75
    		   .stream().filter(s-> s != null)
    		   .filter(Predicate.not(String::isBlank))
    		   .collect(Collectors.toList());
       System.out.println(cleanedQ75);
       
       System.out.println("---------------------------------------------------------------"); 
       System.out.println("8. Behavior Parameterization, Supplier and UnaryOperator");
       System.out.println("Q1 - Write a generic method accepting a Predicate and Consumer to filter and act on a list.");
       List<Integer> numsQ81 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
       Process(numsQ81, f-> f % 2 == 0, System.out::println);
       
       System.out.println("Q2 - Use Supplier for a greeting string, a list factory, and a random number.");
       Supplier<String>       greetingQ82    = () -> "Hello, World!";
       Supplier<List<String>> listFactoryQ82= ArrayList::new;
       System.out.println(greetingQ82.get());

       List<String> list = listFactoryQ82.get();
       list.add("item1");
       System.out.println(list);
       System.out.println(listFactoryQ82.get() == listFactoryQ82.get()); 
       
       
       System.out.println("Q3 - Chain UnaryOperators to trim → lowercase → add \"!\" to strings.");  
       UnaryOperator<String> trimQ83 = String::trim;
       UnaryOperator<String> lowerCaseQ83 = String::toLowerCase; 
       UnaryOperator<String> exclaminationQ83 = s -> s + "!";
       Function<String, String> resultQ83 = trimQ83
    	        .andThen(lowerCaseQ83)
    	        .andThen(exclaminationQ83)
    	        .andThen(String::trim);
       System.out.println(resultQ83.apply("  HELLO  "));
       System.out.println(resultQ83.apply("  JAVA 8  "));
       
       System.out.println("Q4 - Demonstrate that orElse() always evaluates its argument, but orElseGet() is lazy.\n");  
       Optional<String> present = Optional.of("REAL");
       Optional<String> empty   = Optional.empty();

       System.out.println("-- orElse with present --");
       String r1 = present.orElse(expensiveFallback()); // fallback runs!
       System.out.println(r1);

       System.out.println("-- orElseGet with empty --");
       String r2 = empty.orElseGet(() -> expensiveFallback()); // lazy
       System.out.println(r2);
       
       System.out.println("Q5 - Use a BinaryOperator to find the maximum of two values, then reduce a whole list.");  
       BinaryOperator<Integer> maxQ85 = (a, b)-> a > b ? a: b;
       System.out.println(maxQ85.apply(5, 6));
       System.out.println(maxQ85.apply(8, 7));
       List<Integer> numsQ85 = Arrays.asList(4, 7, 2, 9, 1, 5);
       int listMaxQ85 = numsQ85.stream().reduce(Integer.MIN_VALUE, maxQ85);
       System.out.println("Max from a list - " + listMaxQ85);
       
       
       System.out.println("---------------------------------------------------------------"); 
       System.out.println("9. BiPredicate, BiFunction, BiConsumer, Primitive Interfaces");
       System.out.println("Q1 - Use BiFunction<String, String, String> to join two strings with |.");
       BiFunction<String, String, String> joinQ91 = (a, b) -> a + " | " + b;
       BiFunction<Integer, Integer, Integer> powQ91 = (base, exp) -> (int)Math.pow(base, exp);
       System.out.println(joinQ91.apply("Hello ", " World"));
       System.out.println(powQ91.apply(2, 8));
       
       System.out.println("Q2 - Use BiPredicate<String, Character> to check if a string contains a given character.");
       BiPredicate<String, Character> containsQ92 = (s, c) -> s.indexOf(c) >= 0;
       BiPredicate<Integer, Integer> isGreaterQ92 = (a, b) -> a > b;
       System.out.println(containsQ92.test("functional", 'n')); 
       System.out.println(containsQ92.test("java", 'z'));        
       System.out.println(isGreaterQ92.test(10, 5));  
       
       System.out.println("Q3 - Use BiConsumer<String, Integer> to print all key-value pairs in a Map.");
       BiConsumer<String, Integer> printerQ93 = (k, v) -> System.out.println(k + " => "+ v);
       Map<String, Integer> scoresQ93 = new LinkedHashMap();
       scoresQ93.put("Alice", 92);
       scoresQ93.put("Bob",   85);
       scoresQ93.put("Carol", 78);
       scoresQ93.forEach(printerQ93);
       
       System.out.println("Q4 - Use IntPredicate, IntUnaryOperator, and IntBinaryOperator to work with raw int values.");
       IntPredicate      isEvenQ94  = s ->s % 2 == 0;
       IntUnaryOperator  squareQ94  = x -> x * n;
       IntBinaryOperator addIntsQ94 = (a, b) -> a + b;
       System.out.println(isEvenQ94.test(4));            
       System.out.println(squareQ94.applyAsInt(5));       
       System.out.println(addIntsQ94.applyAsInt(3, 7));
       int result = IntStream.rangeClosed(1, 5)
               .filter(isEvenQ94)
               .map(squareQ94)
               .sum();
       System.out.println("Sum of squares of evens 1-5: " + result);
       
       System.out.println("Q5 - Use ToIntFunction<Employee> to extract salary and compute total + max.");
       record Employee(String name, int salary) {}
       List<Employee> emps = List.of(
               new Employee("Alice", 75000),
               new Employee("Bob",   55000),
               new Employee("Carol", 85000)
           );

           ToIntFunction<Employee> getSalary = Employee::salary;

           int totalQ95 = emps.stream().mapToInt(getSalary).sum();
           int max   = emps.stream().mapToInt(getSalary).max().getAsInt();

           System.out.println("Total salary: " + totalQ95);
           System.out.println("Max salary:   " + max);
       
           System.out.println("---------------------------------------------------------------"); 
           System.out.println("10. Custom Class — allMatch(), anyMatch(), noneMatch()");
           System.out.println("Q1 - Check if at least one student has a grade ≥ 50.");
           record Student(String name, int grade) {}
           List<Student> students = List.of(
                   new Student("Alice", 85),
                   new Student("Bob",   42),
                   new Student("Carol", 91)
               );

               boolean anyPassed  = students.stream().anyMatch(s -> s.grade() >= 50);
               boolean anyPerfect = students.stream().anyMatch(s -> s.grade() == 100);

               System.out.println("Any passed (>=50)? " + anyPassed);
               System.out.println("Any scored 100?    " + anyPerfect);
           
           
          System.out.println("Q2 - Check if all employees earn above 30 000, then check above 60 000.");
          record EmployeeQ102(String name, double salary) {}

              List<EmployeeQ102> empsQ102 = List.of(
                  new EmployeeQ102("Alice", 75000),
                  new EmployeeQ102("Bob",   55000),
                  new EmployeeQ102("Carol", 85000)
              );

              boolean all30k = empsQ102.stream().allMatch(e -> e.salary() > 30000);
              boolean all60k = empsQ102.stream().allMatch(e -> e.salary() > 60000);

              System.out.println("All earn > 30k? " + all30k);
              System.out.println("All earn > 60k? " + all60k);
           
          System.out.println("Q3 - Verify that no student has a null name, and no one has a grade below 40.");
          System.out.println("Q4 - Create a Product class and demonstrate all three match operations.");
          System.out.println("Q5 -  Confirm the vacuous truth rules for match operations on an empty stream.");
           
          System.out.println("---------------------------------------------------------------"); 
          System.out.println("11. sorting, sorted(), Comparator with Custom Class");
          System.out.println("Q1 - Sort employees by name in natural alphabetical order.");
          record EmployeeQ111(String name, String dept, double salary) {}
          List<EmployeeQ111> empsQ111 = List.of(
                  new EmployeeQ111("Priya", "IT", 75000),
                  new EmployeeQ111("Rahul", "HR", 55000),
                  new EmployeeQ111("Sneha", "IT", 85000),
                  new EmployeeQ111("Vijay", "HR", 62000)
              );
          empsQ111.stream()
         .sorted(Comparator.comparing(EmployeeQ111::name))
         .map(EmployeeQ111::name)
         .forEach(System.out::println);

          System.out.println("Q2 - Sort employees by salary from highest to lowest.");   
          record EmployeeQ112(String name, double salary) {}
          List<Employee> empsQ112 = List.of(
                  new Employee("Priya", 75000),
                  new Employee("Rahul", 55000),
                  new Employee("Sneha", 85000),
                  new Employee("Vijay", 62000)
              );
          
//          empsQ112.stream()
//          .sorted(Comparator.comparingDouble(EmployeeQ112::salary).reversed())
//          .forEach(e -> System.out.println(e.name() + ": "+ e.salary()));
          System.out.println("Q3 - Primary sort by department (A→Z), secondary sort by name (A→Z).");   
          record EmployeeQ113(String name, String dept) {}
          List<EmployeeQ113> empsQ113 = List.of(
                  new EmployeeQ113("Sneha", "IT"),
                  new EmployeeQ113("Rahul", "HR"),
                  new EmployeeQ113("Priya", "IT"),
                  new EmployeeQ113("Alice", "HR")
              );
          empsQ113.stream().sorted(Comparator.comparing(EmployeeQ113::dept).thenComparing(EmployeeQ113::name))
          .forEach(e -> System.out.println(e.dept() + " | "+ e.name()));
           
          System.out.println("Q4 - Contrast List.sort() (mutates original) vs stream sorted() (non-mutating)");   
          List<Integer> originalQ114 = new ArrayList<>(Arrays.asList(5, 2, 8, 1));
          List<Integer> streamResultQ114 = originalQ114.stream().sorted().collect(Collectors.toList());
          System.out.println("Original after stream sorted: " + originalQ114);
          System.out.println("Stream sorted result:         " + streamResultQ114);
          // List.sort() — mutates original
          originalQ114.sort(Comparator.naturalOrder());
          System.out.println("Original after List.sort():   " + originalQ114);
          
          
          System.out.println("Q5 - Sort a mixed list (some null values) placing nulls at the end.");  
          List<String> namesQ115 = Arrays.asList("Charlie", null, "Alice", null, "Bob");
          List<String> sortedQ115 = namesQ115.stream().sorted(Comparator.nullsLast(Comparator.naturalOrder())).collect(Collectors.toList());
          System.out.println(sortedQ115);
          
          
          System.out.println("---------------------------------------------------------------"); 
          System.out.println("12. Sort on Multiple Criteria");
          System.out.println("Q1 - Sort by last name; within same last name sort by first name.");
          
          
       
       
	}
	
	 @FunctionalInterface
     interface MathsOperation{
  	   int operation(int a, int b);
     }
	  static int apply(int a, int b, MathsOperation op) {
		  return op.operation(a, b);
      }
	  
     static <T> void Process(List<T> data, Predicate<T> condition, Consumer<T> cons){
    	   data.stream().filter(condition).forEach(cons);
       }
     static String expensiveFallback() {
         System.out.println("  [computing fallback...]");
         return "DEFAULT";
     }
}
