
package com.moringaschool.jbooks.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.moringaschool.jbooks.Constants.GOOGLE_API_KEY;
import static com.moringaschool.jbooks.Constants.GOOGLE_BASE_URL;




public class GoogleClient {
    private static Retrofit retrofit = null;

    public static GoogleApi getClient() {

        if (retrofit == null) {
//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request newRequest  = chain.request().newBuilder()
//                                    .addHeader("Authorization", GOOGLE_API_KEY)
//                                    .build();
//                            return chain.proceed(newRequest);
//                        }
//                    })
//                    .build();
            HttpLoggingInterceptor  inteceptor = new HttpLoggingInterceptor();
            inteceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(inteceptor)
            .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(GOOGLE_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(GoogleApi.class);
    }
}
