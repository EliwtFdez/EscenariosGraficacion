/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itver.graf.objLoader.test;

import com.jogamp.opengl.util.FPSAnimator;
import edu.itver.graf.objLoader.test.MyCanvas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Jogl2ObjLoader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Jogl2ObjLoader ol = new Jogl2ObjLoader();
        ol.test("./data/IronMan.obj");

    }

    public void test(String fileName) {
                  
            // o.printVectors();

            JFrame fr = new JFrame();
            MyCanvas canvas = new MyCanvas(fileName);
            
            final FPSAnimator animator = new FPSAnimator(canvas, MyCanvas.FPS, true);
            
            fr.getContentPane().add(canvas);
            
            fr.addKeyListener(canvas);
            
            fr.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                  // Use a dedicate thread to run the stop() to ensure that the
                  // animator stops before program exits.
                  new Thread() {
                     @Override
                     public void run() {
                        if (animator.isStarted()) animator.stop();
                        System.exit(0);
                     }
                  }.start();
               }
            });
                        
            fr.setTitle("Testing object view");
            fr.pack();
            fr.setVisible(true);
            animator.start(); // start the animation loop

    }

}
