package midorum.currying;

public record Rectangle(int left, int top, int width, int height) {

    public int right() {
        return left + width;
    }

    public int bottom() {
        return top + height;
    }

    public static RectangleX builder() {
        return x -> y -> width -> height -> new Rectangle(x, y, width, height);
    }

    @FunctionalInterface
    public interface RectangleX {
        RectangleY x(int x);
    }

    @FunctionalInterface
    public interface RectangleY {
        RectangleWidth y(int y);
    }

    @FunctionalInterface
    public interface RectangleWidth {
        RectangleHeight width(int width);
    }

    @FunctionalInterface
    public interface RectangleHeight {
        Rectangle height(int height);
    }
}
