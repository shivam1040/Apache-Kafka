/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.example;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** Avro Schema for our Customer */
@org.apache.avro.specific.AvroGenerated
public class CustomerV1 extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5500082496433349189L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CustomerV1\",\"namespace\":\"com.example\",\"doc\":\"Avro Schema for our Customer\",\"fields\":[{\"name\":\"first_name\",\"type\":\"string\",\"doc\":\"First Name of Customer\"},{\"name\":\"age\",\"type\":\"int\",\"doc\":\"Age at the time of registration\",\"default\":0},{\"name\":\"height\",\"type\":\"float\",\"doc\":\"Height at the time of registration in cm\",\"default\":0.0}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<CustomerV1> ENCODER =
      new BinaryMessageEncoder<CustomerV1>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<CustomerV1> DECODER =
      new BinaryMessageDecoder<CustomerV1>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<CustomerV1> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<CustomerV1> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<CustomerV1> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<CustomerV1>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this CustomerV1 to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a CustomerV1 from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a CustomerV1 instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static CustomerV1 fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** First Name of Customer */
  private java.lang.CharSequence first_name;
  /** Age at the time of registration */
  private int age;
  /** Height at the time of registration in cm */
  private float height;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CustomerV1() {}

  /**
   * All-args constructor.
   * @param first_name First Name of Customer
   * @param age Age at the time of registration
   * @param height Height at the time of registration in cm
   */
  public CustomerV1(java.lang.CharSequence first_name, java.lang.Integer age, java.lang.Float height) {
    this.first_name = first_name;
    this.age = age;
    this.height = height;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return first_name;
    case 1: return age;
    case 2: return height;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: first_name = (java.lang.CharSequence)value$; break;
    case 1: age = (java.lang.Integer)value$; break;
    case 2: height = (java.lang.Float)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'first_name' field.
   * @return First Name of Customer
   */
  public java.lang.CharSequence getFirstName() {
    return first_name;
  }


  /**
   * Sets the value of the 'first_name' field.
   * First Name of Customer
   * @param value the value to set.
   */
  public void setFirstName(java.lang.CharSequence value) {
    this.first_name = value;
  }

  /**
   * Gets the value of the 'age' field.
   * @return Age at the time of registration
   */
  public int getAge() {
    return age;
  }


  /**
   * Sets the value of the 'age' field.
   * Age at the time of registration
   * @param value the value to set.
   */
  public void setAge(int value) {
    this.age = value;
  }

  /**
   * Gets the value of the 'height' field.
   * @return Height at the time of registration in cm
   */
  public float getHeight() {
    return height;
  }


  /**
   * Sets the value of the 'height' field.
   * Height at the time of registration in cm
   * @param value the value to set.
   */
  public void setHeight(float value) {
    this.height = value;
  }

  /**
   * Creates a new CustomerV1 RecordBuilder.
   * @return A new CustomerV1 RecordBuilder
   */
  public static com.example.CustomerV1.Builder newBuilder() {
    return new com.example.CustomerV1.Builder();
  }

  /**
   * Creates a new CustomerV1 RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CustomerV1 RecordBuilder
   */
  public static com.example.CustomerV1.Builder newBuilder(com.example.CustomerV1.Builder other) {
    if (other == null) {
      return new com.example.CustomerV1.Builder();
    } else {
      return new com.example.CustomerV1.Builder(other);
    }
  }

  /**
   * Creates a new CustomerV1 RecordBuilder by copying an existing CustomerV1 instance.
   * @param other The existing instance to copy.
   * @return A new CustomerV1 RecordBuilder
   */
  public static com.example.CustomerV1.Builder newBuilder(com.example.CustomerV1 other) {
    if (other == null) {
      return new com.example.CustomerV1.Builder();
    } else {
      return new com.example.CustomerV1.Builder(other);
    }
  }

  /**
   * RecordBuilder for CustomerV1 instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CustomerV1>
    implements org.apache.avro.data.RecordBuilder<CustomerV1> {

    /** First Name of Customer */
    private java.lang.CharSequence first_name;
    /** Age at the time of registration */
    private int age;
    /** Height at the time of registration in cm */
    private float height;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.example.CustomerV1.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.first_name)) {
        this.first_name = data().deepCopy(fields()[0].schema(), other.first_name);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.age)) {
        this.age = data().deepCopy(fields()[1].schema(), other.age);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.height)) {
        this.height = data().deepCopy(fields()[2].schema(), other.height);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing CustomerV1 instance
     * @param other The existing instance to copy.
     */
    private Builder(com.example.CustomerV1 other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.first_name)) {
        this.first_name = data().deepCopy(fields()[0].schema(), other.first_name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.age)) {
        this.age = data().deepCopy(fields()[1].schema(), other.age);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.height)) {
        this.height = data().deepCopy(fields()[2].schema(), other.height);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'first_name' field.
      * First Name of Customer
      * @return The value.
      */
    public java.lang.CharSequence getFirstName() {
      return first_name;
    }


    /**
      * Sets the value of the 'first_name' field.
      * First Name of Customer
      * @param value The value of 'first_name'.
      * @return This builder.
      */
    public com.example.CustomerV1.Builder setFirstName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.first_name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'first_name' field has been set.
      * First Name of Customer
      * @return True if the 'first_name' field has been set, false otherwise.
      */
    public boolean hasFirstName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'first_name' field.
      * First Name of Customer
      * @return This builder.
      */
    public com.example.CustomerV1.Builder clearFirstName() {
      first_name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'age' field.
      * Age at the time of registration
      * @return The value.
      */
    public int getAge() {
      return age;
    }


    /**
      * Sets the value of the 'age' field.
      * Age at the time of registration
      * @param value The value of 'age'.
      * @return This builder.
      */
    public com.example.CustomerV1.Builder setAge(int value) {
      validate(fields()[1], value);
      this.age = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'age' field has been set.
      * Age at the time of registration
      * @return True if the 'age' field has been set, false otherwise.
      */
    public boolean hasAge() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'age' field.
      * Age at the time of registration
      * @return This builder.
      */
    public com.example.CustomerV1.Builder clearAge() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'height' field.
      * Height at the time of registration in cm
      * @return The value.
      */
    public float getHeight() {
      return height;
    }


    /**
      * Sets the value of the 'height' field.
      * Height at the time of registration in cm
      * @param value The value of 'height'.
      * @return This builder.
      */
    public com.example.CustomerV1.Builder setHeight(float value) {
      validate(fields()[2], value);
      this.height = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'height' field has been set.
      * Height at the time of registration in cm
      * @return True if the 'height' field has been set, false otherwise.
      */
    public boolean hasHeight() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'height' field.
      * Height at the time of registration in cm
      * @return This builder.
      */
    public com.example.CustomerV1.Builder clearHeight() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CustomerV1 build() {
      try {
        CustomerV1 record = new CustomerV1();
        record.first_name = fieldSetFlags()[0] ? this.first_name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.age = fieldSetFlags()[1] ? this.age : (java.lang.Integer) defaultValue(fields()[1]);
        record.height = fieldSetFlags()[2] ? this.height : (java.lang.Float) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<CustomerV1>
    WRITER$ = (org.apache.avro.io.DatumWriter<CustomerV1>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<CustomerV1>
    READER$ = (org.apache.avro.io.DatumReader<CustomerV1>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.first_name);

    out.writeInt(this.age);

    out.writeFloat(this.height);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.first_name = in.readString(this.first_name instanceof Utf8 ? (Utf8)this.first_name : null);

      this.age = in.readInt();

      this.height = in.readFloat();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.first_name = in.readString(this.first_name instanceof Utf8 ? (Utf8)this.first_name : null);
          break;

        case 1:
          this.age = in.readInt();
          break;

        case 2:
          this.height = in.readFloat();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










