package co.com.mercadolibre.core.network.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MeliBaseURL

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MeliProdBaseURL

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MeliBasicRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MeliProdRetrofit
