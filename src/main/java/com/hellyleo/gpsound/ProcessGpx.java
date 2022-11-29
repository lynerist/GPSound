package com.hellyleo.gpsound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Utente
 */
public class ProcessGpx implements ActionListener{

    final private Model model;
    
    public ProcessGpx(Model model) {
        this.model = model;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( "".equals(model.getGpx().getName())) {
            System.out.println("Manca il File");
        } else {
            System.out.println(model.getGpx().getName());
            System.out.println(model.getGpx().printNext());
        } 
    }
    
}
