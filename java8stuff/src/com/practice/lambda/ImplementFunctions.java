package com.practice.lambda;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Implement Java 8 Functional Interfaces
 * 
 * @author baddie
 *
 *         <table border="1">
 *         <thead>
 *         <tr>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">Functional
 *         Interfaces</span></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">#
 *         Parameters</span></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">Return
 *         Type</span></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">Single
 *         Abstract Method</span></td>
 *         </tr>
 *         </thead>
 * 
 *         <tbody>
 *         <tr>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">Supplier&lt;T&gt;</span></code></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">0</span></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">T</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">get</span></code></td>
 *         </tr>
 *         <tr>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">Consumer&lt;T&gt;</span></code></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">1
 *         </span><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">(T)</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">void</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">accept</span></code></td>
 *         </tr>
 *         <tr>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">BiConsumer&lt;T, U&gt;</span></code></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">2
 *         </span><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">(T, U)</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">void</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">accept</span></code></td>
 *         </tr>
 *         <tr>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">Predicate&lt;T&gt;</span></code></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">1
 *         </span><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">(T)</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">boolean</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">test</span></code></td>
 *         </tr>
 *         <tr>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">BiPredicate&lt;T, U&gt;</span></code></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">2
 *         </span><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">(T, U)</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">boolean</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">test</span></code></td>
 *         </tr>
 *         <tr>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">Function&lt;T, R&gt;</span></code></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">1
 *         </span><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">(T)</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">R</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">apply</span></code></td>
 *         </tr>
 *         <tr>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">BiFunction&lt;T, U, R&gt;</span></code></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">2
 *         </span><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">(T, U)</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">R</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">apply</span></code></td>
 *         </tr>
 *         <tr>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">UnaryOperator&lt;T&gt;</span></code></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">1
 *         </span><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">(T)</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">T</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">apply</span></code></td>
 *         </tr>
 *         <tr>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">BinaryOperator&lt;T&gt;</span></code></td>
 *         <td><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p="1">2
 *         </span><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">(T, T)</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">T</span></code></td>
 *         <td><code><span data-html-highlight-note-id="7891047" class=
 *         "bow_html_highlight" data-html-highlight-note-id-p=
 *         "1">apply</span></code></td>
 *         </tr>
 *         </tbody>
 *         </table>
 */
public class ImplementFunctions {

	public static void main(String[] args) {

		// A Supplier is used when you want to generate or supply values without
		// taking any input
		Supplier<LocalDate> s1 = LocalDate::now;
		Supplier<LocalDate> s2 = () -> LocalDate.now();

		LocalDate d1 = s1.get();
		LocalDate d2 = s2.get();

		System.out.println(d1);
		System.out.println(d2);

		// You use a Consumer when you want to do something with a parameter but
		// not return anything. BiConsumer does the same thing except that it
		// takes two parameters
		Consumer<String> c1 = System.out::println;
		Consumer<String> c2 = x -> System.out.println(x);

		c1.accept("Annie");
		c2.accept("Annie");

		// BiConsumer is called with two parameters. They don’t have to be the
		// same type. For example, we can put a key and a value in a map using
		// this interface
		Map<String, Integer> map = new HashMap<>();
		BiConsumer<String, Integer> b1Consumer = map::put;
		BiConsumer<String, Integer> b2Consumer = (k, v) -> map.put(k, v);

		b1Consumer.accept("chicken", 7);
		b2Consumer.accept("chick", 1);

		System.out.println(map);

		// this function converts a String to the length of the String
		Function<String, Integer> f1 = String::length;
		Function<String, Integer> f2 = x -> x.length();

		System.out.println(f1.apply("cluck")); // 5
		System.out.println(f2.apply("cluck")); // 5

		// The following combines two String objects and produces another
		// String:
		BiFunction<String, String, String> b1 = String::concat;
		BiFunction<String, String, String> b2 = (string, toAdd) -> string.concat(toAdd);

		System.out.println(b1.apply("baby ", "chick")); // baby chick
		System.out.println(b2.apply("baby ", "chick")); // baby chick

		// UnaryOperator and BinaryOperator are a special case of a function.
		// They require all type parameters to be the same type. A UnaryOperator
		// transforms its value into one of the same type. For example,
		// incrementing by one is a unary operation. In fact, UnaryOperator
		// extends Function. A BinaryOperator merges two values into one of the
		// same type. Adding two numbers is a binary operation. Similarly,
		// BinaryOperator extends BiFunction

		UnaryOperator<String> u1 = String::toUpperCase;
		UnaryOperator<String> u2 = x -> x.toUpperCase();

		System.out.println(u1.apply("chirp"));
		System.out.println(u2.apply("chirp"));

		BinaryOperator<String> b1Op = String::concat;
		BinaryOperator<String> b2OP = (string, toAdd) -> string.concat(toAdd);

		System.out.println(b1Op.apply("baby ", "chick")); // baby chick
		System.out.println(b2OP.apply("baby ", "chick")); // baby chick
	}

}
