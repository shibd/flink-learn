// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/java/protobuf/greeter.proto

package com.iquantex.flink.greet.generated;

/**
 * Protobuf type {@code org.apache.flink.statefun.examples.kafka.GreetResponse}
 */
public  final class GreetResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:org.apache.flink.statefun.examples.kafka.GreetResponse)
    GreetResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GreetResponse.newBuilder() to construct.
  private GreetResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GreetResponse() {
    who_ = "";
    greeting_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GreetResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            who_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            greeting_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.iquantex.flink.greet.generated.Greeter.internal_static_org_apache_flink_statefun_examples_kafka_GreetResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iquantex.flink.greet.generated.Greeter.internal_static_org_apache_flink_statefun_examples_kafka_GreetResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iquantex.flink.greet.generated.GreetResponse.class, com.iquantex.flink.greet.generated.GreetResponse.Builder.class);
  }

  public static final int WHO_FIELD_NUMBER = 1;
  private volatile java.lang.Object who_;
  /**
   * <code>string who = 1;</code>
   */
  public java.lang.String getWho() {
    java.lang.Object ref = who_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      who_ = s;
      return s;
    }
  }
  /**
   * <code>string who = 1;</code>
   */
  public com.google.protobuf.ByteString
      getWhoBytes() {
    java.lang.Object ref = who_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      who_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int GREETING_FIELD_NUMBER = 2;
  private volatile java.lang.Object greeting_;
  /**
   * <code>string greeting = 2;</code>
   */
  public java.lang.String getGreeting() {
    java.lang.Object ref = greeting_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      greeting_ = s;
      return s;
    }
  }
  /**
   * <code>string greeting = 2;</code>
   */
  public com.google.protobuf.ByteString
      getGreetingBytes() {
    java.lang.Object ref = greeting_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      greeting_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getWhoBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, who_);
    }
    if (!getGreetingBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, greeting_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getWhoBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, who_);
    }
    if (!getGreetingBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, greeting_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.iquantex.flink.greet.generated.GreetResponse)) {
      return super.equals(obj);
    }
    com.iquantex.flink.greet.generated.GreetResponse other = (com.iquantex.flink.greet.generated.GreetResponse) obj;

    boolean result = true;
    result = result && getWho()
        .equals(other.getWho());
    result = result && getGreeting()
        .equals(other.getGreeting());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + WHO_FIELD_NUMBER;
    hash = (53 * hash) + getWho().hashCode();
    hash = (37 * hash) + GREETING_FIELD_NUMBER;
    hash = (53 * hash) + getGreeting().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iquantex.flink.greet.generated.GreetResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iquantex.flink.greet.generated.GreetResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iquantex.flink.greet.generated.GreetResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iquantex.flink.greet.generated.GreetResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iquantex.flink.greet.generated.GreetResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iquantex.flink.greet.generated.GreetResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iquantex.flink.greet.generated.GreetResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iquantex.flink.greet.generated.GreetResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iquantex.flink.greet.generated.GreetResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iquantex.flink.greet.generated.GreetResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iquantex.flink.greet.generated.GreetResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iquantex.flink.greet.generated.GreetResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.iquantex.flink.greet.generated.GreetResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code org.apache.flink.statefun.examples.kafka.GreetResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:org.apache.flink.statefun.examples.kafka.GreetResponse)
      com.iquantex.flink.greet.generated.GreetResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iquantex.flink.greet.generated.Greeter.internal_static_org_apache_flink_statefun_examples_kafka_GreetResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iquantex.flink.greet.generated.Greeter.internal_static_org_apache_flink_statefun_examples_kafka_GreetResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iquantex.flink.greet.generated.GreetResponse.class, com.iquantex.flink.greet.generated.GreetResponse.Builder.class);
    }

    // Construct using com.iquantex.flink.greet.generated.GreetResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      who_ = "";

      greeting_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iquantex.flink.greet.generated.Greeter.internal_static_org_apache_flink_statefun_examples_kafka_GreetResponse_descriptor;
    }

    public com.iquantex.flink.greet.generated.GreetResponse getDefaultInstanceForType() {
      return com.iquantex.flink.greet.generated.GreetResponse.getDefaultInstance();
    }

    public com.iquantex.flink.greet.generated.GreetResponse build() {
      com.iquantex.flink.greet.generated.GreetResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iquantex.flink.greet.generated.GreetResponse buildPartial() {
      com.iquantex.flink.greet.generated.GreetResponse result = new com.iquantex.flink.greet.generated.GreetResponse(this);
      result.who_ = who_;
      result.greeting_ = greeting_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.iquantex.flink.greet.generated.GreetResponse) {
        return mergeFrom((com.iquantex.flink.greet.generated.GreetResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iquantex.flink.greet.generated.GreetResponse other) {
      if (other == com.iquantex.flink.greet.generated.GreetResponse.getDefaultInstance()) return this;
      if (!other.getWho().isEmpty()) {
        who_ = other.who_;
        onChanged();
      }
      if (!other.getGreeting().isEmpty()) {
        greeting_ = other.greeting_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.iquantex.flink.greet.generated.GreetResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iquantex.flink.greet.generated.GreetResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object who_ = "";
    /**
     * <code>string who = 1;</code>
     */
    public java.lang.String getWho() {
      java.lang.Object ref = who_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        who_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string who = 1;</code>
     */
    public com.google.protobuf.ByteString
        getWhoBytes() {
      java.lang.Object ref = who_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        who_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string who = 1;</code>
     */
    public Builder setWho(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      who_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string who = 1;</code>
     */
    public Builder clearWho() {
      
      who_ = getDefaultInstance().getWho();
      onChanged();
      return this;
    }
    /**
     * <code>string who = 1;</code>
     */
    public Builder setWhoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      who_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object greeting_ = "";
    /**
     * <code>string greeting = 2;</code>
     */
    public java.lang.String getGreeting() {
      java.lang.Object ref = greeting_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        greeting_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string greeting = 2;</code>
     */
    public com.google.protobuf.ByteString
        getGreetingBytes() {
      java.lang.Object ref = greeting_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        greeting_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string greeting = 2;</code>
     */
    public Builder setGreeting(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      greeting_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string greeting = 2;</code>
     */
    public Builder clearGreeting() {
      
      greeting_ = getDefaultInstance().getGreeting();
      onChanged();
      return this;
    }
    /**
     * <code>string greeting = 2;</code>
     */
    public Builder setGreetingBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      greeting_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:org.apache.flink.statefun.examples.kafka.GreetResponse)
  }

  // @@protoc_insertion_point(class_scope:org.apache.flink.statefun.examples.kafka.GreetResponse)
  private static final com.iquantex.flink.greet.generated.GreetResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iquantex.flink.greet.generated.GreetResponse();
  }

  public static com.iquantex.flink.greet.generated.GreetResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GreetResponse>
      PARSER = new com.google.protobuf.AbstractParser<GreetResponse>() {
    public GreetResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GreetResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GreetResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GreetResponse> getParserForType() {
    return PARSER;
  }

  public com.iquantex.flink.greet.generated.GreetResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

