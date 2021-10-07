package com.wizeline.paralel.dto;

public class PokemonType {
    private int slot;
    private Type type;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PokemonType{" +
                "slot=" + slot +
                ", type=" + type +
                '}';
    }
}
