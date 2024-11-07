package org.example.helpers;


import org.example.intefaces.AppHelper;
import org.example.intefaces.Input;
import org.example.intefaces.Service;
import org.example.model.Client;
import org.example.model.HistoryOfPurchasedProducts;
import org.example.model.Product;

import java.time.LocalDate;
import java.util.List;

public class HistoryOfPurchasedProductsAppHelper implements AppHelper<HistoryOfPurchasedProducts> {
    private final Input input;
    private final Service<Product> productService;
    private final Service<Client> clientService;

    public HistoryOfPurchasedProductsAppHelper(Input input, Service<Product> productService, Service<Client> clientService) {
        this.input = input;
        this.productService = productService;
        this.clientService = clientService;
    }

    @Override
    public HistoryOfPurchasedProducts create() {
        if(!productService.print()){
            return null;
        };
        System.out.print("Выберите номер продукта: ");
        int numberProduct = Integer.parseInt(input.nextLine());
        Product product = productService.list().get(numberProduct - 1);
        if(!clientService.print()){
            return null;
        }
        System.out.print("Выберите номер покупателя: ");
        int numberClient = Integer.parseInt(input.nextLine());
        Client client = clientService.list().get(numberClient - 1);
        HistoryOfPurchasedProducts historyOfPurchasedProducts = new HistoryOfPurchasedProducts();
        HistoryOfPurchasedProducts.setProduct(product);
        HistoryOfPurchasedProducts.setClient(client);
        return historyOfPurchasedProducts;

    }
    @Override
    public boolean printList(List<HistoryOfPurchasedProducts> historyOfPurchasedProductss) {
        int count = 0;
        for(int i = 0;i<historyOfPurchasedProductss.size();i++){
            HistoryOfPurchasedProducts historyOfPurchasedProducts = historyOfPurchasedProductss.get(i);
            if(historyOfPurchasedProducts.getReturnProductDate() == null){
                System.out.printf("%d. %s. %s. Читает %s %s%n",
                        i+1,
                        historyOfPurchasedProducts.getProduct().getName(),
                        historyOfPurchasedProducts.getBook().getCategory(),
                        historyOfPurchasedProducts.getUser().getFirstName(),
                        historyOfPurchasedProducts.getUser().getLastName()
                );
                count++;
            }
        }
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<HistoryOfPurchasedProducts> edit(List<HistoryOfPurchasedProducts> historyOfPurchasedProductss) {
        return List.of();
    }

    public List<HistoryOfPurchasedProducts> returnBack(List<HistoryOfPurchasedProducts>historyOfPurchasedProducts){
        if(!this.printList(historyOfPurchasedProductss)){
            return null;
        }
        System.out.print("Выберите номер возвращаемого продукта: ");
        int numberHistoryOfPurchasedProducts = Integer.parseInt(input.nextLine());
        historyOfPurchasedProductss.get(numberHistoryOfPurchasedProducts-1).setReturnProductDate(LocalDate.now());
        return historyOfPurchasedProductss;
    }
}
}
