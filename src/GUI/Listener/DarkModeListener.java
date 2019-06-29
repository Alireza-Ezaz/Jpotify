package GUI.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author M.S.Haeri
 * @version final
 * This class handles dark mode
 */

public class DarkModeListener implements ActionListener {
    private JFrame frame;
    private ArrayList<Object> centerPanelArrayList;
    private ArrayList<Object> eastPanelArrayList;
    private ArrayList<Object> northPanelArrayList;
    private ArrayList<Object> southPanelArrayList;
    private ArrayList<Object> leftPanelArrayList;
    private float[] floats;

    public DarkModeListener(JFrame frame, ArrayList<Object> centerPanelArrayList, ArrayList<Object> eastPanelArrayList, ArrayList<Object> northPanelArrayList, ArrayList<Object> southPanelArrayList, ArrayList<Object> leftPanelArrayList) {
        this.frame = frame;
        this.centerPanelArrayList = centerPanelArrayList;
        this.eastPanelArrayList = eastPanelArrayList;
        this.northPanelArrayList = northPanelArrayList;
        this.southPanelArrayList = southPanelArrayList;
        this.leftPanelArrayList = leftPanelArrayList;
        floats = new float[3];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox darkMode = ((JCheckBox) e.getSource());
        if (darkMode.isSelected()) {
            Color.RGBtoHSB(18, 18, 18, floats);
            frame.setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
            for (Object object : eastPanelArrayList) {
                if (object instanceof JLabel) {

                } else {
                    Color.RGBtoHSB(18, 18, 18, floats);
                    ((JComponent) object).setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                }
                ((JComponent) object).setForeground(Color.lightGray);
            }
            for (Object object : southPanelArrayList) {
                if (object instanceof JLabel) {

                } else {
                    Color.RGBtoHSB(40, 40, 40, floats);
                    ((JComponent) object).setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                }
                ((JComponent) object).setForeground(Color.lightGray);
            }
            for (Object object : northPanelArrayList) {
                if (object instanceof JLabel) {

                } else {
                    Color.RGBtoHSB(25, 25, 70, floats);
                    ((JComponent) object).setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                }
                ((JComponent) object).setForeground(Color.lightGray);
            }
            for (Object object : leftPanelArrayList) {
                if (object instanceof JList) {
                    Color.RGBtoHSB(40, 40, 40, floats);
                    ((JComponent) object).setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                } else {
                    Color.RGBtoHSB(18, 18, 18, floats);
                    ((JComponent) object).setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                }
                ((JComponent) object).setForeground(Color.lightGray);
            }
            for (Object object : centerPanelArrayList) {
                {
                    Color.RGBtoHSB(24, 24, 24, floats);
                    ((JComponent) object).setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                }
                ((JComponent) object).setForeground(Color.lightGray);
            }
            for (Object object : northPanelArrayList) {
                {
                    Color.RGBtoHSB(14, 14, 20, floats);
                    ((JComponent) object).setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                }
                ((JComponent) object).setForeground(Color.lightGray);
            }


        } else {
            frame.setBackground(Color.lightGray);
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
                    ((JComponent) object).setBackground(Color.white);
                    ((JComponent) object).setForeground(Color.gray);
                } else
                    ((JComponent) object).setBackground(Color.LIGHT_GRAY);
                if (!(object instanceof JList))
                    ((JComponent) object).setForeground(Color.black);
            }
            for (Object object : centerPanelArrayList) {
                ((JComponent) object).setBackground(Color.gray);
                ((JComponent) object).setForeground(Color.black);
            }
            for (Object object : northPanelArrayList) {
                Color.RGBtoHSB(200, 200, 200, floats);
                ((JComponent) object).setBackground(Color.getHSBColor(floats[0], floats[1], floats[2]));
                Color.RGBtoHSB(40, 40, 40, floats);
                ((JComponent) object).setForeground(Color.getHSBColor(floats[0], floats[1], floats[2]));
            }

            frame.setBackground(Color.lightGray);
        }
    }


}
