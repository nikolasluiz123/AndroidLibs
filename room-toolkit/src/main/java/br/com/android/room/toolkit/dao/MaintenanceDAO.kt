package br.com.android.room.toolkit.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Um DAO abstrato que fornece operações básicas de manutenção (inserção e atualização)
 * para uma entidade genérica [T].
 *
 * As operações de inserção utilizam `OnConflictStrategy.IGNORE` para evitar erros
 * ao tentar inserir registros com chaves primárias já existentes.
 *
 * @param T O tipo da entidade do Room com a qual este DAO opera.
 *
 * @author Nikolas Luiz Schmitt
 */
abstract class MaintenanceDAO<T: Any>: BaseDAO() {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(model: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertBatch(models: List<T>)

    @Update
    abstract suspend fun update(model: T)

    @Update
    abstract suspend fun updateBatch(models: List<T>)
}