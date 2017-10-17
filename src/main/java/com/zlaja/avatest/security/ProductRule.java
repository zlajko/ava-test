package com.zlaja.avatest.security;

import com.zlaja.avatest.product.Product;

public class ProductRule implements Rule {

    public enum Field implements FieldStrategy<Product, Long> {
        PRICE(Product::getPrice), STOCK(Product::getStock);

        private final FieldStrategy<Product, Long> fieldStrategy;

        Field(FieldStrategy<Product, Long> fieldStrategy) {
            this.fieldStrategy = fieldStrategy;
        }

        @Override
        public Long getField(Product x) {
            return fieldStrategy.getField(x);
        }
    }

    public enum Operation implements OperationStrategy<Long> {
        GR((x, y) -> x > y), LR((x, y) -> x < y), EQ(Long::equals);

        private final OperationStrategy<Long> operationStrategy;

        Operation(OperationStrategy<Long> operationStrategy) {
            this.operationStrategy = operationStrategy;
        }

        @Override
        public boolean compute(Long x, Long y) {
            return operationStrategy.compute(x, y);
        }

    }

    private Field field;
    private Operation operation;
    private long value;

    public ProductRule() {

    }

    public ProductRule(Field field, Operation operation, long value) {
        this.field = field;
        this.operation = operation;
        this.value = value;
    }

    private Field getField() {
        return field;
    }

    private Operation getOperation() {
        return operation;
    }

    private long getValue() {
        return value;
    }

    @Override
    public boolean enforceRule(Object entity) {
        return operation.compute(field.getField((Product) entity), value);
    }
}
