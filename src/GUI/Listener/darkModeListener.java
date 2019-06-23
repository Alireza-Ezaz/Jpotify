package GUI.Listener;

import javax.management.ObjectInstance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class darkModeListener implements ActionListener {
    private JFrame frame;
    private ArrayList<Object> centerPanelArrayList;
    private ArrayList<Object> eastPanelArrayList;
    private ArrayList<Object> northPanelArrayList;
    private ArrayList<Object> southPanelArrayList;
    private ArrayList<Object> leftPanelArrayList;

    public darkModeListener(JFrame frame, ArrayList<Object> centerPanelArrayList, ArrayList<Object> eastPanelArrayList, ArrayList<Object> northPanelArrayList, ArrayList<Object> southPanelArrayList, ArrayList<Object> leftPanelArrayList) {
        this.frame = frame;
        this.centerPanelArrayList = centerPanelArrayList;
        this.eastPanelArrayList = eastPanelArrayList;
        this.northPanelArrayList = northPanelArrayList;
        this.southPanelArrayList = southPanelArrayList;
        this.leftPanelArrayList = leftPanelArrayList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox darkMode = ((JCheckBox) e.getSource());
        if (darkMode.isSelected()) {
            for (Object object : eastPanelArrayList) {
                if (object instanceof JLabel) {

                } else
                    ((JComponent) object).setBackground(Color.BLACK);
                ((JComponent) object).setForeground(Color.lightGray);
            }
            for (Object object : southPanelArrayList) {
                if (object instanceof JLabel) {

                } else
                    ((JComponent) object).setBackground(Color.black);
                ((JComponent) object).setForeground(Color.lightGray);
            }
            for (Object object : northPanelArrayList) {
                if (object instanceof JLabel) {

                } else
                    ((JComponent) object).setBackground(Color.BLACK);
                ((JComponent) object).setForeground(Color.lightGray);
            }
            for (Object object : leftPanelArrayList) {
                if (object instanceof JList) {

                } else
                    ((JComponent) object).setBackground(Color.BLACK);
                if (!(object instanceof JList))
                    ((JComponent) object).setForeground(Color.lightGray);
            }


        } else {
            for (Object object : eastPanelArrayList) {
                ((JComponent) object).setBackground(Color.lightGray);
                ((JComponent) object).setForeground(Color.black);
            }
            for (Object object : southPanelArrayList) {
                ((JComponent) object).setBackground(Color.lightGray);
                ((JComponent) object).setForeground(Color.black);
            }
            for (Object object : northPanelArrayList) {
                ((JComponent) object).setBackground(Color.lightGray);
                ((JComponent) object).setForeground(Color.black);
            }
            for (Object object : leftPanelArrayList) {
                if (object instanceof JList) {

                } else
                    ((JComponent) object).setBackground(Color.LIGHT_GRAY);
                if (!(object instanceof JList))
                    ((JComponent) object).setForeground(Color.black);
            }
            frame.setBackground(Color.lightGray);
        }
    }


}
