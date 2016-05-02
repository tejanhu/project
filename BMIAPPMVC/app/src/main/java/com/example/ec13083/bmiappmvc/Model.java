package com.example.ec13083.bmiappmvc;

/**
 * Created by LatinoWolofKid on 28/01/16.
 */
public class Model {

    String weight="";
    String height="";
    String result="";
    String message="";

    String btnText="";


    public Model(String w, String h, String r, String msg, String btn_text) {
        w = weight;
        h = height;
        r = result;
        msg = message;
        btn_text=btnText;
    }


    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }


}
