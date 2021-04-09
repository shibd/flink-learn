// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/java/protobuf/account.proto

package com.iquantex.flink.account.protobuf;

/**
 * Protobuf type {@code org.apache.flink.statefun.examples.kafka.AccountResponse}
 */
public  final class AccountResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:org.apache.flink.statefun.examples.kafka.AccountResponse)
    AccountResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AccountResponse.newBuilder() to construct.
  private AccountResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AccountResponse() {
    retMsg_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AccountResponse(
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

            retMsg_ = s;
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
    return com.iquantex.flink.account.protobuf.Account.internal_static_org_apache_flink_statefun_examples_kafka_AccountResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iquantex.flink.account.protobuf.Account.internal_static_org_apache_flink_statefun_examples_kafka_AccountResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iquantex.flink.account.protobuf.AccountResponse.class, com.iquantex.flink.account.protobuf.AccountResponse.Builder.class);
  }

  public static final int RETMSG_FIELD_NUMBER = 1;
  private volatile java.lang.Object retMsg_;
  /**
   * <code>string retMsg = 1;</code>
   */
  public java.lang.String getRetMsg() {
    java.lang.Object ref = retMsg_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      retMsg_ = s;
      return s;
    }
  }
  /**
   * <code>string retMsg = 1;</code>
   */
  public com.google.protobuf.ByteString
      getRetMsgBytes() {
    java.lang.Object ref = retMsg_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      retMsg_ = b;
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
    if (!getRetMsgBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, retMsg_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getRetMsgBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, retMsg_);
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
    if (!(obj instanceof com.iquantex.flink.account.protobuf.AccountResponse)) {
      return super.equals(obj);
    }
    com.iquantex.flink.account.protobuf.AccountResponse other = (com.iquantex.flink.account.protobuf.AccountResponse) obj;

    boolean result = true;
    result = result && getRetMsg()
        .equals(other.getRetMsg());
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
    hash = (37 * hash) + RETMSG_FIELD_NUMBER;
    hash = (53 * hash) + getRetMsg().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iquantex.flink.account.protobuf.AccountResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iquantex.flink.account.protobuf.AccountResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iquantex.flink.account.protobuf.AccountResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iquantex.flink.account.protobuf.AccountResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iquantex.flink.account.protobuf.AccountResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iquantex.flink.account.protobuf.AccountResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iquantex.flink.account.protobuf.AccountResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iquantex.flink.account.protobuf.AccountResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iquantex.flink.account.protobuf.AccountResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iquantex.flink.account.protobuf.AccountResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iquantex.flink.account.protobuf.AccountResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iquantex.flink.account.protobuf.AccountResponse parseFrom(
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
  public static Builder newBuilder(com.iquantex.flink.account.protobuf.AccountResponse prototype) {
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
   * Protobuf type {@code org.apache.flink.statefun.examples.kafka.AccountResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:org.apache.flink.statefun.examples.kafka.AccountResponse)
      com.iquantex.flink.account.protobuf.AccountResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iquantex.flink.account.protobuf.Account.internal_static_org_apache_flink_statefun_examples_kafka_AccountResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iquantex.flink.account.protobuf.Account.internal_static_org_apache_flink_statefun_examples_kafka_AccountResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iquantex.flink.account.protobuf.AccountResponse.class, com.iquantex.flink.account.protobuf.AccountResponse.Builder.class);
    }

    // Construct using com.iquantex.flink.account.protobuf.AccountResponse.newBuilder()
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
      retMsg_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iquantex.flink.account.protobuf.Account.internal_static_org_apache_flink_statefun_examples_kafka_AccountResponse_descriptor;
    }

    public com.iquantex.flink.account.protobuf.AccountResponse getDefaultInstanceForType() {
      return com.iquantex.flink.account.protobuf.AccountResponse.getDefaultInstance();
    }

    public com.iquantex.flink.account.protobuf.AccountResponse build() {
      com.iquantex.flink.account.protobuf.AccountResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iquantex.flink.account.protobuf.AccountResponse buildPartial() {
      com.iquantex.flink.account.protobuf.AccountResponse result = new com.iquantex.flink.account.protobuf.AccountResponse(this);
      result.retMsg_ = retMsg_;
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
      if (other instanceof com.iquantex.flink.account.protobuf.AccountResponse) {
        return mergeFrom((com.iquantex.flink.account.protobuf.AccountResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iquantex.flink.account.protobuf.AccountResponse other) {
      if (other == com.iquantex.flink.account.protobuf.AccountResponse.getDefaultInstance()) return this;
      if (!other.getRetMsg().isEmpty()) {
        retMsg_ = other.retMsg_;
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
      com.iquantex.flink.account.protobuf.AccountResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iquantex.flink.account.protobuf.AccountResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object retMsg_ = "";
    /**
     * <code>string retMsg = 1;</code>
     */
    public java.lang.String getRetMsg() {
      java.lang.Object ref = retMsg_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        retMsg_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string retMsg = 1;</code>
     */
    public com.google.protobuf.ByteString
        getRetMsgBytes() {
      java.lang.Object ref = retMsg_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        retMsg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string retMsg = 1;</code>
     */
    public Builder setRetMsg(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      retMsg_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string retMsg = 1;</code>
     */
    public Builder clearRetMsg() {
      
      retMsg_ = getDefaultInstance().getRetMsg();
      onChanged();
      return this;
    }
    /**
     * <code>string retMsg = 1;</code>
     */
    public Builder setRetMsgBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      retMsg_ = value;
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


    // @@protoc_insertion_point(builder_scope:org.apache.flink.statefun.examples.kafka.AccountResponse)
  }

  // @@protoc_insertion_point(class_scope:org.apache.flink.statefun.examples.kafka.AccountResponse)
  private static final com.iquantex.flink.account.protobuf.AccountResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iquantex.flink.account.protobuf.AccountResponse();
  }

  public static com.iquantex.flink.account.protobuf.AccountResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AccountResponse>
      PARSER = new com.google.protobuf.AbstractParser<AccountResponse>() {
    public AccountResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AccountResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AccountResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AccountResponse> getParserForType() {
    return PARSER;
  }

  public com.iquantex.flink.account.protobuf.AccountResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

