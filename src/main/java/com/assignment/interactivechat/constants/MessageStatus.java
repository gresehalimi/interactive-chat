package com.assignment.interactivechat.constants;

public enum MessageStatus {
    SEEN(1), UNSEEN(0), UNKNOWN(-1);

    private MessageStatus(int dbValue) {
        this.dbValue = dbValue;
    }

    private final int dbValue;

    public int getDbValue() {
        return dbValue;
    }

    public static MessageStatus getDBStatusEnum(Integer dbValue) {

        switch (dbValue) {
            case 0:
                return UNSEEN;

            case 1:
                return SEEN;

            default:
                return UNKNOWN;
        }

    }

}
