package com.practice.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {
	public static void main(String[] args) {
		Stream<String> empty = Stream.empty(); // count = 0
		Stream<Integer> singleElement = Stream.of(1); // count = 1
		Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 2

		List<String> list = Arrays.asList("a", "b", "c");
		Stream<String> fromList = list.stream();
		Stream<String> fromListParallel = list.parallelStream();

		// The count() method determines the number of elements in a finite
		// stream. For an infinite stream, it hangs. Why? Count from 1 to
		// infinity and let us know when you are finished. Or rather don’t do
		// that because we’d rather you study for the exam than spend the rest
		// of your life counting. count() is a reduction because it looks at
		// each element in the stream and returns a single value.
		Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
		System.out.println(s.count()); // 3

		// The min() and max() methods allow you to pass a custom comparator and
		// find the smallest or largest value in a finite stream according to
		// that sort order. Like count(), min() and max() hang on an infinite
		// stream because they cannot be sure that a smaller or larger value
		// isn’t coming later in the stream. Both methods are reductions because
		// they return a single value after looking at the entire stream.
		s = Stream.of("monkey", "ape", "bonobo");
		Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
		min.ifPresent(System.out::println); // ape

		Optional<?> minEmpty = Stream.empty().min((s1, s2) -> 0);
		System.out.println(minEmpty.isPresent()); // false

		// /The allMatch(), anyMatch() and noneMatch() methods search a stream
		// and return information about how the stream pertains to the
		// predicate. These may or may not terminate for infinite streams. It
		// depends on the data. Like the find methods, they are not reductions
		// because they do not necessarily look at all of the elements.
		List<String> list2 = Arrays.asList("monkey", "2", "chimp");
		Stream<String> infinite = Stream.generate(() -> "chimp");
		Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
		System.out.println(list2.stream().anyMatch(pred)); // true
		System.out.println(list2.stream().allMatch(pred)); // false
		System.out.println(list2.stream().noneMatch(pred)); // false
		System.out.println(infinite.anyMatch(pred)); // true

		//
		// A looping construct is available. As expected, calling forEach() on
		// an infinite stream does not terminate. Since there is no return
		// value, it is not a reduction.
		// Before you use it, consider if another approach would be better.
		// Developers who learned to write loops first tend to use them for
		// everything. For example, a loop with an if statement should be a
		// filter instead.
		// The method signature is the following:
		// void forEach(Consumer<? super T> action)
		// Notice that this is the only terminal operation with a return type of
		// void. If you want something to happen, you have to make it happen in
		// the loop. Here’s one way to print the elements in the stream. There
		// are other ways, which we cover later in the chapter.
		Stream<String> sForEach = Stream.of("Monkey", "Gorilla", "Bonobo");
		sForEach.forEach(System.out::print); // MonkeyGorillaBonobo

		// The reduce() method combines a stream into a single object. As you
		// can tell from the name, it is a reduction. The method signatures are
		// these:
		// T reduce(T identity, BinaryOperator<T> accumulator)
		// Optional<T> reduce(BinaryOperator<T> accumulator)
		// <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator,
		// BinaryOperator<U> combiner)
		// The initial value of an empty String is the identity. The accumulator
		// combines the current result with the current String. With lambdas, we
		// can do the same thing with a stream and reduction:
		Stream<String> stream = Stream.of("w", "o", "l", "f");
		String word = stream.reduce("", (s1, c) -> s1 + c);
		System.out.println(word); // wolf
		// Notice how we still have the empty String as the identity. We also
		// still concatenate the Strings to get the next value. We can even
		// rewrite this with a method reference:
		stream = Stream.of("w", "o", "l", "f");
		word = stream.reduce("", String::concat);
		System.out.println(word); // wolf
		// Let’s try another one. Can you write a reduction to multiply all of
		// the Integer objects in a stream? Try it. Our solution is shown here:

		Stream<Integer> streamInt = Stream.of(3, 5, 6);
		System.out.println(streamInt.reduce(1, (a, b) -> a * b));

		// The collect() method is a special type of reduction called a mutable
		// reduction. It is more efficient than a regular reduction because we
		// use the same mutable object while accumulating. Common mutable
		// objects include StringBuilder and ArrayList. This is a really useful
		// method, because it lets us get data out of streams and into another
		// form. The method signatures are as follows:
		// <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T>
		// accumulator, BiConsumer<R, R> combiner)
		// <R,A> R collect(Collector<? super T, A,R> collector)
		// Let’s start with the first signature, which is used when we want to
		// code specifically how collecting should work. Our wolf example from
		// reduce can be converted to use collect():

		Stream<String> streamCollect = Stream.of("w", "o", "l", "f");
		StringBuilder wordCollect = streamCollect.collect(StringBuilder::new, StringBuilder::append,
				StringBuilder::append);
		System.out.println(wordCollect);

		// The first parameter is a Supplier that creates the object that will
		// store the results as we collect data. Remember that a Supplier
		// doesn’t take any parameters and returns a value. In this case, it
		// constructs a new StringBuilder.
		// The second parameter is a BiConsumer, which takes two parameters and
		// doesn’t return anything. It is responsible for adding one more
		// element to the data collection. In this example, it appends the next
		// String to the StringBuilder.
		// The final parameter is another BiConsumer. It is responsible for
		// taking two data collections and merging them. This is useful when we
		// are processing in parallel. Two smaller collections are formed and
		// then merged into one. This would work with StringBuilder only if we
		// didn’t care about the order of the letters. In this case, the
		// accumulator and combiner have similar logic.
		// Now let’s look at an example where the logic is different in the
		// accumulator and combiner:

		Stream<String> streamCollect2 = Stream.of("w", "o", "l", "f");
		TreeSet<String> set = streamCollect2.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
		System.out.println(set); // [f, l, o, w]

		// The collector has three parts as before. The supplier creates an
		// empty TreeSet. The accumulator adds a single String from the Stream
		// to the TreeSet. The combiner adds all of the elements of one TreeSet
		// to another in case the operations were done in parallel and need to
		// be merged.

		// In practice, there are many common collectors that come up over and
		// over. Rather than making developers keep reimplementing the same
		// ones, Java provides an interface with common collectors. This
		// approach also makes the code easier to read because it is more
		// expressive. For example, we could rewrite the previous example as
		// follows:

		stream = Stream.of("w", "o", "l", "f");
		TreeSet<String> set2 = stream.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(set2); // [f, l, o, w]

		// If we didn’t need the set to be sorted, we could make the code even
		// shorter:

		stream = Stream.of("w", "o", "l", "f");
		Set<String> set1 = stream.collect(Collectors.toSet());
		System.out.println(set1); // [f, w, l, o]

		// filter()
		// The filter() method returns a Stream with elements that match a given
		// expression. Here is the method signature:
		// Stream<T> filter(Predicate<? super T> predicate)
		// This operation is easy to remember and very powerful because we can
		// pass any Predicate to it. For example, this filters all elements that
		// begin with the letter m:

		s = Stream.of("monkey", "gorilla", "bonobo");
		s.filter(x -> x.startsWith("m")).forEach(System.out::print); // monkey
		System.out.println();
		// distinct()
		// The distinct() method returns a stream with duplicate values removed.
		// The duplicates do not need to be adjacent to be removed. As you might
		// imagine, Java calls equals() to determine whether the objects are the
		// same. The method signature is as follows:
		// Stream<T> distinct()
		// Here’s an example:

		s = Stream.of("duck", "duck", "duck", "goose");
		s.distinct().forEach(System.out::print); // duckgoose
		System.out.println();
		// limit() and skip()
		// The limit() and skip() methods make a Stream smaller. They could make
		// a finite stream smaller, or they could make a finite stream out of an
		// infinite stream. The method signatures are shown here:
		// Stream<T> limit(int maxSize)
		// Stream<T> skip(int n)
		// The following code creates an infinite stream of numbers counting
		// from 1. The skip() operation returns an infinite stream starting with
		// the numbers counting from 6, since it skips the first five elements.
		// The limit() call takes the first two of those. Now we have a finite
		// stream with two elements:
		Stream<Integer> streamOfInt = Stream.iterate(1, n -> n + 1);
		streamOfInt.skip(5).limit(2).forEach(System.out::print); // 67
		System.out.println();
		// The map() method creates a one-to-one mapping from the elements in
		// the stream to the elements of the next step in the stream. The method
		// signature is as follows:
		// <R> Stream<R> map(Function<? super T, ? extends R> mapper)
		// This one looks more complicated than the others you have seen. It
		// uses the lambda expression to figure out the type passed to that
		// function and the one returned. The return type is the stream that
		// gets returned.
		// note The map() method on streams is for transforming data. Don’t
		// confuse it with the Map interface, which maps keys to values.
		// As an example, this code converts a list of String objects to a list
		// of Integers representing their lengths:
		
		s = Stream.of("monkey", "gorilla", "bonobo");
		s.map(String::length).forEach(System.out::print); // 676
		System.out.println();
		// Remember that String::length is shorthand for the lambda x ->
		// x.length(), which clearly shows it is a function that turns a String
		// into an Integer.
	}
}
