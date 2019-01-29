# AvroSpecificExample
Simple example of creating and using a Specific Avro object.

Example will create a specific record as an example using a rock climbing facility as the entity.

This is a Java/Maven project using JDK1.8.

**Simple steps for a simple example:**
- Maven build to turn an avro schema (.avsc file) into code (Avro Schema -> Maven build -> Generated Code)
- Use that generated code to create an Avro object 
- Write out a file using that schema (this will create a .avro file)
- Read the .avro file

To generate your code, use the Maven lifecycle (e.g. clean/package).
Once done, you will be able to see your generated source (.java) and target (.class) for ClimbingGym.

**Reading your Avro object using tools**
```commandline
$ wget http://central.maven.org/maven2/org/apache/avro/avro-tools/1.8.2/avro-tools-1.8.2.jar
$ java -jar avro-tools-1.8.2.jar tojson --pretty climbinggym.avro
```

**Other examples**
<br/>
Generic Record<br/>
https://github.com/scottsappen/AvroGenericExample<br/>
Reflected Record<br/>
https://github.com/scottsappen/AvroReflectedExample
