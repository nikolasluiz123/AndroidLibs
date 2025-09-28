package br.com.android.health.connect.toolkit.mapper.result

/**
 * Define um contrato para resultados de mapeamento que contêm uma série de dados (amostras).
 *
 * Estende [IRecordMapperResult] e é ideal para dados como frequência cardíaca ou estágios do sono,
 * onde um único registro do Health Connect contém múltiplas medições ao longo do tempo.
 *
 * @param SAMPLE O tipo da entidade que representa uma única amostra (ex: `HealthConnectHeartRateSample`).
 *
 * @property samples A lista de amostras de dados associadas ao registro principal.
 *
 * @author Nikolas Luiz Schmitt
 */
interface ISeriesRecordMapperResult<SAMPLE>: IRecordMapperResult {
    val samples: List<SAMPLE>
}