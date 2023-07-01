package midorum.currying;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Desktop {

    private final List<Window> windows = new ArrayList<>();

    public boolean addOrThrow(final Window window) {
        final Function<Rectangle, Boolean> function = window.rectangle().map(intersectCurried).orElseThrow();
        if (windows.stream().anyMatch(w -> w.rectangle().map(function).orElseThrow())) return false;
        return windows.add(window);
    }

    public boolean add(final Window window) {
        final Function<Rectangle, Boolean> function = r -> window.rectangle().map(intersectCurried).map(f -> f.apply(r)).orElseGet(() -> {
            System.out.println("Broken window must match anyway to prevent adding");
            return true;
        });
        if (windows.stream().anyMatch(w -> w.rectangle().map(function).orElse(true))) return false;
        return windows.add(window);
    }

    public boolean add2(final Window window) {
        final Function<Rectangle, Boolean> function = r -> window.rectangle().map(r2 -> intersect(r, r2)).orElseGet(() -> {
            System.out.println("Broken window must match anyway to prevent adding");
            return true;
        });
        if (windows.stream().anyMatch(w -> w.rectangle().map(function).orElse(true))) return false;
        return windows.add(window);
    }

    private boolean intersect(final Rectangle r1, final Rectangle r2) {
        return r1.left() <= r2.right() && r2.left() <= r1.right() && r1.top() <= r2.bottom() && r2.top() <= r1.bottom();
    }

    private final Function<Rectangle, Function<Rectangle, Boolean>> intersectCurried = r1
            -> r2
            -> r1.left() <= r2.right() && r2.left() <= r1.right() && r1.top() <= r2.bottom() && r2.top() <= r1.bottom();

}
