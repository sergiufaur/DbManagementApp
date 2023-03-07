package com.utcn.Sergiu.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class ViewProduct {
    private JFrame frame;
    private JButton insertButton;
    private JButton editButton;
    private JButton viewProducts;
    private JButton deleteButton;
    private JPanel mainPanel;
    private JTable table;

    private JTextField nameInsert;
    private JTextField stockInsert;
    private JTextField idUpdate;
    private JTextField nameUpdate;
    private JTextField stockUpdate;
    private JTextField idDelete;
    private JTextField priceInsert;
    private JTextField priceUpdate;
    private JPanel panel1;
    private JPanel panel2;

    DefaultTableModel m_model= new DefaultTableModel(new Object[]{"ID", "Name", "Stock","Price"},0 );

    public ViewProduct(){
        frame=new JFrame();
        insertButton=new JButton("ADD")   {
            {
                setSize(200,80);
                setMaximumSize(getSize());
            }
        };
        editButton=new JButton("Edit"){
            {
                setSize(200,80);
                setMaximumSize(getSize());
            }
        };
        deleteButton=new JButton("Delete"){
            {
                setSize(200,80);
                setMaximumSize(getSize());
            }
        };
        viewProducts=new JButton("View Products"){
            {
                setSize(200,80);
                setMaximumSize(getSize());
            }
        };

        mainPanel=new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        nameInsert=new JTextField("name:",20);
        stockInsert=new JTextField("stock:",20);
        idUpdate=new JTextField("id:",10);
        nameUpdate=new JTextField("name:",20);
        stockUpdate=new JTextField("stock:",20);
        idDelete=new JTextField("id:",10);
        priceInsert=new JTextField("price:",10);
        priceUpdate=new JTextField("price:",10);
        table=new JTable(m_model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        panel1=new JPanel();
        panel2=new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));

        panel1.add(insertButton);

        panel1.add(nameInsert);
        panel1.add(stockInsert);
        panel1.add(priceInsert);
        panel1.add(editButton);
        panel1.add(idUpdate);
        panel1.add(nameUpdate);
        panel1.add(stockUpdate);
        panel1.add(priceUpdate);

        panel2.add(deleteButton);
        panel2.add(idDelete);

        panel2.add(viewProducts);
        panel2.add(scrollPane);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        frame.setContentPane(mainPanel);
        frame.setSize(600 ,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showFrame(){
        frame.setVisible(true);
    }
    public void addActionListenerAdd(ActionListener actionListener){
        insertButton.addActionListener(actionListener);
    }
    public void addActionListenerEdit(ActionListener actionListener){
        editButton.addActionListener(actionListener);
    }
    public void addActionListenerDelete(ActionListener actionListener){
        deleteButton.addActionListener(actionListener);
    }
    public void addActionListenerList(ActionListener actionListener){
        viewProducts.addActionListener(actionListener);
    }

    public String getNameInsert(){
        return nameInsert.getText().substring(5);
    }
    public int getStockInsert(){
        return Integer.parseInt(stockInsert.getText().substring(6));
    }
    public int getPriceInsert(){
        return Integer.parseInt(priceInsert.getText().substring(6));
    }
    public String getPriceUpdate(){
        return priceUpdate.getText().substring(6);
    }
    public String getNameUpdate(){
        return nameUpdate.getText().substring(5);
    }
    public String getStockUpdate(){
        return stockUpdate.getText().substring(6);
    }
    public String getIdUpdate(){
        return idUpdate.getText().substring(3);
    }
    public String getIdDelete(){
        return idDelete.getText().substring(3);
    }
    public void setTable(Object[] data)
    {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.addRow(data);
    }
}
