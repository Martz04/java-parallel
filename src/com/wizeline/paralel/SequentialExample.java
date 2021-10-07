package com.wizeline.paralel;

import com.wizeline.paralel.dto.PokemonMain;
import com.wizeline.paralel.dto.PokemonPage;
import com.wizeline.paralel.service.PokemonService;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SequentialExample {

    public static void main(String[] args) throws Exception {
        var startTime = Instant.now();
        var pokemonService = new PokemonService();

        var pokemonMainPage = pokemonService.getMainPokemon();

        var searchList = List.of("bulbasaur", "charmander", "squirtle");

        //var filteredPokemons = filterPokemonsByName(pokemonMainPage, searchList);
        var filteredPokemons = filterPokemonsByNumber(pokemonMainPage, 10);

        filteredPokemons.forEach( pokemon -> {
            try {
                var pokemonData = pokemonService.getPokemonData(pokemon.getUrl());
                System.out.println(pokemonData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        var endTime = Instant.now();
        var elapsedTime = Duration.between(startTime, endTime);
        System.out.println(String.format("Elapsed: (%d) milliseconds", elapsedTime.toMillis()));
    }

    private static Set<PokemonPage> filterPokemonsByName(PokemonMain pokemonMainPage, List<String> searchList) {
        return pokemonMainPage.getResults()
                .stream().filter( result -> searchList.contains(result.getName()))
                .collect(Collectors.toSet());
    }

    private static Set<PokemonPage> filterPokemonsByNumber(PokemonMain pokemonMainPage, int limit) {
        return pokemonMainPage.getResults()
                .stream().limit(limit)
                .collect(Collectors.toSet());
    }
}
