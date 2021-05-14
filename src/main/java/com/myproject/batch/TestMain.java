package com.myproject.batch;

import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import org.apache.commons.lang.math.RandomUtils;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMain {
    public static void main(String[] args) {
        Object[][] input = createInputAndPrint();
        final UnmodifiableIterator<List<Object[]>> partition = Iterators.partition(Arrays.stream(input).skip(1).iterator(), 2);
        partition.forEachRemaining(TestMain::process);
    }

    private static void process(final List<Object[]> ints) {
    	AtomicInteger ordinal = new AtomicInteger(1);
        ints.forEach(i -> {
            for (int i1 = 0; i1 < i.length; i1++) {
            	System.out.print(ordinal.getAndIncrement() + "  ");
                System.out.print(i[i1] + "  ");
            }
            System.out.println();
        });
        System.out.println();
        System.out.println();
        System.out.println();
    }
    private static Object[][] createInputAndPrint() {
        Object[][] input = new Object[5][5];
        for (int i = 0; i < input.length; i++) {
            for (int i1 = 0; i1 < input[i].length; i1++) {
                input[i][i1] = RandomUtils.nextInt();
                System.out.print(input[i][i1] + "  ");
            }
            System.out.println("  ");
        }
        System.out.println();
        System.out.println();
        return input;
    }
}