package com.ayoub.controle.cammandes.graphql;

import com.ayoub.controle.cammandes.model.Commande;
import com.ayoub.controle.cammandes.service.CommandeService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandeResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final CommandeService commandeService;

    // Query: Get all commandes
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    // Query: Get a single commande by ID
    public Commande getCommandeById(Long id) {
        return commandeService.getCommandeById(id);
    }

    // Mutation: Create a new commande
    public Commande createCommande(CommandeInput commandeInput) {
        Commande commande = new Commande();
        commande.setProduitId(commandeInput.getProduitId());
        commande.setQuantite(commandeInput.getQuantite());
        return commandeService.createCommande(commande);
    }

    // Mutation: Update an existing commande
    public Commande updateCommande(Long id, CommandeInput commandeInput) {
        Commande updatedCommande = new Commande();
        updatedCommande.setProduitId(commandeInput.getProduitId());
        updatedCommande.setQuantite(commandeInput.getQuantite());
        return commandeService.updateCommande(id, updatedCommande);
    }

    // Mutation: Delete a commande
    public String deleteCommande(Long id) {
        commandeService.deleteCommande(id);
        return "Commande with ID " + id + " was deleted successfully!";
    }
}
