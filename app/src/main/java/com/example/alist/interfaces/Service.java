package com.example.alist.interfaces;



import com.example.alist.model.MassageModel;
import com.example.alist.model.RequestModel;
import com.example.alist.model.complaint.ComplaintData;
import com.example.alist.model.filter.FilterData;
import com.example.alist.model.ComplaintModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Service {



    @POST("Complaint")
    Call<ComplaintModel> userData(@Header("Content-Type") String type
            , @Body RequestModel requestModel);

    @GET("Archive")
    Call<List<ComplaintData>> getArchive();

    @POST("Filter")
    Call<FilterData> filter(@Body FilterData complaintData);

    @FormUrlEncoded
    @POST("search")
    Call<List<ComplaintData>> searchSpacecraft(@Field("query") String query);

    @GET("massage/{id}")
    Call<ComplaintData> getDetails(@Path("id") int id);

    @Multipart
    @POST("massage")
    Call<MassageModel> massage(@Header("Authorization") String token, @Part("massage") RequestBody massage);


    @GET("allMassages")
    Call<MassageModel> allMassages(@Header("Authorization") String token);


    class ApiClient {

        private static final String BASE_URL = "";

        //
        public static Service getRetrofitInstance() {

//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .writeTimeout(10000, TimeUnit.SECONDS)
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request newRequest = chain.request().newBuilder()
//                                    .addHeader("Accept", "application/json")
//                                    .addHeader("Content-Type", "multipart/form-data")
//                                    .addHeader("X-Requested-With", "XMLHttpRequest")
//                                    // .addHeader("Authorization", "Bearer" + Token)
//                                    .addHeader("Accept-Language", "en")
//
//                                    .build();
//                            return chain.proceed(newRequest);
//                        }
//                    })
//
//                    .readTimeout(10000, TimeUnit.SECONDS).addInterceptor(interceptor).addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request newRequest = chain.request().newBuilder()
//                                    .addHeader("Accept", "application/json")
//                                    .addHeader("Content-Type", "multipart/form-data")
//                                    .addHeader("X-Requested-With", "XMLHttpRequest")
//                                    // .addHeader("Authorization", "Bearer" + Token)
//                                    .addHeader("Accept-Language", "en")
//
//                                    .build();
//                            return chain.proceed(newRequest);
//                        }
//                    })
//                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
                    .build();


            return retrofit.create(Service.class);
        }
    }

}

