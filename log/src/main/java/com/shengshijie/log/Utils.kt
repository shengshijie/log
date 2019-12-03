package com.shengshijie.log

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.StringReader
import java.io.StringWriter
import java.util.*
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

object Utils {

    fun toString(any: Any?): String {
        if (any == null) {
            return "null"
        }
        if (!any.javaClass.isArray) {
            return any.toString()
        }
        if (any is BooleanArray) {
            return Arrays.toString(any)
        }
        if (any is ByteArray) {
            return Arrays.toString(any)
        }
        if (any is CharArray) {
            return Arrays.toString(any as CharArray?)
        }
        if (any is ShortArray) {
            return Arrays.toString(any as ShortArray?)
        }
        if (any is IntArray) {
            return Arrays.toString(any as IntArray?)
        }
        if (any is LongArray) {
            return Arrays.toString(any as LongArray?)
        }
        if (any is FloatArray) {
            return Arrays.toString(any as FloatArray?)
        }
        if (any is DoubleArray) {
            return Arrays.toString(any as DoubleArray?)
        }
        return if (any is Array<*>) {
            Arrays.deepToString(any as Array<*>?)
        } else "Couldn't find a correct type for the object"
    }

    fun json(json: String?): String {
        if (json.isNullOrEmpty()) {
            return ("Empty/Null json content")
        }
        try {
            json.trim { it <= ' ' }
            if (json.startsWith("{")) {
                val jsonObject = JSONObject(json)
                return jsonObject.toString(2)
            }
            if (json.startsWith("[")) {
                val jsonArray = JSONArray(json)
                return jsonArray.toString(2)
            }
            return ("Invalid Json")
        } catch (e: JSONException) {
            return ("Invalid Json")
        }
    }

    fun xml(xml: String?): String {
        if (xml.isNullOrEmpty()) {
            return ("Empty/Null xml content")
        }
        return try {
            val xmlInput: Source = StreamSource(StringReader(xml))
            val xmlOutput = StreamResult(StringWriter())
            val transformer = TransformerFactory.newInstance().newTransformer()
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
            transformer.transform(xmlInput, xmlOutput)
            xmlOutput.writer.toString().replaceFirst(">".toRegex(), ">\n")
        } catch (e: TransformerException) {
            "Invalid xml"
        }
    }

}