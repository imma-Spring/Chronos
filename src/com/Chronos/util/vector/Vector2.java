package com.Chronos.util.vector;

import java.security.NoSuchProviderException;

public class Vector2<T extends Number> extends Vector {
    public T x, y;

    @SuppressWarnings("unchecked")
    public Vector2() {
        this((T) Integer.valueOf(0));
    }

    public Vector2(T x_and_y) {
        this(x_and_y, x_and_y);
    }

    public Vector2(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2<T> vector2) {
        this(vector2.x, vector2.y);
    }

    //Static methods return a new Vector
    public static <U extends Number> Vector2<U> add(Vector2<U> vec2, U x, U y) {
        vec2.add(x, y);
        return vec2;
    }

    public static <U extends Number> Vector2<U> subtract(Vector2<U> vec2, U x, U y) {
        vec2.subtract(x, y);
        return vec2;
    }

    public static <U extends Number> Vector2<U> multiply(Vector2<U> vec2, U x, U y) {
        vec2.multiply(x, y);
        return vec2;
    }

    public static <U extends Number> Vector2<U> divide(Vector2<U> vec2, U x, U y) {
        vec2.divide(x, y);
        return vec2;
    }

    public static <U extends Number> Vector2<U> add(Vector2<U> vec2, U x_and_y) {
        vec2.add(x_and_y);
        return vec2;
    }

    public static <U extends Number> Vector2<U> subtract(Vector2<U> vec2, U x_and_y) {
        vec2.subtract(x_and_y);
        return vec2;
    }

    public static <U extends Number> Vector2<U> multiply(Vector2<U> vec2, U x_and_y) {
        vec2.multiply(x_and_y);
        return vec2;
    }

    public static <U extends Number> Vector2<U> divide(Vector2<U> vec2, U x_and_y) {
        vec2.divide(x_and_y);
        return vec2;
    }

    public static <U extends Number> Vector2<U> add(Vector2<U> vec2_1, Vector2<U> vec2_2) {
        vec2_1.add(vec2_2);
        return vec2_1;
    }

    public static <U extends Number> Vector2<U> subtract(Vector2<U> vec2_1, Vector2<U> vec2_2) {
        vec2_1.subtract(vec2_2);
        return vec2_1;
    }

    public static <U extends Number> Vector2<U> multiply(Vector2<U> vec2_1, Vector2<U> vec2_2) {
        vec2_1.multiply(vec2_2);
        return vec2_1;
    }

    public static <U extends Number> Vector2<U> divide(Vector2<U> vec2_1, Vector2<U> vec2_2) {
        vec2_1.divide(vec2_2);
        return vec2_1;
    }

    public static Vector3<?> toVec3(Vector2<?> vector2) {
        return vector2.toVec3();
    }

    public static <U extends Number> Vector2<U> center(Vector2<U> vector2, Vector2<U> center) {
        return vector2.center(center, false);
    }

    //Instance methods that change the value of the Vector
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

    @SuppressWarnings("unchecked")
    public Vector3<Integer> toVec3() {
        return (Vector3<Integer>) new Vector3<>(this);
    }

    public void add(T x_and_y) {
        add(x_and_y, x_and_y);
    }

    public void subtract(T x_and_y) {
        subtract(x_and_y, x_and_y);
    }

    public void multiply(T x_and_y) {
        multiply(x_and_y, x_and_y);
    }

    public void divide(T x_and_y) {
        divide(x_and_y, x_and_y);
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

    //Methods overriden from the Number class
    @Override
    public int intValue() {
        return x.intValue() + y.intValue();
    }

    @Override
    public long longValue() {
        return x.longValue() + y.longValue();
    }

    @Override
    public float floatValue() {
        return x.floatValue() + y.floatValue();
    }

    @Override
    public double doubleValue() {
        return x.doubleValue() + y.doubleValue();
    }

    @Override
    public String toString() {
        return "[%s, %s]".formatted(x.toString(), y.toString());
    }

    @SuppressWarnings("unchecked")
    public <U extends Number> Vector2<U> convert(U type) {
        Vector2<U> returnVec = new Vector2<>();
        if (type instanceof Integer) {
            returnVec.x = (U) Integer.valueOf(this.x.intValue());
            returnVec.y = (U) Integer.valueOf(this.y.intValue());
        } else if (type instanceof Double) {
            returnVec.x = (U) Double.valueOf(this.x.intValue());
            returnVec.y = (U) Double.valueOf(this.y.intValue());
        } else if (type instanceof Long) {
            returnVec.x = (U) Long.valueOf(this.x.intValue());
            returnVec.y = (U) Long.valueOf(this.y.intValue());
        } else if (type instanceof Float) {
            returnVec.x = (U) Float.valueOf(this.x.intValue());
            returnVec.y = (U) Float.valueOf(this.y.intValue());
        } else if (x instanceof Vector) {
            throw new IllegalArgumentException();
        }
        return returnVec;
    }
}
