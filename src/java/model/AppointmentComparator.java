/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;

/**
 *
 * @author slfx7
 */
public class AppointmentComparator implements Comparator<Appointment> {

    @Override
    public int compare(Appointment p, Appointment q) {
        int ret = p.getDate().compareTo(q.getDate());
        if(ret == 0) {
            ret = p.getStart().compareTo(q.getStart());
        }
        return ret;
    }
}
