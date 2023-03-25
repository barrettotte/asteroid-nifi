db = db.getSiblingDB('asteroid');

db.createCollection('asteroid');

// init table
// add deleted date for soft-delete
// delete record via NiFi after ingest of soft-delete?
