package com.chronos.util.vector;

//TODO: fix Vector3 class
/**
 * A generic 3D vector class.
 *
 * @param <T> The type of the vector components (e.g., Integer, Double, Float, etc.).
 */
public class Vector3<T extends Number> extends Vector {
    public T x, y, z;

    /**
     * Default constructor that initializes all components to 0.
     */
    @SuppressWarnings("unchecked")
    public Vector3() {
        this((T) Integer.valueOf(0));
    }

    /**
     * Constructor that initializes all components to the same value.
     *
     * @param x_and_y_and_z The value to set for all components (x, y, z).
     */
    public Vector3(T x_and_y_and_z) {
        this(x_and_y_and_z, x_and_y_and_z, x_and_y_and_z);
    }

    /**
     * Constructor that initializes x and y components and sets z to 0.
     *
     * @param x The value to set for the x component.
     * @param y The value to set for the y component.
     */
    @SuppressWarnings("unchecked")
    public Vector3(T x, T y) {
        this(x, y, (T) Integer.valueOf(0));
    }

    /**
     * Constructor that initializes all components to specified values.
     *
     * @param x The value to set for the x component.
     * @param y The value to set for the y component.
     * @param z The value to set for the z component.
     */
    public Vector3(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructor that converts a 2D vector (Vector2) to a 3D vector by setting the z component to 0.
     *
     * @param vector2 The 2D vector to convert.
     */
    public Vector3(Vector2<T> vector2) {
        this(vector2.x, vector2.y, (T) Integer.valueOf(0));
    }

    /**
     * Constructor that converts a 2D vector (Vector2) to a 3D vector by setting the z component to the specified value.
     *
     * @param vector2 The 2D vector to convert.
     * @param z       The value to set for the z component.
     */
    public Vector3(Vector2<T> vector2, T z) {
        this(vector2.x, vector2.y, z);
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

    public static Vector2<?> toVec2(Vector3<?> vector3) {
        return vector3.toVec2();
    }

    //Instance methods that change the value of the Vector
    @SuppressWarnings("unchecked")
    public void add(T x, T y, T z) {
        if (x instanceof Integer) {
            this.x = (T) Integer.valueOf(this.x.intValue() + x.intValue());
            this.y = (T) Integer.valueOf(this.y.intValue() + y.intValue());
            this.z = (T) Integer.valueOf(this.z.intValue() + z.intValue());
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

    public Vector2<T> toVec2() {
        return new Vector2<>(this.x, this.y);
    }

    public void add(T x_and_y) {
        add(x_and_y, x_and_y, x_and_y);
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
        add(vec2.x, vec2.y, vec2.y);
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
        return "[%s, %s, %s]".formatted(x, y, z);
    }

    @SuppressWarnings("unchecked")
    public <U extends Number> Vector3<U> convert(U type) {
        Vector3<U> returnVec = new Vector3<>();
        if (type instanceof Integer) {
            returnVec.x = (U) Integer.valueOf(this.x.intValue());
            returnVec.y = (U) Integer.valueOf(this.y.intValue());
            returnVec.z = (U) Integer.valueOf(this.z.intValue());
        } else if (type instanceof Double) {
            returnVec.x = (U) Double.valueOf(this.x.intValue());
            returnVec.y = (U) Double.valueOf(this.y.intValue());
            returnVec.z = (U) Double.valueOf(this.z.intValue());
        } else if (type instanceof Long) {
            returnVec.x = (U) Long.valueOf(this.x.intValue());
            returnVec.y = (U) Long.valueOf(this.y.intValue());
            returnVec.z = (U) Long.valueOf(this.z.intValue());
        } else if (type instanceof Float) {
            returnVec.x = (U) Float.valueOf(this.x.intValue());
            returnVec.y = (U) Float.valueOf(this.y.intValue());
            returnVec.z = (U) Float.valueOf(this.z.intValue());
        } else if (x instanceof Vector) {
            throw new IllegalArgumentException();
        }
        return returnVec;
    }
}
