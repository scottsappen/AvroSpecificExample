# AvroSpecificExample
Simple example of creating and using a Specific Avro object.

Example will create a specific record as an example using a rock climbing facility as the entity.

This is a Java/Maven project using JDK1.8.

**Simple steps for a simple example:**
- Write the schema in the code (not recommended, but easy to read)
- Create an object using that schema
- Write out a file using that schema (this will create a .avro file)
- Read the .avro file

To generate your code, use the Maven lifecycle (e.g. clean/package).
Once done, you will be able to see your generated source (.java) and target (.class) for ClimbingGym.
