package gt.edu.umg.ingenieria.sistemas.laboratorio1.controller;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ClientService;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ReportService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/clientes")
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    private ReportService reportService;
    Client cliente  = new Client();

   // @Autowired
    //private ReportService reportService;
    
    @PostMapping("/crearCliente")

    
     public String create(@RequestBody(required = true) Client newClient){
        return this.clientService.createClient(newClient);
   
    }
    
    @GetMapping("/buscarTodos")
    public List<Client> findAllClients() {
    return this.clientService.getAllClients();
    }
    
    @GetMapping("/buscarPorNit")
    public Client getByNit(@RequestParam(name = "nit", required = true) String nit) {
        return this.clientService.getClientByNit(nit);
    }
    
    @GetMapping("/buscarPorNombreApellido")
    public List<Client> getByFirstLast(@RequestParam String query) {
        return this.clientService.getClientsByFirstLast(query);
    }
    
   
    @PutMapping("/editarCliente/{id}")
    public Client update(@PathVariable(required = true) int id, @RequestBody(required = true) Client clientToUpdate) {
        return this.clientService.updateClient(clientToUpdate);
    }   
  
    @PutMapping("/editarCliente/{id}/{nit}")
    public Client updateClient(@PathVariable(required = true) Long id,@PathVariable(required = true) String nit) 
    {
       return this.clientService.updateClientNit(id, nit); 
    } 
    
    @PutMapping("/editarCliente/{id}/{nombre}/{apellido}")
    public Client updateClientNombreApe(@PathVariable(required = true) Long id,@PathVariable(required = true) String nombre,@PathVariable(required = true) String apellido) 
    {
       return this.clientService.updateClientNombreAp(id, nombre,apellido); 
    }
    
    
  
}
