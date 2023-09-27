package com.chronos.util.vector;

/**
 * A generic 2-dimensional vector class with elements of type T, extending Number.
 *
 * @param <T> Type of elements (extending Number).
 */
public class Vector2<T extends Number> extends Vector {
    public T x, y;

    /**
     * Constructs a Vector2 with both elements set to x_and_y.
     *
     * @param x_and_y Value to set both elements.
     */
    public Vector2(T x_and_y) {
        this(x_and_y, x_and_y);
    }

    /**
     * Constructs a Vector2 with specified x and y values.
     *
     * @param x Value for the x element.
     * @param y Value for the y element.
     */
    public Vector2(T x, T y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a Vector2 by copying another Vector2.
     *
     * @param vector2 Vector2 to copy.
     */
    public Vector2(Vector2<T> vector2) {
        this(vector2.x, vector2.y);
    }

    /**
     * Returns a new Vector2 with the elements added to x_and_y.
     *
     * @param x_and_y Values to add.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> add(Vector2<U> vec2, U x_and_y) {
        vec2.add(x_and_y);
        return vec2;
    }

    /**
     * Returns a new Vector2 with the elements subtracted by x_and_y.
     *
     * @param x_and_y Values to subtract.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> subtract(Vector2<U> vec2, U x_and_y) {
        vec2.subtract(x_and_y);
        return vec2;
    }

    /**
     * Returns a new Vector2 with the elements multiplied by x_and_y.
     *
     * @param x_and_y Values to multiply.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> multiply(Vector2<U> vec2, U x_and_y) {
        vec2.multiply(x_and_y);
        return vec2;
    }

    /**
     * Returns a new Vector2 with the elements divided by x_and_y.
     *
     * @param x_and_y Values to divide.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> divide(Vector2<U> vec2, U x_and_y) {
        vec2.divide(x_and_y);
        return vec2;
    }

    /**
     * Returns a new Vector2 with the elements added to x and y.
     *
     * @param x Value to add to x element.
     * @param y Value to add to y element.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> add(Vector2<U> vec2, U x, U y) {
        vec2.add(x, y);
        return vec2;
    }

    /**
     * Returns a new Vector2 with the elements subtracted by x and y.
     *
     * @param x Value to subtract from x element.
     * @param y Value to subtract from y element.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> subtract(Vector2<U> vec2, U x, U y) {
        vec2.subtract(x, y);
        return vec2;
    }

    /**
     * Returns a new Vector2 with the elements multiplied by x and y.
     *
     * @param x Value to multiply x element by.
     * @param y Value to multiply y element by.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> multiply(Vector2<U> vec2, U x, U y) {
        vec2.multiply(x, y);
        return vec2;
    }

    /**
     * Returns a new Vector2 with the elements divided by x and y.
     *
     * @param x Value to divide x element by.
     * @param y Value to divide y element by.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> divide(Vector2<U> vec2, U x, U y) {
        vec2.divide(x, y);
        return vec2;
    }

    /**
     * Returns a new Vector2 with the elements added together.
     *
     * @param vec2_1 First Vector2 to add.
     * @param vec2_2 Second Vector2 to add.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> add(Vector2<U> vec2_1, Vector2<U> vec2_2) {
        vec2_1.add(vec2_2);
        return vec2_1;
    }

    /**
     * Returns a new Vector2 with the elements subtracted.
     *
     * @param vec2_1 First Vector2 to subtract from.
     * @param vec2_2 Second Vector2 to subtract.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> subtract(Vector2<U> vec2_1, Vector2<U> vec2_2) {
        vec2_1.subtract(vec2_2);
        return vec2_1;
    }

    /**
     * Returns a new Vector2 with the elements multiplied together.
     *
     * @param vec2_1 First Vector2 to multiply.
     * @param vec2_2 Second Vector2 to multiply.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> multiply(Vector2<U> vec2_1, Vector2<U> vec2_2) {
        vec2_1.multiply(vec2_2);
        return vec2_1;
    }

    /**
     * Returns a new Vector2 with the elements divided.
     *
     * @param vec2_1 First Vector2 to divide.
     * @param vec2_2 Second Vector2 to divide.
     * @return A new Vector2.
     */
    public static <U extends Number> Vector2<U> divide(Vector2<U> vec2_1, Vector2<U> vec2_2) {
        vec2_1.divide(vec2_2);
        return vec2_1;
    }

    /**
     * Converts this Vector2 to a Vector3 with 0 as its z value.
     *
     * @return A new Vector3.
     */
    @SuppressWarnings("unchecked")
    public Vector3<?> toVec3() {
        return (Vector3<?>) new Vector3<>(this);
    }

    /**
     * Adds x_and_y to both elements of this Vector2.
     *
     * @param x_and_y Value to add.
     */
    public void add(T x_and_y) {
        add(x_and_y, x_and_y);
    }

    /**
     * Subtracts x_and_y from both elements of this Vector2.
     *
     * @param x_and_y Value to subtract.
     */
    public void subtract(T x_and_y) {
        subtract(x_and_y, x_and_y);
    }

    /**
     * Multiplies both elements of this Vector2 by x_and_y.
     *
     * @param x_and_y Value to multiply by.
     */
    public void multiply(T x_and_y) {
        multiply(x_and_y, x_and_y);
    }

    /**
     * Divides both elements of this Vector2 by x_and_y.
     *
     * @param x_and_y Value to divide by.
     */
    public void divide(T x_and_y) {
        divide(x_and_y, x_and_y);
    }

    /**
     * Adds x and y to the respective elements of this Vector2.
     *
     * @param x Value to add to x element.
     * @param y Value to add to y element.
     */
    @SuppressWarnings("unchecked")
    public void add(T x, T y) {
        if (x instanceof Integer) {
            this.x = (T) Integer.valueOf(this.x.intValue() + x.intValue());
            this.y = (T) Integer.valueOf(this.y.intValue() + y.intValue());
        } else if (x instanceof Double) {
            this.x = (T) Double.valueOf(this.x.doubleValue() + x.doubleValue());
            this.y = (T) Double.valueOf(this.y.doubleValue() + y.doubleValue());
        } else if (x instanceof Long) {
            this.x = (T) Long.valueOf(this.x.longValue() + x.longValue());
            this.y = (T) Long.valueOf(this.y.longValue() + y.longValue());
        } else if (x instanceof Float) {
            this.x = (T) Float.valueOf(this.x.floatValue() + x.floatValue());
            this.y = (T) Float.valueOf(this.y.floatValue() + y.floatValue());
        } else if (x instanceof Vector) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Subtracts x and y from the respective elements of this Vector2.
     *
     * @param x Value to subtract from x element.
     * @param y Value to subtract from y element.
     */
    @SuppressWarnings("unchecked")
    public void subtract(T x, T y) {
        if (x instanceof Integer) {
            this.x = (T) Integer.valueOf(this.x.intValue() - x.intValue());
            this.y = (T) Integer.valueOf(this.y.intValue() - y.intValue());
        } else if (x instanceof Double) {
            this.x = (T) Double.valueOf(this.x.doubleValue() - x.doubleValue());
            this.y = (T) Double.valueOf(this.y.doubleValue() - y.doubleValue());
        } else if (x instanceof Long) {
            this.x = (T) Long.valueOf(this.x.longValue() - x.longValue());
            this.y = (T) Long.valueOf(this.y.longValue() - y.longValue());
        } else if (x instanceof Float) {
            this.x = (T) Float.valueOf(this.x.floatValue() - x.floatValue());
            this.y = (T) Float.valueOf(this.y.floatValue() - y.floatValue());
        } else if (x instanceof Vector) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Multiplies x and y with the respective elements of this Vector2.
     *
     * @param x Value to multiply x element by.
     * @param y Value to multiply y element by.
     */
    @SuppressWarnings("unchecked")
    public void multiply(T x, T y) {
        if (x instanceof Integer) {
            this.x = (T) Integer.valueOf(this.x.intValue() * x.intValue());
            this.y = (T) Integer.valueOf(this.y.intValue() * y.intValue());
        } else if (x instanceof Double) {
            this.x = (T) Double.valueOf(this.x.doubleValue() * x.doubleValue());
            this.y = (T) Double.valueOf(this.y.doubleValue() * y.doubleValue());
        } else if (x instanceof Long) {
            this.x = (T) Long.valueOf(this.x.longValue() * x.longValue());
            this.y = (T) Long.valueOf(this.y.longValue() * y.longValue());
        } else if (x instanceof Float) {
            this.x = (T) Float.valueOf(this.x.floatValue() * x.floatValue());
            this.y = (T) Float.valueOf(this.y.floatValue() * y.floatValue());
        } else if (x instanceof Vector) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Divides x and y by the respective elements of this Vector2.
     *
     * @param x Value to divide x element by.
     * @param y Value to divide y element by.
     */
    @SuppressWarnings("unchecked")
    public void divide(T x, T y) {
        if (x instanceof Integer) {
            this.x = (T) Integer.valueOf(this.x.intValue() / x.intValue());
            this.y = (T) Integer.valueOf(this.y.intValue() / y.intValue());
        } else if (x instanceof Double) {
            this.x = (T) Double.valueOf(this.x.doubleValue() / x.doubleValue());
            this.y = (T) Double.valueOf(this.y.doubleValue() / y.doubleValue());
        } else if (x instanceof Long) {
            this.x = (T) Long.valueOf(this.x.longValue() / x.longValue());
            this.y = (T) Long.valueOf(this.y.longValue() / y.longValue());
        } else if (x instanceof Float) {
            this.x = (T) Float.valueOf(this.x.floatValue() / x.floatValue());
            this.y = (T) Float.valueOf(this.y.floatValue() / y.floatValue());
        } else if (x instanceof Vector) {
            throw new IllegalArgumentException();
        }
    }

    public void add(Vector2<T> vec2) {
        add(vec2.x, vec2.y);
    }

    public void subtract(Vector2<T> vec2) {
        subtract(vec2.x, vec2.y);
    }

    public void multiply(Vector2<T> vec2) {
        multiply(vec2.x, vec2.y);
    }

    public void divide(Vector2<T> vec2) {
        divide(vec2.x, vec2.y);
    }

    /**
     * Centers this Vector2 using another Vector2 as the center point.
     *
     * @param center Another Vector2 to be used as the center point.
     * @param adjust If true, the elements of this Vector2 will be adjusted.
     * @return A new Vector2 representing the centered position.
     */
    @SuppressWarnings("unchecked")
    public Vector2<T> center(Vector2<T> center, boolean adjust) {
        Vector2<T> val = null;
        if (center.x instanceof Integer) {
            int nX = x.intValue() - (center.x.intValue() / 2);
            int nY = y.intValue() - (center.y.intValue() / 2);
            val = (Vector2<T>) new Vector2<>(nX, nY);
        } else if (center.x instanceof Double) {
            double nX = x.doubleValue() - (center.x.doubleValue() / 2);
            double nY = y.doubleValue() - (center.y.doubleValue() / 2);
            val = (Vector2<T>) new Vector2<>(nX, nY);
        } else if (center.x instanceof Long) {
            long nX = x.longValue() - (center.x.longValue() / 2L);
            long nY = y.longValue() - (center.y.longValue() / 2L);
            val = (Vector2<T>) new Vector2<>(nX, nY);
        } else if (center.x instanceof Float) {
            float nX = x.floatValue() - (center.x.floatValue() / 2f);
            float nY = y.floatValue() - (center.y.floatValue() / 2f);
            val = (Vector2<T>) new Vector2<>(nX, nY);
        } else throw new IllegalArgumentException();

        if (adjust) {
            x = val.x;
            y = val.y;
        }
        return val;
    }

    /**
     * Returns the integer value of the sum of both elements.
     *
     * @return Integer value of the sum.
     */
    @Override
    public int intValue() {
        return x.intValue() + y.intValue();
    }

    /**
     * Returns the long value of the sum of both elements.
     *
     * @return Long value of the sum.
     */
    @Override
    public long longValue() {
        return x.longValue() + y.longValue();
    }

    /**
     * Returns the float value of the sum of both elements.
     *
     * @return Float value of the sum.
     */
    @Override
    public float floatValue() {
        return x.floatValue() + y.floatValue();
    }

    /**
     * Returns the double value of the sum of both elements.
     *
     * @return Double value of the sum.
     */
    @Override
    public double doubleValue() {
        return x.doubleValue() + y.doubleValue();
    }

    /**
     * Returns a string representation of this Vector2.
     *
     * @return String representation of the vector.
     */
    @Override
    public String toString() {
        return "[%s, %s]".formatted(x.toString(), y.toString());
    }

    /**
     * Converts this Vector2 to a Vector2 of type U.
     *
     * @param type Type of Vector2 to convert to.
     * @return A new Vector2 of type U.
     */
    @SuppressWarnings("unchecked")
    public <U extends Number> Vector2<U> convert(U type) {
        // [Implementation of conversion]
        return new Vector2<>((U)Integer.valueOf(0));
    }
}
