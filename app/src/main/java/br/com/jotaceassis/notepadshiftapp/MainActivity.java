package br.com.jotaceassis.notepadshiftapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.List;

import br.com.jotaceassis.notepadshiftapp.api.NotaAPI;
import br.com.jotaceassis.notepadshiftapp.model.Nota;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etTitulo;
    private EditText etTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitulo = (EditText) findViewById(R.id.etTitulo);
        etTexto = (EditText) findViewById(R.id.etTexto);

        Stetho.initializeWithDefaults(this);

    }

    private Retrofit getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl("https://shift-notepadcloud.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public void salvar(View v) {

        final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                "Aguarde", true);
        dialog.show();

        NotaAPI api = getRetrofit().create(NotaAPI.class);

        Nota nota = new Nota(etTitulo.getText().toString(),
                etTexto.getText().toString());

        api.salvar(nota)
            .enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(MainActivity.this, "gravado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "Ops", Toast.LENGTH_LONG).show();
                }
            });
    }

    public void pesquisar(View v) {
        NotaAPI api = getRetrofit().create(NotaAPI.class);
        api.findByTitulo(etTitulo.getText().toString())
            .enqueue(new Callback<List<Nota>>() {
                @Override
                public void onResponse(Call<List<Nota>> call, Response<List<Nota>> response) {
                    if (response.isSuccessful())
                        etTexto.setText(response.body().get(0).getDescricao());
                }

                @Override
                public void onFailure(Call<List<Nota>> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            "Ops!", Toast.LENGTH_LONG).show();
                }
            });
    }
}
