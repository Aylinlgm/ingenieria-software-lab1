package gt.edu.umg.ingenieria.sistemas.laboratorio1.service;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.dao.ClientRepository;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Excepciones;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
   
        /*
        public Client createClient(Client client){ 
        return this.clientRepository.save(client);
        }
        */
    
        public String createClient(Client client){ 
        
        String cadena = "";
        String FirstName2 = "";
        String LastName2 = "";
        String FirstName1 = client.getFirstName();
        String []  division1 = FirstName1.split(" ");
        String LastName1 = client.getLastName();
        String []  division2 = LastName1.split(" ");

            try {
            
            for (int j = 0; j < division1.length; j++) {
                division1[j] = division1[j].substring(0, 1).toUpperCase() + division1[j].substring(1).toLowerCase();
                FirstName2 = FirstName2 + division1[j] + " ";
            }
            
            for (int j = 0; j < division2.length; j++) {
                division2[j] = division2[j].substring(0, 1).toUpperCase() + division2[j].substring(1).toLowerCase();
                LastName2 = LastName2 + division2[j] + " ";
            }
            
            client.setFirstName(FirstName2);
            client.setLastName(LastName2);
            
            validaciones(client.getAge(),client.getNit());
            this.clientRepository.save(client).toString();
            
            cadena = "id = " + client.getId() + "\nfirstName = " + client.getFirstName()+ "\nlastName = " + client.getLastName() +
                     "\nage = " + client.getAge() + "\nphone = " + client.getPhone() + "\naddress = " + client.getAddress() +
                     "\nnit = " + client.getNit();
            return cadena;
        }
            catch (Excepciones e) 
            {
            return cadena = e.getMessage();
             }
        }
        
    
      public List<Client> getAllClients() {
        return (List<Client>) this.clientRepository.findAll();
    }
      
    public Client getClientByNit(String nit) {
        return this.clientRepository.findClientByNit(nit);
    }
    
    
      public List<Client> getClientsByFirstLast(String query) {
        return (List<Client>) this.clientRepository.findByFirstLast(query.replace("*","%"));
    }
      
    
  
        public Client updateClient(Client client) {
        return this.clientRepository.save(client);        
    }
    
        
       public Client updateClientNit(Long id,String nit) {
        Client c1 = this.clientRepository.findById(id).get();
        c1.setNit(nit);
        return this.clientRepository.save(c1);
        }
       
       public Client updateClientNombreAp(Long id,String nombre,String apellido) {
        
          
        Client c1 = this.clientRepository.findById(id).get();
        c1.setFirstName(nombre);
        c1.setLastName(apellido);
        return this.clientRepository.save(c1);
        }
       
       //validaciones
       
       static void validaciones(int edad, String nit)throws Excepciones{
        
        boolean validar = false;
        
        if((edad<18)){
            throw new Excepciones("El sistema solo permite el ingreso de mayores de edad.");
        }
        
        if(nit.matches("[0-9]*")){
            validar = true;
        }
        
        if ((nit.length()>10) || (validar == false)) {
            throw new Excepciones("Numero de NIT invalido.");
        } 
    }
    
    
}
