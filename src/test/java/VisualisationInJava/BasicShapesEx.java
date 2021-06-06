package VisualisationInJava;

import javax.swing.*;
import java.awt.*;

public class BasicShapesEx extends JFrame {
    public BasicShapesEx() {

        initUI();
    }

    private void initUI() {

        add(new VisualisationTest());

        setTitle("Basic shapes");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                BasicShapesEx ex = new BasicShapesEx();
                ex.setVisible(true);
            }
        });
    }
}
