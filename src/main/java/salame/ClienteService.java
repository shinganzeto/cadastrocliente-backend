package salame;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("dbcliente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteService {
    
    @PersistenceContext(unitName = "persistence")
    private EntityManager entityManager;
    
    
    public ClienteService(){
    }
    
    @GET
    public List<Cliente> getClientes(){  
        Query query = entityManager.createQuery("SELECT c FROM Cliente c");
        return query.getResultList();
    }
    
    
    @POST
    public Cliente adicionar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }
    
    @PUT
    @Path("{id}")
    public Cliente atualizar (@PathParam("id")Integer id, Cliente clienteAtualizado){
        
        entityManager.merge(clienteAtualizado);
        return clienteAtualizado;
    }
    
    @DELETE
    @Path("{id}")
    public Cliente excluir (@PathParam("id") Integer id){
        Cliente cliente = getCliente(id);
        entityManager.remove(cliente);
        return cliente;
    }
    
    @GET
    @Path("{id}")
    public Cliente getCliente (@PathParam("id") Integer id){
        return entityManager.find(Cliente.class, id);        
    }
    
    
    
}