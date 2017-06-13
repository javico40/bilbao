package com.liderbs.modelo;
// Generated 13-jun-2017 16:31:12 by Hibernate Tools 4.0.0


import java.util.Date;

/**
 * Schedule generated by hbm2java
 */
public class Schedule  implements java.io.Serializable {


     private Integer idschedule;
     private Trainer trainer;
     private Day day;
     private Category category;
     private Place place;
     private Users users;
     private Date classDate;
     private Date starttime;
     private Date endtime;
     private Integer maxUsers;

    public Schedule() {
    }

	
    public Schedule(Trainer trainer, Day day, Category category, Place place, Users users) {
        this.trainer = trainer;
        this.day = day;
        this.category = category;
        this.place = place;
        this.users = users;
    }
    public Schedule(Trainer trainer, Day day, Category category, Place place, Users users, Date classDate, Date starttime, Date endtime, Integer maxUsers) {
       this.trainer = trainer;
       this.day = day;
       this.category = category;
       this.place = place;
       this.users = users;
       this.classDate = classDate;
       this.starttime = starttime;
       this.endtime = endtime;
       this.maxUsers = maxUsers;
    }
   
    public Integer getIdschedule() {
        return this.idschedule;
    }
    
    public void setIdschedule(Integer idschedule) {
        this.idschedule = idschedule;
    }
    public Trainer getTrainer() {
        return this.trainer;
    }
    
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
    public Day getDay() {
        return this.day;
    }
    
    public void setDay(Day day) {
        this.day = day;
    }
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    public Place getPlace() {
        return this.place;
    }
    
    public void setPlace(Place place) {
        this.place = place;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public Date getClassDate() {
        return this.classDate;
    }
    
    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }
    public Date getStarttime() {
        return this.starttime;
    }
    
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
    public Date getEndtime() {
        return this.endtime;
    }
    
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
    public Integer getMaxUsers() {
        return this.maxUsers;
    }
    
    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }




}


