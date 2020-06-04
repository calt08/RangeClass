import java.util.List;

import java.util.stream.IntStream;

import jdk.nashorn.api.tree.ForInLoopTree;

import java.util.stream.Collectors;

class Range {
    String expr;
    int[] endPoints;
    List<Integer> values;

    public Range(String expression) {
        String regex = "(\\[|\\()(-\\d*|\\d*),(-\\d*|\\d*)(\\]|\\))";

        if (expression.matches(regex)) {
            expr = expression;
            String rangeNumbers = expression.substring(1, expression.length() - 1);
            String[] valuesString = rangeNumbers.split(",");
            endPoints = new int[2];
            endPoints[0] = Integer.parseInt(valuesString[0]);
            endPoints[1] = Integer.parseInt(valuesString[1]);
            if (expression.startsWith("(")) {
                endPoints[0]++;
            }
            if (expression.endsWith(")")) {
                endPoints[1]--;
            }
            if (endPoints[0] < endPoints[1]) {
                values = IntStream.rangeClosed(endPoints[0], endPoints[1]).boxed().collect(Collectors.toList());
            } else {
                throw new ArithmeticException();
            }

        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean contains(int... vals) {
        for (int val : vals) {
            if (!values.contains(val)) {
                return false;
            }
        }
        return true;
    }

    public boolean doesNotContains(int... vals) {
        return !contains(vals);
    }

    public Integer[] allPoints() {
        Integer[] points = new Integer[values.size()];
        values.toArray(points);
        return points;
    }

    public boolean containsRange(Range otherRange) {
        if (contains(otherRange.endPoints[0], otherRange.endPoints[1])) {
            return true;
        }
        return false;
    }

    public boolean doesNotContainsRange(Range otherRange) {
        return !containsRange(otherRange);
    }

    public int[] endPoints() {
        return endPoints;
    }

    public boolean overlapsRange(Range otherRange) {
        if (contains(otherRange.endPoints[0]) || contains(otherRange.endPoints[1])) {
            return true;
        }
        return false;
    }

    public boolean equals(Range otherRange) {
        if (endPoints[0] == otherRange.endPoints[0] && endPoints[1] == otherRange.endPoints[1]) {
            return true;
        }
        return false;
    }

    public boolean notEquals(Range otherRange) {
        return !equals(otherRange);
    }

}

class Program {
    public static void main(String[] args) {

        System.out.println();
        System.out.println("---------");
        System.out.println("CONSTRUCTOR");
        System.out.println("---------");
        System.out.println();

        Range r1 = new Range("[1,4]");
        Range r2 = new Range("[1,4)");
        Range r3 = new Range("(1,4]");
        Range r4 = new Range("(1,4)");

        for (int i = 0; i < r1.values.size(); i++) {
            System.out.print(r1.values.get(i) + " ");
        }
        System.out.println();

        for (int i = 0; i < r2.values.size(); i++) {
            System.out.print(r2.values.get(i) + " ");
        }
        System.out.println();

        for (int i = 0; i < r3.values.size(); i++) {
            System.out.print(r3.values.get(i) + " ");
        }
        System.out.println();

        for (int i = 0; i < r4.values.size(); i++) {
            System.out.print(r4.values.get(i) + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("---------");
        System.out.println("CONTAINS");
        System.out.println("---------");
        System.out.println();

        System.out.println(r1.contains(2));
        System.out.println(r2.contains(2, 3));
        System.out.println(r3.contains(1));
        System.out.println(r4.contains(2, 5));

        System.out.println();
        System.out.println("---------");
        System.out.println("DOESNOTCONTAINS");
        System.out.println("---------");
        System.out.println();

        System.out.println(r1.doesNotContains(2));
        System.out.println(r2.doesNotContains(2, 3));
        System.out.println(r3.doesNotContains(1));
        System.out.println(r4.doesNotContains(2, 5));

        System.out.println();
        System.out.println("---------");
        System.out.println("ALLPOINTS");
        System.out.println("---------");
        System.out.println();

        for (int i = 0; i < r1.allPoints().length; i++) {
            System.out.print(r1.allPoints()[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < r2.allPoints().length; i++) {
            System.out.print(r2.allPoints()[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < r3.allPoints().length; i++) {
            System.out.print(r3.allPoints()[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < r4.allPoints().length; i++) {
            System.out.print(r4.allPoints()[i] + " ");
        }
        System.out.println();

        System.out.println();
        System.out.println("---------");
        System.out.println("CONTAINSRANGE");
        System.out.println("---------");
        System.out.println();

        System.out.println(r1.containsRange(new Range("[1,2]")));
        System.out.println(r2.containsRange(new Range("[1,3)")));
        System.out.println(r3.containsRange(new Range("[1,4]")));
        System.out.println(r4.containsRange(new Range("[1,3)")));

        System.out.println();
        System.out.println("---------");
        System.out.println("DOESNOTCONTAINSRANGE");
        System.out.println("---------");
        System.out.println();

        System.out.println(r1.doesNotContainsRange(new Range("[1,2]")));
        System.out.println(r2.doesNotContainsRange(new Range("[1,3)")));
        System.out.println(r3.doesNotContainsRange(new Range("[1,4]")));
        System.out.println(r4.doesNotContainsRange(new Range("[1,3)")));

        System.out.println();
        System.out.println("---------");
        System.out.println("ENDPOINTS");
        System.out.println("---------");
        System.out.println();

        for (int i = 0; i < r1.endPoints().length; i++) {
            System.out.print(r1.endPoints()[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < r2.endPoints().length; i++) {
            System.out.print(r2.endPoints()[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < r3.endPoints().length; i++) {
            System.out.print(r3.endPoints()[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < r4.endPoints().length; i++) {
            System.out.print(r4.endPoints()[i] + " ");
        }
        System.out.println();

        System.out.println();
        System.out.println("---------");
        System.out.println("OVERLAPSRANGE");
        System.out.println("---------");
        System.out.println();

        System.out.println(r1.overlapsRange(new Range("[2,9)")));
        System.out.println(r2.overlapsRange(new Range("[2,5)")));
        System.out.println(r3.overlapsRange(new Range("(4,7]")));
        System.out.println(r4.overlapsRange(new Range("[4,10)")));

        System.out.println();
        System.out.println("---------");
        System.out.println("EQUALS");
        System.out.println("---------");
        System.out.println();

        System.out.println(r1.equals(new Range("(0,5)")));
        System.out.println(r2.equals(new Range("[1,4)")));
        System.out.println(r3.equals(new Range("[1,4)")));
        System.out.println(r4.equals(new Range("[10,20]")));

        System.out.println();
        System.out.println("---------");
        System.out.println("NOTEQUALS");
        System.out.println("---------");
        System.out.println();

        System.out.println(r1.notEquals(new Range("(0,5)")));
        System.out.println(r2.notEquals(new Range("[1,4)")));
        System.out.println(r3.notEquals(new Range("[1,4)")));
        System.out.println(r4.notEquals(new Range("[10,20]")));
    }
}