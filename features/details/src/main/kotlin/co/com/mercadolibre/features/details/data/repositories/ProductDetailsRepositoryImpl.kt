package co.com.mercadolibre.features.details.data.repositories

import co.com.mercadolibre.core.common.domain.ErrorHandler
import co.com.mercadolibre.core.common.network.Dispatcher
import co.com.mercadolibre.core.common.network.MeliDispatchers.IO
import co.com.mercadolibre.core.common.result.Result
import co.com.mercadolibre.core.common.result.Result.Error
import co.com.mercadolibre.core.common.result.Result.Success
import co.com.mercadolibre.features.details.data.datasources.ProductDetailsRemoteDataSource
import co.com.mercadolibre.features.details.data.toDomain
import co.com.mercadolibre.features.details.domain.model.Product
import co.com.mercadolibre.features.details.domain.repositories.ProductDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

internal class ProductDetailsRepositoryImpl @Inject constructor(
  private val remoteDataSource: ProductDetailsRemoteDataSource,
  private val errorHandler: ErrorHandler,
  @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : ProductDetailsRepository {

  override suspend fun fetchProductDetails(
    productId: String,
  ): Result<Product> = withContext(ioDispatcher) {
    try {
      coroutineScope {
        val productDetailsDeferred = async { remoteDataSource.performSearch(productId).toDomain() }
        val productDescDeferred = async {
          try {
            remoteDataSource.performSearchDescription(productId).description
          } catch (e: Throwable) {
            // Si el error es un 404 asumo que el producto no tiene descripción y la devuelvo vacia
            if (e is HttpException && e.code() == 404) {
              ""
            } else {
              throw e
            }
          }
        }

        val productDetails = productDetailsDeferred.await()
        val productDescription = productDescDeferred.await()

        val product = productDetails.copy(description = productDescription)

        Success(product)
      }
    } catch (err: Throwable) {
      Error(errorHandler.getError(err))
    }
  }
}
