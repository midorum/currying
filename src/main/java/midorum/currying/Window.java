package midorum.currying;

import java.util.Objects;
import java.util.Optional;

public final class Window {

    private final Rectangle rectangle;
    private final String content;
    private final int uid;

    public Window(Rectangle rectangle, String content, final int uid) {
        this.rectangle = rectangle;
        this.content = content;
        this.uid = uid;
    }

    public Optional<Rectangle> rectangle() {
        return Optional.ofNullable(rectangle);
    }

    public String content() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Window) obj;
        return Objects.equals(this.rectangle, that.rectangle) &&
                Objects.equals(this.content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rectangle, content);
    }

    @Override
    public String toString() {
        return "Window[" +
                "rectangle=" + rectangle + ", " +
                "content=" + content + ']';
    }

    /*
     * In comparing with classic Builder pattern
     * pros:
     * - immutability
     * - thread safety
     * - compile time control (should always set up all parameters, so you never obtain runtime error while constructing final object)
     * - concise syntax (no boilerplate code)
     *
     * cons:
     * - should always set up all parameters so when add new attributes should refactor all builder usages
     * - when add new attributes should do more refactoring with all builder methods and return types
     * - should use null or another marker for default values
     * - no way to use default values for primitive attributes if they are not in the build chain head
     * - need separate builder method for each predefined build case
     * - more short-life instances creating
     *
     * Conclusion: this builder variant ideally fits to non-extendable (stable) objects that used in multithreading environment.
     * Also, it fits to all-required objects to get full compile time control while building.
     */
    public static WindowUid builder() {
        return uid -> rectangle -> content -> () -> new Window(rectangle, content, uid);
    }

    public static WindowContent brokenWindowBuilder() {
        return content -> () -> new Window(null, content, 0);
    }

    public static WindowUid defaultValuesBuilder() {
        return uid -> rectangle -> content -> () -> new Window(rectangle != null ? rectangle : getDefaultRectangle(), content != null ? content : "default content", uid);
    }

    private static Rectangle getDefaultRectangle() {
        return Rectangle.builder().x(0).y(0).width(100).height(75);
    }

    @FunctionalInterface
    public interface WindowUid {
        WindowRectangle uid(int uid);
    }

    @FunctionalInterface
    public interface WindowRectangle {
        WindowContent rectangle(Rectangle rectangle);
    }

    @FunctionalInterface
    public interface WindowContent {
        WindowBuilder content(String content);
    }

    @FunctionalInterface
    public interface WindowBuilder {
        Window build();
    }

}
