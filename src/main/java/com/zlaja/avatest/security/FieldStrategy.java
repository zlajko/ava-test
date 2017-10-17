package com.zlaja.avatest.security;

@FunctionalInterface
public interface FieldStrategy<T, N> {
    N getField(T x);
}
