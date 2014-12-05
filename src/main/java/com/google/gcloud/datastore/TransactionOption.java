package com.google.gcloud.datastore;

import com.google.api.services.datastore.DatastoreV1;


public abstract class TransactionOption extends BatchWriteOption {

  private static final long serialVersionUID = -1862234444015690375L;

  public static final class IsolationLevel extends TransactionOption {

    private static final long serialVersionUID = -5592165378565409515L;

    private final Level level;

    public enum Level {

      SERIALIZABLE(DatastoreV1.BeginTransactionRequest.IsolationLevel.SERIALIZABLE),
      SNAPSHOT(DatastoreV1.BeginTransactionRequest.IsolationLevel.SNAPSHOT);

      private final DatastoreV1.BeginTransactionRequest.IsolationLevel levelPb;

      Level(DatastoreV1.BeginTransactionRequest.IsolationLevel levelPb) {
        this.levelPb = levelPb;
      }

      DatastoreV1.BeginTransactionRequest.IsolationLevel toPb() {
        return levelPb;
      }
    }

    public IsolationLevel(Level level) {
      this.level = level;
    }


    public Level level() {
      return level;
    }

    @Override
    void apply(BatchWriterImpl batchWriter) {
      batchWriter.apply(this);
    }
  }

  TransactionOption() {
    // package protected
  }

  public static IsolationLevel serializable() {
    return new IsolationLevel(IsolationLevel.Level.SERIALIZABLE);
  }

  public static IsolationLevel snapshot() {
    return new IsolationLevel(IsolationLevel.Level.SNAPSHOT);
  }
}