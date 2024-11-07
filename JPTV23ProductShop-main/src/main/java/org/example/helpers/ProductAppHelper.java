package org.example.helpers;

import org.example.intefaces.AppHelper;
import org.example.intefaces.Input;
import org.example.model.ProductStore;
import org.example.model.Product;
import org.example.intefaces.Service;

import java.util.List;

public class AppHelperProduct implements AppHelper<Product> {

    private final Input input;
    private final Service<Product> productService;

    public AppHelperProduct(Input input, Service<Product> productService) {
        this.input = input;
        this.productService = productService;
    }

    @Override
    public Product create() {
        Product product = new Product();
        try {
            System.out.print("Computer model name: ");
            product.setModel(input.nextLine());
            productStoreService.printList();
            System.out.print("Добавить магазин продуктов в лист? (да/нет): ");
            String addProductChoose = input.nextLine();
            if(addProductChoose.equals("да")){
                System.out.println("Выход из задачи");
                return null;
            }
            System.out.print("Company name(s): ");
            int countComputerCompanies = Integer.parseInt(input.nextLine());
            for (int i = 0; i < countComputerCompanies; i++){
                System.out.printf("Choose company number (%d company out of %d%n): ", i+1,countComputerCompanies);
                int numberCompany = Integer.parseInt(input.nextLine());
                computer.getCompanies().add(companyService.list().get(numberCompany-1));
            }
            System.out.print("Year of creation: ");
            computer.setReleaseYear(Integer.parseInt(input.nextLine()));
            return computer;

        }catch (Exception e){
            return null;
        }
    }




    @Override
    public boolean printList(List<Computer> computers) {
        try {
            if(computers.size() == 0) return false;
            for(int i = 0; i < computers.size(); i++){
                StringBuilder sbCompanies = new StringBuilder();
                for (int j = 0; j < computers.get(i).getCompanies().size(); j++) {
                    sbCompanies.append(computers.get(i).getCompanies().get(j).getName());
                    sbCompanies.append(" . ");
                }
                System.out.printf("%d. %s. %s%d%n", i+1,computers.get(i).getModel(),sbCompanies.toString(),computers.get(i).getReleaseYear());
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }
