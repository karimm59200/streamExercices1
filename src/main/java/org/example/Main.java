package org.example;

import org.example.trading.Trader;
import org.example.trading.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {


//        System.out.println("Hello world!");
public static void main(String[] args) {
    Trader abdallah = new Trader("Abdallah", "Cambridge");
    Trader audrey = new Trader("Audrey", "Milan");
    Trader corentin = new Trader("Corentin", "Cambridge");
    Trader tristan = new Trader("Tristan", "Cambridge");


    List<Transaction> transactions = Arrays.asList(new Transaction(abdallah, 2011, 400), new Transaction(abdallah, 2012, 300), new Transaction(audrey, 2012, 1000), new Transaction(audrey, 2011, 400),
            new Transaction(corentin, 2012, 710), new Transaction(corentin, 2012, 700), new Transaction(tristan, 2012, 950), new Transaction(tristan, 2022, 900));


// trier par valeur
    List<Transaction> transaction1 = transactions.stream().filter(t->t.getYear()==2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());

    System.out.println(transaction1);
// trier par valeur unique ici la ville grace à la disctinct()
    List<String> villeUnique = transactions.stream().map(trader->trader.getTrader().getCity()).distinct().toList();
    System.out.println(villeUnique);


// trier la liste des trader par ordre alphabethique
    List<String> nomTries = transactions.stream().map(trader->trader.getTrader().getName()).sorted().distinct().toList();
    System.out.println(nomTries);


    // recuperer un boolean pour savoir si un trader est basé à milan
    boolean traderMilan = transactions.stream().map(trader->trader.getTrader().getCity()).anyMatch(b->b == "Milan");

    if(traderMilan){
        System.out.println("il y a au moins un commerçant basé à Milan");
    } else{
        System.out.println("pas de commerçant basé à Milan");
    }


    // récupérer la transaction la plus elevee et utilisation de l'Optional pour eviter les erreurs si pas de valeur.
    Optional<Integer> highTransaction = transactions.stream().map(transaction ->transaction.getValue()).max(Integer::compare);
    System.out.println(highTransaction);

    //recuperer les trader basés à cambridge et trier alphabétique et renvoie unique.
    List<Trader> cambridgeTraders = transactions.stream().map(t->t.getTrader()).filter(trader -> trader.getCity()=="Cambridge")
            .sorted(Comparator.comparing(Trader::getName))
            .distinct().collect(Collectors.toList());

    System.out.println(cambridgeTraders);

}



}

