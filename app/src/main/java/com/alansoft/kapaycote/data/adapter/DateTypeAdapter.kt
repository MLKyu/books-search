package com.alansoft.kapaycote.data.adapter

import com.alansoft.kapaycote.utils.DateTimeUtils
import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

/**
 * Created by LEE MIN KYU on 2021/04/27
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
class DateTypeAdapter : JsonSerializer<Date?>, JsonDeserializer<Date> {
    override fun serialize(
        src: Date?,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(DateTimeUtils.toDateString(src))
    }

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Date? {
        return DateTimeUtils.fromDateString(json.asString)
    }
}