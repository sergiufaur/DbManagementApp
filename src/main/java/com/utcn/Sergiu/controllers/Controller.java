package com.utcn.Sergiu.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.utcn.Sergiu.Reflection;
import com.utcn.Sergiu.model.Client;
import com.utcn.Sergiu.model.Order;
import com.utcn.Sergiu.model.Product;
import com.utcn.Sergiu.services.ClientService;
import com.utcn.Sergiu.services.OrderService;
import com.utcn.Sergiu.services.ProductService;
import com.utcn.Sergiu.views.View;
import com.utcn.Sergiu.views.ViewClient;
import com.utcn.Sergiu.views.ViewOrder;
import com.utcn.Sergiu.views.ViewProduct;
import com.itextpdf.text.*;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.Callable;

public class Controller {
    private ViewClient vew;
    private View view;
    private ViewProduct viewProduct;
    private ViewOrder viewOrder;
    private ClientService clientService;
    private ProductService productService;
    private OrderService orderService;
    public Controller(View view,ViewClient vew,ViewProduct viewProduct,ViewOrder viewOrder, ClientService clientService,ProductService productService,OrderService orderService) {
        this.vew = vew;
        this.view=view;
        this.viewProduct=viewProduct;
        this.viewOrder=viewOrder;
        this.clientService = clientService;
        this.productService=productService;
        this.orderService=orderService;
        view.showFrame();

        view.addActionListenerClient(e -> {
            vew.showFrame();
        });
        view.addActionListenerProduct(e -> {
            viewProduct.showFrame();
        });
        view.addActionListenerOrder(e -> {
            viewOrder.showFrame();
        });
        vew.addActionListenerAdd(e -> {
            clientService.add(vew.getNameInsert(),vew.getAddressInsert());
        });
        vew.addActionListenerList(e->{
           List<Client> list= clientService.viewAll();
            for (Client client:list
                 ) {
                    Object[] data = Reflection.retrieveProperties(client);
                    vew.setTable(data);
            }
        });
        vew.addActionListenerEdit(e -> {

            Client client =new Client();
            client.setId(Long.valueOf(vew.getIdUpdate()));
            client.setName(vew.getNameUpdate());
            client.setAdress(vew.getAddressUpdate());
            clientService.edit(client);
        });
        vew.addActionListenerDelete(e -> {
            clientService.delete(Long.valueOf(vew.getIdDelete()));
        });
        viewProduct.addActionListenerAdd(e -> {
            productService.add(viewProduct.getNameInsert(),viewProduct.getStockInsert(),viewProduct.getPriceInsert());
        });
        viewProduct.addActionListenerDelete(e -> {
            productService.delete(Long.valueOf(viewProduct.getIdDelete()));
        });
        viewProduct.addActionListenerEdit(e -> {
            Product product=new Product();
            product.setId(Long.valueOf(viewProduct.getIdUpdate()));
            product.setStock(Integer.parseInt(viewProduct.getStockUpdate()));
            product.setName(viewProduct.getNameUpdate());
            product.setPrice(Integer.parseInt(viewProduct.getPriceUpdate()));
            productService.edit(product);
        });
        viewProduct.addActionListenerList(e -> {
            List<Product> list=productService.viewAll();
            for (Product product:list
                 ) {
                Object[] data = Reflection.retrieveProperties(product);
                viewProduct.setTable(data);
            }
        });
        viewOrder.addActionListenerAdd(e -> {
            Long idP = Long.valueOf(viewOrder.getIdProduct());
            int q= Integer.parseInt(viewOrder.getQuantity());
            List<Product> products = productService.viewAll();
            Product productToCheckStock=null;
            Product productToUpdateStock=new Product();
            for (Product product:products
                 ) {
                if(idP==product.getId()) {
                    productToCheckStock=product;
                    break;
                }
            }
            if(productToCheckStock.getStock()>=q)
            {
                int price = q*productToCheckStock.getPrice();
                orderService.add(Long.valueOf(viewOrder.getIdClient()),idP,q,price);
                String s ="Price: "+price;
                viewOrder.setPrice(s);
                productToUpdateStock.setStock(productToCheckStock.getStock()-q);
                productToUpdateStock.setPrice(productToCheckStock.getPrice());
                productToUpdateStock.setId(productToCheckStock.getId());
                productToUpdateStock.setName(productToCheckStock.getName());
                productService.edit(productToUpdateStock);

                List<Order> orders = orderService.viewAll();

               Document document =new Document();
                try {
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Order"+orders.get(orders.size()-1).getId()+".pdf"));
                    document.open();
                    document.add(new Paragraph(orders.get(orders.size()-1).toString()));
                    document.close();
                    writer.close();
                } catch (DocumentException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
            else
            {
                viewOrder.setQuantityInsert("FULL STOCK");
            }

        });
        viewOrder.addActionListenerList(e -> {
            List<Order> list=orderService.viewAll();
            for (Order order:list
            ) {
                Object[] data = Reflection.retrieveProperties(order);
                viewOrder.setTable(data);
            }
        });

    }
}
