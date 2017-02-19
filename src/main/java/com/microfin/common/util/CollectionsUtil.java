package com.microfin.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public final class CollectionsUtil {

    private CollectionsUtil() {
    }

    /**
     * 从集合中得到唯一符合p的一个对象
     *
     * @param c
     *            集合
     * @param p
     *            断言
     * @return
     */
    public static <T> T getUniqueResult(Collection<T> c, Predictor<T> p) {
        for (T t : c) {
            if (p.apply(t)) {
                return t;
            }
        }
        return null;
    }

    public static <T> List<T> filter(Collection<T> c, Predictor<T> p) {
        CopyOnWriteArrayList<T> tmp = new CopyOnWriteArrayList<T>(c);
        for (T t : tmp) {
            if (p.apply(t)) {
                tmp.remove(t);
            }
        }
        return tmp;
    }

    /**
     * 获取bean中的某些属性并用分隔符拼接成字符串
     *
     * @param c
     *            集合
     * @param split
     *            分隔符
     * @param func
     *            提取函数
     * @return
     */
    public static <T> String getStrByGivenSplit(Collection<T> c, String split, Function1A1R<T, String> func) {
        StringBuffer sb = new StringBuffer();
        for (T t : c) {
            sb.append(func.apply(t) + split);
        }

        return sb.length() > 0 ? sb.deleteCharAt(sb.length() - 1).toString() : "";
    }

    /**
     * 将list转化为map
     * 
     * @param list
     * @param func
     * @return
     */
    public static <K, V> Map<K, V> ListToMap(List<V> list, Function1A1R<V, K> func) {
        Map<K, V> ret = new HashMap<K, V>();
        for (V v : list) {
            ret.put(func.apply(v), v);
        }
        return ret;
    }
}
