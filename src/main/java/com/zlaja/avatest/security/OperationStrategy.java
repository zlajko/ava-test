package com.zlaja.avatest.security;

@FunctionalInterface
public interface OperationStrategy<T> {
    boolean compute(T x, T y);
}
