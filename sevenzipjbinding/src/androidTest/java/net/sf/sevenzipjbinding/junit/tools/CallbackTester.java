package net.sf.sevenzipjbinding.junit.tools;

import net.sf.sevenzipjbinding.IOutCreateCallback;
import net.sf.sevenzipjbinding.IOutItemBase;
import net.sf.sevenzipjbinding.ISequentialInStream;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.OutItemFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// TODO Test more callbacks (extraction + compression)
public class CallbackTester<E extends IOutItemBase> implements IOutCreateCallback<E> {
    private Set<String> methodNameSet = new HashSet<String>();
    private IOutCreateCallback<E> instance;

    @SuppressWarnings("unchecked")
    public CallbackTester(IOutCreateCallback instance) {
        this.instance = instance;
    }

    public int getDifferentMethodsCalled() {
        return methodNameSet.size();
    }

    public IOutCreateCallback<E> getProxyInstance() {
        return this;
    }

    public IOutCreateCallback<E> getOriginalCallback() {
        return instance;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> list = new ArrayList<String>(methodNameSet);
        Collections.sort(list);
        for (String methodName : list) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(", ");
            }

            stringBuilder.append(methodName);
            stringBuilder.append("()");
        }
        return "Called methods: " + stringBuilder;
    }

    public void setTotal(long total) throws SevenZipException {
        if (methodNameSet.add("setTotal")) {
            // System.out.println("setTotal");
        }
        instance.setTotal(total);
    }

    public void setCompleted(long complete) throws SevenZipException {
        if (methodNameSet.add("setCompleted")) {
            // System.out.println("setCompleted");
        }
        instance.setCompleted(complete);
    }

    public void setOperationResult(boolean operationResultOk) throws SevenZipException {
        if (methodNameSet.add("setOperationResult")) {
            // System.out.println("setOperationResult");
        }
        instance.setOperationResult(operationResultOk);
    }

    public E getItemInformation(int index, OutItemFactory<E> outItemFactory) throws SevenZipException {
        if (methodNameSet.add("getItemInformation")) {
            // System.out.println("getItemInformation");
        }
        return instance.getItemInformation(index, outItemFactory);
    }

    public ISequentialInStream getStream(int index) throws SevenZipException {
        if (methodNameSet.add("getStream")) {
            // System.out.println("getStream");
        }
        return instance.getStream(index);
    }
}
