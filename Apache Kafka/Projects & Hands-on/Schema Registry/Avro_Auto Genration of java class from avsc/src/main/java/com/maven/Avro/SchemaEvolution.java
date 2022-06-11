package com.maven.Avro;

import java.io.File;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.example.CustomerV1;
import com.example.CustomerV2;

public class SchemaEvolution {
	public static void main(String[] args) {
		CustomerV1.Builder builder=CustomerV1.newBuilder();
		builder.setAge(1);
		builder.setFirstName("AA!");
		builder.setHeight(1f);
		CustomerV1 customerV1=builder.build();
		System.out.println(customerV1);
		
		//writing to file, avro obj
				DatumWriter<CustomerV1> datumWriter=new SpecificDatumWriter<>(CustomerV1.class);
				try(DataFileWriter<CustomerV1> dataFileWriter=new DataFileWriter<>(datumWriter)){
					dataFileWriter.create(customerV1.getSchema(), new File("cusV1.avro"));
					dataFileWriter.append(customerV1);
					System.out.println("done");
				}
				catch (Exception e) {
					System.out.println("fail");
				}
				
				//read avro obj from file using v2 schema
				File file=new File("cusV1.avro");
				DatumReader<CustomerV2> datumReader=new SpecificDatumReader<>(CustomerV2.class);
				DataFileReader<CustomerV2> dataFileReader;
				try{
					System.out.println("reading");
					dataFileReader=new DataFileReader<>(file, datumReader);
					while(dataFileReader.hasNext()) {
						CustomerV2 customer2=dataFileReader.next();
						System.out.println(customer2.toString());
						System.out.println(customer2.getPhone()); //compare this method of fetching against generic record type, you'd see that there's no such method hence no type safety and runtime error
					}
				}
				catch(Exception e) {
					System.out.println("fail read");
				}
				
				//the same above process can be done for v2 building writing and reading using v1 schema
	}
}
