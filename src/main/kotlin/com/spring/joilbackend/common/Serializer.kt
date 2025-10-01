package com.spring.joilbackend.common

import arrow.core.Option
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule

class OptionSerializer : JsonSerializer<Option<*>>() {
    override fun serialize(value: Option<*>?, gen: JsonGenerator, serializers: SerializerProvider) {
        if (value == null) {
            gen.writeNull()
            return
        }

        value.fold(
            ifEmpty = { gen.writeNull() },
            ifSome = { gen.writeObject(it) }
        )
    }
}

class OptionModule : SimpleModule() {
    init {
        addSerializer(Option::class.java, OptionSerializer())
    }
}
