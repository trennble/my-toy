package com.trennble.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 使用jmh测试lambda在时间效率
 *
 * http://www.importnew.com/17262.html
 * 修改总结：
 * 优化流代码，在 reduce 前先使用 mapToInt。
 * 列表不再用 volatile 修饰。
 * 新方法 forMax2 删除对成员变量的访问。
 * 删除 forEachLambda 中的冗余 helper 函数。现在 lambda 表达式作为一个值赋给变量。可读性有所降低，但是速度更快。
 * 消除自动装箱。如果你在 Eclipse 中打开项目的自动装箱警告，旧的代码会有 15 处警告。
 */
@State(Scope.Benchmark)
public class LoopBenchmarkMain {
	private final int size = 100000;
	private List<Integer> integers = null;

	public static void main(String[] args) {
		LoopBenchmarkMain benchmark = new LoopBenchmarkMain();
		benchmark.setup();
		
		System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
		System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
		System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
		System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
		System.out.println("forMax2Integer max is: " + benchmark.forMax2Integer());
		System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
		System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
		System.out.println("lambdaMaxInteger max is: " + benchmark.lambdaMaxInteger());
	}
	
	@Setup
	public void setup() {
		integers = new ArrayList<>(size);
		populate(integers);
	}

	public void populate(List<Integer> list) {
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			list.add(random.nextInt(1000000));
		}
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int iteratorMaxInteger() {
		int max = Integer.MIN_VALUE;
		for (Iterator<Integer> it = integers.iterator(); it.hasNext(); ) {
			max = Integer.max(max, it.next());
		}
		return max;
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int forEachLoopMaxInteger() {
		int max = Integer.MIN_VALUE;
		for (Integer n : integers) {
			max = Integer.max(max, n);
		}
		return max;
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int forEachLambdaMaxInteger() {
		final Wrapper wrapper = new Wrapper();
		wrapper.inner = Integer.MIN_VALUE;
		
		integers.forEach(i -> wrapper.inner = Integer.max(i, wrapper.inner));
		return wrapper.inner;
	}
	
	public static class Wrapper {
		public int inner; 
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int forMaxInteger() {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < size; i++) {
			max = Integer.max(max, integers.get(i));
		}
		return max;
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int forMax2Integer() {
		int max = Integer.MIN_VALUE;
		List<Integer> integersLocal = integers;
		for (int i = 0; i < size; i++) {
			max = Integer.max(max, integersLocal.get(i).intValue());
		}
		return max;
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int parallelStreamMaxInteger() {
		return integers.parallelStream().mapToInt(Integer::intValue).reduce(Integer.MIN_VALUE, Integer::max);
	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int streamMaxInteger() {
		return integers.stream().mapToInt(Integer::intValue).reduce(Integer.MIN_VALUE, Integer::max);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public int lambdaMaxInteger() {
		return integers.stream().mapToInt(Integer::intValue).reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
	}
}