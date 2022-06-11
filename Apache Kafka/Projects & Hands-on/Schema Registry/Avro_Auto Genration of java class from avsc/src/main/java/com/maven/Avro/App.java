package com.maven.Avro;


import java.io.File;

import org.apache.avro.Schema.Parser;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//defining a schema
        org.apache.avro.Schema.Parser parser=new Parser();
        org.apache.avro.Schema schema=parser.parse("{\r\n"
        		+ "     \"type\": \"record\",\r\n"
        		+ "     \"namespace\": \"com.example\",\r\n"
        		+ "     \"name\": \"Customer\",\r\n"
        		+ "     \"doc\": \"Avro Schema for our Customer\",     \r\n"
        		+ "     \"fields\": [\r\n"
        		+ "       { \"name\": \"first_name\", \"type\": \"string\", \"doc\": \"First Name of Customer\" },\r\n"
        		+ "       { \"name\": \"age\", \"type\": \"int\", \"doc\": \"Age at the time of registration\" },\r\n"
        		+ "       { \"name\": \"height\", \"type\": \"float\", \"default\":0.0, \"doc\": \"Height at the time of registration in cm\" }\r\n"
        		+ "     ]\r\n"
        		+ "}");
        
        //creating a generic record
        GenericRecordBuilder genericRecordBuilder=new GenericRecordBuilder(schema);
        genericRecordBuilder.set("first_name", "A");
        genericRecordBuilder.set("age", 25);
        //genericRecordBuilder.set("height", 19f); //not providing this to set default value given in schema
        GenericData.Record record= genericRecordBuilder.build(); //this build will fail if a value is not set or datatype is wrong
        System.out.println(record); //displaying above generated record
        
        //writing avro object to a file
        DatumWriter<GenericRecord> datumWriter=new GenericDatumWriter<>(schema);
        try(DataFileWriter<GenericRecord> dataFileWriter=new DataFileWriter<>(datumWriter)){
        	dataFileWriter.create(record.getSchema(), new File("generic.avro"));
        	dataFileWriter.append(record); //writing avro object
        	System.out.println("written");
        }
        catch (Exception e) {
			System.out.println("error");
		}
        
        //reading from avro obj file
        File file=new File("generic.avro");
        DatumReader<GenericRecord> datumReader=new GenericDatumReader<>();
        GenericRecord read;
        try(DataFileReader<GenericRecord> dataFileReader=new DataFileReader<>(file, datumReader)){
        	read=dataFileReader.next();
        	//dataFileReader.hasNext();
        	System.out.println("read ok");
        	System.out.println(read.toString());
        	//System.out.println(read.get("not_here")); //exception thrown for not existing key
        }
        catch (Exception e) {
        	System.out.println("fail");
		}
    }
}
