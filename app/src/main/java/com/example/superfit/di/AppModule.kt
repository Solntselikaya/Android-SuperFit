package com.example.superfit.di

import androidx.lifecycle.SavedStateHandle
import com.example.superfit.common.Constants.BASE_URL
import com.example.superfit.data.api.AuthApi
import com.example.superfit.data.api.ProfileApi
import com.example.superfit.data.api.TrainingApi
import com.example.superfit.data.db.ExercisesRoomDatabase
import com.example.superfit.data.db.provideDatabase
import com.example.superfit.data.network.OkHttpProvider
import com.example.superfit.data.repository.AuthRepositoryImpl
import com.example.superfit.data.repository.ProfileRepositoryImpl
import com.example.superfit.data.repository.TrainingRepositoryImpl
import com.example.superfit.data.repository.storage.FirstRunStorageRepositoryImpl
import com.example.superfit.data.repository.storage.TokenRepositoryImpl
import com.example.superfit.data.repository.storage.UserCredentialsStorageRepositoryImpl
import com.example.superfit.data.storage.encrypted.EncryptedSharedPreferencesStorage
import com.example.superfit.data.storage.firstrun.FirstRunSharedPreferencesStorage
import com.example.superfit.domain.repository.AuthRepository
import com.example.superfit.domain.repository.ProfileRepository
import com.example.superfit.domain.repository.TrainingRepository
import com.example.superfit.domain.repository.storage.FirstRunStorageRepository
import com.example.superfit.domain.repository.storage.TokenRepository
import com.example.superfit.domain.repository.storage.UserCredentialsStorageRepository
import com.example.superfit.domain.usecase.auth.GetTokenUseCase
import com.example.superfit.domain.usecase.auth.LoginUseCase
import com.example.superfit.domain.usecase.auth.RegisterUseCase
import com.example.superfit.domain.usecase.db.DeleteAllForUserFromDatabaseUseCase
import com.example.superfit.domain.usecase.db.DeleteAllFromDatabaseUseCase
import com.example.superfit.domain.usecase.db.GetAllFromDatabaseUseCase
import com.example.superfit.domain.usecase.db.GetExerciseRepeatCountFromDatabaseUseCase
import com.example.superfit.domain.usecase.db.InitExerciseDatabaseForUserUseCase
import com.example.superfit.domain.usecase.db.InsertExerciseToDatabaseUseCase
import com.example.superfit.domain.usecase.db.UpdateExerciseInDatabaseUseCase
import com.example.superfit.domain.usecase.profile.GetProfileInfoUseCase
import com.example.superfit.domain.usecase.profile.body.parameters.GetBodyParametersHistoryUseCase
import com.example.superfit.domain.usecase.profile.body.parameters.UpdateBodyParametersUseCase
import com.example.superfit.domain.usecase.profile.photo.DeletePhotoByIdUseCase
import com.example.superfit.domain.usecase.profile.photo.GetPhotoByIdUseCase
import com.example.superfit.domain.usecase.profile.photo.GetAllPhotosUseCase
import com.example.superfit.domain.usecase.profile.photo.GetProgressPhotosUseCase
import com.example.superfit.domain.usecase.profile.photo.UploadPhotoUseCase
import com.example.superfit.domain.usecase.storage.credentials.DeleteUserEmailFromLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.credentials.GetUserEmailFromLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.credentials.SaveUserEmailInLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.firstrun.DeleteFirstRunFromLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.firstrun.GetFirstRunFromLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.firstrun.SaveFirstRunInLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.token.DeleteTokenFromLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.token.GetTokenFromLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.token.SaveTokenToLocalStorageUseCase
import com.example.superfit.domain.usecase.training.api.GetUserTrainingListUseCase
import com.example.superfit.domain.usecase.training.api.SaveUserTrainingUseCase
import com.example.superfit.domain.usecase.training.db.GetExerciseRepeatCountForUserFromDatabaseUseCase
import com.example.superfit.domain.usecase.training.db.IncreaseExerciseCountUseCase
import com.example.superfit.domain.usecase.validation.CheckCodeRepeatUseCase
import com.example.superfit.domain.usecase.validation.CheckCodeUseCase
import com.example.superfit.domain.usecase.validation.CheckEmailUseCase
import com.example.superfit.domain.usecase.validation.CheckFieldsFilledUseCase
import com.example.superfit.domain.usecase.validation.CheckUserNameUseCase
import com.example.superfit.presentation.authorization.auth.AuthorizationViewModel
import com.example.superfit.presentation.authorization.pin.PINViewModel
import com.example.superfit.presentation.exercise.common.sensors.AccelerometerSensor
import com.example.superfit.presentation.exercise.crunch.CrunchViewModel
import com.example.superfit.presentation.exercise.plank.PlankViewModel
import com.example.superfit.presentation.exercise.pushups.PushUpsViewModel
import com.example.superfit.presentation.exercise.running.RunningViewModel
import com.example.superfit.presentation.exercise.running.sensors.StepSensor
import com.example.superfit.presentation.exercise.squats.SquatsViewModel
import com.example.superfit.presentation.exercises.ExercisesViewModel
import com.example.superfit.presentation.image.ImageViewModel
import com.example.superfit.presentation.imagelist.ImageListViewModel
import com.example.superfit.presentation.main.MainViewModel
import com.example.superfit.presentation.mybody.MyBodyViewModel
import com.example.superfit.presentation.registration.RegistrationViewModel
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


