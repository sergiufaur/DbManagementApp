package com.utcn.Sergiu.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class ViewClient {
    private JFrame frame;
    private JButton insertButton;
    private JButton editButton;
    private JButton viewClients;
    private JButton deleteButton;
    private JPanel mainPanel;
    private JTable table;

    private JTextField nameInsert;
    private JTextField addressInsert;
    private JTextField idUpdate;
    private JTextField nameUpdate;
    private JTextField addressUpdate;
    private JTextField idDelete;
    private JPanel panel1;
    private JPanel panel2;

    DefaultTableModel m_model= new DefaultTableModel(new Object[]{"ID", "Name", "Address"},0 );

    public ViewClient(){
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
        viewClients=new JButton("View Clients"){
            {
                setSize(200,80);
                setMaximumSize(getSize());
            }
        };

        mainPanel=new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        nameInsert=new JTextField("name:",20);
        addressInsert=new JTextField("addres:",20);
        idUpdate=new JTextField("id:",10);
        nameUpdate=new JTextField("name:",20);
        addressUpdate=new JTextField("addres:",20);
        idDelete=new JTextField("id:",10);
        table=new JTable(m_model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        panel1=new JPanel();
        panel2=new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));

        panel1.add(insertButton);

        panel1.add(nameInsert);
        panel1.add(addressInsert);

        panel1.add(editButton);
        panel1.add(idUpdate);
        panel1.add(nameUpdate);
        panel1.add(addressUpdate);

        panel2.add(deleteButton);
        panel2.add(idDelete);

        panel2.add(viewClients);
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
        viewClients.addActionListener(actionListener);
    }

    public String getNameInsert(){
        return nameInsert.getText().substring(5);
    }
    public String getAddressInsert(){
        return addressInsert.getText().substring(7);
    }
    public String getNameUpdate(){
        return nameUpdate.getText().substring(5);
    }
    public String getAddressUpdate(){
        return addressUpdate.getText().substring(7);
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
