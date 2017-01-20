package iamtaxi.dmi.com.imtaxi.activity;

import android.util.Log;

import java.util.List;

import iamtaxi.dmi.com.imtaxi.rest.ApiClient;
import iamtaxi.dmi.com.imtaxi.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by dmi on 1/20/2017.
 */
public class DemoActivity {
/*    ApiInterface apiService =ApiClient.createClient();

    Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
    call.enqueue(new Callback<MoviesResponse>() {
        @Override
        public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
            int statusCode = response.code();
            List<Movie> movies = response.body().getResults();
            recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
        }

        @Override
        public void onFailure(Call<MoviesResponse> call, Throwable t) {
            // Log error here since request failed
            Log.e(TAG, t.toString());
        }
    });*/
}
