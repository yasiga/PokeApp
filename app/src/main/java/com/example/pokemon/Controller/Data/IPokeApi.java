package com.example.pokemon.Controller.Data;

import com.example.pokemon.Model.Pokedex;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IPokeApi {
        @GET("pokedex.json")
        Observable<Pokedex> getListPokemon();
}

