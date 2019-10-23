/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn.ambala.keypicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author REZA
 */
public class KeyGenerator {
    
    private static String key="xxxx";

    public KeyGenerator() {
        keyGenerator();
    }

    
    
    private String intToString(int valueInt) {
        String valueStr;
        switch(valueInt){
            case 1:{
                valueStr = "A";
                break;
            }
            
            case 2:{
                valueStr = "B";
                break;
            }
            
            case 3:{
                valueStr = "C";
                break;
            }
            
            case 4:{
                valueStr = "D";
                break;
            }
            
            case 5:{
                valueStr = "E";
                break;
            }
            
            case 6:{
                valueStr = "F";
                break;
            }
            
            case 7:{
                valueStr = "G";
                break;
            }
            
            case 8:{
                valueStr = "H";
                break;
            }
            
            case 9:{
                valueStr = "I";
                break;
            }
            
            case 10:{
                valueStr = "J";
                break;
            }
            
            case 11:{
                valueStr = "K";
                break;
            }
            
            case 12:{
                valueStr = "L";
                break;
            }
            
            case 13:{
                valueStr = "M";
                break;
            }
            
            case 14:{
                valueStr = "N";
                break;
            }
            
            case 15:{
                valueStr = "O";
                break;
            }
            
            case 16:{
                valueStr = "P";
                break;
            }
            
            case 17:{
                valueStr = "Q";
                break;
            }
            
            case 18:{
                valueStr = "R";
                break;
            }
            
            case 19:{
                valueStr = "S";
                break;
            }
            
            case 20:{
                valueStr = "T";
                break;
            }
            
            case 21:{
                valueStr = "U";
                break;
            }
            
            case 22:{
                valueStr = "V";
                break;
            }
            
            case 23:{
                valueStr = "W";
                break;
            }
            
            case 24:{
                valueStr = "X";
                break;
            }
            
            case 25:{
                valueStr = "Y";
                break;
            }
            
            case 26:{
                valueStr = "Z";
                break;
            }
            
            case 27:{
                valueStr = "a";
                break;
            }
            
            case 28:{
                valueStr = "b";
                break;
            }
            
            case 29:{
                valueStr = "c";
                break;
            }
            
            case 30:{
                valueStr = "d";
                break;
            }
            
            case 31:{
                valueStr = "e";
                break;
            }
            
            case 0:{
                valueStr = "f";
                break;
            }
            
            default:{
                valueStr = "xx";
                break;
            }
        }
        return valueStr;
    }

    private void keyGenerator() {
        Calendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        month += 1;
        int year = calendar.get(Calendar.YEAR);
        year = year % 100;

        int hour = calendar.get(Calendar.HOUR);
        int ampm = calendar.get(Calendar.AM_PM);
        if (ampm == 1) {
            hour += 12;
        }
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        
        String dayStr = intToString(day);
        String monthStr = intToString(month);
        String yearStr = intToString(year);
        String hourStr = intToString(hour);
        String minuteStr = String.format("%02d", minute);
        String secondStr = String.format("%02d", second);
        
        key = dayStr + monthStr + yearStr + hourStr + minuteStr + secondStr;
    }
    
    public String getKey(){
        return key;
    }
}
