package com.ben.myshoppinglist.network

import android.content.Context
import com.ben.myshoppinglist.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MyShoppingListNetwork {

    companion object {
        private val gson: Gson by lazy {
            GsonBuilder()
                    .setLenient()
                    .create()
        }
        @Volatile private var INSTANCE: MyShoppingListService? = null

        fun initializeRetrofit(authToken: String? = null) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildRetrofit(authToken).also {
                INSTANCE = it
            }
        }


        private fun buildRetrofit(authToken: String? = null): MyShoppingListService {
            return Retrofit.Builder()
                    .client(OkHttpClient.Builder()
                            .addInterceptor { chain ->
                                chain.proceed(chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer $authToken")
                                .build())
                            }
                            .also { client ->
                                if(BuildConfig.DEBUG) {
                                    val logging = HttpLoggingInterceptor()
                                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                                    client.addInterceptor(logging)
                                }
                            }.build())
                    .baseUrl("http://10.0.2.2:4000")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(MyShoppingListService::class.java)
        }
//        val retrofit: MyShoppingListService? by lazy {
//            Retrofit.Builder()
//                    .client(OkHttpClient.Builder()
//                            .addInterceptor { chain ->
//                                chain.proceed(chain.request().newBuilder().also {
//                                    it.addHeader("Authorization", "Bearer ${SessionManager().onFetchAuthToken() ?: ""}")
//                                }.build())
//                            }
//                            .also { client ->
//                                if(BuildConfig.DEBUG) {
//                                    val logging = HttpLoggingInterceptor()
//                                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//                                    client.addInterceptor(logging)
//                                }
//                            }.build())
//                    .baseUrl("http://10.0.2.2:4000")
//                    .addConverterFactory(ScalarsConverterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build()
//                    .create(MyShoppingListService::class.java)
//        }
    }
}