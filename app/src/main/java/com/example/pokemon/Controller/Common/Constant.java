package com.example.pokemon.Controller.Common;

import com.example.pokemon.Model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static final String KEY_ENABLE_HOME = "enable_home";
    public static List<Pokemon> commonPokemonList = new ArrayList<>();

    public static List<Pokemon> CommonPokemonList = new ArrayList<>();

    public static Pokemon findPokemonByNum(String num) {
        for (Pokemon pokemon : commonPokemonList) {
            if (pokemon.getNum().equals(num))
                return pokemon;
        }
        return null;
    }
}
