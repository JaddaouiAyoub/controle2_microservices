package com.ayoub.controle.cammandes.graphql;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandeInput {
    private Long produitId;
    private int quantite;
}
