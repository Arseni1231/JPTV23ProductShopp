package org.example.helpers;

import org.example.intefaces.AppHelper;
import org.example.intefaces.Input;
import org.example.intefaces.Service;
import org.example.model.Client;
import org.example.model.HistoryPurchasedProducts;
import org.example.model.Product;

import java.time.LocalDate;
import java.util.List;

public class HistoryPurchasedProductsAppHelper implements AppHelper<HistoryPurchasedProducts> {
    private final Input input;
    private final Service<Product> productService;
    private final Service<Client> clientService;

    public HistoryPurchasedProductsAppHelper(Input input, Service<Product> productService, Service<Client> clientService) {
        this.input = input;
        this.productService = productService;
        this.clientService = clientService;
    }

    @Override
    public HistoryPurchasedProducts create() {
        if (!productService.print()) {
            return null;
        }
        System.out.print("Выберите номер продукта: ");
        int numberProduct = Integer.parseInt(input.nextLine());
        Product product = productService.list().get(numberProduct - 1);

        if (!clientService.print()) {
            return null;
        }
        System.out.print("Выберите номер покупателя: ");
        int numberClient = Integer.parseInt(input.nextLine());
        Client client = clientService.list().get(numberClient - 1);

        HistoryPurchasedProducts history = new HistoryPurchasedProducts();
        history.setProduct(product);
        history.setClient(client);
        history.setBorrowedProductDate(LocalDate.now());
        return history;
    }

    @Override
    public boolean printList(List<HistoryPurchasedProducts> historyList) {
        int count = 0;
        for (int i = 0; i < historyList.size(); i++) {
            HistoryPurchasedProducts history = historyList.get(i);
            if (history.getPurchasedProductDate() == null) {
                System.out.printf("%d. %s - %s. Покупатель: %s %s%n",
                        i + 1,
                        history.getProduct().getName(),
                        history.getProduct().getCategory(),
                        history.getClient().getFirstName(),
                        history.getClient().getLastName());
                count++;
            }
        }
        return count > 0;
    }

    @Override
    public List<HistoryPurchasedProducts> edit(List<HistoryPurchasedProducts> historyList) {
        return historyList;
    }

    public List<HistoryPurchasedProducts> returnBack(List<HistoryPurchasedProducts> historyList) {
        if (!this.printList(historyList)) {
            return null;
        }
        System.out.print("Выберите номер возвращаемого продукта: ");
        int numberHistory = Integer.parseInt(input.nextLine());
        historyList.get(numberHistory - 1).setPurchasedProductDate(LocalDate.now());
        return historyList;
    }
}
