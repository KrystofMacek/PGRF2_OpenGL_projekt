import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {

    private static final int FPS = 60;

    public void start() {
        try {
            JFrame frame = new JFrame("Katapult");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            //frame.setSize(800, 600);

            GLProfile profile = GLProfile.get(GLProfile.GL2);
            GLCapabilities capabilities = new GLCapabilities(profile);
            capabilities.setRedBits(8);
            capabilities.setBlueBits(8);
            capabilities.setGreenBits(8);
            capabilities.setAlphaBits(8);
            capabilities.setDepthBits(24);

            Renderer ren = new Renderer();

            JLabel label = new JLabel("Ovládání: Pohyb WASD, Hod Spacebar, Reset R");
            JLabel info = new JLabel("Model katapultu, PGRF2  Kryštof Macek 1.5.2019");
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
            bottomPanel.add(label);
            bottomPanel.add(info);
            frame.add(BorderLayout.SOUTH, bottomPanel);
            JLabel sliderLabel = new JLabel("Síla hodu");
            JSlider slider = new JSlider(0,100);
            slider.setValue(85);
            ren.setInitialStrength(85);

            slider.addChangeListener(e -> ren.setInitialStrength(slider.getValue()));
            ren.setStrengthSlider(slider);

            JPanel panel = new JPanel();
            panel.add(sliderLabel);
            panel.add(slider);

            frame.add(BorderLayout.NORTH,panel);

            GLCanvas canvas = new GLCanvas(capabilities);

            canvas.addGLEventListener(ren);
            canvas.addMouseListener(ren);
            canvas.addMouseMotionListener(ren);
            canvas.addKeyListener(ren);
            canvas.setSize(800, 600);
            

            frame.add(BorderLayout.CENTER,canvas);




            final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    new Thread() {
                        @Override
                        public void run() {
                            if (animator.isStarted()) animator.stop();
                            System.exit(0);
                        }
                    }.start();
                }
            });
            frame.setTitle(ren.getClass().getName());
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            animator.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().start());
    }
}
