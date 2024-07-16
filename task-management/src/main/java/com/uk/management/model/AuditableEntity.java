package com.uk.management.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class AuditableEntity {

    @Column(name="created_at", updatable = false)
    protected LocalDateTime createdAt;

    @Column(name="created_by" , updatable = false)
    protected String createdBy;

    @Column(name="updatedAt")
    protected LocalDateTime updatedAt;

    @Column(name="updatedBy")
    protected String updatedBy;

    @PrePersist
public void setAuditFields(){
        //We need to to this Authorization sessionUtils for createdBy and modifiedBy column
    this.createdBy= "SYSTEM";
        this.createdAt= LocalDateTime.now();
    this.updatedAt=null;
    this.updatedBy=null;
    }

    @PreUpdate
    public void updateAuditFields(){
       // this.updatedBy= SessionUtils.getCurrentUser()

        this.updatedAt= LocalDateTime.now();
    }


}
