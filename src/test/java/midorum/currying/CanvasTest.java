package midorum.currying;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanvasTest {

    @Test
    void add() {
        final Canvas canvas = new Canvas();
        assertTrue(canvas.addRectangle(Rectangle.builder().x(0).y(0).width(10).height(10)));
        assertTrue(canvas.addRectangle(Rectangle.builder().x(11).y(0).width(5).height(10)));
        assertFalse(canvas.addRectangle(Rectangle.builder().x(0).y(0).width(5).height(10)));
    }

    @Test
    void add2() {
        final Canvas canvas = new Canvas();
        assertTrue(canvas.addRectangle2(Rectangle.builder().x(0).y(0).width(10).height(10)));
        assertTrue(canvas.addRectangle2(Rectangle.builder().x(11).y(0).width(5).height(10)));
        assertFalse(canvas.addRectangle2(Rectangle.builder().x(0).y(0).width(5).height(10)));
    }
}