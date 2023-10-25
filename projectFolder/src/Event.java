

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nadam
 */
import java.util.*;
//import java.util.*;
public class Event {
    private String name;
    private String location;
    private int id ;
    private int status = 1 ;
    private Date date;
    private int pmid;
    private int spid;
    private int cid;
    private String spname ;

    public String getSpname() {
        return spname;
    }

    public void setSpname(String sname) {
        this.spname = sname;
    }

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPmid() {
        return pmid;
    }

    public void setPmid(int pmid) {
        this.pmid = pmid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public Event(){
    
}
    public Event(int cid,String name){//this constractor will use in FlagChange
    this.cid=cid;
    this.name=name;
}

    public Event(String name , String location,String sname, Date date){
        this.name=name;
        this.location=location;
        this.date=date;
        this.spname=sname;
    }
    
    
    

}

    

