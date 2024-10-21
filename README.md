# MercadoLibre-Challenge [2024]
Esta es una prueba técnica desarrollada para **Mercado Libre** con el propósito de validar habilidades y conocimientos sobre la plataforma Android. Los datos fueron consumidos de la API **[developers-mercadolibre](https://developers.mercadolibre.com.ar/es_ar/items-y-busquedas)**.

> App construída sobre los principios **SOLID**, el concepto de **Clean Architecture** y la estructura **modular**. Arquitectura MVVM.

## Parámetros
* Android Studio Koala Feature Drop | **2024.1.2**
* Kotlin: 2.0.21
* AGP: 8.6.1

## Estructura del proyecto
```text
MercadolibreChallenge
  ├── :build-logic
  ├── :app
  ├── :core
  |      ├── :common
  |      ├── :data
  |      ├── :designsystem
  |      ├── :navigation
  |      └── :network
  └── :features
         ├── :details
         ├── :results
         └── :search
```

## Algunas de las librería usadas
```
Compose, Animation, Navigation, Coroutines, Flow, Material3,
SplashScreen, Hilt, Retrofit, Paging3, Coil
```

## Screenshots
| SplashScreen | MainScreen | SearchScreen | ResultsScreen | DetailsScreen |
|-|-|-|-|-|
|<img src="https://github.com/user-attachments/assets/eb3943d5-94d4-4170-8c97-ecfd8c9cffd7" width="174"/>|<img src="https://github.com/user-attachments/assets/30593486-aa6a-40b7-bd5d-14ca9859d2b2"  width="200"/>|<img src="https://github.com/user-attachments/assets/7d69d2b5-563d-45f5-9b51-a79304caeb11"  width="200"/>|<img src="https://github.com/user-attachments/assets/9adf7f00-76cb-4ef0-a405-338a4267d72d"  width="200"/>|<img src="https://github.com/user-attachments/assets/3b0e6ee9-c88b-4c57-a38b-f0a706daa554"  width="200"/>|
