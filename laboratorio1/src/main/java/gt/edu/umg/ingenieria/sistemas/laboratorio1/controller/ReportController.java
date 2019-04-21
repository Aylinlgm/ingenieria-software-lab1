/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.edu.umg.ingenieria.sistemas.laboratorio1.controller;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ReportService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aylin
 */
@RestController
@RequestMapping("/clientes")
public class ReportController {
    
     @Autowired
     private ReportService reportService;
     
    @GetMapping("/generarReporteClientes")
    public String reporteCliente() throws IOException{
        return this.reportService.reporteClient();
    }
    
}
