package io.lucky.common.io;

import io.lucky.common.io.exception.DataIOException;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;

public class DataInputX {

    private int offset;
    private DataInput inner;
    private ByteArrayInputStream bin;

    public DataInputX(byte[] buff) {
        this(new ByteArrayInputStream(buff));
    }

    public DataInputX(ByteArrayInputStream bin){
        this.bin = bin;
        this.inner = new DataInputStream(bin);
    }


    public byte readByte(){
        this.offset += 1;
        try{
            return this.inner.readByte();
        } catch (IOException e) {
            throw new DataIOException();
        }
    }

    public short readShort() {
        this.offset += 2;
        try {
            return this.inner.readShort();
        } catch (IOException e) {
            throw new DataIOException(e);
        }
    }

    public int readInt() {
        this.offset += 4;
        try {
            return this.inner.readInt();
        } catch (IOException e) {
            throw new DataIOException(e);
        }
    }

    public long readLong() {
        this.offset += 8;
        try {
            return this.inner.readLong();
        } catch (IOException e) {
            throw new DataIOException(e);
        }
    }

    public long readDecimal(){
        byte len = readByte();
        switch (len) {
            case 0:
                return 0;
            case 1 :
                return readByte();
            case 2:
                return readShort();
            case 4 :
                return readInt();
            case 8 :
                return readLong();
            default:
                return readLong();
        }
    }
}
