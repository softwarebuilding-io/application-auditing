package io.softwarebuilding.fusionplex.entity;

import io.softwarebuilding.fusionplex.util.IpUtil;
import io.softwarebuilding.fusionplex.util.LoginUtil;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -33114089982657335L;

    @Column(name = "created_on", nullable = false)
    private ZonedDateTime createdOn;

    @Column(name = "created_ip", nullable = false, length = 15)
    private String createdIp;

    @Column(name = "created_user", nullable = false, length = 60)
    private String createdUser;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;

    @Column(name = "updated_ip", length = 15)
    private String updatedIp;

    @Column(name = "updated_user", length = 60)
    private String updatedUser;

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedIp() {
        return createdIp;
    }

    public void setCreatedIp(String createdIp) {
        this.createdIp = createdIp;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public ZonedDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(ZonedDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedIp() {
        return updatedIp;
    }

    public void setUpdatedIp(String updatedIp) {
        this.updatedIp = updatedIp;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    /**
     * PrePersist data for auditing purposes.
     */
    @PrePersist
    public void prePersist() {
        this.createdUser = this.getAuthenticatedUser();
        this.createdOn = ZonedDateTime.now();
        this.createdIp = this.getClientIpAddress();
    }

    /**
     * PreUpdate data for auditing purposes.
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedUser = this.getAuthenticatedUser();
        this.updatedOn = ZonedDateTime.now();
        this.updatedIp = this.getClientIpAddress();
    }

    private String getAuthenticatedUser() {
        return LoginUtil.getAuthenticatedUser();
    }

    private String getClientIpAddress() {
        return IpUtil.getClientIpAddress();
    }

}
