package com.liderbs.modelo;
// Generated 02-jun-2017 18:37:36 by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;

/**
 * Day generated by hbm2java
 */
public class Day  implements java.io.Serializable {


     private Integer idday;
     private String name;
     private Set<Schedule> schedules = new HashSet<Schedule>(0);
     private Set<Timetable> timetables = new HashSet<Timetable>(0);

    public Day() {
    }

    public Day(String name, Set<Schedule> schedules) {
       this.name = name;
       this.schedules = schedules;
    }
   
    public Integer getIdday() {
        return this.idday;
    }
    
    public void setIdday(Integer idday) {
        this.idday = idday;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set<Schedule> getSchedules() {
        return this.schedules;
    }
    
    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Set<Timetable> getTimetables() {
        return this.timetables;
    }
    
    public void setTimetables(Set<Timetable> timetables) {
        this.timetables = timetables;
    }



}


