package com.example.pokemon.Presentation;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokemon.Controller.Adapter.PokeListAdapter;
import com.example.pokemon.Controller.Common.Constant;
import com.example.pokemon.Controller.Common.ItemOffsetDecoration;
import com.example.pokemon.Model.Pokedex;
import com.example.pokemon.R;
import com.example.pokemon.Controller.Data.IPokeApi;
import com.example.pokemon.Controller.Data.Singletons;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonList extends Fragment {

    IPokeApi iPokemonDex;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView pokemon_list_recyclerview;
    static PokemonList instance;

    public static PokemonList getInstance() {
        if(instance==null) instance = new PokemonList();
        return instance;
    }

    public PokemonList() {
        Retrofit retrofit = Singletons.getInstace();
        iPokemonDex = retrofit.create(IPokeApi.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        pokemon_list_recyclerview = (RecyclerView) view.findViewById(R.id.pokemon_list_recyclerview);
        pokemon_list_recyclerview.setHasFixedSize(true);
        pokemon_list_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),1));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(),R.dimen.spacing);
        pokemon_list_recyclerview.addItemDecoration(itemOffsetDecoration);

        fetchData();
        return view;
    }

    private void fetchData() {
        compositeDisposable.add(iPokemonDex.getListPokemon()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        Constant.commonPokemonList = pokedex.getPokemon();
                        PokeListAdapter adapter = new PokeListAdapter(getActivity(), Constant.commonPokemonList);
                        pokemon_list_recyclerview.setAdapter(adapter);
                    }
                })
        );
    }
}
