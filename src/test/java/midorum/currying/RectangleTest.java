package midorum.currying;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RectangleTest {

    @Test
    void builder() {
        final Rectangle expected = new Rectangle(1, 2, 10, 20);
        final Rectangle rectangle = Rectangle.builder()
                .x(1)
                .y(2)
                .width(10)
                .height(20);
        assertEquals(expected, rectangle);
        System.out.println(rectangle);
    }

    @Test
    void prefilledBuilder() {
        final Rectangle expected = new Rectangle(1, 2, 10, 20);
        final Rectangle.RectangleWidth prefilledRectangle = Rectangle.builder()
                .x(1)
                .y(2);
        final Rectangle rectangle1 = prefilledRectangle
                .width(10)
                .height(20);
        final Rectangle rectangle2 = prefilledRectangle
                .width(15)
                .height(20);
        assertEquals(expected, rectangle1);
        assertNotEquals(expected, rectangle2);
        System.out.println(rectangle1);
        System.out.println(rectangle2);
    }

}