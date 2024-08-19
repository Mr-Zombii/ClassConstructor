package com.github.puzzle.cc.util;

public class ImmutablePair<A, B> extends Pair<A, B> {

    public final A a;
    public final B b;

    public ImmutablePair(A a, B b) {
        super(null, null);
        this.a = a;
        this.b = b;
    }
}
