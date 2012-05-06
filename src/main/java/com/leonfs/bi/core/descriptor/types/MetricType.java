package com.leonfs.bi.core.descriptor.types;

import com.leonfs.bi.core.exceptions.TypeNotSupportedException;

public enum MetricType {
	INTEGER(Integer.class),
	LONG(Long.class),
	DOUBLE(Double.class);

	private Class<? extends Number> clazz;
	
	private MetricType(Class<? extends Number> clazz) {
		this.clazz = clazz;
	}
	
	public static Class<? extends Number> GetMetricTypeClass(String name) {
		for (MetricType metricType : MetricType.values()) {
			if (metricType.name().equals(name)) {
				return metricType.clazz;
			}
		}
		throw new TypeNotSupportedException(name);
	}

	public Class<? extends Number> getType() {
		return this.clazz;
	}
}
