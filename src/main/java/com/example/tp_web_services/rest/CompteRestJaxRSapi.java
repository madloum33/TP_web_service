package com.example.tp_web_services.rest;



import com.example.tp_web_services.Entity.Compte;
import com.example.tp_web_services.Repository.CompteRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
@Path("/banque")
public class CompteRestJaxRSapi {
    private static final Logger logger = Logger.getLogger(CompteRestJaxRSapi.class.getName());

    @Autowired
    private CompteRepository compteRepository;

    @Path("/compte")
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Compte> listComptes() {
        logger.info("listComptes called");
        return compteRepository.findAll();
    }

    @Path("/compte")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Compte save(Compte c) {
        logger.info("save called with: " + c);
        return compteRepository.save(c);
    }

    @Path("/compte/{id}")
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Compte update(@PathParam("id") Long id, Compte compte) {
        logger.info("update called with id: " + id + " and compte: " + compte);
        Compte existingcompte = compteRepository.findById(id).orElse(null);
        if (existingcompte != null) {
            existingcompte.setSolde(compte.getSolde());
            existingcompte.setDateCreation(compte.getDateCreation());
            existingcompte.setTypeCompte(compte.getTypeCompte());
            return compteRepository.save(existingcompte);
        }
        return null;
    }

    @Path("/compte/{id}")
    @DELETE
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void delete(@PathParam("id") Long id) {
        logger.info("delete called with id: " + id);
        compteRepository.deleteById(id);
    }
}