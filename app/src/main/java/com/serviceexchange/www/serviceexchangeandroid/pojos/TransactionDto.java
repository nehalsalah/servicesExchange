package com.serviceexchange.www.serviceexchangeandroid.pojos;

import java.io.Serializable;
import java.math.BigInteger;

public class TransactionDto implements Serializable {

    private Integer id;
    private String type;
    private String state;
    private Long sD;
    private Long eD;
    private BigInteger duration;
    private Integer price;
    private String serviceName;
    private String otherUserName;
    private Boolean serviceProvider;
    private String jopFile;
    private String otherUserImage;
    private String serviceDescription;

    public Boolean getReviewAdded() {
        return reviewAdded;
    }

    public void setReviewAdded(Boolean reviewAdded) {
        this.reviewAdded = reviewAdded;
    }

    private Boolean reviewAdded;
    //    private String typeOfPayment;
    private Integer serviceId;
    private Integer sByUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getsD() {
        return sD;
    }

    public void setsD(Long sD) {
        this.sD = sD;
    }

    public Long geteD() {
        return eD;
    }

    public void seteD(Long eD) {
        this.eD = eD;
    }

    public Boolean getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(Boolean serviceProvider) {
        serviceProvider = serviceProvider;
    }

    public BigInteger getDuration() {
        return duration;
    }

    public void setDuration(BigInteger duration) {
        this.duration = duration;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getOtherUserName() {
        return otherUserName;
    }

    public void setOtherUserName(String otherUserName) {
        this.otherUserName = otherUserName;
    }

    public boolean isServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(boolean serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getJopFile() {
        return jopFile;
    }

    public void setJopFile(String jopFile) {
        this.jopFile = jopFile;
    }

    public String getOtherUserImage() {
        return otherUserImage;
    }

    public void setOtherUserImage(String otherUserImage) {
        this.otherUserImage = otherUserImage;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getsByUser() {
        return sByUser;
    }

    public void setsByUser(Integer sByUser) {
        this.sByUser = sByUser;
    }
}
