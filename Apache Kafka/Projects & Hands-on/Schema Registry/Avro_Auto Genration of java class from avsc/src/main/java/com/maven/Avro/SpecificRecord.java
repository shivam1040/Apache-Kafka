package com.maven.Avro;



import java.io.File;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.example.Customer;

public class SpecificRecord {
	public static void main(String[] args) {
		
		//creating specific record, do move generated class from target folder to this source folder
		Customer.Builder builder=Customer.newBuilder();
		builder.setAge(1);
		builder.setFirstName("aa");
		builder.setHeight(1f);
		Customer customer=builder.build();
		System.out.println(customer);
		
		//writing to file, avro obj
		DatumWriter<Customer> datumWriter=new SpecificDatumWriter<>(Customer.class);
		try(DataFileWriter<Customer> dataFileWriter=new DataFileWriter<>(datumWriter)){
			dataFileWriter.create(customer.getSchema(), new File("specificRecordavroObj.avro"));
			dataFileWriter.append(customer);
			System.out.println("done");
		}
		catch (Exception e) {
			System.out.println("fail");
		}
		
		//read avro obj from file
		File file=new File("specificRecordavroObj.avro");
		DatumReader<Customer> datumReader=new SpecificDatumReader<>(Customer.class);
		DataFileReader<Customer> dataFileReader;
		try{
			System.out.println("reading");
			dataFileReader=new DataFileReader<>(file, datumReader);
			while(dataFileReader.hasNext()) {
				Customer customer2=dataFileReader.next();
				System.out.println(customer2.toString());
				System.out.println(customer2.getAge()); //compare this method of fetching against generic record type, you'd see that there's no such method hence no type safety and runtime error
			}
		}
		catch(Exception e) {
			System.out.println("fail read");
		}
	}
}
