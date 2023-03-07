package com.utcn.Sergiu.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class ViewOrder {
    private JFrame frame;
    private JButton insertButton;
    private JButton viewOrders;
    private JButton deleteButton;
    private JPanel mainPanel;
    private JTable table;
    private JTextField idClientInsert;
    private JTextField idProductInsert;
    private JTextField quantityInsert;
    private JTextField price;
    private JPanel panel1;
    private JPanel panel2;
    DefaultTableModel m_model= new DefaultTableModel(new Object[]{"ID", "IDClient", "IDProduct","Quantity","Price"},0 );
    public ViewOrder(){
        frame=new JFrame();
        insertButton=new JButton("Create an Order")   {
            {
                setSize(200,80);
                setMaximumSize(getSize());
            }
        };
        viewOrders=new JButton("View Orders"){
            {
                setSize(200,80);
                setMaximumSize(getSize());
            }
        };
        mainPanel=new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        idClientInsert=new JTextField("Client id:",10);
        idProductInsert=new JTextField("Product id:",10);
        quantityInsert=new JTextField("Quantity:",10);
        price=new JTextField("Price:",10);
        table=new JTable(m_model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        panel1=new JPanel();
        panel2=new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));

        panel1.add(insertButton);
        panel1.add(idClientInsert);
        panel1.add(idProductInsert);
        panel1.add(quantityInsert);
        panel1.add(price);

        panel2.add(viewOrders);
        panel2.add(scrollPane);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        frame.setContentPane(mainPanel);
        frame.setSize(400 ,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void addActionListenerAdd(ActionListener actionListener){
        insertButton.addActionListener(actionListener);
    }
    public void addActionListenerList(ActionListener actionListener){
        viewOrders.addActionListener(actionListener);
    }
    public void showFrame(){
        frame.setVisible(true);
    }
    public void setPrice(String s){
        price.setText(s);
    }
    public String getIdClient(){
        return idClientInsert.getText().substring(10);
    }
    public String getIdProduct(){
        return idProductInsert.getText().substring(11);
    }
    public String getQuantity(){
        return quantityInsert.getText().substring(9);
    }
    public void setQuantityInsert(String s){
        quantityInsert.setText(s);
    }
    public void setTable(Object[] data)
    {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.addRow(data);
    }
}
