// /opt/nifi/nifi-current/groovy/TransformAsteroid.groovy
//
// inject additional fields to asteroid JSON

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import org.apache.nifi.processor.ProcessSession
import org.apache.nifi.processor.Relationship
import org.apache.nifi.processor.io.StreamCallback

def flowFile = session.get()
def flowFileContent = session.read(flowFile).getText('UTF-8')
def inputJson = new JsonSlurper().parseText(flowFileContent)

def nasaDtFormatter = DateTimeFormatter.ofPattern('yyyy-MMM-dd HH:mm') // 2015-Sep-07 07:32
def mainDtFormatter = DateTimeFormatter.ofPattern('yyyy-MM-dd HH:mm:ss.SSSSSS') // 2023-04-07 21:17:57
def now = LocalDateTime.now().format(mainDtFormatter)

// add new fields
inputJson.put('updated_by', 'nifi')
inputJson.put('updated', now)

// cleanup created date
if (inputJson.containsKey('created')) {
    def created = inputJson.get('created')
    try {
        LocalDateTime.parse(created, mainDtFormatter) // check if created is in expected datetime format
    } catch (DateTimeParseException e) {
        // assumed to be NASA datetime format
        inputJson.put('created', LocalDateTime.parse(created, nasaDtFormatter).format(mainDtFormatter))
    }
} else {
    inputJson.put('created', now) // add created when missing
}

// add missing fields
if (!inputJson.containsKey('created_by')) {
    inputJson.put('created_by', 'unknown')
}

// output new json as success
def outputJson = new JsonBuilder(inputJson).toPrettyString()
flowFile = session.write(flowFile, {inputStream, outputStream -> outputStream.write(outputJson.getBytes('UTF-8'))} as StreamCallback)
session.transfer(flowFile, REL_SUCCESS)
