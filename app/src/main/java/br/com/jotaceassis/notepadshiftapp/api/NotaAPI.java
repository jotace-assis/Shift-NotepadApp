package br.com.jotaceassis.notepadshiftapp.api;

import java.util.List;

import br.com.jotaceassis.notepadshiftapp.model.Nota;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotaAPI {

    @GET("nota/titutlo/{titulo}")
    Call<List<Nota>> buscarNota(@Path(value = "titulo") String titulo);

    @POST("/nota")
    Call<Void> salvar(@Body Nota nota);
}
