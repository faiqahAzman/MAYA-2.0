package com.raven.model;

import javax.swing.Icon;

public class ModelCard {

   
    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public ModelCard(String title,String desc, int percentage) {
        this.title = title;
        this.desc = desc;
        this.percentage = percentage;
        //this.icon = icon;
    }

    public ModelCard() {
    }

    private String title;
    private String desc;
    private int percentage;
    private Icon icon;
}
