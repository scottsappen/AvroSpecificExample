package com.github.scotts.avro;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class SpecificRecordClimber {

    public static void main(String[] args) {

        /**
         * - Maven build to turn an avro schema (.avsc file) into code (Avro Schema -> Maven build -> Generated Code)
         * - Use that generated code to create an Avro object
         * - Write out a file using that schema (this will create a .avro file)
         * - Read the .avro file
         */

        //let's create a specific record from our generated code
        ClimbingGym.Builder climbingGymBuilder = ClimbingGym.newBuilder();
        climbingGymBuilder.setGymName("Inner Peaks");
        climbingGymBuilder.setGymNickname("New Gym or South End");
        climbingGymBuilder.setLocation("Charlotte, NC");
        climbingGymBuilder.setHastopropeclimbing(true);
        climbingGymBuilder.setHasleadclimbing(true);
        climbingGymBuilder.setHasbouldering(true);
        climbingGymBuilder.setHasspeedclimbing(true);
        ClimbingGym climbingGym = climbingGymBuilder.build();

        //let's create a file for that record
        final DatumWriter<ClimbingGym> datumWriter = new SpecificDatumWriter<>(ClimbingGym.class);

        try (DataFileWriter<ClimbingGym> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(climbingGym.getSchema(), new File("climbinggym.avro"));
            dataFileWriter.append(climbingGym);
            System.out.println("Created file climbinggym.avro");
        } catch (IOException e){
            e.printStackTrace();
        }

        //let's read back that newly specific avro file, create a specific record and thumb through some fields
        final File file = new File("climbinggym.avro");
        final DatumReader<ClimbingGym> datumReader = new SpecificDatumReader<>(ClimbingGym.class);
        final DataFileReader<ClimbingGym> dataFileReader;
        try {
            System.out.println("Reading the specific record");
            dataFileReader = new DataFileReader<>(file, datumReader);
            while (dataFileReader.hasNext()) {
                ClimbingGym readClimbingGym = dataFileReader.next();
                System.out.println(readClimbingGym.toString());
                System.out.println("Name of Climbing Gym: " + readClimbingGym.getGymName());
                System.out.println("Nick Name of Climbing Gym: " + readClimbingGym.getGymNickname());
                System.out.println("Location of the Climbing Gym: " + readClimbingGym.getLocation());
                System.out.println("Gym offers top rope climbing?: " + readClimbingGym.getHastopropeclimbing());
                System.out.println("Gym offers lead climbing: " + readClimbingGym.getHasleadclimbing());
                System.out.println("Gym offers bouldering: " + readClimbingGym.getHasbouldering());
                System.out.println("Gym offers speed climbing: " + readClimbingGym.getHasspeedclimbing());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
