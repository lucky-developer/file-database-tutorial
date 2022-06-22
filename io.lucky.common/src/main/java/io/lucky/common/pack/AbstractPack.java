package io.lucky.common.pack;

import io.lucky.common.io.DataInputX;
import io.lucky.common.io.DataOutputX;

public abstract class AbstractPack implements IPack {
    protected long pcode;
    protected long time;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("time=").append(time);
        sb.append(", pcode=").append(pcode);
        return sb.toString();
    }

    abstract public PackType getPackType();

    public void write(DataOutputX dout){
        byte version = 0;
        dout.writeByte(version);
        dout.writeLong(this.pcode);
        dout.writeLong(this.time);
    }
    public void read(DataInputX din){
        byte version = din.readByte();
        if (version == 0) {
            this.pcode = din.readLong();
            this.time = din.readLong();
        }
    }
}
