package midorum.currying;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DesktopTest {

    @Test
    void addOrThrow() {
        final Desktop desktop = new Desktop();
        assertTrue(desktop.addOrThrow(Window.builder().uid(1).rectangle(Rectangle.builder().x(0).y(0).width(100).height(50)).content("window 1").build()));
        assertFalse(desktop.addOrThrow(Window.builder().uid(2).rectangle(Rectangle.builder().x(10).y(10).width(100).height(50)).content("window 2").build()));
        assertThrows(NoSuchElementException.class, () -> desktop.addOrThrow(Window.brokenWindowBuilder().content("broken window").build()));
    }

    @Test
    void add() {
        final Desktop desktop = new Desktop();
        assertTrue(desktop.add(Window.builder().uid(1).rectangle(Rectangle.builder().x(0).y(0).width(100).height(50)).content("window 1").build()));
        assertFalse(desktop.add(Window.builder().uid(2).rectangle(Rectangle.builder().x(10).y(10).width(100).height(50)).content("window 2").build()));
        assertFalse(() -> desktop.add(Window.brokenWindowBuilder().content("broken window").build()));
    }
}