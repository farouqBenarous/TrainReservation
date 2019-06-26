package com.example.trainreservation;

public class ModelTicket {
    private  String id , dep , arv , depdate , arvdate , nbplaces , price;

    public  ModelTicket() {}

    public  ModelTicket(String id ,String dep , String arv ,  String depdate , String arvdate , String nbplaces ,String price) {
        this.id = id ;
        this.dep = dep;
        this.arv = arv ;
        this.depdate = depdate ;
        this.arvdate = arvdate;
        this.nbplaces = nbplaces ;
        this.price = price;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getArv() {
        return arv;
    }

    public void setArv(String arv) {
        this.arv = arv;
    }

    public String getDepdate() {
        return depdate;
    }

    public void setDepdate(String depdate) {
        this.depdate = depdate;
    }

    public String getArvdate() {
        return arvdate;
    }

    public void setArvdate(String arvdate) {
        this.arvdate = arvdate;
    }

    public String getNbplaces() {
        return nbplaces;
    }

    public void setNbplaces(String nbplaces) {
        this.nbplaces = nbplaces;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
