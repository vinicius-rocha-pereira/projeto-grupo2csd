/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  java.lang.Deprecated
 *  java.lang.Object
 *  org.apache.kafka.streams.processor.ProcessorContext
 */
package org.apache.kafka.streams.processor;

import org.apache.kafka.streams.processor.ProcessorContext;

@Deprecated
public interface Processor<K, V> {
    public void init(ProcessorContext var1);

    public void process(K var1, V var2);

    public void close();
}
