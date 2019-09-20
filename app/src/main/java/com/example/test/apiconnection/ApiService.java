package com.example.test.apiconnection;

import com.example.test.model.Item;
import com.example.test.model.ItemDescription;
import com.example.test.model.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * Obtengo con el ID del item los detalles del mismo
     * @param id codigo del item
     * @param attributes solo busco los atributos que me interesan mostrar
     * @return Llamada a la api
     */
    @GET("items/{id}")
    Call<Item> getItem(@Path("id") String id, @Query("attributes") String attributes);

    /**
     * Intenta encontrar itmes que coincidan con el criterio de busqueda
     * @param query criterio de busqueda
     * @return Llamada a la api
     */
    @GET("sites/MLA/searchItems")
    Call<Search> getSearch(@Query("q") String query);

    /**
     * Obtiene la descripcion de un item determinado
     * @param id codigo del item
     * @return Llamada a la api
     */
    @GET("items/{id}/description")
    Call<ItemDescription> getItemDescription(@Path("id") String id);
}
