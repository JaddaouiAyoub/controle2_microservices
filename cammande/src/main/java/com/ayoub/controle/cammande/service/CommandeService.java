package com.ayoub.controle.cammande.service;

import com.ayoub.controle.cammande.kafka.CommandeProducer;
import com.ayoub.controle.cammande.model.Commande;
import com.ayoub.controle.cammande.model.Product;
import com.ayoub.controle.cammande.repository.CommandeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeService {
    // Injecter CommandeProducer
    private final CommandeProducer commandeProducer;

    private final CommandeRepository commandeRepository;

    public Commande createCommande(Commande commande) {
        Commande savedCommande = commandeRepository.save(commande);

        // Publier un message Kafka
        String message = "Nouvelle commande créée : ID=" + savedCommande.getId() +
                ", ProduitID=" + savedCommande.getProduitId() +
                ", Quantité=" + savedCommande.getQuantite();
        commandeProducer.sendMessage(message);

        return savedCommande;
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }
    @CircuitBreaker(name = "commande-service", fallbackMethod = "fallbackGetProductDetails")

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande not found with id: " + id));
    }

    public Commande updateCommande(Long id, Commande updatedCommande) {
        Commande existingCommande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande not found with id: " + id));

        existingCommande.setProduitId(updatedCommande.getProduitId());
        existingCommande.setQuantite(updatedCommande.getQuantite());
        return commandeRepository.save(existingCommande);
    }

    public void deleteCommande(Long id) {
        if (!commandeRepository.existsById(id)) {
            throw new RuntimeException("Commande not found with id: " + id);
        }
        commandeRepository.deleteById(id);
    }

    // Méthode fallback
    public Product fallbackGetProductDetails(Long productId, Throwable throwable) {
        // Retournez une réponse par défaut ou gérez l'exception
        return new Product(productId, "Produit par défaut", "Description par défaut", BigDecimal.ZERO);
    }
}
