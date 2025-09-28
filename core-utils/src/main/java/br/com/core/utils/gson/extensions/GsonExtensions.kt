package br.com.core.utils.gson.extensions

import br.com.core.utils.gson.adapters.InstantTypeAdapter
import br.com.core.utils.gson.adapters.LocalDateTimeTypeAdapter
import br.com.core.utils.gson.adapters.LocalDateTypeAdapter
import br.com.core.utils.gson.adapters.LocalTimeTypeAdapter
import br.com.core.utils.gson.adapters.OffsetDateTimeTypeAdapter
import br.com.core.utils.gson.adapters.ZoneOffsetTypeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

fun GsonBuilder.defaultGSon(serializeNulls: Boolean = false): Gson {
    val builder = this.registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter())
        .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
        .registerTypeAdapter(LocalTime::class.java, LocalTimeTypeAdapter())
        .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeTypeAdapter())
        .registerTypeAdapter(Instant::class.java, InstantTypeAdapter())
        .registerTypeAdapter(ZoneOffset::class.java, ZoneOffsetTypeAdapter())

    if (serializeNulls) {
        builder.serializeNulls()
    }

    return builder.create()
}