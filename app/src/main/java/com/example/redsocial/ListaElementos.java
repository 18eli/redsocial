package com.example.redsocial;

public class ListaElementos {
    public String color;
    public String name;
    public String comment;

    public ListaElementos(String color, String name, String comment) {
        this.color = color;
        this.name = name;
        this.comment = comment;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