val appModule = module {

    /* Database */
    single {
        provideDatabase(application = get())
    }
    single {
        get<ExercisesRoomDatabase>().exercisesDao()
    }

    /* API */
    val gson = GsonBuilder()
        .setLenient()
        .create()

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpProvider.provideClient(get()))
            .build()
            .create(AuthApi::class.java)
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpProvider.provideClient(get()))
            .build()
            .create(ProfileApi::class.java)
    }
    single<ProfileRepository> {
        ProfileRepositoryImpl(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpProvider.provideClient(get()))
            .build()
            .create(TrainingApi::class.java)
    }
    single<TrainingRepository> {
        TrainingRepositoryImpl(get())
    }

    single { EncryptedSharedPreferencesStorage(androidContext()) }
    single<TokenRepository> { TokenRepositoryImpl(get()) }
    single<UserCredentialsStorageRepository> { UserCredentialsStorageRepositoryImpl(get()) }

    single { FirstRunSharedPreferencesStorage(androidContext()) }
    single<FirstRunStorageRepository> { FirstRunStorageRepositoryImpl(get()) }

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

    factory { GetAllPhotosUseCase(get()) }
    factory { GetProgressPhotosUseCase(get()) }
    factory { GetPhotoByIdUseCase(get()) }
    factory { UploadPhotoUseCase(get()) }
    factory { DeletePhotoByIdUseCase(get()) }

    factory { GetUserTrainingListUseCase(get()) }
    factory { SaveUserTrainingUseCase(get()) }

    factory { SaveTokenToLocalStorageUseCase(get()) }
    factory { GetTokenFromLocalStorageUseCase(get()) }
    factory { DeleteTokenFromLocalStorageUseCase(get()) }

    factory { SaveFirstRunInLocalStorageUseCase(get()) }
    factory { GetFirstRunFromLocalStorageUseCase(get()) }
    factory { DeleteFirstRunFromLocalStorageUseCase(get()) }

    factory { SaveUserEmailInLocalStorageUseCase(get()) }
    factory { GetUserEmailFromLocalStorageUseCase(get()) }
    factory { DeleteUserEmailFromLocalStorageUseCase(get()) }

    factory { GetAllFromDatabaseUseCase(get()) }
    factory { GetExerciseRepeatCountFromDatabaseUseCase(get()) }
    factory { InsertExerciseToDatabaseUseCase(get()) }
    factory { UpdateExerciseInDatabaseUseCase(get()) }
    factory { DeleteAllForUserFromDatabaseUseCase(get(), get()) }
    factory { DeleteAllFromDatabaseUseCase(get()) }

    factory { InitExerciseDatabaseForUserUseCase(get(), get()) }
    factory { IncreaseExerciseCountUseCase(get(), get(), get()) }
    factory { GetExerciseRepeatCountForUserFromDatabaseUseCase(get(), get()) }

    /* Sensors */
    single { AccelerometerSensor(get()) }
    single { StepSensor(get()) }

    /* ViewModels */
    viewModel {
        AuthorizationViewModel(get(), get())
    }

    viewModel {
        PINViewModel(get(), get(), get())
    }

    viewModel {
        RegistrationViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    viewModel {
        MainViewModel(get(), get(), get(), get())
    }

    viewModel {
        ExercisesViewModel()
    }

    viewModel {
        CrunchViewModel(get(), get(), get())
    }

    viewModel {
        SquatsViewModel(get(), get(), get(), get())
    }

    viewModel {
        PlankViewModel(get(), get(), get())
    }

    viewModel {
        PushUpsViewModel(get(), get(), get(), get())
    }

    viewModel {
        RunningViewModel(get(), get(), get(), get())
    }

    viewModel {
        MyBodyViewModel(get(), get(), get(), get())
    }

    viewModel {
        ImageListViewModel(get())
    }

    viewModel { (handle: SavedStateHandle) ->
        ImageViewModel(handle)
    }

}