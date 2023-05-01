package OpenGL;

import java.nio.IntBuffer;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Boot {

    // window id
    private long window;

    public void run() {

    }

    public void init() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to intilize GLFW");
        }

        // https://www.glfw.org/docs/latest/window_guide.html#window_hints
        // takes only one parameter per definition, so multiple assignments were made
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        // param = width, height, title, monitor (screen mode / full screen = NULL), share
        // https://www.glfw.org/docs/latest/group__window.html#ga3555a418df92ad53f917597fe2f64aeb
        window = GLFW.glfwCreateWindow(124,768,"Prototype testing", NULL, NULL);

        if (window == NULL) {
            throw new IllegalStateException("Unable to create window");
        }

        // keyCallback = sets settings to open key input, when window is passive the keys get state of 'released'
        // key = type of key
        // scancode = platform specific scancode
        // action = the action that happens when key is pressed
         // mods = modifier bits
        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {});

        try(MemoryStack stack = stackPush()) {
            // Buffe object to save Integer
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(2);

            GLFW.glfwGetWindowSize(window, pWidth, pHeight);

            // https://www.glfw.org/docs/latest/monitor_guide.html#monitor_modes
            // Video mode object for window settings like fullscreen, windowed, etc.
            GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

            GLFW.glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);

            GLFW.glfwMakeContextCurrent(window);
            GLFW.glfwSwapInterval(1);
            GLFW.glfwShowWindow(window);

        }


    }

    public void loop() {
        // finishes the initializing process
        GL.createCapabilities();

        // run window untill x button is pressed
        while(!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);

            GLFW.glfwSwapBuffers(window);

            GLFW.glfwPollEvents();
        }

    }
}
