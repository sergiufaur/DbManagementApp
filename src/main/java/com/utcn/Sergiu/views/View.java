package com.utcn.Sergiu.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame frame;
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;
    private JPanel panel;

    public View(){
        frame=new JFrame();
        clientButton=new JButton("Client") {
            {
                setSize(200,80);
                setMaximumSize(getSize());
            }
        };
        productButton=new JButton("Product") {
            {
                setSize(200,80);
                setMaximumSize(getSize());
            }
        };
        orderButton =new JButton("Orders") {
            {
                setSize(200,80);
                setMaximumSize(getSize());
            }
        };
        panel=new JPanel();

        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        panel.add(clientButton);
        panel.add(productButton);
        panel.add(orderButton);

        clientButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.setContentPane(panel);
        frame.setSize(400 ,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void showFrame(){
        frame.setVisible(true);
    }
    public void addActionListenerClient(ActionListener actionListener){
        this.clientButton.addActionListener(actionListener);
    }
    public void addActionListenerProduct(ActionListener actionListener){
        this.productButton.addActionListener(actionListener);
    }
    public void addActionListenerOrder(ActionListener actionListener){
        this.orderButton.addActionListener(actionListener);
    }
}
