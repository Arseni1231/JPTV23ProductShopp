package org.example.services;

import org.example.helpers.HistoryPurchasedProductsAppHelper;
import org.example.intefaces.AppHelper;
import org.example.intefaces.Repository;
import org.example.intefaces.Service;

import java.util.List;

public class HistoryPurchasedProductsService implements Service<org.example.model.HistoryPurchasedProducts> {
    private final AppHelper<org.example.model.HistoryPurchasedProducts> historyOfPurchasedProductsAppHelper;
    private final Repository<org.example.model.HistoryPurchasedProducts> repository;

    public HistoryPurchasedProductsService(AppHelper<org.example.model.HistoryPurchasedProducts> historyOfPurchasedProductsAppHelper, Repository<org.example.model.HistoryPurchasedProducts> repository) {
        this.historyOfPurchasedProductsAppHelper=historyOfPurchasedProductsAppHelper;
        this.repository = repository;
    }

    @Override
    public boolean add() {
        org.example.model.HistoryPurchasedProducts historyPurchasedProducts = historyOfPurchasedProductsAppHelper.create();
        if(historyPurchasedProducts == null) return false;
        try {
            repository.save(historyPurchasedProducts);
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }

    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public boolean print() {
        return historyOfPurchasedProductsAppHelper.printList(repository.load());
    }

    @Override
    public List<org.example.model.HistoryPurchasedProducts> list() {
        return repository.load();
    }

    public boolean returnProduct(){
            List<org.example.model.HistoryPurchasedProducts> modifiedHistoryPurchasedProductsses = ((HistoryPurchasedProductsAppHelper) historyOfPurchasedProductsAppHelper).returnBack(this.list());
        if(modifiedHistoryPurchasedProductsses != null) {
            repository.saveAll(modifiedHistoryPurchasedProductsses);
            return true;
        }else{
            return false;
        }
    };

}
