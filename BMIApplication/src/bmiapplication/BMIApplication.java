/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmiapplication;

import java.util.Scanner;

/**
 *
 * @author LatinoWolofKid
 */
public class BMIApplication {

    double weight;
    double height;
    double bmi;
    String measurement;
    Scanner in=new Scanner(System.in);
    
    public void calculateBMI(){
        
        System.out.println("Would you like to calculate this in");
        System.out.print("Metric or Imperial (m) for metric (i) for Imperial) ");
        System.out.println(" ");
        measurement = in.nextLine();
        
        if(measurement.contains("m")){
            
            metric();
        }
        
        else if(measurement.contains("i")){
            
           imperial();
        }
    }//END calculateBMI
    
    public void metric(){
        System.out.println("Please enter your weight in (KG) ");
        weight=in.nextDouble();
          
         System.out.println("Please enter your height in (M) ");
         height=in.nextDouble();
           System.out.println(" ");
         bmi= (weight/ (height*height) );
         
         System.out.println("Your BMI is "+ bmi);
         
         if(bmi <18.5){
             System.out.println(bmi +"is considered underweight");
         }
         
         else if(bmi>18.5 && bmi < 24.9){
              System.out.println(bmi +"is considered to be of a healthy weight");
         }
         
          else if(bmi>25 && bmi < 30){
              System.out.println(bmi +"is considered to be overweight");
         }
         
           else if(bmi>40){
              System.out.println(bmi +" is considered to be morbidly obese");
         }
    }
    
    public void imperial(){
        System.out.println("Please enter your weight in (IB) ");
        weight=in.nextDouble();
          
         System.out.println("Please enter your height in (INCHES) ");
         height=in.nextDouble();
           System.out.println(" ");
         bmi= (weight/ (height*height) ) * 703;
         
         System.out.println("Your BMI is "+ bmi);
         
         if(bmi <18.5){
             System.out.println(bmi +"is considered underweight");
         }
         
         else if(bmi>18.5 && bmi < 24.9){
              System.out.println(bmi +"is considered to be of a healthy weight");
         }
         
          else if(bmi>25 && bmi < 30){
              System.out.println(bmi +"is considered to be overweight");
         }
         
           else if(bmi>40){
              System.out.println(bmi +" is considered to be morbidly obese");
         }
    }
    
}//END BMIApplication
