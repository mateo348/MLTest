package com.example.test.service;

import com.example.test.model.item.Item;
import com.example.test.model.item.ItemDescription;
import com.example.test.model.search.Search;

import retrofit2.Callback;

/**
 * Servicio encargado de hacer las llamadas a la ApiService y ejecutar las acciones
 * recibidas desde los viewModel
 */
public interface ItemService {

    /**
     * Codigos re respuesta de la API
     */
    int SERVER_OK_CODE = 200;
    int SERVER_ERROR_CODE = 404;

    /**
     * Intenta encontrar itmes que coincidan con el criterio de busqueda
     * y luego ejecuta el callback
     * @param query criterio de busqueda
     * @param callback acciones a realizar luego de la llamad a a la API
     */
    void searchItems(String query, Callback<Search> callback) throws Exception;

    /**
     * Establece el Item selecconado a traves del callback
     * @param id ID del item seleccioado
     * @param callback acciones a realizar luego de la llamad a a la API (establece el Item seleccionado)
     */
    void setSelectedItem(String id, Callback<Item> callback) throws Exception;

    /**
     * Establece la descripcion completa del item
     * @param id ID del item buscado
     * @param callback acciones a realizar luego de la llamad a a la API
     */
    void setSelectedItemDescription(String id, Callback<ItemDescription> callback) throws Exception;
}
