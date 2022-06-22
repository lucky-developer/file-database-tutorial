package io.lucky.common.io;

import io.lucky.common.io.exception.DataIOException;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataOutputX {

    private int written;
    private DataOutput inner;
    private ByteArrayOutputStream bout;

    public DataOutputX(){
        this(new ByteArrayOutputStream());
    }

    public DataOutputX(ByteArrayOutputStream bout){
        this.bout = bout;
        this.inner = new DataOutputStream(bout);
    }

    public DataOutputX writeByte(int v){
        this.written++;
        try{
            this.inner.writeByte((byte) v);
        } catch (IOException e) {
            throw new DataIOException(e);
        }
        return this;
    }

    /**
     * {dataSize:1byte}
     * {data:${dataSize}}
     */
    public DataOutputX writeDecimal(long v){
        if (v == 0){
            return writeByte(0);
        } else if (Byte.MIN_VALUE <= v && v <= Byte.MAX_VALUE) {
            byte[] b = new byte[2];
            b[0] = 1;
            b[1] = (byte) v;
            write(b);
        } else if (Short.MIN_VALUE <= v && v <= Short.MAX_VALUE) {
            byte[] b = new byte[3];
            b[0] = 2;
            toBytes(b, 1, (short) v);
            write(b);
        } else if (Integer.MIN_VALUE <= v && v <= Integer.MAX_VALUE) {
            byte[] b = new byte[5];
            b[0] = 4;
            toBytes(b, 1, (int) v);
            write(b);
        } else if (Long.MIN_VALUE <= v && v <= Long.MAX_VALUE) {
            byte[] b = new byte[9];
            b[0] = 8;
            toBytes(b, 1, v);
            write(b);
        }
        return this;
    }

    public DataOutputX writeLong(long v) {
        this.written += 8;
        try{
            this.inner.write(toBytes(v));
        } catch (IOException e) {
            throw new DataIOException();
        }
        return this;
    }

    public DataOutputX write(byte[] b) {
        this.written += b.length;
        try{
            this.inner.write(b);
        } catch (IOException e) {
            throw new DataIOException(e);
        }
        return this;
    }

    /**
     * buf로 데이터 복사
     * Big Endian
     * 2bytes
     *
     */
    public static byte[] toBytes(byte[] buf, int off, short v) {
        buf[off] = (byte) ((v >>> 8) & 0xFF);
        buf[off+1] = (byte) ((v >>> 0) & 0xFF);
        return buf;
    }

    public static byte[] toBytes(byte[] buf, int off, int v) {
        buf[off] = (byte) ((v >>> 24) & 0xFF);
        buf[off + 1] = (byte) ((v >>> 16) & 0xFF);
        buf[off + 2] = (byte) ((v >>> 8) & 0xFF);
        buf[off + 3] = (byte) ((v >>> 0) & 0xFF);
        return buf;
    }

    public static byte[] toBytes(byte[] buf, int off, long v) {
        buf[off] = (byte) ((v >>> 56) & 0xFF);
        buf[off + 1] = (byte) ((v >>> 48) & 0xFF);
        buf[off + 2] = (byte) ((v >>> 40) & 0xFF);
        buf[off + 3] = (byte) ((v >>> 32) & 0xFF);
        buf[off + 4] = (byte) ((v >>> 24) & 0xFF);
        buf[off + 5] = (byte) ((v >>> 16) & 0xFF);
        buf[off + 6] = (byte) ((v >>> 8) & 0xFF);
        buf[off + 7] = (byte) ((v >>> 0) & 0xFF);
        return buf;
    }

    public static byte[] toBytes(long v) {
        byte buf[] = new byte[8];
        buf[0] = (byte) (v >>> 56);
        buf[1] = (byte) (v >>> 48);
        buf[2] = (byte) (v >>> 40);
        buf[3] = (byte) (v >>> 32);
        buf[4] = (byte) (v >>> 24);
        buf[5] = (byte) (v >>> 16);
        buf[6] = (byte) (v >>> 8);
        buf[7] = (byte) (v >>> 0);
        return buf;
    }

}
