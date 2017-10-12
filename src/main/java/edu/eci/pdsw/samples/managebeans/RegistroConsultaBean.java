/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managebeans;


import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Eps;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosPacientes;
import edu.eci.pdsw.samples.services.ServiciosHistorialPacientesFactory;
import edu.eci.pdsw.samples.services.ServiciosPacientes;
import edu.eci.pdsw.samples.simpleview.ServiciosPacientesImpl;
import edu.eci.pdsw.samples.simpleview.Tupla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "HistorialPacientes")
@SessionScoped
public class RegistroConsultaBean implements Serializable {
    
    public Paciente paciPivote;
    public int nID;
    public String documenType;
    public String name;
    public String date;
    public Eps epsPaci;
    private  List<Consulta> consultas;
    private Consulta consulta;
    private int id;
    private String fechayHora;
    private String resumen;
    private long costo;
    private final ServiciosPacientes servicepacientes = ServiciosHistorialPacientesFactory.getInstance().getServiciosPaciente();
    public List<Paciente> listaPacientes;
    public List<Eps> epsRegis;
    public Eps eps1;
    public List<String> nombresEps;
    public String nombreEps;
    public Set<Consulta> consultas2;
    

    

    public RegistroConsultaBean() throws ExcepcionServiciosPacientes {
        epsRegis = new ArrayList<>();
        nombresEps = new ArrayList<>();
        epsRegis = servicepacientes.obtenerEPSsRegistradas();
        cargarNombresEps(epsRegis);
        listaPacientes = servicepacientes.consultarPacientes();
        consultas= new ArrayList<>();
        consultas2= new LinkedHashSet<>();
    }
 
    
    public void cargarNombresEps(List<Eps> listaEps){
        for(Eps eps: listaEps){
            nombresEps.add(eps.getNombre());
        } 
    }
    public Eps escogerEps(String nombreEps){        
        for(Eps eps: epsRegis){
            if(eps.getNombre().equals(nombreEps)){
                eps1 = eps;
            }
        }
        return eps1;
    }
    public void registrarNuevoPaciente() throws ExcepcionServiciosPacientes {           
        Paciente paciente1 = new Paciente(nID,documenType, name, java.sql.Date.valueOf(date), escogerEps(nombreEps));
        servicepacientes.registrarNuevoPaciente(paciente1);
        listaPacientes = servicepacientes.consultarPacientes();
    }
    public void setNombreEps(String nombreEps){
        this.nombreEps=nombreEps;
    }
    public String getNombreEps(){
        return nombreEps;
    }
    public void setNombresEps(List<String> nombresEps){
        this.nombresEps = nombresEps;        
    }
    public List<String> getNombresEps(){
        return nombresEps;        
    }

    public void setListaPacientes(List<Paciente> pacientes){
        this.listaPacientes = pacientes;
    }
    public List<Paciente> getListaPacientes(){
        return listaPacientes;
    }
    public void setPaciPivote(Paciente paciPivote){
        this.paciPivote = paciPivote;
    }
    public Paciente getPaciPivote(){
        return paciPivote;
    }
    public void setnID(int nID){
        this.nID = nID;
    }
    public void setdocumenType(String documenType){
        this.documenType = documenType;
    }
    public void setname(String name){
        this.name = name;
    }
    public void setdate(String date){
        this.date  = date;
    }
    public void setepsPaci(Eps epsPaci){
        this.epsPaci = epsPaci;
    }
    public int getnID(){
        return nID;
    }
    public String getdocumenType(){
        return documenType;
    }
    public String getname(){
        return name;
    }
    public String getdate(){
        return date;
    }
    public Eps getepsPaci(){
        return epsPaci;
    }
   
    public void showMessage(String estado, String mensaje) {
        FacesMessage message;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, estado, mensaje);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void setConsultas(List<Consulta> c){
        this.consultas=c;
    }
    
    public List<Consulta> getConsultas() throws ExcepcionServiciosPacientes{        
        return consultas;
    }
    
    public void setConsulta(Consulta c){
        this.consulta=c;
    }
    
    public Consulta getConsulta(){
        return consulta;
    }

    public void crearConsulta() throws ExcepcionServiciosPacientes{        
        id+=1;
        consulta=new Consulta(java.sql.Date.valueOf(fechayHora),resumen,costo);
        consulta.setId(id); 
        consultas.add(consulta);   
        consultas2.add(consulta);
    }
    
    public int getId(){
        return id;
    }
    public void setId(int s){
        this.id=s;
    }
    
    public String getfechayHora(){
        return fechayHora;
    }
    public void setfechayHora(String s){
        this.fechayHora=s;
    }
    
    public String getResumen(){
        return resumen;
    }
    public void setResumen(String s){
        this.resumen=s;
    }
    
     public long getCosto(){
        return costo;
    }
    public void setCosto(long s){
        this.costo=s;
    }
    
    public void setConsultas2(Set<Consulta> con){
        this.consultas2=con;
    }
    
    public Set<Consulta> getConsultas2(){
        return consultas2;
    }
            
    public void seleccionarPaciente(){
        name=listaPacientes.get(0).getNombre();
        nombreEps=listaPacientes.get(0).getEps().getNombre();
        consultas2=listaPacientes.get(0).getConsultas();
    }            
    
}
