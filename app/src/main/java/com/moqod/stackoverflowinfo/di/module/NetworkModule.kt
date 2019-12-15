package com.moqod.stackoverflowinfo.di.module

import android.content.Context
import com.moqod.stackoverflowinfo.BuildConfig
import com.moqod.stackoverflowinfo.network.tags.TagsRepository
import com.moqod.stackoverflowinfo.network.tags.TagsService
import com.moqod.stackoverflowinfo.network.tags.TagsServiceImp
import com.moqod.stackoverflowinfo.network.NetworkStatus
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    private const val TIMEOUT = 60L

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideAccountRepo(tagsService: TagsService): TagsRepository {
        return TagsServiceImp(
            tagsService
        )
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideTagService(@Named("apiRetrofit") retrofit: Retrofit): TagsService {
        return retrofit.create(TagsService::class.java)
    }


    /**
     * Provides the OkHttpClient object.
     * @return the OkHttpClient object
     */
    @Provides
    @Singleton
    @JvmStatic
    @Named("apiOkHttpClient")
    internal fun provideApiOkHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE


        val builder = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .followRedirects(false)
                .followSslRedirects(false)

        return builder.build()
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Singleton
    @JvmStatic
    @Named("apiRetrofit")
    internal fun provideRetrofitInterface(@Named("apiOkHttpClient") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }



    @Provides
    @Singleton
    @JvmStatic
    internal fun provideNetworkStatus(context: Context): NetworkStatus {
        return NetworkStatus(context)
    }
}