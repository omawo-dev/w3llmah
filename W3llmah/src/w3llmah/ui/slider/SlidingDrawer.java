/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.slider;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author 96655
 */
public class SlidingDrawer {
    private JFXDrawer drawer;
    
    private JFXHamburger hamb;

    public SlidingDrawer(JFXDrawer drawer, JFXHamburger hamb) {
        this.drawer = drawer;
        this.hamb = hamb;
    }

    public SlidingDrawer() {
    }
    
    public void setDrawer(JFXDrawer drawer) {
        this.drawer = drawer;
    }

    public void setHamb(JFXHamburger hamb) {
        this.hamb = hamb;
    }
    
    public void slider() {
        try {
            VBox vbox = FXMLLoader.load(getClass().getResource("SliderPanel.fxml"));
            drawer.setSidePane(vbox);
        } catch (IOException ex) {
            Logger.getLogger(SlidingDrawer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamb);
        drawer.close();
        transition.setRate(transition.getRate()*-1);
        hamb.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate()*-1);
            transition.play();
            if(drawer.isOpened()){
                drawer.close();
                drawer.setMinWidth(0);
            }else{
                drawer.open();
                drawer.setMinWidth(180);
            }
        });
    }
}
