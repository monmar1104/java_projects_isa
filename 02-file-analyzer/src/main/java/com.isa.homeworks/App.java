package com.isa.homeworks;

import com.sun.tools.classfile.Opcode;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class App {

    public static void main( String[] args ) {

        ArrayList<Activity> activityList = new ArrayList<>();
        FileReader readActivitiesFile = new FileReader("activities.csv");
        Scanner fileScanner = readActivitiesFile.getScanner();
        Pattern pattern = Pattern.compile("([0-9]{4}-[0-9]{2}-[0-9]{2}) (.+):.+,(.+),.+,(.+),(.+),(.+)$");

        while (fileScanner.hasNextLine()) {
            Activity activity = new Activity();
            String activityItem = fileScanner.nextLine();
            Matcher matcher = pattern.matcher(activityItem);

            if (matcher.matches()) {
                activity.setActivityName(matcher.group(4));
                activity.setActivitySpentTime(Integer.valueOf(matcher.group(6)));
                activity.setActivityStartHour(Integer.valueOf(matcher.group(2)));
                activityList.add(activity);
            }
        }
        int sum = 0;
        double average;
        ArrayList<String> facebookHours = new ArrayList<>();
        ArrayList<Integer> hoursList = new ArrayList<>();
        int[] spentTiameSum = new int[hoursList.size()];
        ArrayList<Integer> spentTimeList = new ArrayList<>();
        int hour = 0;
        for (Activity act: activityList) {
            spentTimeList.add(act.getActivitySpentTime());
            sum = sum+act.getActivitySpentTime();
            if(act.getActivityName().equals("facebook.com")){
               if(!facebookHours.contains(String.valueOf(act.getActivityStartHour()))){
                   facebookHours.add(String.valueOf(act.getActivityStartHour()));
               }
            }
            if(!hoursList.contains(act.getActivityStartHour())){
                hoursList.add(act.getActivityStartHour());
            }
            if(hour==act.getActivityStartHour()){
                hoursList.indexOf(act.getActivityStartHour());
                //spentTiameSum[hoursList.indexOf(act.getActivityStartHour())] = spentTiameSum[hoursList.indexOf(act.getActivityStartHour())]+act.getActivitySpentTime();
            }
            else {
                hour = act.getActivityStartHour();

            }

        }

        average = (double) sum/spentTimeList.size();
        System.out.println("1) Activities: "+activityList.size());
        System.out.println("2) Statystyka wszystkich aktywności:");
        System.out.println("   a) Najkrótsze: "+Collections.min(spentTimeList));
        System.out.println("   b) Najdłuższe: "+Collections.max(spentTimeList));
        System.out.println("   c) Średnia aktywność: "+BigDecimal.valueOf(average).setScale(2,0));
        System.out.println("   d) Łączny czas pracy: "+(sum/3600)+" godzin i "+((sum%3600)/60)+" minut");
        System.out.println("3) Godziny na facebooku: "+facebookHours);
        System.out.println("4) Najbardziej intensywna godzina: "+" (Pracowano) "+" minut");
            System.out.println(hoursList);

            for (int i=0;i<spentTiameSum.length;i++){
                System.out.println(spentTiameSum[i]);
            }



    }
}
