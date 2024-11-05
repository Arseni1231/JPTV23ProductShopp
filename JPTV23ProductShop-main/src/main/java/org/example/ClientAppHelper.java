package org.example;

import org.example.intefaces.AppHelper;
import org.example.intefaces.Input;
import org.example.model.Client;

import java.util.List;

public class ClientAppHelper implements AppHelper<Client> {
    private final Input input;

    public ClientAppHelper(Input input) {
        this.input = input;
    }

    @Override
    public Client create() {
        try {
            Client client = new Client();
            System.out.print("Имя пользователя: ");
            client.setFirstName(input.nextLine());
            System.out.print("Фамилия пользователя: ");
            client.setLastName(input.nextLine());
            System.out.print("Телефон: ");
            client.setPhone(input.nextLine());
            return client;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean printList(List<Client>clients) {
        try {
            if(clients.size() == 0) return false;
            for(int i = 0; i < clients.size(); i++){
                System.out.printf("%d. %s %s. %s%n",
                        i+1,
                        clients.get(i).getFirstName(),
                        clients.get(i).getLastName(),
                        clients.get(i).getPhone(),
                        clients.get(i).getClientId(),
                        clients.get(i).getPhone()
                );
            }
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.toString());
            return false;
        }
    }
}
