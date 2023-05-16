package com.example.superfit.di

import com.example.superfit.common.Constants.BASE_URL
import com.example.superfit.common.Constants.CONNECT_TIMEOUT
import com.example.superfit.common.Constants.READ_TIMEOUT
import com.example.superfit.common.Constants.WRITE_TIMEOUT
import com.example.superfit.data.Interceptor
import com.example.superfit.data.api.TrainingApi
import com.example.superfit.data.repository.TrainingRepositoryImpl
import com.example.superfit.domain.repository.TrainingRepository
import com.example.superfit.data.api.ProfileApi
import com.example.superfit.data.repository.ProfileRepositoryImpl
import com.example.superfit.domain.repository.ProfileRepository
import com.example.superfit.data.api.AuthApi
import com.example.superfit.data.repository.AuthRepositoryImpl
import com.example.superfit.domain.repository.AuthRepository
import com.example.superfit.domain.usecase.auth.GetTokenUseCase
import com.example.superfit.domain.usecase.auth.LoginUseCase
import com.example.superfit.domain.usecase.auth.RegisterUseCase
import com.example.superfit.domain.usecase.profile.GetProfileInfoUseCase
import com.example.superfit.domain.usecase.profile.body.parameters.GetBodyParametersHistoryUseCase
import com.example.superfit.domain.usecase.profile.body.parameters.UpdateBodyParametersUseCase
import com.example.superfit.domain.usecase.profile.photo.DeletePhotoByIdUseCase
import com.example.superfit.domain.usecase.profile.photo.GetPhotoByIdUseCase
import com.example.superfit.domain.usecase.profile.photo.GetPhotosUseCase
import com.example.superfit.domain.usecase.profile.photo.UploadPhotoUseCase
import com.example.superfit.domain.usecase.training.GetUserTrainingListUseCase
import com.example.superfit.domain.usecase.training.SaveUserTrainingUseCase
import com.example.superfit.domain.usecase.validation.*
import com.example.superfit.presentation.authorization.auth.AuthorizationViewModel
import com.example.superfit.presentation.authorization.pin.PINViewModel
import com.example.superfit.presentation.registration.RegistrationViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    val client = OkHttpClient.Builder().apply {
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(Interceptor())

        val logLevel = HttpLoggingInterceptor.Level.BODY
        addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(AuthApi::class.java)
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(ProfileApi::class.java)
    }
    single<ProfileRepository> {
        ProfileRepositoryImpl(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(TrainingApi::class.java)
    }
    single<TrainingRepository> {
        TrainingRepositoryImpl(get())
    }

    /* UseCases */
    factory { CheckUserNameUseCase() }
    factory { CheckEmailUseCase() }
    factory { CheckCodeUseCase() }
    factory { CheckCodeRepeatUseCase() }
    factory { CheckFieldsFilledUseCase() }

    factory { GetTokenUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }

    factory { GetProfileInfoUseCase(get()) }

    factory { GetBodyParametersHistoryUseCase(get()) }
    factory { UpdateBodyParametersUseCase(get()) }

    factory { GetPhotosUseCase(get()) }
    factory { GetPhotoByIdUseCase(get()) }
    factory { UploadPhotoUseCase(get()) }
    factory { DeletePhotoByIdUseCase(get()) }

    factory { GetUserTrainingListUseCase(get()) }
    factory { SaveUserTrainingUseCase(get()) }

    /* ViewModels */
    viewModel {
        AuthorizationViewModel(get())
    }

    viewModel {
        PINViewModel(get())
    }

    viewModel {
        RegistrationViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

}