import java.awt.event.*;

import javax.swing.JComboBox;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class ComboBoxListener implements AncestorListener, ActionListener {
    String myTextArea;

    @Override
    public void ancestorAdded(AncestorEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        // TODO Auto-generated method stub

    }

    public void actionPerformed(ActionEvent e) {
        JComboBox t = (JComboBox) e.getSource();
        System.out.println(t.getSelectedItem());
        App.AddAnimal(t.getSelectedItem().toString());
    }

}
