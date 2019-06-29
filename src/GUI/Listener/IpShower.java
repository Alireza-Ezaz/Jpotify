package GUI.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IpShower implements ActionListener {
    private DefaultListModel ipList;
    private String newIp;
    public IpShower(DefaultListModel ipList){
        this.ipList =ipList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("IP List");
        JList list = new JList(ipList);
        list.setPreferredSize(new Dimension(200,300));
        frame.setPreferredSize(new Dimension(200,300));
        frame.setMinimumSize(new Dimension(200,300));
        list.setMinimumSize(new Dimension(200,300));
        frame.add(list);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        newIp = JOptionPane.showInputDialog("Enter new IP:");
        ipList.addElement(newIp);


    }
}
