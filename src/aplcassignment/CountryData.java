/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplcassignment;

import java.util.Date;

/**
 *
 * @author rainy
 */
public class CountryData {
    private Date date;
    private int number;

    public CountryData(Date date, int number) {
        this.date = date;
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getData() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }   
}
