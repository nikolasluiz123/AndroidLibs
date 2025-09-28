package br.com.android.firebase.toolkit.injection

import br.com.android.firebase.toolkit.authentication.FirebaseDefaultAuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingletonFirebaseModule {

    @Provides
    fun provideDefaultAuthenticationService(): FirebaseDefaultAuthenticationService {
        return FirebaseDefaultAuthenticationService()
    }
}