package com.examples.network.util

import com.google.gson.JsonParseException

import com.google.gson.JsonDeserializationContext

import com.google.gson.JsonElement

import com.google.gson.JsonDeserializer
import java.lang.reflect.Type


// if Api response has empty string, it is shown as Empty String error in Gson parsing.
// This class will replace empty string with null if the field is annotated with @EmptyStringAsNullTypeAdapter
class EmptyStringAsNullTypeAdapter<T>  // Let Gson instantiate it itself
private constructor() : JsonDeserializer<T> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        jsonElement: JsonElement,
        type: Type?,
        context: JsonDeserializationContext
    ): T? {
        if (jsonElement.isJsonPrimitive) {
            val jsonPrimitive = jsonElement.asJsonPrimitive
            if (jsonPrimitive.isString && jsonPrimitive.asString.isEmpty()) {
                return null
            }
        }
        return context.deserialize(jsonElement, type)
    }
}