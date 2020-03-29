package com.example.enkripdekrip.service

import com.example.enkripdekrip.model.dekrip.PostDekrip
import com.example.enkripdekrip.model.dekrip.ResponseDekrip
import com.example.enkripdekrip.model.enkrip.PostEnkrip
import com.example.enkripdekrip.model.enkrip.ResponseEnkrip
import com.example.enkripdekrip.utils.Constants
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface BaseApi {

    @POST("enkripsi_chiper.php")
    fun postDataEnkrip(
        @Body postEnkrip: PostEnkrip?
    ) : Observable<Response<ResponseEnkrip>>

    @POST("dekripsi_chiper.php")
    fun postDayaDekrip(
        @Body postDekrip: PostDekrip?
    ) : Observable<Response<ResponseDekrip>>

    companion object {
        var URL: String = Constants.BASE_URL
        fun create(): BaseApi {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val clientBuilder = OkHttpClient.Builder()
            clientBuilder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()

            val client = clientBuilder.build()
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(BaseApi::class.java)
        }
    }
}