package com.microfin.common.util;

public interface Predictor<T> {

	public boolean apply(T arg);
}
