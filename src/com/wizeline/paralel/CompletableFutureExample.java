package com.wizeline.paralel;

import com.wizeline.paralel.dto.PokemonData;
import com.wizeline.paralel.dto.PokemonMain;
import com.wizeline.paralel.dto.PokemonPage;
import com.wizeline.paralel.service.PokemonService;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureExample {
    public static void main(String[] args) throws Exception {
        var startTime = Instant.now();
        var pokemonService = new PokemonService();
        var executor = Executors.newFixedThreadPool(10);

        var futurePokemonMain = CompletableFuture.supplyAsync(()-> {
            PokemonMain result = null;
            try {
                result = pokemonService.getMainPokemon();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        futurePokemonMain.thenAccept(pokemonMain -> {
            var filteredPokemons = filterPokemonsByNumber(pokemonMain, 10);
            for(var pokemon : filteredPokemons ) {
                CompletableFuture.runAsync(()-> {
                    try {
                        PokemonData pokemonData = null;
                        pokemonData = pokemonService.getPokemonData(pokemon.getUrl());
                        System.out.println(pokemonData);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }, executor);
            }
        });

        executor.awaitTermination(5, TimeUnit.SECONDS);
        executor.shutdown();

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
