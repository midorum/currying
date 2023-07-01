package midorum.currying;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Canvas {

    private final List<Rectangle> rectangles = new ArrayList<>();

    /**
     * Puts the rectangle on the canvas. The rectangle must not intersect with all earlier added rectangles.
     *
     * @param rectangle rectangle to put on
     * @return true - if rectangle has put on canvas successfully; false - if rectangle intersects with another shapes on canvas
     */
    public boolean addRectangle(final Rectangle rectangle) {
        if (rectangles.stream().anyMatch(r -> intersect(r, rectangle))) return false;
        return rectangles.add(rectangle);
    }

    /**
     * Puts the rectangle on the canvas. The rectangle must not intersect with all earlier added rectangles.
     *
     * @param rectangle rectangle to put on
     * @return true - if rectangle has put on canvas successfully; false - if rectangle intersects with another shapes on canvas
     */
    public boolean addRectangle2(final Rectangle rectangle) {
        if (rectangles.stream().anyMatch(r -> intersectCurried.apply(r).apply(rectangle))) return false;
        return rectangles.add(rectangle);
    }

    private boolean intersect(final Rectangle r1, final Rectangle r2) {
        return r1.left() <= r2.right() && r2.left() <= r1.right() && r1.top() <= r2.bottom() && r2.top() <= r1.bottom();
    }

    private final Function<Rectangle, Function<Rectangle, Boolean>> intersectCurried = r1
            -> r2
            -> r1.left() <= r2.right() && r2.left() <= r1.right() && r1.top() <= r2.bottom() && r2.top() <= r1.bottom();


}
