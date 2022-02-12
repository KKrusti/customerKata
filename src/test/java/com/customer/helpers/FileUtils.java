package com.customer.helpers;

import io.micrometer.core.instrument.util.IOUtils;

import java.io.InputStream;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUtils {

	public static String getFileContents(String fileName) {
		ClassLoader classLoader = FileUtils.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		return IOUtils.toString(Objects.requireNonNull(inputStream), UTF_8).trim();
	}
}
