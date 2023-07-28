package com.example.datatofile;

import java.util.Comparator;

public class Compare implements Comparator<Studenti> {
    @Override
    public int compare(Studenti o1, Studenti o2){
        return CharSequence.compare(o1.getPrenume(), o2.getPrenume());
    }
}
