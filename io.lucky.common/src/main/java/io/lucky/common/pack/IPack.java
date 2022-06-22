package io.lucky.common.pack;

import io.lucky.common.io.DataInputX;
import io.lucky.common.io.DataOutputX;

public interface IPack {
    PackType getPackType();
    void write(DataOutputX dout);
    void read(DataInputX din);
}
