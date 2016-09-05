package com.practice.lambda;

import java.util.Optional;

/**
 * Optional instance methods
 * <table border="1">
 * <thead>
 * <tr>
 * <td>Method</td>
 * <td>When <code>Optional</code> Is Empty</td>
 * <td>When <code>Optional</code> Contains a Value</td>
 * </tr>
 * </thead>
 * 
 * <tbody>
 * <tr>
 * <td><code>get()</code></td>
 * <td>Throws an exception</td>
 * <td>Returns value</td>
 * </tr>
 * <tr>
 * <td><code>ifPresent(Consumer c)</code></td>
 * <td>Does nothing</td>
 * <td>Calls Consumer <code>c</code> with value</td>
 * </tr>
 * <tr>
 * <td><code>isPresent()</code></td>
 * <td>Returns <code>false</code></td>
 * <td>Returns <code>true</code></td>
 * </tr>
 * <tr>
 * <td><code>orElse(T other)</code></td>
 * <td>Returns <code>other</code> parameter</td>
 * <td>Returns value</td>
 * </tr>
 * <tr>
 * <td><code>orElseGet(Supplier s)</code></td>
 * <td>Returns result of calling <code>Supplier</code></td>
 * <td>Returns value</td>
 * </tr>
 * <tr>
 * <td><code>orElseThrow(Supplier s)</code></td>
 * <td>Throws exception created by&nbsp;calling <code>Supplier</code></td>
 * <td>Returns value</td>
 * </tr>
 * </tbody>
 * </table>
 * 
 * @author baddie
 *
 */
public class OptionalDemo {

	public static void main(String[] args) {
		Optional<Double> opt = average(90, 100);
		opt.ifPresent(System.out::println); // 95.0

		Optional<Double> opt1 = average();
		opt1.ifPresent(System.out::println); // Value is not present

		Optional<Double> opt2 = average();
		System.out.println(opt2.orElse(Double.NaN));
		System.out.println(opt2.orElseGet(() -> Math.random()));
		// System.out.println(opt2.orElseThrow(() -> new
		// IllegalStateException()));

		Optional<Double> opt3 = average(90, 100);
		System.out.println(opt3.orElse(Double.NaN));
		System.out.println(opt3.orElseGet(() -> Math.random()));
		System.out.println(opt3.orElseThrow(() -> new IllegalStateException()));
	}

	public static Optional<Double> average(int... scores) {
		if (scores.length == 0)
			return Optional.empty();
		int sum = 0;
		for (int score : scores)
			sum += score;
		return Optional.of((double) sum / scores.length);
	}
}
