package com.aninfo.model;

import java.util.List;

public class AssociationDTO {

    Long ticket_id;
    List<Long> upList;
    List<Long> downList;

    public void setUpList(List<Long> upList){

        this.upList = upList;
    }
    public List<Long> getUpList(){

        return this.upList;
    }

    public void setDownList(List<Long> downList){

        this.downList = downList;
    }
    public List<Long> getDownList(){

        return this.downList;
    }

    public void setTicketId(Long ticket_id){

        this.ticket_id = ticket_id;
    }
    public Long getTicketId(){

        return this.ticket_id;
    }
    
}
