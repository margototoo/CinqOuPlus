package com.example.cinqouplus.geometrie;

public interface Geometrie {
    void draw(float[] mvpMatrix);
    float[] getPosition();
    void set_position(float[] pos);
    boolean equals(Object o);
}
