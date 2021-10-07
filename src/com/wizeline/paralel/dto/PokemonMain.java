package com.wizeline.paralel.dto;

import java.util.List;

public class PokemonMain {
    private int count;
    private String next;
    private String previous;
    private List<PokemonPage> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PokemonPage> getResults() {
        return results;
    }

    public void setResults(List<PokemonPage> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "PokemonMain{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}
