package br.com.jotaceassis.notepadshiftapp.api;

import java.util.List;

import br.com.jotaceassis.notepadshiftapp.model.Nota;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotaAPI {

    @GET("nota/{titulo}")
    Call<List<Nota>> findByTitulo(@Path(value = "titulo") String titulo);

    @GET("nota")
    Call<List<Nota>> findAll();

    @POST("/nota")
    Call<Void> salvar(@Body Nota nota);
}
