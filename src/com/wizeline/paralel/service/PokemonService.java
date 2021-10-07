package com.wizeline.paralel.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wizeline.paralel.dto.PokemonData;
import com.wizeline.paralel.dto.PokemonMain;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PokemonService {
    private static String POKEMON_API = "https://pokeapi.co/api/v2/pokemon/";
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public PokemonService() {
        this.httpClient = HttpClient.newBuilder().build();
        this.mapper = new ObjectMapper();
    }

    public PokemonMain getMainPokemon() throws Exception{
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(POKEMON_API))
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) {
            throw new Exception("Error processing Pokemons");
        }
        return this.mapper.readValue(response.body(), PokemonMain.class);
    }

    public PokemonData getPokemonData(String url) throws Exception{
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) {
            throw new Exception("Error processing Pokemons");
        }
        return mapper.readValue(response.body(), PokemonData.class);
    }
}
