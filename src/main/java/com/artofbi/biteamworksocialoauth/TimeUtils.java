/*
 * =============================================================================
 * Copyright (c) 2009-2013 Fyght Group, Inc & ArtOfBI.com. All rights reserved.
 *
 * All source code and material within this package java application 
 * is the copyright of Fyght Group, Inc, Fyght, ArtOfBI.com, and affiliated 
 * companies and
 * is protected under copyright laws of the United States. This source code may
 * not be hosted on any other site at any time.  No attempt to reverse engineer 
 * any part of the application or code shall take place. 
 *
 * We have made every effort and taken great care in making sure that the source
 * code and other content included with this application is technically accurate, 
 * but we disclaim any and all responsibility for any loss, damage or destruction of
 * data or any other property which may arise from utlizing the source code or the 
 * application for which this code is built. Technical Support may be purchased for 
 * this product application on our website, http://www.biteamwork.com,
 * http://www.artofbisoftware.com, http://www.fyghtsoft.com or http://fyght.com.
 *
 * =============================================================================
 */
package com.artofbi.biteamworksocialoauth;

import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.MILLISECONDS;



/**
 * Class provides functions related to manipulating time
 * 
 * @author CS <christian@artofbi.com>
 */
public class TimeUtils {

    /**
     * Converts time to a human readable format within the specified range
     * http://stackoverflow.com/questions/3859288/how-to-calculate-time-ago-in-java
     * @param duration the time in milliseconds to be converted
     * @param max      the highest time unit of interest
     * @param min      the lowest time unit of interest
     */
    public static String formatMillis(long duration, TimeUnit max, TimeUnit min, boolean useAbbreviation) {
        StringBuilder res = new StringBuilder();

        TimeUnit current = max;

        while (duration > 0) {
            long temp = current.convert(duration, MILLISECONDS);

            if (temp > 0) {
                duration -= current.toMillis(temp);
                res.append(temp).append(" ").append(current.name().toLowerCase());
                if (temp < 2) {
                    res.deleteCharAt(res.length() - 1);
                }
                res.append(", ");
            }

            if (current == min) {
                break;
            }

            current = TimeUnit.values()[current.ordinal() - 1];
        }

        // clean up our formatting....

        // we never got a hit, the time is lower than we care about
        if (res.lastIndexOf(", ") < 0) {
            return "0 " + min.name().toLowerCase();
        }

        // yank trailing  ", "
        res.deleteCharAt(res.length() - 2);

        //  convert last ", " to " and"
        int i = res.lastIndexOf(", ");
        if (i > 0) {
            res.deleteCharAt(i);
            res.insert(i, " and");
        }

        if( useAbbreviation ){
            return res.toString().replace("minute", "min").replace("hour", "hr");
        }
        else {        
            return res.toString();
        }
    }
}