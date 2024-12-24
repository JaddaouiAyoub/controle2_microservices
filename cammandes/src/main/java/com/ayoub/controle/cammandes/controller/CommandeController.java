package com.ayoub.controle.cammandes.controller;

import com.ayoub.controle.cammandes.model.Commande;
import com.ayoub.controle.cammandes.service.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;

    // Create a new Commande
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.createCommande(commande);
    }

    // Get all Commandes
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    // Get a Commande by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }

    // Update a Commande
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Commande updateCommande(@PathVariable Long id, @RequestBody Commande commande) {
        return commandeService.updateCommande(id, commande);
    }

    // Delete a Commande
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
    }
}
