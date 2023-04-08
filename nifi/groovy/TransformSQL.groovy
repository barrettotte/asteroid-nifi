// /opt/nifi/nifi-current/groovy/TransformSQL.groovy
//
// make adjustments to generated asteroid insert SQL

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

// Example:
//
// INSERT INTO asteroid (
//   diameter_max, diameter_min, distance, orbiting_body, relative_velocity, 
//   created, id, name, hazard, created_by, updated_by, updated
// ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

def outputSql = "${flowFileContent} ON CONFLICT DO NOTHING"
// could probably dynamically build an ON CONFLICT(ID) DO UPDATE ... but, too lazy for that right now

flowFile = session.write(flowFile, {inputStream, outputStream -> outputStream.write(outputSql.getBytes('UTF-8'))} as StreamCallback)
session.transfer(flowFile, REL_SUCCESS)
