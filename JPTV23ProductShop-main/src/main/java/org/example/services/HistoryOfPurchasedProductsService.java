package org.example.services;

import org.example.helpers.HistoryOfPurchasedProductsAppHelper;
import org.example.intefaces.AppHelper;
import org.example.intefaces.Repository;
import org.example.intefaces.Service;
import org.example.model.HistoryOfPurchasedProducts;

import java.util.List;

public class HistoryOfPurchasedProductsService implements Service<HistoryOfPurchasedProducts> {
    private final AppHelper<HistoryOfPurchasedProducts> historyOfPurchasedProductsAppHelper;
    private final Repository<HistoryOfPurchasedProducts> repository;

    public HistoryOfPurchasedProductsService(AppHelper<HistoryOfPurchasedProducts> historyOfPurchasedProductsAppHelper, Repository<HistoryOfPurchasedProducts> repository) {
        this.historyOfPurchasedProductsAppHelper=historyOfPurchasedProductsAppHelper;
        this.repository = repository;
    }

    @Override
    public boolean add() {
        HistoryOfPurchasedProducts historyOfPurchasedProducts = historyOfPurchasedProductsAppHelper.create();
        if(historyOfPurchasedProducts == null) return false;
        try {
            repository.save(historyOfPurchasedProducts);
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
    public List<HistoryOfPurchasedProducts> list() {
        return repository.load();
    }

    public boolean returnProduct(){
            List<HistoryOfPurchasedProducts> modifiedHistoryOfPurchasedProductss = ((HistoryOfPurchasedProductsAppHelper) historyOfPurchasedProductsAppHelper).returnBack(this.list());
        if(modifiedHistoryOfPurchasedProductss != null) {
            repository.saveAll(modifiedHistoryOfPurchasedProductss);
            return true;
        }else{
            return false;
        }
    };

}
