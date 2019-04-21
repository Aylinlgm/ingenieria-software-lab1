package gt.edu.umg.ingenieria.sistemas.laboratorio1.service;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.dao.ClientRepository;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Josué Barillas (jbarillas)
 */

@Service
public class ReportService {
    
    @Autowired
    private ClientRepository clientRepository;
    
     public String reporteClient() throws IOException{ 
         
         String cadena ="";       
         
         List<Client> listaClients = (List<Client>) this.clientRepository.findAll();
    
            StringBuilder contenido = new StringBuilder();
            contenido.append("<!DOCTYPE html>\n" +
        "        <html>\n" +
        "        <head>\n" +
        "        </head>\n" +
        "        <body>\n" +
        "\n" +
        "        <h2>Reporte de Clientes</h2>");
            contenido.append("<table border='2px'>\n" +
        "          <tr>\n" +
        "            <th>ID</th>\n" +
        "            <th>FirstName</th>\n" +
        "            <th>LastName</th>\n" +
        "            <th>Age</th>\n" +
        "            <th>NIT</th>\n" +
        "            <th>Phone</th>\n" +
        "            <th>Address</th>\n" +
        "          </tr>");
            
            for (Client client1 : listaClients) {
            contenido.append("<tr>");
            contenido.append("<td>");
            contenido.append(client1.getId());
            contenido.append("</td>");
            contenido.append("<td>");
            contenido.append(client1.getFirstName());
            contenido.append("</td>");
            contenido.append("<td>");
            contenido.append(client1.getLastName());
            contenido.append("</td>");
            contenido.append("<td>");
            contenido.append(client1.getAge());
            contenido.append("</td>");
            contenido.append("<td>");
            contenido.append(client1.getPhone());
            contenido.append("</td>");
            contenido.append("<td>");
            contenido.append(client1.getAddress());
            contenido.append("</td>");
            contenido.append("<td>");
            contenido.append(client1.getNit());
            contenido.append("</td>");
            contenido.append("</tr>");
        }
            
        contenido.append("</table>\n" +
        "\n" +
        "        </body>\n" +
        "        </html>");
            
        
        File directorio = new File("/var/www");
        List<String> ficheros = Arrays.asList(directorio.list());
        
        Collections.sort(ficheros, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return extractInt(o1) - extractInt(o2);
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        }); //orden
        
        String palabra = "";
        if (ficheros.isEmpty())
           palabra = "Clientes_1.html";
        else
           palabra = ficheros.get(ficheros.size()-1); 
        
        
        char[] caracteres = palabra.toCharArray();
        String p = "";
        int contador = 0;

        for (int i = 0; i < caracteres.length; i++) {
            if(Character.isDigit(caracteres[i])){
                p += caracteres[i];
            }
        }
        
        if (ficheros.isEmpty() == false)
            contador = Integer.parseInt(p)+1;
        else
            contador = 0+1;

  
        String ruta = "/var/www/Clientes_"+contador+".html";
              File file = new File(ruta);    
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido.toString());
            bw.close();
            return cadena = "Se ha generado el reporte /var/www/Clientes_"+contador+".html";
 
     }

}
